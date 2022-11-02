package com.olegsazonov.departments_lectors_rest_app.repository;

import com.olegsazonov.departments_lectors_rest_app.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
