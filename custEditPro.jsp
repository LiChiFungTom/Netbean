<%-- 
    Document   : custEditPro
    Created on : 2022/4/27, 上午 11:23:23
    Author     : User
--%>

<%@page import="ict.bean.Booking"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.db.BookingDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/Create_1.css">
         <link rel="stylesheet" href="css/main.css" />
    </head>
    <body>
         <%
            ict.bean.UserInfo user = (ict.bean.UserInfo)session.getAttribute("userInfo"); 
            session.setAttribute("user", user);
            ict.bean.UserInfo users = user; 
            session.setAttribute("user", users);
            String dbUser = this.getServletContext().getInitParameter("dbUser");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            BookingDB bookingdb = new BookingDB (dbUrl, dbUser, dbPassword);
            ArrayList bookings = bookingdb.getCusComfBookingsById(user.getID());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            int haveBookings = 0;
            boolean have = false;
            for(int i = 0; i < bookings.size();i++){
                Booking booking = (Booking)bookings.get(i);
                if(booking.getDate().contains(date)){
                         haveBookings++;
                         have=true;
                 }
            }
        %>
    <center>
        <nav class="nav">
        <ul class="nav__list">
          <li class="nav__listlogo">
            <img src="img/southeast.jpg" height="100" width="100">
          </li>
          <li class="nav__listitem" id="home">Home</li>
          
          <li class="nav__listitem">Function
              <% 
                if(have){
                    out.println("<span class='badge'>!</span>");
                }
              %> 
            <ul class="nav__listitemdrop">
                <li id="reminder">Reminder <% if(have){
                    out.println("<span class='badge'>"+haveBookings+"</span>");
                }%>
              <li id="booking">Booking GYM Center</li>
              <li id="bookingList">Booking List</li>
              <li id="center">GYM Centers</li>
              <li id="trainer">Our Trainer</li>
              
            </ul>
          </li>
          <li class="nav__listitem">Account
            <ul class="nav__listitemdrop">
              <li id="profile">Profile</li>
              <li id="password">Reset Password</li>
            </ul>
          </li>
          <li class="nav__listitem" id="logout">Logout</li>
        </ul>
      </nav>
              <br/><br/><br/><br/><br/><br/><br/><br/>
        <div class="container">
        <div class="title">Account Information</div>
        
       <form method="post" action="RegisterController" enctype="multipart/form-data">
            <input type="hidden" name="action" value="custEdit">
            <input type='hidden' name='id' value="<%= user.getID()%>">
            <div class="user-details">
            <div class="input-box">                
                <span class="details">Email : </span>
                <input type="text" name="email" value="<%= user.getEmail()%>" readonly>                     
             </div>
                
               <div class="input-box">  
                   <span class="details">User Name :</span>
                  <input type="text" name="username" value="<%= user.getUsername()%>">                       
                </div>
                <div class="input-box">  
                   <span class="details">Phone number : </span>
                  <input type="text" name="tel" maxlength="8" size="15" value="<%= user.getTel()%>">                    
                </div>
                  
                <div class="input-box">  
                   <span class="details">Password : </span>
                  <input type="password" name="password"  value="<%= user.getPassword()%>">                   
                </div>
                
               </div>  
          
            <div class="button">
                    <input type="submit"  value="Update">
             </div>
            </form>
          </div>    
                </center>
    </body>
</html>
