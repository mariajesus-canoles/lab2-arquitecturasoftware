package com.horaextraservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraExtraAux {
    private String fecha;
    private String num_horas;
    private String rut;
}
