package com.olegsazonov.departments_lectors_rest_app.service;

import com.olegsazonov.departments_lectors_rest_app.entity.Lector;
import com.olegsazonov.departments_lectors_rest_app.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectorServiceImpl implements LectorService {

    @Autowired
    LectorRepository lectorRepository;

    @Override
    public List<Lector> findAllLectors() {
        return lectorRepository.findAll();
    }

    @Override
    public String saveLector(Lector lector) {
        lectorRepository.save(lector);
        return "Lector was saved with id: " + lector.getId().toString();
    }

    @Override
    public Lector getLector(Long id) {
        Lector lector = null;
        Optional<Lector> optionalLector = lectorRepository.findById(id);
        if (optionalLector.isPresent()) {
            lector = optionalLector.get();
        }
        return lector;
    }

    @Override
    public String deleteLector(Long id) {
        lectorRepository.deleteById(id);
        return "Lector with id: " + id + " was deleted.";
    }
}
