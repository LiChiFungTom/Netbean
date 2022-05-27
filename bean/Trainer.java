/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.InputStream;
import javax.servlet.http.Part;

/**
 *
 * @author LCF
 */
public class Trainer {
    
    private int id;
    private Part img;
    private byte[]  imgs;
    private String name;
    private String description;
    private String gender; 
    private String state;
    private int price;
    private InputStream img1;
    
    public Trainer(){}
    
    public Trainer(int id , Part img , String name , String description ,String gender , String state, int price){
        this.id=id;
        this.img=img;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.state = state;
        this.price = price;
    }
    
     public Trainer(int id , byte[] imgs , String name , String description ,String gender , String state, int price){
        this.id=id;
        this.imgs=imgs;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.state = state;
        this.price = price;
    }
     
       public Trainer(int id ,InputStream img , String name , String description ,String gender , String state, int price){
        this.id=id;
        this.img1=img;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.state = state;
        this.price = price;
    }
    
      public Trainer(int id , String name , String description ,String gender , String state, int price){
        this.id=id;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.state = state;
        this.price = price;
    }
    
    public void setid(int id){this.id=id;}
    public int getid(){return this.id;}
    
    public void setImgs(byte[]  img){
        this.imgs = img;
    }
    
    public byte[] getImgs(){
        return this.imgs;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}
    
    public void setDescription(String des){this.description = des;}
    public String getDescription(){return this.description;}
    
    public void setState(String state){this.state = state;}
    public String getState(){return this.state;}
    
    public void setGender(String gender){this.gender = gender;}
    public String getGender(){return this.gender;}
    
}
