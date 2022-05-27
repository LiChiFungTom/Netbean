/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;
import ict.bean.Booking;
import ict.bean.Trainer;
import java.io.IOException;
import java.io.InputStream;
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
public class TrainerDB {
    
    private String url="";
    private String username = "";
    private String password = "";
    
    public TrainerDB(String dburl, String dbUser , String Password){
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
    
    public void CreateTrainerTable(){
        Statement stmnt =null;
        Connection cnnct =null;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT  EXISTS Trainer ("
                    +"id int(3) NOT NULL,"
                    +"img mediumblob,"
                    +"name varchar(25) NOT NULL,"
                    +"description varchar(100) NOT NULL,"
                    +"gender varchar(10) NOT NULL,"
                    +"state varchar(15) NOT NULL,"
                    + "price int(10) NOT NULL,"
                    +"FOREIGN KEY (id) REFERENCES UserInfo(id)"
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
    
    public boolean AddTrainer(InputStream  img ,String name,String des , String gender ,String state,int price){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess=false;
        try{
            
                cnnct = getConnection();
                String preQueryStatement ="INSERT INTO Trainer VALUES (?,?,?,?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);    
                pStmnt.setInt(1, getId());
                pStmnt.setBlob(2, img);
                pStmnt.setString(3, name);
                pStmnt.setString(4, des);
                pStmnt.setString(5,gender);
                pStmnt.setString(6, state); 
                pStmnt.setInt(7, price); 
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
    public boolean AddTrainerByWithID(int ID,InputStream  img ,String name,String des , String gender ,String state,int price){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess=false;
        try{
            
                cnnct = getConnection();
                String preQueryStatement ="INSERT INTO Trainer VALUES (?,?,?,?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);    
                pStmnt.setInt(1, ID);
                pStmnt.setBlob(2, img);
                pStmnt.setString(3, name);
                pStmnt.setString(4, des);
                pStmnt.setString(5,gender);
                pStmnt.setString(6, state); 
                pStmnt.setInt(7, price);
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
    
    public int getId(){
         int id = 0;
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Trainer";
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
    
    public ArrayList getTrainers(){
        ArrayList trainer = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Trainer";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                trainer.add(new Trainer(rs.getInt("id"),rs.getBytes("img")  ,rs.getString("name"),rs.getString("description"),rs.getString("gender"),rs.getString("state"),rs.getInt("price")  ));
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
        return trainer;
    } 
    public ArrayList getAvaTrainers(){
        ArrayList trainer = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Trainer where state=? AND price>?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "Free");
            pStmnt.setInt(2, 0);
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                trainer.add(new Trainer(rs.getInt("id"),rs.getBytes("img")  ,rs.getString("name"),rs.getString("description"),rs.getString("gender"),rs.getString("state"),rs.getInt("price")  ));
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
        return trainer;
    } 
    
    public Trainer getTrainerById(int id){
        Trainer cd = null;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        try{
            cnnt = getConnection();
            String perQueryStatement = "SELECT * FROM trainer WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                cd = new Trainer();
                cd.setid(rs.getInt("id"));
                cd.setImgs(rs.getBytes("img")  );
                cd.setName(rs.getString("name"));
                cd.setDescription(rs.getString("description"));
                cd.setGender(rs.getString("gender"));
                cd.setState(rs.getString("state"));
                cd.setPrice(rs.getInt("price"));
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
    
    
    public boolean editRecord( Trainer tr, InputStream  img){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        Trainer cu = tr;
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE Trainer SET img=?, name=?, description=?, gender=? , state=? ,price=? WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
         
            pStmnt.setBlob(1, img );
            pStmnt.setString(2, cu.getName());
            pStmnt.setString(3, cu.getDescription());
            pStmnt.setString(4, cu.getGender());
            pStmnt.setString(5, cu.getState());
            pStmnt.setInt(6, cu.getPrice());
            pStmnt.setInt(7, cu.getid());
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
    
        public boolean NotImg( Trainer tr){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        Trainer cu = tr;
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE Trainer SET name=?, description=?, gender=? , state=? ,price=? WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
                 
            pStmnt.setString(1, cu.getName());
            pStmnt.setString(2, cu.getDescription());
            pStmnt.setString(3, cu.getGender());
            pStmnt.setString(4, cu.getState());
            pStmnt.setInt(5, cu.getPrice());
            pStmnt.setInt(6, cu.getid());
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
    
    
    public boolean updataState( Trainer tr){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        Trainer cu = tr;
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE Trainer SET state=? WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);          
            pStmnt.setString(1, cu.getState());
            pStmnt.setInt(2, cu.getid());
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
    
    
    
    //for delete record
     public boolean delRecord(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        boolean isSuccess=false;
       
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM trainer WHERE Id=?";
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
     
       public ArrayList getTrainerBookings(int trainerID){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where trainerID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, trainerID);
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
   
     public boolean checkIsExitTrainer(int id){
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        boolean isExist = false;
        try{
            cnnt = getConnection();
            String perQueryStatement = "SELECT * FROM trainer WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                isExist = true;
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
        return isExist;
    }
     public boolean updateTrainerSenior( int id, String name, String desc, String gender){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE Trainer SET  name=?, description=?, gender=?  WHERE id=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, desc);
            pStmnt.setString(3, gender);
            pStmnt.setInt(4, id);
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
    
}
