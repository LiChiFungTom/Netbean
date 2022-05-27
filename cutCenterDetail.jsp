<%-- 
    Document   : cutCenterDetail
    Created on : 2022/4/11, 上午 10:49:47
    Author     : User
--%>

<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel = "stylesheet" href = "css/centerDetail.css">
    </head>
    <body>
         <jsp:useBean id="center" class="ict.bean.GymVenue" scope="request" />
        <section id = "product-sec-1">
        <div class = "container">
            <div class = "product-left">
                <%
                    String encode = Base64.getEncoder().encodeToString(center.getImgs());
                    out.println("<img src='data:image/jpeg;base64, "+ encode + " '  width= \"500\" height=\"300\" >");
                    %>
            </div>
            <div class = "product-right">
                <span class = "logo-img">
                    <img src="img/southeast.jpg" height="100" width="100">
                </span>
                <h2><%= center.getName()%></h2>
                <span class = "text"><%= center.getDescription()%></span>
                <div class = "btns-group">
                    <a href = "#" class = "btn-link"><%= center.getAddress()%>
                    </a>
                </div>
            </div>
        </div>
    </section>
    </body>
</html>
