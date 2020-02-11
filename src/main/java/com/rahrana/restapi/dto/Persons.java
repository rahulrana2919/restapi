/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Persons
{
    @ApiModelProperty(notes = "The person's name")
    private List<Person> person;
}
