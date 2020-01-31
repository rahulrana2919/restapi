package com.embl.restapi.services;

import com.embl.restapi.dto.Persons;

public interface PersonService
{
    public void addPersons(Persons person);
    public Persons getAllPersons();
    public void modifyPersons(Persons person);
    public void deletePersons(Persons person);
    public void deleteAllPersons();

}
