package com.embl.restapi.controller;

import com.embl.restapi.dto.AuthenticationRequest;
import com.embl.restapi.dto.AuthenticationResponse;
import com.embl.restapi.dto.Persons;
import com.embl.restapi.services.PersonService;
import com.embl.restapi.services.impl.MyUserDetailsService;
import com.embl.restapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
