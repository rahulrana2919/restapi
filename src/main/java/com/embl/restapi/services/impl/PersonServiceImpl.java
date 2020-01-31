package com.embl.restapi.services.impl;

import com.embl.restapi.dto.Person;
import com.embl.restapi.dto.Persons;
import com.embl.restapi.repo.PersonRepository;
import com.embl.restapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service public class PersonServiceImpl implements PersonService
{
    @Autowired PersonRepository personRepository;

    @Override public void addPersons(Persons persons)
    {
        for (Person aInPerson : persons.getPerson())
        {
            Optional<Person> lDBPerson = getStoredPerson(aInPerson);
            lDBPerson.map(value -> {
                aInPerson.setId(value.getId());
                return personRepository.save(aInPerson);
            }).orElse(personRepository.save(aInPerson));
        }
    }

    @Override public Persons getAllPersons()
    {
        List<Person> personList = personRepository.findAll();
        return new Persons(personList);
    }

    @Override public void modifyPersons(Persons persons)
    {
        for (Person person : persons.getPerson())
        {
            Optional<Person> lDBPerson = getStoredPerson(person);
            lDBPerson.map(value -> {
                person.setId(value.getId());
                return personRepository.save(person);
            });
        }
    }

    @Override public void deletePersons(Persons persons)
    {
        for (Person person : persons.getPerson())
        {
            Optional<Person> personObjInDB = getStoredPerson(person);
            personObjInDB.ifPresent(
                    value -> personRepository.deleteById(value.getId()));
        }
    }

    @Override public void deleteAllPersons()
    {
        personRepository.deleteAll();
    }

    /**
     * @param person person to search in DB
     * @return person object if it is found in DB
     */
    private Optional<Person> getStoredPerson(Person person)
    {
        return personRepository.findAll().stream()
                .filter(person1 -> person1.equals(person)).findFirst();
    }
}
