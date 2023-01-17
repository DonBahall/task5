package com.example.task5.Service;

import com.example.task5.Models.ClientModel;
import com.example.task5.Repositories.ClientRepos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    final ClientRepos repos;

    public ClientService(ClientRepos repos) {
        this.repos = repos;
    }

    public Page<ClientModel> findPaginated(int pageNo, int pageSize,String firstnameSearch,String lastnameSearch) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.repos.findByFirstnameAndLastname(firstnameSearch,lastnameSearch,pageable);
    }

    public void updateClient(long id, String newFirstname,String newLastname,String newPassword){
        ClientModel model = repos.findById(id).orElseThrow();
        model.setFirstname(newFirstname);
        model.setLastname(newLastname);
        model.setPassword(newPassword);
        repos.save(model);
    }
    public ClientModel saveClient(String firstname, String lastname, String password){
        ClientModel clientModel = new ClientModel(firstname, lastname, password);
        return repos.save(clientModel);
    }
    public String  deleteClient(long id){
        repos.deleteById(id);
        return "SUCCESS";
    }
}
