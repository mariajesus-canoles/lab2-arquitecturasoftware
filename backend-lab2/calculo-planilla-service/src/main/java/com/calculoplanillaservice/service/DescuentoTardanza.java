package com.calculoplanillaservice.service;

import com.calculoplanillaservice.model.Justificativo;
import com.calculoplanillaservice.model.Reloj;

import java.time.LocalDate;
import java.util.ArrayList;

public class DescuentoTardanza implements DescuentoStrategy {

    @Override
    public Integer descuento(Long idPersonal, Integer sueldoBruto, ArrayList<String> listaFechasDelMes, ArrayList<Reloj> relojesPersonal, ArrayList<Justificativo> justificativosPersonal){
        Double descuentoTardanza = 0.0;
        for (String fecha: listaFechasDelMes){
            //buscar reloj segun fecha
            Integer horaEntrada = 0;
            Integer minutoEntrada = 0;
            for (Reloj reloj: relojesPersonal){
                if (reloj.getFecha().getYear() == LocalDate.parse(fecha).getYear()
                        && reloj.getFecha().getMonth() == LocalDate.parse(fecha).getMonth()
                        && reloj.getFecha().getDayOfMonth() == LocalDate.parse(fecha).getDayOfMonth()){
                    horaEntrada = reloj.getHora_entrada().getHour();
                    minutoEntrada = reloj.getHora_entrada().getMinute();
                    break;
                }
            }
            if ((horaEntrada == 0 && minutoEntrada == 0) || horaEntrada > 9 || (horaEntrada == 9 && minutoEntrada > 10)){
                //buscar justificativo
                Boolean existeJustificativo = false;
                for (Justificativo justificativo: justificativosPersonal){
                    if (justificativo.getFecha() == LocalDate.parse(fecha)){
                        existeJustificativo = true;
                    }
                }
                //inasistencia
                if (existeJustificativo == false){
                    descuentoTardanza = descuentoTardanza + (sueldoBruto.doubleValue() * 0.15);
                }
            } else if (horaEntrada == 8 && minutoEntrada > 10 && minutoEntrada <= 25){
                descuentoTardanza = descuentoTardanza + (sueldoBruto.doubleValue() * 0.01);
            } else if (horaEntrada == 8 && minutoEntrada > 25 && minutoEntrada <= 45){
                descuentoTardanza = descuentoTardanza + (sueldoBruto.doubleValue() * 0.03);
            } else if (horaEntrada == 8 && minutoEntrada > 45 || horaEntrada == 9 && minutoEntrada >= 10){
                descuentoTardanza = descuentoTardanza + (sueldoBruto.doubleValue() * 0.06);
            }
        }
        return descuentoTardanza.intValue();
    }

}
