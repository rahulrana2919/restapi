/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.controller;

import com.rahrana.restapi.dto.Persons;
import com.rahrana.restapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/person")
public class PersonController
{
    @Autowired private PersonService personService;

    @PostMapping public ResponseEntity<Persons> addPerson(
            @RequestBody Persons persons)
    {
        personService.addPersons(persons);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping public ResponseEntity<Persons> getAllPersons()
    {
        Persons allPerson = personService.getAllPersons();
        return new ResponseEntity<>(allPerson, HttpStatus.OK);
    }

    @PutMapping public ResponseEntity<Persons> modifyPerson(
            @RequestBody Persons persons)
    {
        personService.modifyPersons(persons);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping public ResponseEntity<Persons> removePerson(
            @RequestBody Persons persons)
    {
        personService.deletePersons(persons);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/all") public ResponseEntity<Persons> removeAllPerson()
    {
        personService.deleteAllPersons();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
