package com.olegsazonov.departments_lectors_rest_app.controller;

import com.olegsazonov.departments_lectors_rest_app.entity.Department;
import com.olegsazonov.departments_lectors_rest_app.entity.Lector;
import com.olegsazonov.departments_lectors_rest_app.repository.DepartmentRepository;
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
public class DepartmentController {

//    @Autowired
//    DepartmentService departmentService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    LectorRepository lectorRepository;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> allDepartments = new ArrayList<>();

        departmentRepository.findAll().forEach(allDepartments::add);

        if (allDepartments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    @GetMapping("/lectors/{lectorId}/departments")
    public ResponseEntity<List<Department>> getDepartmentsByLectorId(@PathVariable("lectorId") Long lectorId) {
        if (!lectorRepository.existsById(lectorId)) {
            throw new ResourceNotFoundException("Not found Lector with id = " + lectorId);
        }

        List<Department> departments = departmentRepository.findDepartmentsByLectorsId(lectorId);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Department with id = " + id));

        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/departments/{departmentId}/lectors")
    public ResponseEntity<List<Lector>> getLectorsByDepartmentId(@PathVariable(value = "departmentId") Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Not found Department  with id = " + departmentId);
        }

        List<Lector> lectors = lectorRepository.findLectorByDepartmentsId(departmentId);
        return new ResponseEntity<>(lectors, HttpStatus.OK);
    }

    @PostMapping("/lectors/{lectorId}/departments")
    public ResponseEntity<Department> addDepartment(@PathVariable(value = "lectorId") Long lectorId
            , @RequestBody Department departmentRequest) {

        Department department = lectorRepository.findById(lectorId).map(lector -> {
            long departmentId = departmentRequest.getId();
//use Optional
            if (departmentId != 0L) {
                Department department1 = departmentRepository.findById(departmentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Department with id = " + departmentId));
                lector.addDepartment(department1);
                lectorRepository.save(lector);
                return department1;
            }

            lector.addDepartment(departmentRequest);
            return departmentRepository.save(departmentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + lectorId));

        return new ResponseEntity<>(departmentRepository.save(department), HttpStatus.OK);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id
            , @RequestBody Department departmentRequest) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DepartmentId " + id + "not found"));

        department.setName(departmentRequest.getName());

        return new ResponseEntity<>(departmentRepository.save(department), HttpStatus.OK);
    }

    @DeleteMapping("/lectors/{lectorId}/departments/{departmentId}")
    public ResponseEntity<HttpStatus> deleteDepartmentFromLector(@PathVariable(value = "lectorId") Long lectorId
            , @PathVariable(value = "departmentId") Long departmentId) {
        Lector lector = lectorRepository.findById(lectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Lector with id = " + lectorId));

        lector.removeDepartment(departmentId);
        lectorRepository.save(lector);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") Long id) {
        departmentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/departments")
//    public List<Department> getAllDepartments() {
//
//        List<Department> allDepartments = departmentService.findAllDepartments();
//        return allDepartments;
//    }
//
//    @GetMapping("/departments/{id}")
//    public Department getDepartment(@PathVariable Long id) {
//
//        Department department = departmentService.getDepartment(id);
//        return department;
//    }
//
//    @PostMapping("/departments")
//    public Department postNewDepartment(@RequestBody Department department) {
//
//        departmentService.saveDepartment(department);
//        return department;
//    }
//
//    @PutMapping("/departments")
//    public Department updateLector(@RequestBody Department department) {
//
//        departmentService.saveDepartment(department);
//        return department;
//    }
//
//    @DeleteMapping("/departments/{id}")
//    public String deleteLector(@PathVariable Long id) {
//
//        departmentService.deleteDepartment(id);
//        return "Department with id: " + id + " was deleted.";
//    }
}
