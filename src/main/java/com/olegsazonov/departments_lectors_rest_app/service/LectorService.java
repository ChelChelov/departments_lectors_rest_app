package com.olegsazonov.departments_lectors_rest_app.service;

import com.olegsazonov.departments_lectors_rest_app.entity.Lector;

import java.util.List;

public interface LectorService {

    List<Lector> findAllLectors();

    String saveLector(Lector lector);

    Lector getLector(Long id);

    String deleteLector(Long id);

}
