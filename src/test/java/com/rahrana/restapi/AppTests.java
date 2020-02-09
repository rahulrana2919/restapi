/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi;

import com.rahrana.restapi.controller.AuthenticationController;
import com.rahrana.restapi.controller.PersonController;
import com.rahrana.restapi.controller.UsersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest @AutoConfigureMockMvc
class AppTests
{
    @Autowired private MockMvc mockMvc;

    @Autowired AuthenticationController authenticationController;
    @Autowired PersonController personController;
    @Autowired UsersController usersController;

    /**
     * Smoke test
     *
     * @throws Exception in case of error
     */
    @Test public void contextLoads() throws Exception
    {
        assertThat(authenticationController).isNotNull();
        assertThat(personController).isNotNull();
        assertThat(usersController).isNotNull();
    }
}
