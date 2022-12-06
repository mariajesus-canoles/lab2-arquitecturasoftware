package com.horaextraservice.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.horaextraservice.entity.HoraExtra;
import com.horaextraservice.model.HoraExtraAux;
import com.horaextraservice.repository.HoraExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HoraExtraService {
    @Autowired
    HoraExtraRepository horaExtraRepository;

    @Autowired
    RestTemplate restTemplate;

    public ArrayList<HoraExtra> obtenerHorasExtrasPorIdPersonal(Long idPersonal){
        return horaExtraRepository.buscarHorasExtrasPorIdPersonal(idPersonal);
    }



    public HoraExtraAux ingresarHoraExtraEnBD(HoraExtraAux horaExtraAux){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha = LocalDate.parse(horaExtraAux.getFecha(), df);
        Integer numHoras = Integer.parseInt(horaExtraAux.getNum_horas());
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Long idPersonal = mapper.convertValue(restTemplate.getForObject("http://personal-service/personal/idpersonal/"+horaExtraAux.getRut(), Long.class), new TypeReference<Long>(){});
        horaExtraRepository.ingresarQuery(fecha, numHoras, idPersonal);
        return horaExtraAux;
    }

}
