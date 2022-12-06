package com.calculoplanillaservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reloj {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora_entrada;
    private LocalTime hora_salida;
    private Long id_personal;
}
