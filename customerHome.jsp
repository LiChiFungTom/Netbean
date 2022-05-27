<%-- 
    Document   : customerHome
    Created on : 2022/3/20, 上午 11:12:08
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
        <link rel="stylesheet" href="css/main.css" />
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
.content{
    width: 100%;
    top: 40%;
    transform: translateY(-50%);
    text-align: center;
    position: absolute;
}

.content img{
    left : 35%;
    position: absolute;
}

.content h1{
    font-size: 60px;
    
}
.content p{
    margin: 20px auto;
    font-weight: 100;
    line-height: 25px;
}
button{
    width: 200px;
    padding: 15px 0;
    text-align: center;
    margin: 20px 10px;
    border-radius: 25px;
    font-weight: 25px;
    border: 2px solid #000000;
    background: transparent;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
#btn{
    background: #000000;
    height: 100%;
    width: 0;
    border-radius: 25px;
    position: absolute;
    left: 0;
    bottom: 0;
    z-index: -1;
    transition: 0.5s;
}
button:hover #btn {
    width: 100%;
}
button:hover {
   border: none;
   color: white;
}
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}

html {
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: inherit;
}

.column {
  float: left;
  width: 33.3%;
  margin-bottom: 16px;
  padding: 0 8px;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  margin: 8px;
}

.about-section {
  padding: 50px;
  text-align: center;
  background-color: #474e5d;
  color: white;
}

.container {
  padding: 0 16px;
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
}

.button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
}

.button:hover {
  background-color: #555;
}

@media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  }
        </style>
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
            </ul>
          </li>
          <li class="nav__listitem" id="logout">Logout</li>
        </ul>
      </nav>
              <br> <br><br><br>
              <div class="content">
                  <h1>BE THE BEST VERSION OF YOURSELF</h1>
       
                  <p>The Dream Gym Limited (DGL) is a fitness company. </br>
                      DGL has five gym centers located at Tuen Mun, 
                      Sha Tin, Tsing Yi, Lee Wai Lee, and Chai Wan in Hong Kong.</p>                
                         
                  <div>
                      <button type="button" onclick="window.location.href = 'cutBooking.jsp'"><span id="btn"></span>Booking Now</button>
                      <button type="button" onclick="window.location.href = 'cutBookingList.jsp'"><span id="btn"></span>My Booking</button>
                       
                  </div>
                  <div><img src="img/Fitness1.jpg"  weight="400" height="400" ></div>
              </div>
               
<br> <br> <br><br> <br><br><br> <br> <br><br> <br><br><br> <br> <br><br> <br><br><br> <br> <br><br> <br><br><br><br><br><br><br> <br><br><br> <br> <br><br> <br><br><br> <br> <br><br> <br><br><br> <br> 
       <div class="about-section">
           <h1>The Top of the Trainer</h1>
           <p>Some Trainer will display here</p>
        </div>
              
        <h2 style="text-align:center">Our Top Trainer</h2>
        <div class="row">
            <div class="column">
              <div class="card">
                   <img src="img/Trainer1.jpg" style="width:100%"/>
                   <div class="container">
                             <h2>Ho Man Chung</h2>
                             <p>Higher Diploma in Fitness, Coaching and Sports Management</p>
                   </div>
              </div>
            </div>
       
        
        <div class="column">
            <div class="card">
              <img src="img/train_01.jpg" style="width:75%"/>
              <div class="container">
                      <h2>LI Chi Fung</h2>
                      <p>Higher Diploma in Fitness, Coaching and Sports Management</p>
              </div>
            </div>
        </div>
        
        <div class="column">
            <div class="card">
                 <img src="img/trainer_02.jpg" style="width:75%"/>
       
            <div class="container">
                   <h2>Tom</h2>
                   <p>National Council on Strength & Fitness (NCSF) AND (CPT)</p>
            </div>
           </div>
        </div>
     </div>
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
            Logout.addEventListener('click',logout);
            
            
            var allTrainer = document.getElementById("trainer");
            allTrainer.addEventListener('click',trainers);
            function trainers(){
                window.location.href = "TrainerController?action=list";
            }
            var profile = document.getElementById("profile");
            profile.addEventListener('click',editpro);
            function editpro(){
                window.location.href = "custEditPro.jsp";
            }
            var center = document.getElementById("center");
            center.addEventListener('click',allcenter);
            function allcenter(){
                window.location.href = "GymVenueController?action=cutList";
            }
            reminder.addEventListener('click',calender);

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
            
            function logout(){
                window.location.href="login.jsp"; 
            }
            
        </script>
    </body>
</html>
