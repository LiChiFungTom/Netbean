<%-- 
    Document   : BookingRecords
    Created on : 2022年3月30日, 下午04:54:49
    Author     : LCF
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Records</title>
        <link rel="stylesheet" href="css/accountTable.css" />
          <style>
           a{
                 text-decoration: none;
            }
            
            a:visited{
               color: blue;
            }
    </style>
    </head>
    <body>
         <%@ include file="StaffMenu.jsp" %>
    <center><h1>Booking Record</h1></center>
        <jsp:useBean id="bookings" scope="request" class="ArrayList" />
        <div class="container">   
        <div class="table">
		<div class="table-header">
			<div class="header__item"><a id="name" class="filter__link">BookingID</a></div>
			<div class="header__item"><a id="losses" class="filter__link filter__link--number" >CustName</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >CustEmail</a></div>
                    <div class="header__item"><a id="losser" class="filter__link filter__link--number" >CustPhone</a></div>
                     <div class="header__item"><a id="losser" class="filter__link filter__link--number" >CenterAddress</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >BookDate</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >State</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >Amount</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >Option</a></div>
		</div>
         <div class="table-content">
           <%
               if(bookings.size() != 0){
                   for(int i =0; i <bookings.size();i++){
                       Booking b = (Booking)bookings.get(i);
                       out.println("<div class='table-row'>");
                       out.println("<div class='table-data'>"+b.getBookingID()+"</div>");
                       out.println("<div class='table-data'>"+ b.getCustName() +"</div>");
                       out.println("<div class='table-data'>"+ b.getCustEmail() +"</div>");
                       out.println("<div class='table-data'>"+ b.getCustPhone() +"</div>");
                       out.println("<div class='table-data'>"+ b.getCenterAddress() +"</div>");
                       out.println("<div class='table-data'>"+ b.getDate() +"</div>");
                       out.println("<div class='table-data'>"+ b.getState() +"</div>");
                       out.println("<div class='table-data'>"+ b.getAmount() +"</div>");
                       if(b.getState().equals("confirmed")){
                           out.println("<div class='table-data'>confirmed</div>");
                       }else if(b.getState().equals("decline")){
                           out.println("<div class='table-data'>decline</div>");
                       }else{
                           out.println("<div class='table-data'><a href='BookingController?action=StaffconfirmBooking&BookingID=" + b.getBookingID() + "&TrainerID=" + b.getTrainerID()+ "'>confirm</a> / <a href='BookingController?action=StaffdeclineBooking&BookingID=" + b.getBookingID() + "&TrainerID=" + b.getTrainerID()+ "'>decline</a></div>");
            
                       }
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
                               out.println("</div>");
                             }
            
           %>
           </div>  
   </div>  
        </div>
      
    </body>
</html>
