package com.relojservice.controller;

import com.relojservice.entity.Reloj;
import com.relojservice.service.RelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/reloj")
public class RelojController {
    @Autowired
    RelojService relojService;

    @GetMapping("/salida/{idPersonal}/{fecha}")
    public ResponseEntity<LocalTime> getByStudentId(@PathVariable("idPersonal") Long idPersonal, @PathVariable("fecha") String fecha) {
        LocalTime salida = relojService.buscarSalida(idPersonal, fecha);
        return ResponseEntity.ok(salida);
    }

    @GetMapping("/numdiasdelmes")
    public ResponseEntity<Integer> getNumDiasDelMes() {
        Integer numDiasDelMes = relojService.obtenerNumDiasDelMes();
        return ResponseEntity.ok(numDiasDelMes);
    }

    @GetMapping("/relojespersonal/{idPersonal}")
    public ResponseEntity<ArrayList<Reloj>> getRelojesDePersonal(@PathVariable("idPersonal") Long idPersonal) {
        ArrayList<Reloj> relojesPersonal = relojService.obtenerRelojesDePersonal(idPersonal);
        return ResponseEntity.ok(relojesPersonal);
    }

    @GetMapping("/fechasdelmes")
    public ResponseEntity<ArrayList<String>> getFechasDelMes() {
        ArrayList<String> fechasDelMes = relojService.obtenerFechasDelMes();
        return ResponseEntity.ok(fechasDelMes);
    }

    @PostMapping()
    public ResponseEntity<ArrayList<Reloj>> save(@RequestParam("archivo") MultipartFile archivo) {
        ArrayList<Reloj> relojes = relojService.cargarMarcaHoraria(archivo);
        return ResponseEntity.ok(relojes);
    }


}
