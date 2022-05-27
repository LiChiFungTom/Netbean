/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.UserDB;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ShowAlluserWithArraylist {
    public static void main(String[] arg){
        ArrayList users = new ArrayList();
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        UserDB userdb = new UserDB(url,username,password);
        users = userdb.getUsers();
       System.out.println(users.size());
    }
}
