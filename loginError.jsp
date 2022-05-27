<%-- 
    Document   : loginError
    Created on : 2022年3月14日, 上午11:34:05
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <p>Incorrect Password</p>
        <p>
            <%
                out.println(" <a href=\" "+ request.getContextPath()+ "/home\"> Login again </a>");
            %>
            
        </p>
    </body>
</html>
