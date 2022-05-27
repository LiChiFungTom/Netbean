/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.BookingDB;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AddBookingTest {
    public static void main(String[] arg){
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        BookingDB psa = new BookingDB(url,username,password);
        //psa.AddBooking(2, "4", "xyzaaaa","homanchung", "88888888", "Hong Kong", "2022-07-02", "processing");
        if(psa.getAvailableCenter("TTG9V6", "2022-05-18 11:00")){
         System.out.println("Yes");
    }else{
             System.out.println("no");
        }
        //psa.AddBooking(2, "1","LD4GPM", "tom1", "tom@email", "123123312", "Hong Kong", "2022-07-03", "confirmed",200);   
        //ArrayList test = psa.getCusComfBookingsById(2);
        //System.out.println(test.size());
        //psa.upDateBooking("WYZ6UH");
    }
}
