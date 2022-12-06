package com.calculoplanillaservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraExtra {
    private Long id;
    private LocalDate fecha;
    private Integer num_horas;
    private Long id_personal;
}
