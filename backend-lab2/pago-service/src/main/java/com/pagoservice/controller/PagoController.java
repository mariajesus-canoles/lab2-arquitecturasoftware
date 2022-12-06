package com.pagoservice.controller;

import com.pagoservice.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    PagoService pagoService;

    @GetMapping("/sueldo/{personalIdCategoria}/{personalIdArea}")
    public ResponseEntity<Integer> getSueldoFijoMensual(@PathVariable("personalIdCategoria") Long personalIdCategoria, @PathVariable("personalIdArea") Long personalIdArea) {
        Integer sueldoFijo = pagoService.obtenerSueldoFijoMensual(personalIdCategoria, personalIdArea);
        if(sueldoFijo == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sueldoFijo);
    }

    @GetMapping("/horaextra/{personalIdCategoria}/{personalIdArea}")
    public ResponseEntity<Integer> getValorHoraExtra(@PathVariable("personalIdCategoria") Long personalIdCategoria, @PathVariable("personalIdArea") Long personalIdArea) {
        Integer sueldoFijo = pagoService.obtenerValorHoraExtra(personalIdCategoria, personalIdArea);
        if(sueldoFijo == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sueldoFijo);
    }
}
