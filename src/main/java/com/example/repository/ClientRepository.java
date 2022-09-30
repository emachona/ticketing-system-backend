/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.repository;

import com.example.Role;
import com.example.State;
import com.example.model.Client;
import com.example.model.Ticket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/ticketing_system";
    static final String USER = "root";
    static final String PASS = "Asnub123*";

    
    public Map<String, String> createClientProfile(String username, String password, String name){
        HashMap<String, String> map = new HashMap<>();
        try {
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users where role='CLIENT'");
            while(rs.next()){
                if(rs.getString("username").equals(username)){
                    System.out.println("This username is already taken. Can't create profile.");
                    map.put("msg", "This username is already taken. Try creating profile with different username.");
                }
            }
            Statement stmt1 = con.createStatement();
            String create = "INSERT INTO users(username, password, name, role, active) values ('"+ username +"', '"+ password +"', '"+ name +"', 'CLIENT',1)";
            stmt1.executeUpdate(create);
            con.close();
            System.out.println("Successfully created profile. You can now post a ticket.");
            map.put("msg", "Successfully created profile. You can now post a ticket");
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            map.put("msg", "Error creating profile, try again.");
        }
        return map;
    }
    
    public Client login(String username, String password){
        Client c = new Client();
        try{
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users where username='"+ username +"' and password='"+ password +"'");
            if(rs.next()){
                Role r;
                r = Role.getEnum(rs.getString("role"));
                c = new Client(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"),r,rs.getBoolean("active"));
            }
            con.close();
            System.out.println("Logged in");
            return c;
        }catch(Exception e){System.out.println(e);return null;}
    }
    
    public List<Client>getAllClients(){
         List<Client> users = new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users where role='CLIENT'");
            while(rs.next()){
                Role r;
                r = Role.getEnum(rs.getString("role"));
                Client c = new Client(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"),r,rs.getBoolean("active"));
                users.add(c);
            }
            con.close();
            return users;
        }catch(Exception e){System.out.println(e);return null;}
    }
    
    public List<Client>getAllTechnicians(){
         List<Client> techs = new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/ticketing_system","root","Asnub123*");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users where role='TECHNICIAN'");
            while(rs.next()){
                Role r;
                r = Role.getEnum(rs.getString("role"));
                Client c = new Client(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"),r,rs.getBoolean("active"));
                techs.add(c);
            }
            con.close();
            return techs;
        }catch(Exception e){System.out.println(e);return null;}
    }
    
    public Client getClientByName(String name){
        Client c = new Client();
        try{
            Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM users WHERE name = '"+ name +"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Role r;
                r = Role.getEnum(rs.getString("role"));
                c = new Client(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"),r,rs.getBoolean("active"));
            }
            con.close();
            return c;
        }catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Ticket> getAssignedTickets(int techId){
            List<Ticket> dbtickets = new ArrayList<>();
            Connection con;
        try {
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM tickets WHERE assignee="+ techId +"");
            while(rs.next()){
                State s;
                s = State.getEnum(rs.getString(6));
                int asId, apId;
                Client a1 = null;
                Client a2 = null;
                asId = rs.getInt("assignee");
                apId = rs.getInt("approver");
                Statement stmt2=con.createStatement();
                ResultSet rs2=stmt2.executeQuery("select * from users where id="+ asId +" limit 1");
                if(rs2.next()){
                    Role r;
                    r = Role.getEnum(rs2.getString("role"));
                    a1 = new Client(rs2.getInt("id"),rs2.getString("username"),rs2.getString("password"), rs2.getString("name"), r, rs2.getBoolean("active"));
                }
                Statement stmt3=con.createStatement();
                ResultSet rs3=stmt3.executeQuery("select * from users where id="+ apId +" limit 1");
                if(rs3.next()){
                    Role rAp;
                    rAp = Role.getEnum(rs3.getString("role"));
                    a2 = new Client(rs3.getInt("id"),rs3.getString("username"),rs3.getString("password"), rs3.getString("name"), rAp, rs3.getBoolean("active"));
                }
                Ticket t;
                t = new Ticket(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), s, rs.getBoolean(7), a1,rs.getString(9),a2);
                dbtickets.add(t);
            }
            con.close();
            return dbtickets;
            } catch (NumberFormatException | SQLException ex)
 {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);return null;}
    }
    
    public String deleteProfile(String uname, String password) throws SQLException{
        String ans = "Fatal error";
        Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt=con.createStatement();
        String sql = "UPDATE users SET active = 0 WHERE username = '"+ uname +"' AND password = '"+ password +"' limit 1";
        int result=stmt.executeUpdate(sql);
        con.close();
        if(result == 1){
            ans = "Your profile has been disabled.";
        }else if(result == 0){
            ans = "Error disabling profile.";
        }
        return ans;
    }
}
