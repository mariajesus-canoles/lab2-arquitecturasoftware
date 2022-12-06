package com.areaservice.controller;

import com.areaservice.entity.Area;
import com.areaservice.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/area")
public class AreaController {

    @Autowired
    AreaService areaService;

    @GetMapping
    public ResponseEntity<List<Area>> getAll() {
        List<Area> areas = areaService.getAll();
        if (areas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(areas);
    }
}
