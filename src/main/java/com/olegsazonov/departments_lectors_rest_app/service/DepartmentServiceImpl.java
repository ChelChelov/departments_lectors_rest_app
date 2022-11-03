package com.olegsazonov.departments_lectors_rest_app.service;

import com.olegsazonov.departments_lectors_rest_app.entity.Department;
import com.olegsazonov.departments_lectors_rest_app.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public String saveDepartment(Department department) {
        departmentRepository.save(department);
        return "Department was saved with id: " + department.getId().toString();
    }

    @Override
    public Department getDepartment(Long id) {
        Department department = null;
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            department = optionalDepartment.get();
        }
        return department;
    }

    @Override
    public String deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
        return "Department with id: " + id + " was deleted.";
    }
}
