/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;
import ict.bean.Booking;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.Date;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author User
 */

//呢個我遲D搞
public class BookingDB {
    private String url="";
    private String username = "";
    private String password = "";
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom rnd = new SecureRandom();
     public BookingDB(String dburl, String dbUser , String Password){
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
     public void CreateBookingTable(){
        Statement stmnt =null;
        Connection cnnct =null;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT  EXISTS booking ("
                    +"bookingId varchar(6) NOT NULL,"
                    +"custID int(3) NOT NULL,"
                    +"trainerID int(3) NULL,"
                    +"centerID varchar(6) NOT NULL,"
                    +"custName varchar(50) NOT NULL,"  
                    +"custEmail varchar(60) NOT NULL,"
                    +"custPhone varchar(10) NOT NULL,"
                    +"centerAddress varchar(100) NOT NULL,"
                    +"bookDate varchar(50) NOT NULL,"  
                    +"state varchar(15) NOT NULL,"
                    +"bookingCreateDate varchar(100) NOT NULL," 
                    +"amount int(50) NOT NULL," 
                    +"PRIMARY KEY (bookingId),"
                    +"FOREIGN KEY (custID) REFERENCES UserInfo(id),"
                    + "FOREIGN KEY (trainerID) REFERENCES Trainer(id),"
                    +"FOREIGN KEY (centerID) REFERENCES gymcenter(id)"
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
     
     public boolean AddBooking(int cid, String trainerid, String centerID, String cname , String cemail, String cphone , String caddress, String bookDate, String state, int amount ){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess=false;
        String id = getID();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");  
        Date date = new Date();   
        try{
                cnnct = getConnection();
                String preQueryStatement ="INSERT INTO booking VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);    
                pStmnt.setString(1,id );
                pStmnt.setInt(2, cid);
                if(!trainerid.equals("null")){
                    pStmnt.setInt(3, Integer.parseInt(trainerid));
                }else{
                    pStmnt.setNull(3, Types.NULL);
                }
                pStmnt.setString(4, centerID);
                pStmnt.setString(5, cname);
                pStmnt.setString(6,cemail);     
                pStmnt.setString(7,cphone);  
                pStmnt.setString(8,caddress);  
                pStmnt.setString(9,bookDate);  
                pStmnt.setString(10,state);  
                pStmnt.setString(11,formatter.format(date));
                pStmnt.setInt(12,amount);
                int rowCount = pStmnt.executeUpdate();
                if(rowCount >= 1){
                    isSuccess=true;
                    System.out.println(id+" is added");
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
     
     public String getID(){
        String id = "";
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            id = randomString(6);
            while(rs.next()){
                while(id.equals(rs.getString("bookingId"))){
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
    
    
   //display array list of booking records
   public ArrayList getBooking(){
        ArrayList booking = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                booking.add(new Booking(rs.getString("bookingid"),rs.getInt("custID"),rs.getInt("trainerID"),rs.getString("centerID"),rs.getString("custName"),rs.getString("custEmail"),rs.getString("custPhone"),rs.getString("centerAddress"),rs.getString("bookDate"),rs.getString("state"),rs.getString("bookingCreateDate"),rs.getInt("amount")  ));
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
        return booking;
    } 
    public boolean getAvailableCenter(String centerID, String date){
        boolean available = true;
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        int count=0;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT COUNT(*) AS count FROM booking WHERE centerID=? and bookDate=? and state=? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, centerID);
            pStmnt.setString(2, date);
            pStmnt.setString(3, "confirmed");
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                count = rs.getInt("count");
            }
            
            if(count>=10){
                available = false;
            }
            System.out.println(count);
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
        return available;
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
      public ArrayList getGymBooking(String ID){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where centerID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ID);
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
     
     
     public ArrayList getCustomerBookings(int custID){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where custID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, custID);
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
      public boolean editRecord(String bookingID, int custID, String trainerID, String centerID, String custName, String custEmail,String custPhone, String centerAddress, String bookDate, int amount ){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE booking SET custID=?, trainerID=?, centerID=?, custName=?, custEmail=?,custPhone=?,centerAddress=?,bookDate=?,amount=? WHERE bookingId=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setInt(1, custID);
            
            if(!trainerID.equals("null")){
                    pStmnt.setInt(2, Integer.parseInt(trainerID));
                }else{
                    pStmnt.setNull(2, Types.NULL);
                }
            pStmnt.setString(3, centerID);
            pStmnt.setString(4,  custName);
            pStmnt.setString(5,  custEmail);
            pStmnt.setString(6, custPhone);
            pStmnt.setString(7,  centerAddress);
            pStmnt.setString(8, bookDate);
            pStmnt.setInt(9,  amount);
            pStmnt.setString(10, bookingID);
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
    public boolean upDateBooking(String bookingID , String state){
        boolean isSuccess = false;
        Connection cnnt = null;
        PreparedStatement pStmnt = null; 
        try{
            cnnt = getConnection();
            String perQueryStatement = "UPDATE booking SET state=? WHERE bookingId=?";
            pStmnt = cnnt.prepareStatement(perQueryStatement);
            pStmnt.setString(1, state);
            pStmnt.setString(2, bookingID);
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
     public Booking getBookingByID(String bookingid){
        Booking booking = null;
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where bookingId=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, bookingid);
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                booking = new Booking(rs.getString("bookingId"),rs.getInt("custID"),rs.getInt("trainerID"),rs.getString("centerID"),rs.getString("custName"),rs.getString("custEmail"),rs.getString("custPhone"),rs.getString("centerAddress"),rs.getString("bookDate") ,rs.getString("state") ,rs.getString("bookingCreateDate"),rs.getInt("amount") );
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
        return booking;
    } 
     public ArrayList getBookingByDate(int trainerID, String date){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where trainerID=? AND bookDate LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, trainerID);
            pStmnt.setString(2, date+"%");
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
     public ArrayList getBookingByDateWithCenterID(String centerID, String date){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where centerID=? AND bookDate LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, centerID);
            pStmnt.setString(2, date+"%");
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
     public ArrayList getCusComfBookingsById(int custID){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where custID=? AND state=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, custID);
            pStmnt.setString(2, "confirmed");
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
     public ArrayList getCusProBookingsById(int custID){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where custID=? AND state=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, custID);
            pStmnt.setString(2, "processing");
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
     public ArrayList getCusDelBookingsById(int custID){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where custID=? AND state=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, custID);
            pStmnt.setString(2, "decline");
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
      public boolean havBooked(int custID, String centerID, String date){
        ArrayList bookings = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt =null;
        boolean haveBooked = false; 
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where custID=? AND centerID=? AND bookDate=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, custID);
            pStmnt.setString(2, centerID);
            pStmnt.setString(3, date);
            ResultSet rs= null;
            rs = pStmnt.executeQuery();
            
            if(rs.next()){
                haveBooked = true;
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
        return haveBooked;
    } 
}
