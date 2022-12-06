package com.justificativoservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justificativoservice.entity.Justificativo;
import com.justificativoservice.model.JustificativoAux;
import com.justificativoservice.repository.JustificativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
@Service
public class JustificativoService {
    @Autowired
    JustificativoRepository justificativoRepository;

    @Autowired
    RestTemplate restTemplate;

    public ArrayList<Justificativo> obtenerJustificativosDePersonal(Long idPersonal){
        return justificativoRepository.buscarJustificativosDePersonal(idPersonal);
    }

    public JustificativoAux ingresarJustificativoEnBD(JustificativoAux justificativo){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha = LocalDate.parse(justificativo.getFecha(), df);
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Long idPersonal = mapper.convertValue(restTemplate.getForObject("http://personal-service/personal/idpersonal/"+justificativo.getRut(), Long.class), new TypeReference<Long>(){});
        justificativoRepository.ingresarQuery(fecha, idPersonal);
        return justificativo;
    }
}
