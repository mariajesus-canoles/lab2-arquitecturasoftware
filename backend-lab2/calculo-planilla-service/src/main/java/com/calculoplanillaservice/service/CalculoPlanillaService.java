package com.calculoplanillaservice.service;

import com.calculoplanillaservice.entity.Planilla;
import com.calculoplanillaservice.model.*;
import com.calculoplanillaservice.repository.CalculoPlanillaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.length;

@Service
public class CalculoPlanillaService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CalculoPlanillaRepository calculoPlanillaRepository;

    @Autowired
    AnosDeServicioService anosDeServicioService;

    @Autowired
    BonificacionHorasExtrasService bonificacionHorasExtrasService;

    @Autowired
    BonificacionTiempoDeServicioService bonificacionTiempoDeServicioService;

    @Autowired
    BonificacionPuntualidadService bonificacionPuntualidadService;

    @Autowired
    CotizacionFacade cotizacionFacade;

    public List<Planilla> getAll() {
        return calculoPlanillaRepository.findAll();
    }

    public void guardarPlanillaEnBD(Planilla planilla){
        List<Planilla> listaPlanillas = this.getAll();
        Boolean aux = true;
        for (Planilla auxPlanilla: listaPlanillas){
            if (auxPlanilla.getRut().equals(planilla.getRut())){
                aux = false;
            }
        }
        if (aux == true){
            calculoPlanillaRepository.ingresarQuery(planilla.getRut(), planilla.getNombre_empleado(), planilla.getCategoria(), planilla.getAnos_servicio_empresa(), planilla.getSueldo_fijo_mensual(), planilla.getBonificacion_horas_extras(), planilla.getBonificacion_tiempo_servicio(), planilla.getBonificacion_puntualidad(), planilla.getDescuento_tardanza(), planilla.getDescuento_retiro(), planilla.getSueldo_bruto(), planilla.getCotizacion_previsional(), planilla.getCotizacion_plan_salud(), planilla.getMonto_suelto_final());
        }
        else {
            calculoPlanillaRepository.actualizarQuery(planilla.getRut(), planilla.getBonificacion_horas_extras(), planilla.getBonificacion_tiempo_servicio(), planilla.getBonificacion_puntualidad(), planilla.getDescuento_tardanza(), planilla.getDescuento_retiro(), planilla.getSueldo_bruto(), planilla.getCotizacion_previsional(), planilla.getCotizacion_plan_salud(), planilla.getMonto_suelto_final());
        }
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public ArrayList<Planilla> calcularPlanillaPersonales() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        List<Personal> listaPersonal = mapper.convertValue(restTemplate.getForObject("http://personal-service/personal", List.class), new TypeReference<List<Personal>>(){});
        ArrayList<Planilla> listaPlanilla = new ArrayList<Planilla>();

        for (Personal personal: listaPersonal) {
            Planilla planilla = new Planilla();
            //Rut
            planilla.setRut(personal.getRut());

            //Nombre empleado
            planilla.setNombre_empleado(personal.getNombres() + " " + personal.getApellidos());

            //Categoria
            Categoria categoria = mapper.convertValue(restTemplate.getForObject("http://categoria-service/categoria/"+personal.getId_categoria(), Categoria.class), new TypeReference<Categoria>(){});
            planilla.setCategoria(categoria.getNombre());

            //Años de servicio empresa
            Integer anosServicioEmpresa = anosDeServicioService.calcularAnosServicios(personal.getFecha_ingreso());
            planilla.setAnos_servicio_empresa(anosServicioEmpresa);

            //Sueldo fijo mensual
            Integer sueldoFijoMensual = mapper.convertValue(restTemplate.getForObject("http://pago-service/pago/sueldo/"+personal.getId_categoria()+"/"+personal.getId_area(), Integer.class), new TypeReference<Integer>(){});
            planilla.setSueldo_fijo_mensual(sueldoFijoMensual);

            //Bonificación horas extras
            Integer bonificacionHorasExtras = bonificacionHorasExtrasService.calcularBonificacionHorasExtras(personal.getId(), personal.getId_categoria(), personal.getId_area());
            planilla.setBonificacion_horas_extras(bonificacionHorasExtras);

            //Bonificación tiempo de servicio
            Integer bonificacionTiempoServicio = bonificacionTiempoDeServicioService.calcularBonificacionTiempoServicio(anosServicioEmpresa, sueldoFijoMensual);
            planilla.setBonificacion_tiempo_servicio(bonificacionTiempoServicio);

            //Bonificación por puntualidad
            Integer bonificacionPuntualidad = bonificacionPuntualidadService.calcularBonificacionPuntualidad(personal.getId(), sueldoFijoMensual);
            planilla.setBonificacion_puntualidad(bonificacionPuntualidad);

            //---Información---
            ArrayList<String> listaFechasDelMes = mapper.convertValue(restTemplate.getForObject("http://reloj-service/reloj/fechasdelmes", ArrayList.class), new TypeReference<ArrayList<String>>(){});
            ArrayList<Reloj> relojesPersonal = mapper.convertValue(restTemplate.getForObject("http://reloj-service/reloj/relojespersonal/"+personal.getId(), ArrayList.class), new TypeReference<ArrayList<Reloj>>(){});
            ArrayList<Justificativo> justificativosPersonal = mapper.convertValue(restTemplate.getForObject("http://justificativo-service/justificativo/bypersonal/"+personal.getId(), ArrayList.class), new TypeReference<ArrayList<Justificativo>>(){});

            //Descuentos por tardanzas en el ingreso
            DescuentoContext context = new DescuentoContext();
            context.setDescuentoMethod(new DescuentoTardanza());
            Integer descuentoTardanza = context.aplicarDescuento(personal.getId(), sueldoFijoMensual, listaFechasDelMes, relojesPersonal, justificativosPersonal);
            planilla.setDescuento_tardanza(descuentoTardanza);

            //Descuentos por retiros antes de la hora de salida
            DescuentoContext context2 = new DescuentoContext();
            context.setDescuentoMethod(new DescuentoRetiro());
            Integer descuentoRetiro = context.aplicarDescuento(personal.getId(), sueldoFijoMensual, listaFechasDelMes, relojesPersonal, justificativosPersonal);
            planilla.setDescuento_retiro(descuentoRetiro);

            //Sueldo bruto
            Integer sueldoBruto = sueldoFijoMensual + bonificacionHorasExtras + bonificacionTiempoServicio + bonificacionPuntualidad - (descuentoTardanza + descuentoRetiro);
            planilla.setSueldo_bruto(sueldoBruto);

            //Cotizaciones
            LocalDate fechaIngreso = mapper.convertValue(restTemplate.getForObject("http://personal-service/personal/fechaingreso/"+personal.getId(), LocalDate.class), new TypeReference<LocalDate>(){});
            ArrayList<Integer> cotizaciones = cotizacionFacade.calcularCotizaciones(fechaIngreso, sueldoBruto);
            //Cotización Previsional
            planilla.setCotizacion_previsional(cotizaciones.get(0));
            //Cotización salud
            planilla.setCotizacion_plan_salud(cotizaciones.get(1));

            //Monto sueldo final
            Integer sueldoFinal = sueldoBruto - (cotizaciones.get(0) + cotizaciones.get(1));
            planilla.setMonto_suelto_final(sueldoFinal);

            guardarPlanillaEnBD(planilla);
            System.out.println("hola\n");
            listaPlanilla.add(planilla);

        }
        return listaPlanilla;
    }


}
