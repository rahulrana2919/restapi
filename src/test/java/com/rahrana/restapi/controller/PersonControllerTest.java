/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests
 */
@SpringBootTest @AutoConfigureMockMvc
class PersonControllerTest
{
    private static final String AUTHORIZATION = "Authorization";
    public static final String INVALID_JWT = "invalid";
    private String jwt;
    private String personsJson;

    @Autowired private MockMvc mockMvc;

    @BeforeEach void setUp() throws Exception
    {
        jwt = getJwtString();
        personsJson = getPersonsJson();
    }

    @Test void addPerson() throws Exception
    {
        this.mockMvc.perform(post("/person").content(personsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, jwt)).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test void getAllPersons() throws Exception
    {
        this.mockMvc.perform(get("/person").content("")
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, jwt)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test void modifyPerson() throws Exception
    {
        this.mockMvc.perform(put("/person").content(personsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, jwt)).andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test void modifyPerson_Exception() throws Exception
    {
        this.mockMvc.perform(put("/person").content(personsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, INVALID_JWT)).andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test void removePerson() throws Exception
    {
        this.mockMvc.perform(delete("/person").content(personsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, jwt)).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test void removeAllPerson() throws Exception
    {
        this.mockMvc.perform(delete("/person/all").content(personsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, jwt)).andDo(print())
                .andExpect(status().isNoContent());
    }

    private String getJwtString() throws Exception
    {
        String jwtBody = getUsersJson();
        MockHttpServletResponse jwt = this.mockMvc.perform(
                post("/authenticate").content(jwtBody)
                        .contentType(MediaType.APPLICATION_JSON)).andReturn()
                .getResponse();
        String jwtStr = "Bearer " + jwt.getContentAsString().split(":")[1];
        jwtStr = jwtStr.replaceAll("\"", "").replace("}", "");
        return jwtStr;
    }

    private String getPersonsJson()
    {
        return "{\n" + "    \"person\": [\n" + "        {\n"
                + "            \"first_name\": \"Sarah\",\n"
                + "            \"last_name\": \"Robinson\",\n"
                + "            \"age\": \"56\",\n"
                + "            \"favorite_colour\": \"orange\",\n"
                + "            \"hobby\": [\n" + "                \"chess\",\n"
                + "                \"sleep\"\n" + "            ]\n"
                + "        }\n" + "    ]\n" + "}";
    }

    private String getUsersJson()
    {
        return "{\n" + "\t\"username\": \"admin\",\n"
                + "\t\"password\": \"pass\"\n" + "}";
    }
}