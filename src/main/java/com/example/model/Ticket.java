/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import com.example.State;
import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Ticket {
    
    private int id;
    private String name;
    private String description;
    private LocalDate createdOn;
    private String location;
    private State state;
    private boolean urgency;
    private Client assignee;
    private String summary;
    private Client approver;
}
