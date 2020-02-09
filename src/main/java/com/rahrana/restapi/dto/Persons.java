/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Persons
{
    private List<Person> person;
}