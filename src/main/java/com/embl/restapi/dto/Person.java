package com.embl.restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "person")
@EqualsAndHashCode
@NoArgsConstructor
public class Person implements Serializable
{
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // to generate id automatically by JPA
    @EqualsAndHashCode.Exclude
    private int id;
    private String first_name;
    private String last_name;
    private String age;
    @EqualsAndHashCode.Exclude
    private String favorite_colour;
    @ElementCollection
    @EqualsAndHashCode.Exclude
    private List<String> hobby;
}

