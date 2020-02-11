/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi;

import com.rahrana.restapi.controller.AuthenticationController;
import com.rahrana.restapi.controller.PersonController;
import com.rahrana.restapi.dto.AuthenticationRequest;
import com.rahrana.restapi.dto.AuthenticationResponse;
import com.rahrana.restapi.dto.Person;
import com.rahrana.restapi.dto.Persons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@SpringBootApplication @Slf4j @EnableCaching
public class App
{

    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    @Bean public CommandLineRunner demo(PersonController personController,
            AuthenticationController authenticationController)
    {
        return (args) -> {
            // generate awt
            log.info("");
            log.info("Generate awt token using user credentials");
            ResponseEntity<AuthenticationResponse> responseEntity = authenticationController
                    .createAuthenticationToken(
                            new AuthenticationRequest("admin", "pass"));
            log.info("jwt token: " + Objects
                    .requireNonNull(responseEntity.getBody()).toString());
            log.info("--------------------------------");

            // save a few persons
            Person person1 = new Person("Chloe", "O'Brian", "56", "orange",
                    List.of("chess"));
            Person person2 = new Person("Sarah", "Green", "55", "yellow",
                    List.of("badminton"));
            Persons persons = new Persons(List.of(person1, person2));
            log.info("Save the persons");
            personController.addPerson(persons);
            log.info("--------------------------------");

            // fetch all customers
            log.info("Persons found with getAllPersons():");
            log.info(Objects.requireNonNull(
                    personController.getAllPersons().getBody()).toString());
            log.info("-------------------------------");
            log.info("");

            // update a person
            log.info("Update the person with first_name = Chloe");
            Person updatedPerson1 = new Person("Chloe", "O'Brian", "56", "red",
                    List.of("chess", "carrom"));
            personController.modifyPerson(new Persons(List.of(updatedPerson1)));
            log.info("--------------------------------");
            // fetch all customers
            log.info("Persons found with getAllPersons() after update:");
            log.info(personController.getAllPersons().getBody().toString());
            log.info("-------------------------------");
            log.info("");

            // delete person
            log.info("Delete person with first_name = Chloe:");
            personController.removePerson(new Persons(List.of(person1)));
            log.info("--------------------------------------------");
            // fetch all customers
            log.info("Persons found with getAllPersons after remove:");
            log.info(personController.getAllPersons().getBody().toString());
            log.info("-------------------------------");
            log.info("");

            //delete all persons
            log.info("Delete all persons:");
            personController.removeAllPerson();
            // fetch all customers
            log.info("Persons found with getAllPersons after delete all:");
            log.info(personController.getAllPersons().getBody().toString());
            log.info("-------------------------------");
            log.info("");
        };
    }
}
