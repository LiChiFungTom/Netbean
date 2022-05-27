<%-- 
    Document   : ShowCenterRecords
    Created on : 2022年3月30日, 下午04:54:49
    Author     : LCF
--%>

<%@page import="ict.bean.Trainer"%>
<%@page import="ict.bean.Booking"%>
<%@page import="ict.db.TrainerDB"%>
<%@page import="ict.db.BookingDB"%>
<%@page import="ict.bean.GymVenue"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Records</title>
        <link rel="stylesheet" href="css/accountTable.css" />
    </head>
    <body>
        
         <%@ include file="seniorMenu.jsp" %>
        <br/><br/><br/><br/><br/><br/>
        <h1>Booking Record of Gym Venue</h1>
        
        <div class="container">
	
	<div class="table">
		<div class="table-header">
			<div class="header__item"><a id="name" class="filter__link" href="#">Booking ID</a></div>
			<div class="header__item"><a id="wins" class="filter__link filter__link--number" >Customer Email</a></div>
			<div class="header__item"><a id="draws" class="filter__link filter__link--number" >Customer Name</a></div>
			<div class="header__item"><a id="losses" class="filter__link filter__link--number" >Customer Tel</a></div>
                        <div class="header__item"><a id="total" class="filter__link filter__link--number" >Trainer</a></div>
			<div class="header__item"><a id="total" class="filter__link filter__link--number" >Center Address</a></div>
                        <div class="header__item"><a id="total" class="filter__link filter__link--number" >Booking Date</a></div>
                        <div class="header__item"><a id="total" class="filter__link filter__link--number" >Amount</a></div>
                        <div class="header__item"><a id="total" class="filter__link filter__link--number" >State</a></div>

                        
		</div>
		<div class="table-content">
			 <%
                             ArrayList center = (ArrayList)request.getAttribute("center");
                            String dbUser = this.getServletContext().getInitParameter("dbUser");
                            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
                            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
                            
                            TrainerDB trainerdb = new TrainerDB (dbUrl, dbUser, dbPassword);
                         
                             if(center.size() != 0){
                                 for(int i = 0; i < center.size(); i++){
                               Booking booking = (Booking)center.get(i);
                               Trainer trainer = trainerdb.getTrainerById(booking.getTrainerID());
                               out.println("<div class='table-row'>");
                               out.println("<div class='table-data'>"+booking.getBookingID()+"</div>");
                               out.println("<div class='table-data'>"+booking.getCustEmail()+"</div>");
                               out.println("<div class='table-data'>"+booking.getCustName()+"</div>");
                               out.println("<div class='table-data'>"+booking.getCustPhone()+"</div>");
                               if(trainer != null){
                                   out.println("<div class='table-data'>"+trainer.getName()+"</div>");
                               }else{
                                   out.println("<div class='table-data'>Not Need</div>");
                               }
                               
                               out.println("<div class='table-data'>"+booking.getCenterAddress()+"</div>");
                               out.println("<div class='table-data'>"+booking.getDate()+"</div>");
                               out.println("<div class='table-data'>"+booking.getAmount()+"</div>");
                               out.println("<div class='table-data'>"+booking.getState()+"</div>");

                               
                               //out.println("<tr><td>" +user.getID() + "</td><td>"+user.getEmail()+"</td><td>"+user.getUsername()+"</td><td>"+user.getTel()+"</td><td>"+user.getState()+"</td><td>"+user.getPassword()+"</td><td><a href='DeleteController?ID="+user.getID()+"'>Delete</a></td></tr>");
                               out.println("</div>");
                           } 
                             }else{
                                  out.println("<div class='table-row'>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'>No Booking</div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               //out.println("<tr><td>" +user.getID() + "</td><td>"+user.getEmail()+"</td><td>"+user.getUsername()+"</td><td>"+user.getTel()+"</td><td>"+user.getState()+"</td><td>"+user.getPassword()+"</td><td><a href='DeleteController?ID="+user.getID()+"'>Delete</a></td></tr>");
                               out.println("</div>");
                             }
                           
                           
                           %>
         
			
		</div>	
	</div>
</div>
      
    </body>
</html>
