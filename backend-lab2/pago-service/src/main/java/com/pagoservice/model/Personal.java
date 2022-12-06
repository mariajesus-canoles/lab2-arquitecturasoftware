package com.pagoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personal {
    private String id;
    private String nombres;
    private String apellidos;
    private String rut;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_ingreso;
    private Long id_categoria;
    private Long id_area;
}
