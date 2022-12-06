package com.calculoplanillaservice.service;

import com.calculoplanillaservice.model.Justificativo;
import com.calculoplanillaservice.model.Reloj;

import java.util.ArrayList;

public class DescuentoContext {

    private DescuentoStrategy strategy;

    //se setea el algoritmo
    public void setDescuentoMethod(DescuentoStrategy strategy){
        this.strategy = strategy;
    }

    public DescuentoStrategy getStrategy(){
        return this.strategy;
    }

    public Integer aplicarDescuento(Long idPersonal, Integer sueldoFijo, ArrayList<String> listaFechasDelMes, ArrayList<Reloj> relojesPersonal, ArrayList<Justificativo> justificativosPersonal){
        return this.strategy.descuento(idPersonal, sueldoFijo, listaFechasDelMes, relojesPersonal, justificativosPersonal);
    }
}
