package com.embl.restapi.services.impl;

import com.embl.restapi.dto.Person;
import com.embl.restapi.dto.Persons;
import com.embl.restapi.repo.PersonRepository;
import com.embl.restapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "personsCache")
public class PersonServiceImpl implements PersonService
{
    @Autowired PersonRepository personRepository;

    @Override @Transactional @CachePut(key = "#persons")
    public void addPersons(Persons persons)
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

    @Override @Cacheable(key = "#persons", condition="#persons!=null")
    public Persons getAllPersons()
    {
        List<Person> personList = personRepository.findAll();
        return new Persons(personList);
    }

    @Override @Transactional @CachePut(key = "#persons")
    public void modifyPersons(Persons persons)
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

    @Override @Transactional @CacheEvict(key = "#persons")
    public void deletePersons(Persons persons)
    {
        for (Person person : persons.getPerson())
        {
            Optional<Person> personObjInDB = getStoredPerson(person);
            personObjInDB.ifPresent(
                    value -> personRepository.deleteById(value.getId()));
        }
    }

    @Override @CacheEvict
    public void deleteAllPersons()
    {
        personRepository.deleteAll();
    }

    /**
     * @param person person to search in DB based on equals
     * @return person object if it is found in DB
     */
    private Optional<Person> getStoredPerson(Person person)
    {
        return personRepository.findAll().stream()
                .filter(person1 -> person1.equals(person)).findFirst();
    }
}
