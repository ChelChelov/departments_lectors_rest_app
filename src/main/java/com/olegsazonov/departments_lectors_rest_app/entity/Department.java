package com.olegsazonov.departments_lectors_rest_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "head_of_department_id")
//    private Lector headOfDepartment;

    @ManyToMany(mappedBy = "departments"
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            , fetch = FetchType.LAZY)
    @JsonIgnore // is used to ignore the logical property used in serialization and deserialization.
    private Set<Lector> lectors = new HashSet<>();

    public Department(String name) {
        this.name = name;
    }
}


//    public void addLectorToDepartment(Lector lector) {
//        if (lectors == null) {
//            lectors = new ArrayList<>();
//        }
//        lectors.add(lector);
//    }