package com.tableinsql.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String subject;
    private String email;
    private String phonenumber;

    // Many staff -> one designation
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "designation_id")
    private Designation designation;

    // Many-to-many with Clazz
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "staff_clazz",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "clazz_id")
    )
    private Set<Clazz> classes = new HashSet<>();
}
