package com.calculoplanillaservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Justificativo {
    private Long id;
    private LocalDate fecha;
    private Long id_personal;
}
