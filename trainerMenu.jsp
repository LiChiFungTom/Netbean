<%-- 
    Document   : main
    Created on : 2022/3/21, 上午 10:59:54
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" />
        <title>JSP Page</title>
    </head>
    <body>
         <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session" />
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
        <script src="js/trainerPage.js"></script>
       
        
        
        
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
              
         var logout = document.getElementById("logout");
         logout.addEventListener('click',Logout);
        
        
         function file(){
            window.location.href = "TrainerEditPro.jsp";
        }
        
         function Home(){
            window.location.href = "trainerHome.jsp";
        }
        
        
        function bRequest(ID){
            window.location.href = "UserController?action=trainerBookingRequest&TrainerID="+ID;
        }
        
        function Venue(){
            window.location.href = "GymVenueController";
        }
        
        function Trainer(){
             window.location.href ="PersonalTrainerController";
        }
        
    
        
        function Logout(){
             window.location.href ="login.jsp";
        }
        
        

    </script>
</html>
