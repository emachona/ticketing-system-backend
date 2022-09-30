/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.model.Client;
import com.example.model.Ticket;
import com.example.repository.ClientRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    ClientRepository repository;
    
    public Map<String, String> createClientProfile(String username, String password, String name){
        return repository.createClientProfile(username, password, name);
    }
    
    public Client login(String username, String password){
        return repository.login(username, password);
    }
    
    public List<Client> getAllClients(){
        return repository.getAllClients();
    }
    
    public List<Client> getAllTechnicians(){
        return repository.getAllTechnicians();
    }
    
    public Client getClientByName(String name){
        return repository.getClientByName(name);
    }
    
    public  List<Ticket> getAssignedTickets(int techId){
        return repository.getAssignedTickets(techId);
    }
    
    public String deleteProfile(String username, String password) throws SQLException{
         return repository.deleteProfile(username, password);
     }
}
