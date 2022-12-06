package com.justificativoservice.controller;

import com.justificativoservice.entity.Justificativo;
import com.justificativoservice.model.JustificativoAux;
import com.justificativoservice.service.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/justificativo")
public class JustificativoController {
    @Autowired
    JustificativoService justificativoService;

    @GetMapping("/bypersonal/{idPersonal}")
    public ResponseEntity<ArrayList<Justificativo>> getJustificativosDePersonal(@PathVariable("idPersonal") Long idPersonal) {
        ArrayList<Justificativo> justificativosDePersonal = justificativoService.obtenerJustificativosDePersonal(idPersonal);
        return ResponseEntity.ok(justificativosDePersonal);
    }

    @GetMapping("/ingresarJustificativo")
    public String justificativo(Model model){
        return "justificativo";
    }


    @PostMapping()
    public ResponseEntity<JustificativoAux> save(@RequestBody JustificativoAux justificativoAux) {
        JustificativoAux justificativo = justificativoService.ingresarJustificativoEnBD(justificativoAux);
        return ResponseEntity.ok(justificativo);
    }
}
