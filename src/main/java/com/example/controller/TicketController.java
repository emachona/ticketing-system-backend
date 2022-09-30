/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.model.Ticket;
import com.example.service.TicketService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "/tickets")
@CrossOrigin

public class TicketController {
    @Autowired
    private TicketService service;

    @GetMapping
    public List<Ticket> getAllTickets(){
        return service.getAllTickets();
    }
     
    @GetMapping("/{id}")
    public Ticket getById(@PathVariable("id") int id) throws SQLException {
        return service.getTicketById(id);
    }
    
    @GetMapping("/state/{state}")
    public List<Ticket> getByState(@PathVariable("state") String state) throws SQLException{
        return service.getTicketsByState(state);
    }
    
    @GetMapping("/urgnecy/{urgnet}")
    public List<Ticket> getByUrgency(@PathVariable("urgnet") String urgnecy) throws SQLException{
        return service.getTicketsByUrgnecy(urgnecy);
    }
    
    @GetMapping("/date/{date}")
    public List<Ticket> getByDate(@PathVariable("date") String date){
        return service.getTicketsByDateCreated(date);
    }

    @PostMapping("/create")
    public String createTicket(@RequestBody Ticket ticket) throws SQLException{
        return service.createTicket(ticket.getName(),ticket.getDescription(), ticket.getCreatedOn(), ticket.getLocation(), ticket.isUrgency());
    }
    
    @PutMapping("/update/tech/{id}")
    public String updateAsTech(@PathVariable int id, @RequestParam String status, @RequestParam String summary) throws SQLException{
        return service.updateAsTech(id,status,summary);
    }
    
     @PutMapping("/update/admin/{id}")
    public String updateAsAdmin(@PathVariable int id, @RequestParam int c){
        return service.updateAsAdmin(id,c);
    }
    
    @PutMapping("/admin/approve/{ticketId}")
    public String approveTicket(@PathVariable int ticketId, @RequestParam int adminId, @RequestParam String status) throws SQLException{
        return service.approveTicket(ticketId, adminId, status);
    }
    
    @DeleteMapping("/ticket/delete/{id}")
    public String deleteTicket(@PathVariable int id) throws SQLException {
        return service.deleteTicket(id);
    }
    
}
