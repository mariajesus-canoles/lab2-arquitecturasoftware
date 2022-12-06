package com.categoriaservice.controller;

import com.categoriaservice.entity.Categoria;
import com.categoriaservice.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable("id") Long id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        System.out.println("no nulo\n");
        if(categoria == null)
            return ResponseEntity.notFound().build();
            System.out.println("nulo\n");
        return ResponseEntity.ok(categoria);
    }
}