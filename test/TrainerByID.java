/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.Trainer;
import ict.db.TrainerDB;

/**
 *
 * @author User
 */
public class TrainerByID {
    public static void main(String[] arg){
    String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        TrainerDB psa = new TrainerDB(url,username,password);
        Trainer as = psa.getTrainerById(3);
        System.out.println(as.getDescription());
        //Trainer td = new Trainer(3,"train_01.jpg","Ho Man Chuww","aa","man","On Work");
        //if(psa.editRecord(td)){
        //}
    }
}
