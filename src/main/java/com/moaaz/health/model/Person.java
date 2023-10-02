package com.moaaz.health.model;


import com.moaaz.health.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id ;
    protected String name;

    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    protected Authentication authentication;

    protected String image;


    protected Role role;



}
