/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.GymCenterDB;

/**
 *
 * @author LCF
 */
public class AddGymCenterTable {
    public static void main(String[] arg){
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        GymCenterDB gymdb = new GymCenterDB(url,username,password);
        gymdb.CreateGymCenterTable();
      
        
    }
}
