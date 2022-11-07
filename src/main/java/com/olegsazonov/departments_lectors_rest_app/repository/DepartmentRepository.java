package com.olegsazonov.departments_lectors_rest_app.repository;

import com.olegsazonov.departments_lectors_rest_app.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findDepartmentsByLectorsId(Long lectorId);
}
