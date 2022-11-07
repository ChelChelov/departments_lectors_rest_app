package com.olegsazonov.departments_lectors_rest_app.controller;

import com.olegsazonov.departments_lectors_rest_app.entity.Lector;
import com.olegsazonov.departments_lectors_rest_app.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LectorController {

//    @Autowired
//    LectorService lectorService;

    @Autowired
    LectorRepository lectorRepository;

    @GetMapping("/lectors")
    public ResponseEntity<List<Lector>> getAllLectors(@RequestParam(required = false) String title) {
        List<Lector> allLectors = new ArrayList<>();

        if (title == null)
            lectorRepository.findAll().forEach(allLectors::add);

        if (allLectors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allLectors, HttpStatus.OK);
    }

    @GetMapping("/lectors/{id}")
    public ResponseEntity<Lector> getLectorById(@PathVariable("id") Long id) {

        Lector lector = lectorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Lector with id = " + id));

        return new ResponseEntity<>(lector, HttpStatus.OK);
    }

    @PostMapping("/lectors")
    public ResponseEntity<Lector> addLector(@RequestBody Lector lector) {

        Lector lector1 = lectorRepository
                .save(new Lector(lector.getName(), lector.getSalary(), lector.getAcademicDegree()));

        return new ResponseEntity<>(lector1, HttpStatus.CREATED);
    }

    @PutMapping("/lectors/{id}")
    public ResponseEntity<Lector> updateLector(@PathVariable("id") long id, @RequestBody Lector lector) {

        Lector lector1 = lectorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Lector with id = " + id));

        lector1.setName(lector.getName());
        lector1.setSalary(lector.getSalary());
        lector1.setAcademicDegree(lector.getAcademicDegree());

        return new ResponseEntity<>(lectorRepository.save(lector1), HttpStatus.OK);
    }

    @DeleteMapping("/lectors/{id}")
    public ResponseEntity<HttpStatus> deleteLector(@PathVariable("id") Long id) {

        lectorRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/lectors")
    public ResponseEntity<HttpStatus> deleteAllLectors() {

        lectorRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    @GetMapping("/lectors/{id}")
//    public Lector getLector(@PathVariable Long id) {
//
//        Lector lector = lectorService.getLector(id);
//        return lector;
//    }
//
//    @PostMapping("/lectors")
//    public Lector postNewLector(@RequestBody Lector lector) {
//
//        lectorService.saveLector(lector);
//        return lector;
//    }
//
//    @PutMapping("/lectors")
//    public Lector updateLector(@RequestBody Lector lector) {
//
//        lectorService.saveLector(lector);
//        return lector;
//    }
//
//    @DeleteMapping("/lectors/{id}")
//    public String deleteLector(@PathVariable Long id) {
//
//        lectorService.deleteLector(id);
//        return "Lector with id: " + id + " was deleted.";
//    }
}
