package com.reporteplanillaservice.controller;

import com.reporteplanillaservice.model.Planilla;
import com.reporteplanillaservice.service.ReportePlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/reporte-planilla")
public class ReportePlanillaController {
    @Autowired
    ReportePlanillaService reportePlanillaService;

    @GetMapping
    public ResponseEntity<List<Planilla>> obtenerPlanilla() {
        List<Planilla> listaPlanilla = reportePlanillaService.obtenerPlanilla();
        if(listaPlanilla.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaPlanilla);
    }
}
