/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.BookingDB;
import ict.db.TrainerDB;
import java.security.SecureRandom;

/**
 *
 * @author LCF
 */
public class AddBookingTable {
    
    
    public static void main(String[] arg){
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        BookingDB psa = new BookingDB(url,username,password);
        psa.CreateBookingTable();
        
    }
    


    
    
}
