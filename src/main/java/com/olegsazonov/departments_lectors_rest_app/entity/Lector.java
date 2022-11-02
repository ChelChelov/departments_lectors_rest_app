package com.olegsazonov.departments_lectors_rest_app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lectors")
@Data
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private int salary;

    @Column(name = "academic_degree")
    private String academicDegree;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "lectors_departments"
            , joinColumns = @JoinColumn(name = "lectors_id")
            , inverseJoinColumns = @JoinColumn(name = "departments_id"))
    private List<Department> departments;

//    public void addDepartmentToLector(Department department) {
//        if (departments == null) {
//            departments = new ArrayList<>();
//        }
//        departments.add(department);
//    }
}
