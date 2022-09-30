/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

/**
 *
 * @author User
 */
public enum Role {
    
    CLIENT,
    ADMIN,
    TECHNICIAN;
        
    @Override
    public String toString(){
        switch(this){
        case CLIENT:
            return "CLIENT";
        case ADMIN :
            return "ADMIN";
        case TECHNICIAN :
            return "TECHNICIAN";
        }
        
        return null;
    }

    public static Role getEnum(String value) {
        for(Role v : Role.values()){
            if(v.toString().equalsIgnoreCase(value)) 
                return v;
        }
        throw new IllegalArgumentException();
    }
    
//    public static Map<String, Role> typeMapping = Maps.newHashMap();
//    static {
//        typeMapping.put(CLIENT.name(), CLIENT);
//        typeMapping.put(ADMIN.name(), ADMIN);
//        typeMapping.put(ADMIN.name(), ADMIN);
//    }
//
//    public static Role getType(String typeName) {
//        if (typeMapping.get(typeName) == null) {
//            throw new RuntimeException(String.format("There is no Type mapping with name (%s)"));
//        }
//        return typeMapping.get(typeName);
//    }
}
