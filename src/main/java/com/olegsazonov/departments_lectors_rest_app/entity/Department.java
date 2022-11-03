package com.olegsazonov.departments_lectors_rest_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_of_department_id")
    private Lector headOfDepartment;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
    private Set<Lector> lectors = new HashSet<>();

//    public void addLectorToDepartment(Lector lector) {
//        if (lectors == null) {
//            lectors = new ArrayList<>();
//        }
//        lectors.add(lector);
//    }
}
