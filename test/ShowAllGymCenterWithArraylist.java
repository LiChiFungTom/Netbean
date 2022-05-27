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
public class ShowAllGymCenterWithArraylist {
    
     public static void main(String[] arg){
        ArrayList center = new ArrayList();
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        GymCenterDB gydb = new GymCenterDB(url,username,password);
        center = gydb.getCenter();    
        System.out.println(center.size());
        for(int i =0; i< center.size();i++){
            GymVenue gyv = (GymVenue)center.get(i);
            System.out.println(gyv.getImgs() + " "+ gyv.getName()   + " " + gyv.getDescription() +" "+" "+ gyv.getAddress()+ " " +gyv.getState() );
            
        }
        
     }
    
}
