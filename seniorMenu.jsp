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
    <nav class="nav">
        <ul class="nav__list">
          <li class="nav__listlogo">
            <img src="img/southeast.jpg" height="100" width="100">
          </li>
          <li class="nav__listitem" id="home">Home</li>
          <li class="nav__listitem">Function
            <ul class="nav__listitemdrop">
              <li id="accountMan">Account Management</li>
              <li id="displayBookGym">Display Booking Gym center</li>
              <li id="trainerBookingIn">Trainer Booking Information</li>
              <li id="centerBookingIn">Center Booking Information</li>
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
        <script src="js/seniorPage.js"></script>
    </body>
</html>
