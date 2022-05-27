/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.GymVenue;
import ict.db.GymCenterDB;
import java.util.ArrayList;

/**
 *
 * @author LCF
 */
public class EditGymCenter {
    
    public static void main (String args[]){
    
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        GymCenterDB gydb = new GymCenterDB(url,username,password);
        //GymVenue gyv = new GymVenue("","Fitness1.jpg","GOOD GYM Room","a lot of tool", "Kwun Tong, Hong Kong", "opening","HKD100" );
        //gydb.editRecord(gyv);
        System.out.println("Done");
        
        
    }
    
}
