/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Booking implements Serializable{
    private String bookingID,centerID, custName, custEmail, custPhone, centerAddress, date,time, state, createDate;
    private int custID,trainerID,amount;
    
    public Booking(){
        
    }
    public Booking(String bookingid, int custid,int trainerid,String centerID, String custname,String custemail, String custphone, String centeraddress, String date, String state,String createDate, int amount){
        bookingID = bookingid;
        custID = custid;
        trainerID = trainerid;
        custName = custname;
        custEmail = custemail;
        custPhone = custphone;
        centerAddress = centeraddress;
        this.date = date;
        this.state = state;
        this.centerID = centerID;
        this.amount = amount;
        this.createDate = createDate;
    }
    public void setBookingID(String id){
        bookingID = id;
    }
    public void setCustID(int custid){
        custID = custid;
    }
    public void setCenterID(String ceid){
        this.centerID = ceid;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public void setTrainerID(int tid){
        trainerID = tid;
    }
    public void setCustName(String cname){
        custName = cname;
    }
    public void setCustEmail(String cemail){
        custEmail = cemail;
    }
    public void setCustPhone(String cphone){
        custPhone = cphone;
    }
    public void setCenterAddress(String caddress){
        centerAddress = caddress;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getBookingID(){ return bookingID;}
    public int getCustID(){ return custID; }
    public int getTrainerID() { return trainerID; }
    public String getCustName(){ return custName;}
    public String getCustEmail(){ return custEmail; }
    public String getCustPhone(){ return custPhone;}
    public String getCenterAddress(){ return centerAddress;}
    public String getDate(){ return date;}
    public String getState(){ return state;}
    public String getCenterID(){ return centerID;}
    public String getTime(){ return time;}
    public int getAmount() { return amount; }
}
