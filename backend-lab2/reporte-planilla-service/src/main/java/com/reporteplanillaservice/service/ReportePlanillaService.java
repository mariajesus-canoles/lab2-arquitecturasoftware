package com.reporteplanillaservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reporteplanillaservice.model.Planilla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportePlanillaService {
    @Autowired
    RestTemplate restTemplate;

    public List<Planilla> obtenerPlanilla(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        List<Planilla> listaPlanilla = mapper.convertValue(restTemplate.getForObject("http://calculo-planilla-service/calculo-planilla/get", List.class), new TypeReference<List<Planilla>>(){});
        return listaPlanilla;
    }
}
