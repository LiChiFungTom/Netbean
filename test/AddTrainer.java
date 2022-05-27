/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.Trainer;
import ict.db.TrainerDB;
import java.util.ArrayList;

/**
 *
 * @author LCF
 */
public class AddTrainer {
    
     public static void main(String[] arg){
        String url ="jdbc:mysql://localhost:3306/ITP4511_AS";
        String username = "root";
        String password ="";
        TrainerDB psa = new TrainerDB(url,username,password);
        //psa.AddPersonal("Trainer1.jpg","Tom","Chi Tat Cup Men's Bodybuilding Overall Champion","M","Trainers");
        //psa.AddTrainer(null, "Adrian", "Hong Kong Bodybuilding Overall Champion", "M","free");
         //Trainer tr = psa.getTrainerById(1);
           //          System.out.println(tr.getDescription());
           ArrayList allTrainer = psa.getAvaTrainers();

               System.out.print(allTrainer.size());
           
    }
    
}
