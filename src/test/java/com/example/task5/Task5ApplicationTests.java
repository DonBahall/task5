package com.example.task5;

import com.example.task5.Controllers.GreetingController;
import com.example.task5.Models.ClientModel;
import com.example.task5.Repositories.ClientRepos;
import com.example.task5.Service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class Task5ApplicationTests {
    @Autowired
    private GreetingController controller;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientService service;
    @Autowired
    private ClientRepos repos;

    @Test
    void contextLoads() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void testSavingClient() throws Exception{
        repos.deleteAll();
        this.mockMvc.perform(post("/newClient").param("firstname","Arseniy")
                        .param("lastname","Strelkov").param("password","1234")).
                andExpect(status().is3xxRedirection());
        assertFalse(repos.findAll().isEmpty());
    }
    @Test
    public void testDeleteClients()throws Exception{
        long id = 1L;
        repos.deleteAll();
        ClientModel model = new ClientModel(id,"Artem","Petrushcow","19123");
        repos.save(model);
        Mockito.when(service.deleteClient(id)).thenReturn("SUCCESS");
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1", id))
                .andExpect(status().isOk());
    }
}
