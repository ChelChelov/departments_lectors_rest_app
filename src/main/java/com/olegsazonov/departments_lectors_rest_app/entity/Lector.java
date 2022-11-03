package com.olegsazonov.departments_lectors_rest_app.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lectors")
@Getter
@Setter
@ToString
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "academic_degree")
    private AcademicDegree academicDegree;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.REFRESH, CascadeType.DETACH}
            , fetch = FetchType.EAGER)
    @JoinTable(name = "lectors_departments"
            , joinColumns = @JoinColumn(name = "lector_id")
            , inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments = new HashSet<>();

//    public void addDepartmentToLector(Department department) {
//        if (departments == null) {
//            departments = new ArrayList<>();
//        }
//        departments.add(department);
//    }

    public enum AcademicDegree {
        CANDIDATE("Candidate of Sciences"), DOCTOR("Doctor of Sciences"), ASPIRANT("Aspirant"), PROFESSOR("Professor");

        private final String degree;

        private AcademicDegree(String degree) {
            this.degree = degree;
        }

        @Override
        public String toString() {
            return degree;
        }
    }
}
