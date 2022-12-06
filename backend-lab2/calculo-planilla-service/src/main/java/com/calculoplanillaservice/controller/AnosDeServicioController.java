package com.calculoplanillaservice.controller;

import com.calculoplanillaservice.service.AnosDeServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/anos-servicio")
public class AnosDeServicioController {
    @Autowired
    AnosDeServicioService anosDeServicioService;

    @GetMapping("/{fechaIngreso}")
    public ResponseEntity<Integer> getAnosServicio(@PathVariable("fechaIngreso") LocalDate fechaIngreso) {
        Integer anosServicio = anosDeServicioService.calcularAnosServicios(fechaIngreso);
        if(anosServicio == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(anosServicio);
    }
}
