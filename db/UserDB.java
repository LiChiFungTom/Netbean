/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.UserInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LCF
 */
public class UserDB {
    
    private String url="";
    private String username = "";
    private String password = "";
    
     public UserDB(String dburl, String dbUser , String Password){
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
    
    
     
     public void CreateUserInfoTable(){
        Statement stmnt =null;
        Connection cnnct =null;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT  EXISTS userInfo ("
                    +"id int(3) NOT NULL,"
                    +"email varchar(35) NOT NULL,"
                    +"username varchar(25) NOT NULL,"
                    +"tel varchar(8) NOT NULL,"
                    +"password varchar(20) NOT NULL,"
                    +"state varchar(15) NOT NULL,"
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
     
    
     
     
    
     public boolean addUserInfo(String email, String user, String tel, String pwd ,String state ){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess=false;
        try{
            if(checkEmail(email)){
                cnnct = getConnection();
                String preQueryStatement ="INSERT INTO USERINFO VALUES (?,?,?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);    
                pStmnt.setInt(1, getID());
                pStmnt.setString(2, email);
                pStmnt.setString(3, user);
                pStmnt.setString(4, tel);
                pStmnt.setString(5, pwd);
                pStmnt.setString(6, state);
                int rowCount = pStmnt.executeUpdate();
                if(rowCount >= 1){
                    isSuccess=true;
                    System.out.println(user+" is added");
                }
                pStmnt.close();
                cnnct.close();
            }else{
                System.out.println("is exist");
            }
                  
       
            
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
     
    
     
     public UserInfo queryCustById(int id){
        UserInfo cd = null;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        try{
            cnnt = getConnection();
            String perQueryStatement = "SELECT * FROM userInfo WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                cd = new UserInfo();
                cd.setID(rs.getInt("id"));
                cd.setEmail(rs.getString("email"));
                cd.setUsername(rs.getString("username"));
                cd.setTel(rs.getString("tel"));
                cd.setPassword(rs.getString("password"));
                cd.setState(rs.getString("state"));
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
     
     public int getID(){
         int id = 0;
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userInfo";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("id");
            }
            id ++;
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
     
     public UserInfo getUserByEmailAndPsw(String user, String pwd){
        UserInfo cd = null;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try{
           cnnct = getConnection();
           String preQueryStatement="SELECT * FROM USERINFO WHERE email=? and password=?";
           pStmnt = cnnct.prepareStatement(preQueryStatement);
           pStmnt.setString(1, user);
           pStmnt.setString(2, pwd);
           ResultSet rs= null;
           rs = pStmnt.executeQuery();
           if (rs.next()) {
                cd = new UserInfo();
                cd.setID(rs.getInt("id"));
                cd.setEmail(rs.getString("email"));
                cd.setUsername(rs.getString("username"));
                cd.setTel(rs.getString("tel"));
                cd.setPassword(rs.getString("password"));
                cd.setState(rs.getString("state"));
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
        return cd;
    }
     
     
     
     
     public boolean isValidUser(String user, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid=false;
        try{
           cnnct = getConnection();
           String preQueryStatement="SELECT * FROM USERINFO WHERE email=? and password=?";
           pStmnt = cnnct.prepareStatement(preQueryStatement);
           pStmnt.setString(1, user);
           pStmnt.setString(2, pwd);
           ResultSet rs= null;
           rs = pStmnt.executeQuery();
           if(rs.next()){
               isValid=true;
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
        return isValid;
    }
     
     public void dropUserInfoTable(){
        Statement stmnt =null;
        Connection cnnct =null;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "DROP TABLE USERINFO";
        stmnt.execute(sql);
        System.out.println("Done");
        stmnt.close();
        cnnct.close();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            ex = ex.getNextException();
        }catch(IOException ex){
            ex.printStackTrace();
        }
   
   }
     public String getUserType(String user, String pwd){
         String type = "";
          Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try{
           cnnct = getConnection();
           String preQueryStatement="SELECT * FROM USERINFO WHERE email=? and password=?";
           pStmnt = cnnct.prepareStatement(preQueryStatement);
           pStmnt.setString(1, user);
           pStmnt.setString(2, pwd);
           ResultSet rs= null;
           rs = pStmnt.executeQuery();
           if(rs.next()){
               type = rs.getString("state");
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
         return type;
     }
     
     public boolean editRecord( UserInfo user){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        UserInfo cu = user;
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE USERINFO SET email=?, username=?, tel=?, password=?, state=? WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setString(1, cu.getEmail());
            pStmnt.setString(2, cu.getUsername());
            pStmnt.setString(3, cu.getTel());
            pStmnt.setString(4, cu.getPassword());
            pStmnt.setString(5, cu.getState());
            pStmnt.setInt(6, cu.getID());
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
     
     public boolean checkEmail(String email){
        boolean notExist = true;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try{
           cnnct = getConnection();
           String preQueryStatement="SELECT * FROM USERINFO WHERE email=?";
           pStmnt = cnnct.prepareStatement(preQueryStatement);
           pStmnt.setString(1, email);
           ResultSet rs= null;
           rs = pStmnt.executeQuery();
           if(rs.next()){
               notExist=false;
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
         return notExist;
     }
    
   public UserDB queryUser(){
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        
        UserDB db =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userinfo";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Email: "+rs.getString("email"));
                System.out.println("Name: "+rs.getString("username"));
                System.out.println("Password: "+rs.getString("password"));
                 System.out.println("Tel: "+rs.getString("tel"));
                System.out.println("state: "+rs.getString("state"));
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
        return db;
    }
   
   
   
   
    public boolean delRecord(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        boolean isSuccess=false;
       
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM USERINFO WHERE Id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,id);
           
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
    
    public ArrayList getUsers(){
        ArrayList users = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        UserInfo db =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM userinfo";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                users.add(new UserInfo(rs.getInt("id"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("tel"),rs.getString("state")));
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
        return users;
    }
    
    public int getUserIdByEmail(String email){
        int id=0;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try{
           cnnct = getConnection();
           String preQueryStatement="SELECT * FROM USERINFO WHERE email=?";
           pStmnt = cnnct.prepareStatement(preQueryStatement);
           pStmnt.setString(1, email);
           ResultSet rs= null;
           rs = pStmnt.executeQuery();
           if(rs.next()){
               id = rs.getInt("id");
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
  
}
