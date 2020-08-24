package com.socialeshop.identification;

import com.socialeshop.identification.controllers.SessionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.web.servlet.function.ServerResponse.status;

@WebMvcTest(SessionController.class)
public class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllApplicationsBeforeInsertAnyData() throws Exception {
        mockMvc.perform(get("/api/session")).andExpect(status().isOk());
    }


}
