/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.services.impl;

import com.embl.restapi.dto.Person;
import com.embl.restapi.dto.Persons;
import com.embl.restapi.repo.PersonRepository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PersonServiceImplTest
{
    @InjectMocks private PersonServiceImpl personService;
    @Mock private PersonRepository personRepository;
    private Persons persons;

    @BeforeEach void setUp()
    {
        initMocks(this);
        persons = createPersons();
        when(personRepository.findAll()).thenReturn(persons.getPerson());
    }

    @Test void addPersons()
    {
        personService.addPersons(persons);
        verify(personRepository, atLeastOnce()).save(any(Person.class));
    }

    @Test void getAllPersons()
    {
        Persons actualPersons = personService.getAllPersons();
        verify(personRepository, atMostOnce()).findAll();
        assertEquals(persons, actualPersons);
    }

    @Test void modifyPersons()
    {
        personService.modifyPersons(persons);
        verify(personRepository, atLeastOnce()).save(any(Person.class));
    }

    @Test void deletePersons()
    {
        personService.deletePersons(persons);
        verify(personRepository, atLeastOnce()).deleteById(anyInt());
    }

    @Test void deleteAllPersons()
    {
        personService.deleteAllPersons();
        verify(personRepository, atMostOnce()).deleteAll();
    }

    private Persons createPersons()
    {
        Person person = new Person(1, "firstname", "lastname", "age", "color",
                List.of("hobby"));
        return new Persons(List.of(person));
    }
}