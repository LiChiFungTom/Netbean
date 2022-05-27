<%-- 
    Document   : StaffHome
    Created on : 2022年4月30日, 下午03:14:17
    Author     : LCF
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="ict.bean.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/main.css" />
      
    </head>
    <body>
        
       
        <% 
            UserInfo userinfo = (UserInfo)session.getAttribute("userInfo");
            if(userinfo == null){
                 RequestDispatcher rd;
                 rd = getServletContext().getRequestDispatcher("/login.jsp" );
                 rd.forward(request,response);
            }


        %>
     
        <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session" />
        <nav class="nav">
        <ul class="nav__list">
          <li class="nav__listlogo">
            <img src="img/southeast.jpg" height="100" width="100">
          </li>
          <li class="nav__listitem" id="Home">Home</li>
          <li class="nav__listitem">Function
            <ul class="nav__listitemdrop">
              <li id="bookingRequest" onclick="bRequest();">Booking Request</li>
              <li id="Venue">Gym Venue</li>
              <li id="Trainer">Personal Trainer</li>
              <li id="ED">Enable/Disable list of booking system</li>
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
        <br/><br/><br/><br/><br/>
        
        
        
        
        
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
            window.location.href = "staffEditPro.jsp";
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
