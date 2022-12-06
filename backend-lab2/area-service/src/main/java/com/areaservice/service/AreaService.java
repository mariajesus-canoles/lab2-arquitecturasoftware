package com.areaservice.service;

import com.areaservice.entity.Area;
import com.areaservice.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

@Service
public class AreaService {
    @Autowired
    AreaRepository areaRepository;


    public List<Area> getAll() {
        return areaRepository.findAll();
    }
}
