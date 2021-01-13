package com.example.CarSell;

import com.example.CarSell.controller.LoginController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private LoginController controller;

    @Test
    public void testMainPage() throws Exception {
        this.mock.perform(get("/"))
                 .andDo(print())
                 .andExpect(status().isOk())
                 .andExpect(content().string(containsString("Welcome to our site")));
    }

    @Test
    public void testLogin() throws Exception {
        this.mock.perform(get("/login"))
                 .andDo(print())
                 .andExpect(status().isOk())
                 .andExpect(content().string(containsString("Sign In")));
    }

    @Test
    public void testCorrectLogin() throws Exception {
        this.mock.perform(formLogin().user("u").password("p"))
                 .andDo(print())
                 .andExpect(status().is3xxRedirection())
                 .andExpect(redirectedUrl("/main"));
    }

    @Test
    public void testAccessToPages() throws Exception {
        this.mock.perform(get("/add-new-post"))
                 .andDo(print())
                 .andExpect(status().is3xxRedirection())
                 .andExpect(redirectedUrl("http://localhost/login"));
    }

}
