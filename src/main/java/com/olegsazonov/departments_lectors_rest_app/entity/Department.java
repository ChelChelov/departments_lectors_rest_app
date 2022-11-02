package com.olegsazonov.departments_lectors_rest_app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lectors_id")
    private Lector headOfDepartment;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "lectors_departments"
            , joinColumns = @JoinColumn(name = "departments_id")
            , inverseJoinColumns = @JoinColumn(name = "lectors_id"))
    private List<Lector> lectors;

//    public void addLectorToDepartment(Lector lector) {
//        if (lectors == null) {
//            lectors = new ArrayList<>();
//        }
//        lectors.add(lector);
//    }
}
