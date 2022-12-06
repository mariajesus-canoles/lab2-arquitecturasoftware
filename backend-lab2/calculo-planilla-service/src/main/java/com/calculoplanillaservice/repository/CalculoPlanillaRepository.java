package com.calculoplanillaservice.repository;

import com.calculoplanillaservice.entity.Planilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface CalculoPlanillaRepository extends JpaRepository<Planilla, Long> {

    @Modifying
    @Query(value = "INSERT INTO public.planilla (rut, nombre_empleado, categoria, anos_servicio_empresa, sueldo_fijo_mensual, bonificacion_horas_extras, bonificacion_tiempo_servicio, bonificacion_puntualidad, descuento_tardanza, descuento_retiro, sueldo_bruto, cotizacion_previsional, cotizacion_plan_salud, monto_suelto_final) " +
            "VALUES (:rut, :nombre_empleado, :categoria, :anos_servicio_empresa, :sueldo_fijo_mensual, :bonificacion_horas_extras, :bonificacion_tiempo_servicio, :bonificacion_puntualidad, :descuento_tardanza, :descuento_retiro, :sueldo_bruto, :cotizacion_previsional, :cotizacion_plan_salud, :monto_suelto_final)",
            nativeQuery = true)
    @Transactional
    void ingresarQuery(@Param("rut") String rut,
                       @Param("nombre_empleado") String nombre_empleado,
                       @Param("categoria") String categoria,
                       @Param("anos_servicio_empresa") Integer anos_servicio_empresa,
                       @Param("sueldo_fijo_mensual") Integer sueldo_fijo_mensual,
                        @Param("bonificacion_horas_extras") Integer bonificacion_horas_extras,
                        @Param("bonificacion_tiempo_servicio") Integer bonificacion_tiempo_servicio,
                        @Param("bonificacion_puntualidad") Integer bonificacion_puntualidad,
                        @Param("descuento_tardanza") Integer descuento_tardanza,
                        @Param("descuento_retiro") Integer descuento_retiro,
                        @Param("sueldo_bruto") Integer sueldo_bruto,
                        @Param("cotizacion_previsional") Integer cotizacion_previsional,
                        @Param("cotizacion_plan_salud") Integer cotizacion_plan_salud,
                        @Param("monto_suelto_final") Integer monto_suelto_final);

    @Modifying
    @Query(value = "UPDATE public.planilla SET bonificacion_horas_extras=:bonificacion_horas_extras, bonificacion_tiempo_servicio=:bonificacion_tiempo_servicio, bonificacion_puntualidad=:bonificacion_puntualidad, descuento_tardanza=:descuento_tardanza, descuento_retiro=:descuento_retiro, sueldo_bruto=:sueldo_bruto, cotizacion_previsional=:cotizacion_previsional, cotizacion_plan_salud=:cotizacion_plan_salud, monto_suelto_final=:monto_suelto_final WHERE planilla.rut=:rut",
            nativeQuery = true)
    @Transactional
    void actualizarQuery(@Param("rut") String rut,
                         @Param("bonificacion_horas_extras") Integer bonificacion_horas_extras,
                         @Param("bonificacion_tiempo_servicio") Integer bonificacion_tiempo_servicio,
                         @Param("bonificacion_puntualidad") Integer bonificacion_puntualidad,
                         @Param("descuento_tardanza") Integer descuento_tardanza,
                         @Param("descuento_retiro") Integer descuento_retiro,
                         @Param("sueldo_bruto") Integer sueldo_bruto,
                         @Param("cotizacion_previsional") Integer cotizacion_previsional,
                         @Param("cotizacion_plan_salud") Integer cotizacion_plan_salud,
                         @Param("monto_suelto_final") Integer monto_suelto_final);
}

