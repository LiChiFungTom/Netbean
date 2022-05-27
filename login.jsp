<%-- 
    Document   : login
    Created on : 2022年3月14日, 上午11:16:33
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	  <link rel="stylesheet" href="css/register.css">
          <style>
              .input-box.button {
  color: #fff;
  letter-spacing: 1px;
  border: none;
  background: #4070f4;
  cursor: pointer;
}
          </style>
    </head>
    <body>
      <div class="wrapper">
         <h2>Login </h2>
                <form method="post" action="home">
                    <input type="hidden" name ="action" value="authenticate"/>
                     <div class="input-box">
                 <input type="text" name="username" placeholder="Enter your email"> 
             </div>
                     <div class="input-box">
                <input type="password" name="password" placeholder="Enter your password">
             </div>
                     <div class="input-box button">
                     <input type="submit" value="Login">
                 </div>
                     <div class="input-box button">
                          <input type="button" value="Register" onclick="window.location.href = 'register.jsp'" >
              
                    </div>
            </form>
             </div>
            

            
        
    </body>
</html>
