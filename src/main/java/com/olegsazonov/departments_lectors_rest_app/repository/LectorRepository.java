package com.olegsazonov.departments_lectors_rest_app.repository;

import com.olegsazonov.departments_lectors_rest_app.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectorRepository extends JpaRepository<Lector, Long> {
}
