/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.UserInfo;
import ict.db.UserDB;

/**
 *
 * @author LCF
 */
public class ShowUser {
    public static void main(String[] arg){
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        UserDB userdb = new UserDB(url,username,password);
        int id = 1;
        String Email = "heminchong";
        String Username = "tom888";
        String Password = "123";
        String Tel = "99999999";
        String State = "senior";;
        UserInfo user = new UserInfo(id,Email,Username,Password,Tel,State);
       if(userdb.editRecord(user)){
           System.out.println("dwe");
       }else{
           System.out.println("dwdddddd");
       }
       
    }
}
