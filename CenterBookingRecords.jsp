<%-- 
    Document   : BookingRecords
    Created on : 2022年3月30日, 下午04:54:49
    Author     : LCF
--%>

<%@page import="java.util.Base64"%>
<%@page import="ict.bean.GymVenue"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Records</title>
        <link rel="stylesheet" href="css/accountTable.css" />
                 <style>
           a{
                 text-decoration: none;
            }
            
            a:visited{
               color: blue;
            }
    </style>
    </head>
    <body>
        
         <%@ include file="seniorMenu.jsp" %>
        <br/><br/><br/><br/><br/><br/>
        <h1>Booking Record of Gym Venue</h1>
        <jsp:useBean id="center" scope="request" class="ArrayList" />
        <div class="container">   
        <div class="table">
		<div class="table-header">
			<div class="header__item"><a id="name" class="filter__link" href="#">ID</a></div>
			<div class="header__item"><a id="wins" class="filter__link filter__link--number" >Img</a></div>
			<div class="header__item"><a id="draws" class="filter__link filter__link--number" >Name</a></div>
			<div class="header__item"><a id="losses" class="filter__link filter__link--number" >Description</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >Address</a></div>
			<div class="header__item"><a id="total" class="filter__link filter__link--number" >State</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >Price</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >Price</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >Show Booking</a></div>
		</div>
         <div class="table-content">
           <%
               if(center.size() != 0){
                   for(int i =0; i <center.size();i++){
                       GymVenue gyv = (GymVenue)center.get(i);
                       out.println("<div class='table-row'>");
                       out.println("<div class='table-data'>"+gyv.getid()+"</div>");   
                       if(gyv.getImgs() == null){
                            out.println("<div class='table-data'>"+ " <img src='img/user.jpg'  width= \"60\" height=\"70\" >"+"</div>");
                       }else{
                        String encode = Base64.getEncoder().encodeToString(gyv.getImgs());
                        out.println("<div class='table-data'><img src='data:image/jpeg;base64, "+ encode + " '  width= \"80\" height=\"70\" >"+"</div>");
                       }
                       out.println("<div class='table-data'>"+ gyv.getName()+"</div>" );
                       out.println("<div class='table-data'>"+ gyv.getDescription() +"</div>");
                       out.println("<div class='table-data'>"+ gyv.getAddress() +"</div>");
                       out.println("<div class='table-data'>"+ gyv.getState() +"</div>");
                       out.println("<div class='table-data'>"+ gyv.getPrice() +"</div>");
                       
                       out.println("<div class='table-data'><a href='ShowRecordController?action=show&id=" + gyv.getid() + "'>Show Booking</a></div>");
                       out.println("</div>");
                   }
                 }else{
                               out.println("<div class='table-row'>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'>No Account</div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("</div>");
                             }
            
           %>
           </div>  
   </div>  
        </div>
      
    </body>
</html>
