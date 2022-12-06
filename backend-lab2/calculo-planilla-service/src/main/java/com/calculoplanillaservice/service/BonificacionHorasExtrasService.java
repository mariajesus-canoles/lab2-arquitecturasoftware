package com.calculoplanillaservice.service;

import com.calculoplanillaservice.model.HoraExtra;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class BonificacionHorasExtrasService {
    @Autowired
    RestTemplate restTemplate;

    public Integer calcularNumeroHorasExtras(Long idPersonal){
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        Integer horaSalidaEstablecida = 18;
        Integer numHorasTotales= 0;
        ArrayList<HoraExtra> listaHorasExtras = mapper.convertValue(restTemplate.getForObject("http://hora-extra-service/hora-extra/bypersonal/"+idPersonal, ArrayList.class), new TypeReference<ArrayList<HoraExtra>>(){});

        for (HoraExtra horaExtra: listaHorasExtras){
            LocalDate fecha = horaExtra.getFecha();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaString = fecha.format(formatters);
            Integer numHorasPermitidas = horaExtra.getNum_horas();
            LocalTime horaSalida = mapper.convertValue(restTemplate.getForObject("http://reloj-service/reloj/salida/"+idPersonal+"/"+fechaString, LocalTime.class), new TypeReference<LocalTime>(){});
            if (horaSalida != null){
                Integer numHoraSalida = horaSalida.getHour();
                if (numHoraSalida>=(horaSalidaEstablecida+numHorasPermitidas)){
                    numHorasTotales = numHorasTotales + numHorasPermitidas;
                }
            }
        }
        return numHorasTotales;
    }

    public Integer calcularBonificacionHorasExtras(Long idPersonal, Long idCategoria, Long idArea){
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Integer valorHoraExtra = mapper.convertValue(restTemplate.getForObject("http://pago-service/pago/horaextra/"+idCategoria+"/"+idArea, Integer.class), new TypeReference<Integer>(){});
        Integer numHorasTotales = calcularNumeroHorasExtras(idPersonal);
        Integer bonificacionHorasExtras = valorHoraExtra * numHorasTotales;
        return bonificacionHorasExtras;
    }
}
