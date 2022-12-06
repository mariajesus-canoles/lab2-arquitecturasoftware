package com.horaextraservice.controller;

import com.horaextraservice.entity.HoraExtra;
import com.horaextraservice.model.HoraExtraAux;
import com.horaextraservice.service.HoraExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/hora-extra")
public class HoraExtraController {

    @Autowired
    HoraExtraService horaExtraService;

    @GetMapping("/bypersonal/{idPersonal}")
    public ResponseEntity<List<HoraExtra>> getById_personal(@PathVariable("idPersonal") Long idPersonal) {
        List<HoraExtra> horasExtras = horaExtraService.obtenerHorasExtrasPorIdPersonal(idPersonal);
        return ResponseEntity.ok(horasExtras);
    }

    @PostMapping()
    public ResponseEntity<HoraExtraAux> save(@RequestBody HoraExtraAux horaExtraAux) {
        horaExtraService.ingresarHoraExtraEnBD(horaExtraAux);
        return ResponseEntity.ok(horaExtraAux);
    }
}
