/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.Booking;
import ict.bean.GymVenue;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.http.Part;

/**
 *
 * @author LCF
 */
public class GymCenterDB {
    
    private String url="";
    private String username = "";
    private String password = "";
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom rnd = new SecureRandom();
    
     public GymCenterDB(String dburl, String dbUser , String Password){
        this.url=dburl;
        this.username=dbUser;
        this.password=Password;
    }
    
     public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
        return DriverManager.getConnection(url,username,password);
        
    }
     
     public void CreateGymCenterTable(){
        Statement stmnt =null;
        Connection cnnct =null;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT  EXISTS gymcenter ("
                    +"id varchar(6) NOT NULL,"
                    +"img mediumblob,"
                    +"name varchar(25) NOT NULL,"
                    +"description varchar(100) NOT NULL,"  
                    +"address varchar(100) NOT NULL," 
                    +"state varchar(15) NOT NULL,"
                    +"price varchar(15) NOT NULL,"
                    +"PRIMARY KEY (id)"
                    +")";
        stmnt.execute(sql);
        stmnt.close();
        cnnct.close();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            ex = ex.getNextException();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
     
      // Add trainer and venue 
     public boolean AddGymCenter(InputStream  img ,String name,String des ,String address, String status , String price){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess=false;
        
        try{
            
                cnnct = getConnection();
                String preQueryStatement ="INSERT INTO gymcenter VALUES (?,?,?,?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);    
                pStmnt.setString(1, getID());
                pStmnt.setBlob(2, img);                         
                pStmnt.setString(3, name);
                pStmnt.setString(4, des);
                pStmnt.setString(5, address);
                pStmnt.setString(6, status);
                pStmnt.setString(7, price);  
                int rowCount = pStmnt.executeUpdate();
                if(rowCount >= 1){
                    isSuccess=true;
                    System.out.println(name+" is added");
                }
                pStmnt.close();
                cnnct.close();
                                      
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        return isSuccess; 
     }
    
     //for center id 
     public String getID(){
        String id = "";
        id = randomString(6);
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM gymcenter";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                 while(id.equals(rs.getString("id"))){
                    id = randomString(6);
                }
            }
           
            pStmnt.close();
            cnnct.close();
            
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }       
         return id;
     }
     
     
     
     private static String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
           sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

  
   
   
   // for select id form the table of gymcenter db
    public GymVenue queryGymcenter(String id){
        GymVenue cd = null;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        try{
            cnnt = getConnection();
            String perQueryStatement = "SELECT * FROM gymcenter WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                
                cd = new GymVenue();
                cd.setid(rs.getString("id"));
                cd.setImgs( rs.getBytes("img") );
                cd.setName(rs.getString("name"));
                cd.setDescription(rs.getString("description"));
                cd.setAddress(rs.getString("address"));
                cd.setState(rs.getString("state"));
                cd.setPrice(rs.getString("price"));
            }
            pStmnt.close();
            cnnt.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return cd;
    }
   
   public ArrayList getTrainerBookings(String centerID){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where centerID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, centerID);
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                bookings.add(new Booking(rs.getString("bookingId"),rs.getInt("custID"),rs.getInt("trainerID"),rs.getString("centerID"),rs.getString("custName"),rs.getString("custEmail"),rs.getString("custPhone"),rs.getString("centerAddress"),rs.getString("bookDate") ,rs.getString("state") ,rs.getString("bookingCreateDate"),rs.getInt("amount") ));
            }
            pStmnt.close();
            cnnct.close();
            
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }       
        return bookings;
    } 
    
    
    
    
    
    // for edit center table
     public boolean editRecord( GymVenue gymvenue ){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        GymVenue gyv = gymvenue;
        
        
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE gymcenter SET name=?, description=?, address=? ,state=? , price=? WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            
               
            pStmnt.setString(1, gyv.getName());
            pStmnt.setString(2, gyv.getDescription());
            pStmnt.setString(3, gyv.getAddress());
            pStmnt.setString(4, gyv.getState());
            pStmnt.setString(5, gyv.getPrice());
            pStmnt.setString(6, gyv.getid());
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnt.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
                
            }
        }catch(IOException ex){
            ex.printStackTrace();
            
        }
        return isSuccess;
     }
     
     //for Change enable/disable state
     
      public boolean updataState( GymVenue gymvenue){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        GymVenue gyv = gymvenue;
        
        
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE gymcenter SET state=? WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setString(1, gyv.getState() );   
            pStmnt.setString(2, gyv.getid());
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnt.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
                
            }
        }catch(IOException ex){
            ex.printStackTrace();
            
        }
        return isSuccess;
     }
      
      
      
      
      
     
     
     //for delete record of center
     public boolean delRecord(String id){
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        boolean isSuccess=false;
       
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM gymcenter WHERE Id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,id);
           
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1 ){
               isSuccess = true;
               System.out.println( id +" is deleted");
            }
            
            
            pStmnt.close();
            cnnct.close();
            
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }       
        return isSuccess;
        
    }
   
    
    
     
    //for get center gym venue
    public ArrayList getCenter(){
        ArrayList centers = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM gymcenter";
            pStmnt = cnnct.prepareStatement(preQueryStatement);                  
            ResultSet rs= null;
            rs = pStmnt.executeQuery();                              
            while(rs.next()){
                centers.add(new GymVenue(rs.getString("id"), rs.getBytes("img")  ,rs.getString("name"),rs.getString("description"),rs.getString("address"),rs.getString("state"),rs.getString("price")  ));
            }
            pStmnt.close();
            cnnct.close();
            
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }       
        return centers;
    } 
     public ArrayList getAvaCenters(){
        ArrayList centers = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM gymcenter where state=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);   
            pStmnt.setString(1,"opening");
            ResultSet rs= null;
            rs = pStmnt.executeQuery();                              
            while(rs.next()){
                centers.add(new GymVenue(rs.getString("id"), rs.getBytes("img")  ,rs.getString("name"),rs.getString("description"),rs.getString("address"),rs.getString("state"),rs.getString("price")  ));
            }
            pStmnt.close();
            cnnct.close();
            
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }       
        return centers;
    } 
   
}
