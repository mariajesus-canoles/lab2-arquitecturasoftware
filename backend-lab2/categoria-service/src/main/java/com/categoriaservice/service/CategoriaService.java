package com.categoriaservice.service;

import com.categoriaservice.entity.Categoria;
import com.categoriaservice.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
}
