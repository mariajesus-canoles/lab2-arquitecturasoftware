package com.personalservice.service;

import com.personalservice.entity.Personal;
import com.personalservice.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

//import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

@Service
public class PersonalService {

    @Autowired
    PersonalRepository personalRepository;
    public List<Personal> getAll() {
        return personalRepository.findAll();
    }

    public LocalDate obtenerFechaIngreso(Long idPersonal){
        return personalRepository.buscarFechaIngreso(idPersonal);
    }

    public Long obtenerIdPersonalPorRut(String rut){
        return personalRepository.buscarIdPersonalPorRut(rut);
    }


}
