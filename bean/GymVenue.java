/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.InputStream;
import java.sql.Blob;
import javax.servlet.http.Part;

/**
 *
 * @author LCF
 */
public class GymVenue {
    
    private String id;
    private Part img;
    private byte[]  imgs;
    private String name;
    private String description;
    private String state;
    private String address;
    private String price;
    
    public GymVenue(String id , Part img , String name , String description ,String address, String state , String price){
        this.id=id;
        this.img=img;
        this.name = name;
        this.description = description;
        this.state = state;
        this.address = address;
        this.price = price;
                
    }
    
       public GymVenue(String id , byte[] imgs , String name , String description ,String address, String state , String price){
        this.id=id;
        this.imgs=imgs;
        this.name = name;
        this.description = description;
        this.state = state;
        this.address = address;
        this.price = price;
                
    }
    
    public GymVenue(String id , String name , String description ,String address, String state , String price){
        this.id=id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.address = address;
        this.price = price;       
    }
  
    
    public GymVenue(){}
    
    public void setid(String id){this.id=id;}
    public String getid(){return this.id;}
    
    
    public void setAddress(String address){
        this.address= address;
    }
    public String getAddress(){
        return this.address;
    }
    
    public void setImg(Part img){
        this.img = img;
    }
    
    public void setImgs(byte[]  img){
        this.imgs = img;
    }
    
    public byte[] getImgs(){
        return this.imgs;
    }
   
    
    
    
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}
    
    public void setDescription(String des){this.description = des;}
    public String getDescription(){return this.description;}
    
    public void setState(String state){this.state = state;}
    public String getState(){return this.state;}
    
    public void setPrice(String price){this.price = price;}
    public String getPrice(){return this.price;}
    
}
