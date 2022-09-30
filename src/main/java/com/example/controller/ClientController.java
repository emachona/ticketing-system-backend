/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.model.Client;
import com.example.model.Ticket;
import com.example.service.ClientService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {
    
    @Autowired
    private ClientService service;
    
    @PostMapping("/client/createProfile")
    public Map<String, String> createClientProfile(@RequestBody Client client){
        return service.createClientProfile(client.getUsername(), client.getPassword(), client.getName());
    }
    
    @GetMapping("/login")
    public Client login(@RequestParam String username, @RequestParam String password){
        return service.login(username, password);
    }
    
    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return service.getAllClients();
    }
    
    @GetMapping("/technicians")
    public List<Client> getAllTechnicians(){
        return service.getAllTechnicians();
    }
    
    @GetMapping("/client/name")
    public Client getClientByName(@RequestParam String name){
        return service.getClientByName(name);
    }
    
    @GetMapping("/assignedTickets/{techId}")
    public List<Ticket> getAssignedTickets(@PathVariable("techId") int techId){
        return service.getAssignedTickets(techId);
    }
    
    @DeleteMapping("/profile/disable/{uname}/{pass}")
    public String deleteProfile(@PathVariable String uname, @PathVariable String pass) throws SQLException {
        return service.deleteProfile(uname,pass);
    }
}
