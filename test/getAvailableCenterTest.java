/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.BookingDB;

/**
 *
 * @author User
 */
public class getAvailableCenterTest {
    public static void main(String[] arg){
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        BookingDB psa = new BookingDB(url,username,password);
        //int a = psa.getAvailableCenter("1O382P", "2022-07-02");
        if(psa.getAvailableCenter("1O382P", "2022-07-02")){
            System.out.println("dwd");
        }else{
            System.out.println("error");
        }
        //System.out.println(a);
    }
}
