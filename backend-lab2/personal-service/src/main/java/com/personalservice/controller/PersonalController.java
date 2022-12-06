package com.personalservice.controller;

import com.personalservice.entity.Personal;
import com.personalservice.model.Reloj;
import com.personalservice.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<Personal>> getAll() {
        List<Personal> personales = personalService.getAll();
        if(personales.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(personales);
    }

    @GetMapping("/fechaingreso/{idPersonal}")
    public ResponseEntity<LocalDate> getFechaIngreso(@PathVariable("idPersonal") Long idPersonal) {
        LocalDate fechaIngreso = personalService.obtenerFechaIngreso(idPersonal);
        return ResponseEntity.ok(fechaIngreso);
    }

    @GetMapping("/idpersonal/{rutPersonal}")
    public ResponseEntity<Long> getIdPersonalPorRut(@PathVariable("rutPersonal") String rutPersonal) {
        Long idPersonal = personalService.obtenerIdPersonalPorRut(rutPersonal);
        return ResponseEntity.ok(idPersonal);
    }






}
