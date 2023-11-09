package com.lms.model;


import jakarta.persistence.*;
import lombok.*;

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
    protected long id;
    protected String name;

    private String address;

    private String phone;
    private String whatsappNumber;

    @OneToOne(cascade = CascadeType.ALL)
    protected Authentication authentication;

    protected String image;


    protected String role;


}
