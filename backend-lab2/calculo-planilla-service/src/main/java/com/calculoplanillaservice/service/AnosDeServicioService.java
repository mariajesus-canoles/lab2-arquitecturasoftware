package com.calculoplanillaservice.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AnosDeServicioService {

    public Integer calcularAnosServicios(LocalDate fechaIngresoAux){
        LocalDate fechaActual = LocalDate.now();
        return fechaActual.getYear() - fechaIngresoAux.getYear();
    }
}
