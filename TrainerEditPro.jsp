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
        %>
    <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session" />
    <center>
        <nav class="nav">
        <ul class="nav__list">
          <li class="nav__listlogo">
            <img src="img/southeast.jpg" height="100" width="100">
          </li>
          <li class="nav__listitem" id="Home">Home</li>
          <li class="nav__listitem">Function
            <ul class="nav__listitemdrop">
              <li id="bookingRequest" onclick="bRequest(<%= userInfo.getID()%>);">Booking Request</li>
              <li id="Venue">Gym Venue</li>
              <li id="Trainer">Personal Trainer</li>
              
            </ul>
          </li>
          <li class="nav__listitem">Account
            <ul class="nav__listitemdrop">
              <li id="pro">Profile</li>         
            </ul>
          </li>
          <li class="nav__listitem" id="logout">Logout</li>
        </ul>
           
      </nav>
              <br/><br/><br/><br/><br/><br/><br/><br/>
              
        <div class="container">
        <div class="title">Account Information</div>
        
       <form method="post" action="RegisterController" enctype="multipart/form-data">
            <input type="hidden" name="action" value="TrainerEdit">
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
    
     <script>
        
        var Por = document.getElementById("pro");
        Por.addEventListener('click' , file);
        
        var home = document.getElementById("Home");
        home.addEventListener('click',Home);
        
        var venue = document.getElementById("Venue");
        venue.addEventListener('click',Venue);
        
        var trainer = document.getElementById("Trainer");
        trainer.addEventListener('click',Trainer);
        
        var ed = document.getElementById("ED");
        ed.addEventListener('click',ED);
        
         var logout = document.getElementById("logout");
         logout.addEventListener('click',Logout);
        
        function Home(){
            window.location.href = "StaffHome.jsp";
        }
        
        function file(){
            window.location.href = "TrainerEditPro.jsp";
        }
        
        function bRequest(){
            window.location.href = "UserController?action=staffBookingRequest";
        }
        
        function Venue(){
            window.location.href = "GymVenueController?action=staffGymVenue";
        }
        
        function Trainer(){
             window.location.href ="PersonalTrainerController?action=staffTrainer";
        }
        
        function ED(){
             window.location.href ="EDBookingListController";
        }
        
        function Logout(){
             window.location.href ="login.jsp";
        }
        
        

    </script>
    
</html>
