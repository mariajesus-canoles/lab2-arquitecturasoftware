package com.calculoplanillaservice.service;

import com.calculoplanillaservice.model.Justificativo;
import com.calculoplanillaservice.model.Reloj;

import java.util.ArrayList;

public interface DescuentoStrategy {
    public Integer descuento(Long idPersonal, Integer sueldoBruto, ArrayList<String> listaFechasDelMes, ArrayList<Reloj> relojesPersonal, ArrayList<Justificativo> justificativosPersonal);
}
