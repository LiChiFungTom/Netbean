<%-- 
    Document   : cutReminder
    Created on : 2022/4/21, 下午 03:04:05
    Author     : User
--%>

<%@page import="ict.bean.GymVenue"%>
<%@page import="ict.db.GymCenterDB"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="ict.bean.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.db.BookingDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='css/fullcalendar.css' rel='stylesheet' />
        <link rel="stylesheet" href="css/main.css" />
        <script src='https://github.com/mozilla-comm/ical.js/releases/download/v1.4.0/ical.js'></script>
        <script src='js/fullcalendar.js'></script>
        
        <style>
            .badge {
                position: absolute;
                top: -10px;
                right: -10px;
                padding: 5px 10px;
                border-radius: 50%;
                background-color: red;
                color: white;
             }
        </style>
        
        
        <jsp:useBean id="user" class="ict.bean.UserInfo" scope="session" />
        <%
            ict.bean.UserInfo users = user; 
            session.setAttribute("user", users);
            String test = user.getID()+ " DAY";
            String dbUser = this.getServletContext().getInitParameter("dbUser");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            BookingDB bookingdb = new BookingDB (dbUrl, dbUser, dbPassword);
            GymCenterDB centerDB = new GymCenterDB (dbUrl, dbUser, dbPassword);
            ArrayList bookings = bookingdb.getCusComfBookingsById(user.getID());
            
        %>
           
        <script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth',
          navLinks: true,
          headerToolbar:{
              left:'prev,next today',
              center:'title',
              right:'dayGridMonth,timeGridWeek,timeGridDay'
          },
          events:[
              <%
            int check = bookings.size() - 1;
              for(int i = 0; i < bookings.size(); i ++){
                  Booking booking = (Booking)bookings.get(i);
                  GymVenue center = centerDB.queryGymcenter(booking.getCenterID());
                  String actualDate = booking.getDate();
                  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                  DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                  Format times = new SimpleDateFormat("HH:mm");
                  LocalDate ld = LocalDate.parse(actualDate, dtf);
                  Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(actualDate);  
                  String day = dtf2.format(ld);
                  String time = times.format(date);
                  //System.out.println(month_name);
                  if(check != i){
                      out.println("{"
                              + "title:'"+center.getName()+ "',"
                                      + "start:'"+ day+"T"+ time+"',"
                                              + "duration:'01:00',"
                                              + "},");
                  }else{
                      out.println("{"
                              + "title:'"+center.getName()+ "',"
                                      + "start:'"+ day+"T"+ time+"',"
                                              + "duration:'01:00',"
                                              + "}");
                  }
              }
              %> 
          ]
        });
        calendar.render();
      });

    </script>
    <style>
        #calendar{
            position: absolute;
            box-shadow: 10px 10px 20px gray;
            padding: 5px;
            height: 80%;
            width: 80%;
            top: 15%;
            left:10%;
        }
    </style>
    </head>
    <body>
        <%
          
            ArrayList comBookings = bookingdb.getCusComfBookingsById(user.getID());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            int haveBookings = 0;
            boolean have = false;
            for(int i = 0; i < comBookings.size();i++){
                Booking booking = (Booking)comBookings.get(i);
                if(booking.getDate().contains(date)){
                         haveBookings++;
                         have=true;
                 }
            }
        %>
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
    <center><div id='calendar'></div></center>
    </body>
    <script>
            var homeli = document.getElementById("home");
            var booking = document.getElementById("booking");
            var bookingList = document.getElementById("bookingList");
            var Logout = document.getElementById("logout");
            var reminder = document.getElementById("reminder");
            //Logout.addEventListener('click',l);
            bookingList.addEventListener('click',bookingLists);
            homeli.addEventListener('click',home);
            booking.addEventListener('click',bookings);
            reminder.addEventListener('click',calender);
            var allTrainer = document.getElementById("trainer");
            allTrainer.addEventListener('click',trainers);
            function trainers(){
                window.location.href = "TrainerController?action=list";
            }
            var center = document.getElementById("center");
            center.addEventListener('click',allcenter);
            function allcenter(){
                window.location.href = "GymVenueController?action=cutList";
            }
            function bookings(){
                window.location.href = "cutBooking.jsp";
            }

            function bookingLists(){
                window.location.href = "cutBookingList.jsp";
            }


            function home(){
                window.location.href = "customerHome.jsp";
            }

            function calender(){
                window.location.href="cutReminder.jsp"; 

            }
             var Logout = document.getElementById("logout");
              Logout.addEventListener('click',logout);
            function logout(){
                window.location.href="login.jsp"; 
            }
        </script>
</html>
