package com.calculoplanillaservice.controller;

import com.calculoplanillaservice.entity.Planilla;
import com.calculoplanillaservice.service.CalculoPlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/calculo-planilla")
public class CalculoPlanillaController {

    @Autowired
    CalculoPlanillaService calculoPlanillaService;

    @GetMapping
    public ResponseEntity<List<Planilla>> calcularPlanilla() {
        calculoPlanillaService.calcularPlanillaPersonales();
        List<Planilla> listaPlanilla = calculoPlanillaService.getAll();
        if(listaPlanilla.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaPlanilla);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Planilla>> getPlanilla() {
        List<Planilla> listaPlanilla = calculoPlanillaService.getAll();
        if(listaPlanilla.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaPlanilla);
    }
}
