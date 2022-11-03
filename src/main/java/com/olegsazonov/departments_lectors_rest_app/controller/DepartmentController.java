package com.olegsazonov.departments_lectors_rest_app.controller;

import com.olegsazonov.departments_lectors_rest_app.entity.Department;
import com.olegsazonov.departments_lectors_rest_app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {

        List<Department> allDepartments = departmentService.findAllDepartments();
        return allDepartments;
    }

    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable Long id) {

        Department department = departmentService.getDepartment(id);
        return department;
    }

    @PostMapping("/departments")
    public Department postNewDepartment(@RequestBody Department department) {

        departmentService.saveDepartment(department);
        return department;
    }

    @PutMapping("/departments")
    public Department updateLector(@RequestBody Department department) {

        departmentService.saveDepartment(department);
        return department;
    }

    @DeleteMapping("/departments/{id}")
    public String deleteLector(@PathVariable Long id) {

        departmentService.deleteDepartment(id);
        return "Department with id: " + id + " was deleted.";
    }
}
