/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import com.example.Role;
//import javax.persistence.Entity;
//import javax.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    
    private int id;
    
    private String username;
    private String password;
    private String name;
    private Role role;
    private boolean active;
}
