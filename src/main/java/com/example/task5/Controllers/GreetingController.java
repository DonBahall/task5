package com.example.task5.Controllers;

import com.example.task5.Models.ClientModel;
import com.example.task5.Models.TovarModel;
import com.example.task5.Repositories.ClientRepos;
import com.example.task5.Repositories.TovarRepos;
import com.example.task5.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GreetingController {

    final ClientRepos repos;
    final TovarRepos tovRepos;
    @Autowired
    ClientService service;

    public GreetingController(ClientRepos repos, TovarRepos tovRepos) {
        this.repos = repos;
        this.tovRepos = tovRepos;
    }

    @GetMapping(value = "/")
    public String welcome(Model model) {
        Iterable<ClientModel> models = repos.findAll();
        model.addAttribute("models", models);
        return "welcome";
    }

    @GetMapping(value = "/welcome/{id}")
    public String details(@PathVariable(value = "id") long id, Model model) {
        Object models = repos.findAllById(id).orElseThrow();
        model.addAttribute("models", models);
        return "details";
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable(value = "id") long id) {
        service.deleteClient(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @PostMapping(value = "/welcome/{id}")
    public String updateClient(@PathVariable(value = "id") long id, @RequestParam String newFirstname,
                               @RequestParam String newLastname, @RequestParam String newPassword) {
        service.updateClient(id, newFirstname, newLastname, newPassword);
        return "redirect:/";
    }

    @GetMapping("/newClient")
    public String addClient() {
        return "newClient";
    }

    @PostMapping("/newClient")
    public String saveClient(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
                             @RequestParam("password") String password) {
        service.saveClient(firstname, lastname, password);
        return "redirect:/";
    }

    @RequestMapping(value={"/search"}, method={RequestMethod.POST,RequestMethod.GET})
    public String getClients(@RequestParam(value = "page", defaultValue = "1") int pageNo,
                                  @RequestParam String firstnameSearch,
                                  @RequestParam String lastnameSearch,
                                Model model) {
        int pageSize = 2;
        Page<ClientModel> page = service.findPaginated(pageNo,pageSize,firstnameSearch,lastnameSearch);
        List<ClientModel> listEmployees = page.getContent();
        model.addAttribute("firstnameSearch",firstnameSearch);
        model.addAttribute("lastnameSearch",lastnameSearch);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("page", listEmployees);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        return "search";
    }

    @GetMapping("/tovar")
    public String getTovar(Model model) {
        Iterable<TovarModel> list = tovRepos.findAll();
        model.addAttribute("tovars", list);
        return "tovar";
    }
}
