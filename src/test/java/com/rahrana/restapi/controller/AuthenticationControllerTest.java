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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests
 */
@SpringBootTest @AutoConfigureMockMvc
class AuthenticationControllerTest
{
    @Autowired private MockMvc mockMvc;

    @Test public void authenticationTest() throws Exception
    {
        String body = getUsersBody();

        this.mockMvc.perform(post("/authenticate").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"jwt\":")));
    }

    private String getUsersBody()
    {
        return "{\n" + "\t\"username\": \"admin\",\n"
                + "\t\"password\": \"pass\"\n" + "}";
    }
}