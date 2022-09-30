/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.model.Ticket;
import com.example.repository.TicketRepository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TicketService {
    @Autowired
    private TicketRepository repository;
    
    public List<Ticket> getAllTickets(){
        return repository.getAllTickets();
    }
    
    public Ticket getTicketById(int id) throws SQLException{
        return repository.getTicketById(id);
    }
    
    public List<Ticket> getTicketsByState(String state) throws SQLException{
        return repository.getTicketsByState(state);
    }
    
    public List<Ticket> getTicketsByUrgnecy(String urgency) throws SQLException{
        return repository.getTicketsByUrgency(urgency);
    }
    
    public List<Ticket> getTicketsByDateCreated(String date){
        return repository.getTicketsByDateCreated(date);
    }
    
    public String updateAsTech(int id, String status, String summary) throws SQLException{
        return repository.updateAsTech(id, status, summary);
    }
    
    public String updateAsAdmin(int id, int techId){
        return repository.updateAsAdmin(id, techId);
    }
    
    public String approveTicket(int ticketId,  int adminId, String status) throws SQLException{
        return repository.approveTicket(ticketId, adminId, status);
    }

    public String createTicket(String name, String desc, LocalDate createdOn, String location, boolean urgency) throws SQLException{
        return repository.createTicket(name,desc, createdOn, location, urgency);
    }
    
    public String deleteTicket(int id) throws SQLException{
        return  repository.deleteTicket(id);
    }
}
