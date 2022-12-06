package com.calculoplanillaservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rut;
    private String nombre_empleado;
    private String categoria;
    private Integer anos_servicio_empresa;
    private Integer sueldo_fijo_mensual;
    private Integer bonificacion_horas_extras;
    private Integer bonificacion_tiempo_servicio;
    private Integer bonificacion_puntualidad;
    private Integer descuento_tardanza;
    private Integer descuento_retiro;
    private Integer sueldo_bruto;
    private Integer cotizacion_previsional;
    private Integer cotizacion_plan_salud;
    private Integer monto_suelto_final;
}
