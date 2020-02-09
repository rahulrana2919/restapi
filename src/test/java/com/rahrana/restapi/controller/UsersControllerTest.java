/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests
 */
@SpringBootTest @AutoConfigureMockMvc
class UsersControllerTest
{
    @Autowired private MockMvc mockMvc;

    @Test void createUpdateUser() throws Exception
    {
        this.mockMvc.perform(post("/users").content(getUsersJson())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated());

        //created dummy user above and then updating it
        this.mockMvc.perform(put("/users").content(getUsersJson())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test void resetPassword_Forbidden() throws Exception
    {
        this.mockMvc.perform(put("/users").content(getUsersJson())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test void deleteUser() throws Exception
    {
        this.mockMvc.perform(delete("/users").content(getUsersJson())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test void getUsers() throws Exception
    {
        this.mockMvc.perform(get("/users")).andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                .string(stringContainsInOrder("username", "password",
                        "enabled")));
    }

    private String getUsersJson()
    {
        return "{\n" + "\t\"username\": \"abc\",\n"
                + "\t\"password\": \"def\"\n" + "}";
    }
}