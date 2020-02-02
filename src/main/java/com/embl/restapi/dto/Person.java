/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "person")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person
{
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private int id;
    private String first_name;
    private String last_name;
    private String age;
    @EqualsAndHashCode.Exclude
    private String favorite_colour;
    @ElementCollection (fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<String> hobby;

    public Person(String first_name, String last_name, String age,
            String favorite_colour, List<String> hobby)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.favorite_colour = favorite_colour;
        this.hobby = hobby;
    }
}

