package com.olegsazonov.departments_lectors_rest_app.service;

import com.olegsazonov.departments_lectors_rest_app.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAllDepartments();

    String saveDepartment(Department department);

    Department getDepartment(Long id);

    String deleteDepartment(Long id);
}
