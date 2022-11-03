package com.olegsazonov.departments_lectors_rest_app.controller;

import com.olegsazonov.departments_lectors_rest_app.entity.Lector;
import com.olegsazonov.departments_lectors_rest_app.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LectorController {

    @Autowired
    LectorService lectorService;

    @GetMapping("/lectors")
    public List<Lector> getAllLectors() {

        List<Lector> allLectors = lectorService.findAllLectors();
        return allLectors;
    }

    @GetMapping("/lectors/{id}")
    public Lector getLector(@PathVariable Long id) {

        Lector lector = lectorService.getLector(id);
        return lector;
    }

    @PostMapping("/lectors")
    public Lector postNewLector(@RequestBody Lector lector) {

        lectorService.saveLector(lector);
        return lector;
    }

    @PutMapping("/lectors")
    public Lector updateLector(@RequestBody Lector lector) {

        lectorService.saveLector(lector);
        return lector;
    }

    @DeleteMapping("/lectors/{id}")
    public String deleteLector(@PathVariable Long id) {

        lectorService.deleteLector(id);
        return "Lector with id: " + id + " was deleted.";
    }
}
