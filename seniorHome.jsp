<%-- 
    Document   : seniorHome
    Created on : 2022/3/20, 上午 11:07:20
    Author     : User
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>senior</title>
         <link rel="stylesheet" href="css/main.css" />
         <style>
             .topbar{
    
    width: 100%;
    justify-content: space-between;
    display: flex;
    align-items: center;
    padding: 0 10px;
}


.toggle{
    position: relative;
    width: 60px;
    height: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2.5em;
    cursor: pointer;
}



.cardBox{
    position: relative;
    width: 100%;
    padding: 20px;
    display: grid;
    grid-template-columns: repeat(4,1fr);
 
}

.cardBox .card{
    position: relative;
    background: var(--white);
    padding: 30px;
    box-shadow: 0 7px 25px rgba(0,0, 0, 0.08);
    justify-content: space-between;
    display: flex;
    border-radius: 20px;
    
}
.cardBox .card .Name,
.cardBox .card .classNumber
{
    position: relative;
    font-weight: 500;
    font-size: 2.5m;
    color: var(--blue);
}


.className
{
    position: absolute;

    width: 200px;

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
  padding: 5px 16px;
  margin: 5px ;
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
}



    </style>
    </head>
    <body>
        <%
            ict.bean.UserInfo user = (ict.bean.UserInfo)session.getAttribute("userInfo"); 
            session.setAttribute("user", user);
            ict.bean.UserInfo users = user; 
            session.setAttribute("user", users);
            %>
        <%@ include file="seniorMenu.jsp" %>
        
        <br> <br><br> <br><br> <br><br><br> <br>
      
 
        <div class="cardBox">

            <div class="card">
                <div class="Name">Senior Name :</div>     
            </div>

            <div class="card">
                <div class="Name"><%= user.getUsername()%></div>     
            </div>

            <div class="card">
                <div class="Name">Senior Number :</div>     
            </div>
            
            <div class="card">
                <div class="classNumber"><%= user.getID()%></div>
            </div>
            <div class="card">
                <div class="Name"><img src='img/calendar.jpg'  width= "30" height="20" >Date</div>     
            </div>
                <%
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    %>
            <div class="card">
                <div class="classNumber"><%= LocalDate.now().format(formatter)%></div>
            </div>
        </div>     
            <br><br>
            <h2 style="text-align:center">Functions</h2>
<div class="row">
  <div class="column">
    <div class="card">
        <p style="text-align:center"><img src="img/account.jpg" alt="Jane" width= "100" height="100"  ></p>
      <div class="container">
        <h2>Account Management</h2>
        <p class="title">Management account information</p>
        <p><button class="button" onclick="window.location.href = 'UserController'">Go</button></p>
      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
        <p style="text-align:center"><img src="img/letter.jpg" alt="Jane" width= "100" height="100"  ></p>
      <div class="container">
        <h2>Booking Records</h2>
        <p class="title">Display Trainer/Gym Center booking records</p>
        <p><button class="button" onclick="window.location.href = 'SelectBookingRecord.jsp'">Go</button></p>
      </div>
    </div>
  </div>
      <div class="column">
    <div class="card">
        <p style="text-align:center"><img src="img/detail.jpg" alt="Jane" width= "100" height="100"  ></p>
      <div class="container">
        <h2>Trainer Booking Information</h2>
        <p class="title">Display Trainer Bookings Report</p>
        <p><button class="button" onclick="window.location.href = 'PersonalTrainerController?action=seniorRate'">Go</button></p>
      </div>
    </div>
  </div>

   <div class="column">
    <div class="card">
        <p style="text-align:center"><img src="img/detail.jpg" alt="Jane" width= "100" height="100"  ></p>
      <div class="container">
        <h2>Gym Center Booking Information</h2>
        <p class="title">Display Gym Center Bookings Report</p>
        <p><button class="button" onclick="window.location.href = 'GymVenueController?action=seniorRateCen'">Go</button></p>
      </div>
    </div>
  </div>
    </body>
</html>
