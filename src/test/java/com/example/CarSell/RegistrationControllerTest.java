package com.example.CarSell;

import com.example.CarSell.controller.RegistrationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private RegistrationController controller;

    @Test
    public void testRegistration() throws Exception {
        this.mock.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Register")));
    }

    @Test
    public void testCreateDuplicateUser() throws Exception {
        this.mock.perform(post("/registration").param("root", "1111"))
                .andDo(print())
                .andExpect(content().string(containsString("User exist")));
    }
}
