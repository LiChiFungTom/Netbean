<%-- 
    Document   : register
    Created on : 2022/3/19, 下午 03:35:03
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
         <link rel="stylesheet" href="css/register.css">
         <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    </head>
    <body>
        <div class="wrapper">
         <h2>Registration</h2>
         <form method="post" action="RegisterController" >
           
             <div class="input-box">
                 <input type="text" placeholder="Enter your email" name="email">
             </div>
             
             <div class="input-box">
                 <input type="text" placeholder="Enter your Username" name="username">
                
             </div>
             
             <div class="input-box">
                 <input type="text" placeholder="Enter your phone number" name="tel">
             </div>
             
             <div class="input-box">
                 <input type="text" placeholder="Enter your password" name="password">
             </div>
             
             <div class="input-box">
                 <input type="text" placeholder="Enter your confirm password" name="comPassword">
             </div>
             <div class="input-box button">
                 <input type="submit" value="Register">
             </div>
             
             <div class="text">
                <h3>Already have an account? <a href="login.jsp">Login now</a></h3>
             </div>
                </form>    
         </div>    
        
        
    </body>
</html>
