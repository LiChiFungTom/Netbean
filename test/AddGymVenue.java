/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.GymVenue;
import ict.db.GymCenterDB;

/**
 *
 * @author LCF
 */
public class AddGymVenue {
     public static void main(String[] arg){
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        GymCenterDB gymdb = new GymCenterDB(url,username,password);
        //gymdb.AddGymCenter(null,"GOOD GYM ROOM","a lot of tool","Wong Tai Xin, Hong Kong","opening","300");
        GymVenue center = gymdb.queryGymcenter("1O382P");
        System.out.println(center.getAddress());
        //gymdb.AddGymCenter("Fitness1.jpg","GOOD GYM ROOM","a lot of tool","Kwun Tong, Hong Kong","close");
        
    }
}
