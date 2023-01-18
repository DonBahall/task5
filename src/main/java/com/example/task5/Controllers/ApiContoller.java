package com.example.task5.Controllers;

import com.example.task5.Models.ClientModel;
import com.example.task5.Repositories.ClientRepos;
import com.example.task5.Service.ClientService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiContoller {

    final ClientService service;
    final ClientRepos repos;

    public ApiContoller(ClientService service, ClientRepos repos) {
        this.service = service;
        this.repos = repos;
    }

    @GetMapping(value = "/get")
    public List<ClientModel> welcome() {
        return service.getAllClients();
    }
    @PostMapping(value = "/post")
    public ClientModel create(@RequestBody ClientModel clientModel) {
        return service.saveApi(clientModel);
    }
    @PutMapping(value = "/put/{id}")
    public ClientModel update(@PathVariable long id, @RequestBody ClientModel clientModel) {
        return service.updateApi(id,clientModel);
    }
    @DeleteMapping(value = "/deleting/{id}")
    public void deleteUser(@PathVariable long id) {
        service.deleteClient(id);
    }
}
