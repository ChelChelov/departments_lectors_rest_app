package com.olegsazonov.departments_lectors_rest_app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "academic_degree")
    private AcademicDegree academicDegree;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            , fetch = FetchType.LAZY)
    @JoinTable(name = "lectors_departments"
            , joinColumns = @JoinColumn(name = "lector_id")
            , inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments = new HashSet<>();

    public Lector(String name, BigDecimal salary, AcademicDegree academicDegree) {
        this.name = name;
        this.salary = salary;
        this.academicDegree = academicDegree;
    }

    public void addDepartment(Department department) {
        this.departments.add(department);
        department.getLectors().add(this);
    }

    public void removeDepartment(long departmentId) {
        Department department = this.departments.stream()
                .filter(t -> t.getId() == departmentId).findFirst().orElse(null);
        if (department != null) {
            this.departments.remove(department);
            department.getLectors().remove(this);
        }
    }

    public enum AcademicDegree {
        CANDIDATE("Candidate of Sciences"), DOCTOR("Doctor of Sciences"), ASPIRANT("Aspirant"), PROFESSOR("Professor");

        private final String degree;

        AcademicDegree(String degree) {
            this.degree = degree;
        }

        @Override
        public String toString() {
            return degree;
        }
    }
}
//    public void addDepartmentToLector(Department department) {
//        if (departments == null) {
//            departments = new ArrayList<>();
//        }
//        departments.add(department);
//    }