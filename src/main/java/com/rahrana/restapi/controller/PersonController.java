/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.controller;

import com.rahrana.restapi.dto.Persons;
import com.rahrana.restapi.services.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/person")
public class PersonController
{
    @Autowired private PersonService personService;

    @PostMapping
    @ApiOperation(value = "Create all persons",
            notes = "Create all the persons provided in request",
            response = Persons.class)
    public ResponseEntity<Persons> addPerson(
            @ApiParam(value = "Body of Persons entity to be created", required = true) @RequestBody Persons persons)
    {
        personService.addPersons(persons);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Find all persons",
            notes = "Provide all the persons created in the system",
            response = Persons.class)
    public ResponseEntity<Persons> getAllPersons()
    {
        Persons allPerson = personService.getAllPersons();
        return new ResponseEntity<>(allPerson, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Modify all persons",
            response = Persons.class)
    public ResponseEntity<Persons> modifyPerson(
            @ApiParam(value = "Body of Persons entity to be modified", required = true) @RequestBody Persons persons)
    {
        personService.modifyPersons(persons);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @ApiOperation(value = "Remove the persons provided in request",
            response = Persons.class)
    public ResponseEntity<Persons> removePerson(
            @ApiParam(value = "Body of Persons entity to be removed", required = true) @RequestBody Persons persons)
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
