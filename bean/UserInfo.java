/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author LCF
 */
public class UserInfo implements Serializable {
    private int id;
    private String email;
    private String username;
    private String password;
    private String tel;
    private String state;
    
    public UserInfo(int id, String email, String username, String password, String tel, String state){
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.state = state;
    }

    
    public UserInfo(){
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    
    public String getPassword(){
        return this.password;
    }
    public int getID(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getTel(){
        return tel;
    }
    
    public void setTel(String tel){
        this.tel = tel;
    }
    
    
    public void setUsername(String name){
        this.username = name;
    }
    
    public void setPassword(String pw){
        this.password = pw;
    }
    
    public void setID(int id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }

    
}
