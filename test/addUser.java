/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.UserDB;

/**
 *
 * @author LCF
 */
public class addUser {
     public static void main(String[] arg){
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        UserDB userdb = new UserDB(url,username,password);
        //userdb.addUserInfo("heminchong","tom","88888888","123","senior");
        userdb.addUserInfo("tom3", "tom3", "123", "123", "trainer");
        
    }
}
