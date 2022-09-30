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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository

public class TicketRepository {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/ticketing_system";
    static final String USER = "root";
    static final String PASS = "Asnub123*";

    //SREDI ZEMANJE PODATOCI OD BAZA
     public List<Ticket> getAllTickets() {
        List<Ticket> dbtickets = new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tickets");
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
                    rAp =  Role.getEnum(rs2.getString("role"));
                    a2 = new Client(rs3.getInt("id"),rs3.getString("username"),rs3.getString("password"), rs3.getString("name"), rAp, rs3.getBoolean("active"));
                }
                Ticket t;
                t = new Ticket(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), s, rs.getBoolean(7), a1,rs.getString(9),a2);
                dbtickets.add(t);
            }
            con.close();
            System.out.println("getAllTickets - success");
            return dbtickets;
        }catch(Exception e){System.out.println(e);return null;}
    }
    
    public Ticket getTicketById(int id) throws SQLException {
        Ticket ticket=new Ticket();
         Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from tickets where id="+ id +"");
            if(rs.next()){
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
                ticket = new Ticket(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), s, rs.getBoolean(7), a1,rs.getString(9),a2);
            }
            con.close();
            return ticket;
    }
    
    public List<Ticket> getTicketsByState(String status) throws SQLException {
        if(status.equalsIgnoreCase("new") || status.equalsIgnoreCase("in_progress") || status.equalsIgnoreCase("approved") || status.equalsIgnoreCase("declined") || status.equalsIgnoreCase("done") || status.equalsIgnoreCase("cancelled")){
            List<Ticket> dbtickets = new ArrayList<>();
            String inputS = status.toUpperCase();
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM tickets WHERE state='"+ inputS +"'");
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
        }else{
            System.out.println("Unsupported state input");
            return null;
            }
    }
    
        public List<Ticket> getTicketsByUrgency(String urgency) throws SQLException {
        if(urgency.equalsIgnoreCase("urgnet")){
            List<Ticket> dbtickets = new ArrayList<>();
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM tickets WHERE urgency='1'");
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
        }else{
            System.out.println("Didn't find tickets with urgency "+urgency);
            return null;
            }
    }
    
    //proverka za formatot na datum, da vraka poraka za korisnikot da znae shto da vnesi
    public List<Ticket> getTicketsByDateCreated(String date) {
        List<Ticket> dbtickets = new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date datum = sdf1.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(datum.getTime());
            ResultSet rs=stmt.executeQuery("select * from tickets where createdOn = '"+sqlDate+"'");
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
        }catch(Exception e){System.out.println(e + "Try entering the date in this format yyyy-MM-dd");return null;}
    }
    
    public String createTicket(String name, String desc, LocalDate createdOn, String location, boolean urgency) throws SQLException{
        Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO tickets(name, description, createdOn, location, state, urgency, assignee, summary, approver) "
            + "VALUES ('"+ name +"', '"+ desc +"', '"+ createdOn +"', ' "+ location +"', 'NEW', "+ urgency +", null, ' ', null)";
        stmt.executeUpdate(sql);
        con.close();
        return ("Successfully created ticket!");
    }
    
     public String updateAsTech(int id, String status, String summary) throws SQLException{
         if(status.equals("Done") || status.equals("Cancelled")){
            Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = con.createStatement();
            String sql = "UPDATE tickets SET state = '"+ status +"', summary = '"+ summary +"' WHERE id = '"+ id +"'";
            stmt.executeUpdate(sql);
            con.close();
            return ("Posted summary of ticket with id "+ id +" and changed status to "+status);
         }else{
             return ("Status can't be "+ status +" , please enter valid format(Done/Cancelled)");
         }    
    }
     
    public String updateAsAdmin(int id, int techId){
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String sql = "UPDATE tickets SET state = 'IN_PROGRESS', assignee = '"+ techId +"' WHERE id = '"+ id +"'";
            stmt.executeUpdate(sql);
            con.close();
            return ("Ticket with id "+ id +"assigned to technican "+ techId);
        }catch(SQLException e){return null;}
    }

    public String approveTicket(int ticketId, int adminId, String status) throws SQLException{
        String ans = "Error";
        if(status.equals("Approved") || status.equals("Declined")){
            Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = con.createStatement();
            String sql = "UPDATE tickets SET state = '"+ status +"', approver = '"+ adminId +"' WHERE id = '"+ ticketId +"'";
            int res = stmt.executeUpdate(sql);
            con.close();
            if(res == 1){ // promenet 1 zapis
                ans=status+" ticket with id "+ ticketId;
            }else if(res == 0){ // nema takov zapis
                ans= "No ticket with id "+ ticketId +" exists.";
            }
         }else{ // greshen status
             ans = "Status can't be "+ status +" , please enter valid format(Approved/Declined)";
         }
        return ans;
    }
    
    public String deleteTicket(int id) throws SQLException{
        Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            int result=stmt.executeUpdate("delete from tickets where id="+ id +"");
            con.close();
            return ("Deleted "+result+" ticket from db");
    }
}
