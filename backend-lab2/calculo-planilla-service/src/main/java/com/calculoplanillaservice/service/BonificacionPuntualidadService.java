package com.calculoplanillaservice.service;

import com.calculoplanillaservice.model.Categoria;
import com.calculoplanillaservice.model.Reloj;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class BonificacionPuntualidadService {

    @Autowired
    RestTemplate restTemplate;


    public ArrayList<Integer> calcularPorcentajePuntualidad(Long idPersonal){
        Integer horaEntrada = 8;
        Integer horaSalida = 18;
        Integer numDiasEntradaPuntual = 0;
        Integer numDiasSalidaPuntual = 0;
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Integer numDiasDelMes = mapper.convertValue(restTemplate.getForObject("http://reloj-service/reloj/numdiasdelmes", Integer.class), new TypeReference<Integer>(){});
        ArrayList<Reloj> listaRelojes = mapper.convertValue(restTemplate.getForObject("http://reloj-service/reloj/relojespersonal/"+idPersonal, ArrayList.class), new TypeReference<ArrayList<Reloj>>(){});
        for (Reloj reloj: listaRelojes){
            if (reloj.getHora_entrada().getHour()<horaEntrada ||
                    reloj.getHora_entrada().getHour()==horaEntrada && reloj.getHora_entrada().getMinute()==0){
                numDiasEntradaPuntual++;
            }
            if (reloj.getHora_salida().getHour()>=horaSalida){
                numDiasSalidaPuntual++;
            }
        }
        Double porcentajeDiasEntradaPuntual = numDiasEntradaPuntual.doubleValue() * 100.0 / numDiasDelMes.doubleValue();
        Double porcentajeDiasSalidaPuntual = numDiasSalidaPuntual.doubleValue() * 100.0 / numDiasDelMes.doubleValue();
        ArrayList<Integer> porcentajesPuntualidad = new ArrayList<Integer>();
        porcentajesPuntualidad.add(porcentajeDiasEntradaPuntual.intValue());
        porcentajesPuntualidad.add(porcentajeDiasSalidaPuntual.intValue());
        return porcentajesPuntualidad;
    }

    public Integer calcularBonificacionPuntualidad(Long idPersonal, Integer sueldoFijoMensual){
        Double bonificacionPuntualidad = 0.0;
        ArrayList<Integer> porcentajesPuntualidad = this.calcularPorcentajePuntualidad(idPersonal);
        if (porcentajesPuntualidad.get(0)>80.0 && porcentajesPuntualidad.get(1)>80.0 &&
                porcentajesPuntualidad.get(0)<=90.0 && porcentajesPuntualidad.get(1)<=90.0){
            bonificacionPuntualidad = sueldoFijoMensual * 0.05;
        }
        if (porcentajesPuntualidad.get(0)>90.0 && porcentajesPuntualidad.get(1)>90.0){
            bonificacionPuntualidad = sueldoFijoMensual * 0.08;
        }
        return bonificacionPuntualidad.intValue();
    }
}
