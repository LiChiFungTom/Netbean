/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.Booking;
import ict.db.BookingDB;
import java.util.ArrayList;

/**
 *
 * @author LCF
 */
public class ShowAllBookingRecordsWithArraylist {
    public static void main(String[] arg){
        ArrayList booking = new ArrayList();
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        BookingDB bdb = new BookingDB(url,username,password);
        booking = bdb.getBooking();    
        System.out.println(booking.size());
//        for(int i =0; i< booking.size();i++){
//            Booking bdb = (Booking)booking.get(i);
//            System.out.println(gyv.getImg() + " "+ gyv.getName()   + " " + gyv.getDescription() +" "+" "+ gyv.getAddress()+ " " +gyv.getState() );
//        
//        }
        
     }
}
