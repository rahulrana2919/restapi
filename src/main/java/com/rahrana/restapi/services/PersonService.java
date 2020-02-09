/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.services;

import com.rahrana.restapi.dto.Persons;

public interface PersonService
{
    public void addPersons(Persons person);
    public Persons getAllPersons();
    public void modifyPersons(Persons person);
    public void deletePersons(Persons person);
    public void deleteAllPersons();

}
