/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;


/**
 *
 * @author User
 */
public enum State {
    
    NEW,
    APPROVED,
    DECLINED,
    IN_PROGRESS,
    DONE,
    CANCELLED;
    
    @Override
    public String toString(){
        switch(this){
        case NEW:
            return "New";
        case APPROVED :
            return "Approved";
        case DECLINED :
            return "Declined";
        case IN_PROGRESS :
            return "In_progress";
        case DONE :
            return "Done";
        case CANCELLED :
            return "Cancelled";
        }
        
        return null;
    }
    
    public static State getEnum(String value) {
        for(State v : State.values()){
            if(v.toString().equalsIgnoreCase(value)) 
                return v;
        }
        throw new IllegalArgumentException();
    }
}
