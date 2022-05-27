<%-- 
    Document   : cutBookingList
    Created on : 2022/4/4, 下午 12:22:41
    Author     : User
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="ict.bean.Trainer"%>
<%@page import="ict.db.TrainerDB"%>
<%@page import="ict.bean.Booking"%>
<%@page import="ict.db.BookingDB"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" />
        <title>Bookings Management</title>
        <style>
* {
  box-sizing: border-box;
}

#myInput {
  background-image: url('img/search.jpg');
  background-position: 10px 10px;
  background-size: 20px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

#myTable {
  border-collapse: collapse;
  width: 100%;
  border: 1px solid #ddd;
  font-size: 18px;
}

#myTable th, #myTable td {
  text-align: left;
  padding: 12px;
}

#myTable tr {
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  background-color: #f1f1f1;
}

.imgWrap:hover {
    background-color: #696969;
}
.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.button2 {background-color: #008CBA;} /* Blue */
.button4 {background-color: #e7e7e7; color: black;} /* Gray */ 
.button5 {background-color: #555555;} 
.button3 {background-color: #f44336;} /* Red */ 

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
 <script type="text/javascript" src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
       $(document).ready(function(){  
            $("#myInput").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
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
        <br/><br/><br/><br/><br/><br/><br/>
    <center><h1>Bookings</h1></center>
         
          <input type="text" id="myInput"  placeholder="Search for detail.." title="Type in a name">
          <center><button class="button" onclick="allBookings(this);" id="all">All Bookings</button><button id="com"  class="button button2" onclick="comBookings(this);">Confirmed Bookings</button><button onclick="proBookings(this);" class="button button4" id="pro">Processing Bookings</button><button class="button button3" onclick="delBookings(this);" id="del">Decline Bookings</button></center>
            <table id="myTable">
                <thead>
                   <tr class="header">
                     <th style="width:10%;">Booking ID</th>
                     <th style="width:10%;">Customer Email</th>
                     <th style="width:10%;">Customer Name</th>
                     <th style="width:10%;">Trainer</th>
                     <th style="width:20%;">Center Address</th>
                     <th style="width:10%;">Booking Date</th>
                     <th style="width:10%;">Amount</th>
                     <th style="width:10%;">State</th>
                     <th style="width:10%;">Option</th>
                   </tr>
                   </thead>
            <%
                TrainerDB trainerdb = new TrainerDB (dbUrl, dbUser, dbPassword);
                ArrayList userbookings = bookingdb.getCustomerBookings(user.getID());
                  if(userbookings.size() != 0){
                      out.println(" <tbody id='allBookings'>");
                      for(int i =0; i <userbookings.size();i++){
                          Booking booking = (Booking)userbookings.get(i);
                          out.println(" <tr id='allBookings'>");
                          out.println("<td>"+booking.getBookingID()+"</td>");
                          out.println("<td>"+ booking.getCustEmail()+"</td>" );
                          out.println("<td>"+ booking.getCustName()+"</td>" );
                          Trainer trainer = trainerdb.getTrainerById(booking.getTrainerID());
                          if(trainer==null){
                              out.println("<td>No Need</td>");
                          }else{
                              out.println("<td>"+ trainer.getName()+"</td>");
                          }
                          
                          out.println("<td>"+ booking.getCenterAddress()+"</td>");
                          out.println("<td>"+ booking.getDate()+"</td>");
                          out.println("<td>"+ booking.getAmount()+"</td>");
                          out.println("<td>"+ booking.getState()+"</td>");
                          out.println("<td><div class='table-data'><span class='imgWrap'><img src='img/del.jpg' onclick=\"del('"+booking.getBookingID()+"')\" width= \"30\" height=\"25\" ></span>");
                          if(booking.getState().equals("processing")){
                              out.println(" / <span class='imgWrap'><img src='img/update.jpg' onclick=\"update('"+booking.getBookingID()+"')\" width= \"30\" height=\"25\" ></span></div>");
                          }else{
                              out.println("</div></td>");
                          }
                          out.println("</tr>");
                      }
                      out.println(" </tbody>");
                      out.println(" <tbody id='comBookings'>");
                      for(int i =0; i <comBookings.size();i++){
                          Booking booking = (Booking)comBookings.get(i);
                          out.println(" <tr id='comBookings' style='display:none;'>");
                          out.println("<td>"+booking.getBookingID()+"</td>");
                          out.println("<td>"+ booking.getCustEmail()+"</td>" );
                          out.println("<td>"+ booking.getCustName()+"</td>" );
                           Trainer trainer = trainerdb.getTrainerById(booking.getTrainerID());
                          if(trainer==null){
                              out.println("<td>No Need</td>");
                          }else{
                              out.println("<td>"+ trainer.getName()+"</td>");
                          }
                          out.println("<td>"+ booking.getCenterAddress()+"</td>");
                          out.println("<td>"+ booking.getDate()+"</td>");
                          out.println("<td>"+ booking.getAmount()+"</td>");
                          out.println("<td>"+ booking.getState()+"</td>");
                          out.println("<td><div class='table-data'><span class='imgWrap'><img src='img/del.jpg' onclick=\"del('"+booking.getBookingID()+"')\" width= \"30\" height=\"25\" ></span>");
                          if(booking.getState().equals("processing")){
                              out.println(" / <span class='imgWrap'><img src='img/update.jpg' onclick=\"update('"+booking.getBookingID()+"')\" width= \"30\" height=\"25\" ></span></div>");
                          }else{
                              out.println("</div></td>");
                          }
                          out.println("</tr>");
                      }
                      out.println(" </tbody>");
                      
                      ArrayList progressBookings = bookingdb.getCusProBookingsById(user.getID());
                      for(int i =0; i <progressBookings.size();i++){
                          Booking booking = (Booking)progressBookings.get(i);
                          out.println(" <tr id='proBookings' style='display:none;'>");
                          out.println("<td>"+booking.getBookingID()+"</td>");
                          out.println("<td>"+ booking.getCustEmail()+"</td>" );
                          out.println("<td>"+ booking.getCustName()+"</td>" );
                           Trainer trainer = trainerdb.getTrainerById(booking.getTrainerID());
                          if(trainer==null){
                              out.println("<td>No Need</td>");
                          }else{
                              out.println("<td>"+ trainer.getName()+"</td>");
                          }
                          out.println("<td>"+ booking.getCenterAddress()+"</td>");
                          out.println("<td>"+ booking.getDate()+"</td>");
                          out.println("<td>"+ booking.getAmount()+"</td>");
                          out.println("<td>"+ booking.getState()+"</td>");
                          out.println("<td><div class='table-data'><span class='imgWrap'><img src='img/del.jpg' onclick=\"del('"+booking.getBookingID()+"')\" width= \"30\" height=\"25\" ></span>");
                          if(booking.getState().equals("processing")){
                              out.println(" / <span class='imgWrap'><img src='img/update.jpg' onclick=\"update('"+booking.getBookingID()+"')\" width= \"30\" height=\"25\" ></span></div>");
                          }else{
                              out.println("</div></td>");
                          }
                          out.println("</tr>");
                      }
                      ArrayList delBookings = bookingdb.getCusDelBookingsById(user.getID());
                      for(int i =0; i <delBookings.size();i++){
                          Booking booking = (Booking)delBookings.get(i);
                          out.println(" <tr id='delBookings' style='display:none;'>");
                          out.println("<td>"+booking.getBookingID()+"</td>");
                          out.println("<td>"+ booking.getCustEmail()+"</td>" );
                          out.println("<td>"+ booking.getCustName()+"</td>" );
                           Trainer trainer = trainerdb.getTrainerById(booking.getTrainerID());
                          if(trainer==null){
                              out.println("<td>No Need</td>");
                          }else{
                              out.println("<td>"+ trainer.getName()+"</td>");
                          }
                          out.println("<td>"+ booking.getCenterAddress()+"</td>");
                          out.println("<td>"+ booking.getDate()+"</td>");
                          out.println("<td>"+ booking.getAmount()+"</td>");
                          out.println("<td>"+ booking.getState()+"</td>");
                          out.println("<td><div class='table-data'><span class='imgWrap'><img src='img/del.jpg' onclick=\"del('"+booking.getBookingID()+"')\" width= \"30\" height=\"25\" ></span>");
                          if(booking.getState().equals("processing")){
                              out.println(" / <span class='imgWrap'><img src='img/update.jpg' onclick=\"update('"+booking.getBookingID()+"')\" width= \"30\" height=\"25\" ></span></div>");
                          }else{
                              out.println("</div></td>");
                          }
                          out.println("</tr>");
                      }
                    }
                  
               
              %>
             </table>
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
            function allBookings(button){
                button.className = "button button5";
                document.getElementById("com").className = 'button button2';
                document.getElementById("pro").className = 'button button4';
                document.getElementById("del").className = 'button button3';
                var bookings = document.querySelectorAll("#allBookings");
                for(let i = 0;i<bookings.length;i++){
                    bookings[i].style.display = "";
                }
                var comBookings = document.querySelectorAll("#comBookings");
                for(let i = 0;i<comBookings.length;i++){
                    comBookings[i].style.display = "none";
                }
                var proBookings = document.querySelectorAll("#proBookings");
                for(let i = 0;i<proBookings.length;i++){
                    proBookings[i].style.display = "none";
                }
                var delBookings = document.querySelectorAll("#delBookings");
                for(let i = 0;i<delBookings.length;i++){
                    delBookings[i].style.display = "none";
                }
            }
            function comBookings(button){
                button.className = "button button5";
                document.getElementById("all").className = 'button';
                document.getElementById("pro").className = 'button button4';
                document.getElementById("del").className = 'button button3';
                var bookings = document.querySelectorAll("#allBookings");
                for(let i = 0;i<bookings.length;i++){
                    bookings[i].style.display = "none";
                }
                var comBookings = document.querySelectorAll("#comBookings");
                for(let i = 0;i<comBookings.length;i++){
                    comBookings[i].style.display = "";
                }
                var proBookings = document.querySelectorAll("#proBookings");
                for(let i = 0;i<proBookings.length;i++){
                    proBookings[i].style.display = "none";
                }
                var delBookings = document.querySelectorAll("#delBookings");
                for(let i = 0;i<delBookings.length;i++){
                    delBookings[i].style.display = "none";
                }
            }
            function del(id){
              window.location.href = "BookingController?action=delete&bookingid=" + id;
            }
            function update(id){
              window.location.href = "BookingController?action=edit&bookingid=" + id;
            }
            function proBookings(button){
                button.className = "button button5";
                document.getElementById("all").className = 'button';
                document.getElementById("com").className = 'button button2';
                document.getElementById("del").className = 'button button3';
                var bookings = document.querySelectorAll("#allBookings");
                for(let i = 0;i<bookings.length;i++){
                    bookings[i].style.display = "none";
                }
                var comBookings = document.querySelectorAll("#comBookings");
                for(let i = 0;i<comBookings.length;i++){
                    comBookings[i].style.display = "none";
                }
                var proBookings = document.querySelectorAll("#proBookings");
                for(let i = 0;i<proBookings.length;i++){
                    proBookings[i].style.display = "";
                }
                var delBookings = document.querySelectorAll("#delBookings");
                for(let i = 0;i<delBookings.length;i++){
                    delBookings[i].style.display = "none";
                }
            }
             function delBookings(button){
                button.className = "button button5";
                document.getElementById("all").className = 'button';
                document.getElementById("com").className = 'button button2';
                document.getElementById("pro").className = 'button button4';
                var bookings = document.querySelectorAll("#allBookings");
                for(let i = 0;i<bookings.length;i++){
                    bookings[i].style.display = "none";
                }
                var comBookings = document.querySelectorAll("#comBookings");
                for(let i = 0;i<comBookings.length;i++){
                    comBookings[i].style.display = "none";
                }
                var proBookings = document.querySelectorAll("#proBookings");
                for(let i = 0;i<proBookings.length;i++){
                    proBookings[i].style.display = "none";
                }
                var delBookings = document.querySelectorAll("#delBookings");
                for(let i = 0;i<delBookings.length;i++){
                    delBookings[i].style.display = "";
                }
            }
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
            var Logout = document.getElementById("logout");
              Logout.addEventListener('click',logout);
            function logout(){
                window.location.href="login.jsp"; 
            }
        </script>
    </body>
</html>
