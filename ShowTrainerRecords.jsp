<%-- 
    Document   : ShowTrainerRecords
    Created on : 2022年4月4日, 下午03:51:08
    Author     : LCF
--%>

<%@page import="java.util.Base64"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.Trainer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h1>Booking Record of Trainers</h1>
       <jsp:useBean id="trainers" scope="request" class="ArrayList" />
        <div class="container">   
        <div class="table">
		<div class="table-header">
			<div class="header__item"><a id="name" class="filter__link" href="#">ID</a></div>
			<div class="header__item"><a id="wins" class="filter__link filter__link--number" >Img</a></div>
			<div class="header__item"><a id="draws" class="filter__link filter__link--number" >Name</a></div>
			<div class="header__item"><a id="losses" class="filter__link filter__link--number" >Description</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >Gender</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >State</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >Show Booking</a></div>
			              
		</div>
            <div class="table-content">
           <%
               if(trainers.size() != 0){
                   for(int i =0; i <trainers.size();i++){
                       Trainer psl = (Trainer)trainers.get(i);
                       
                       out.println("<div class='table-row'>");
                       out.println("<div class='table-data'>"+psl.getid()+"</div>");
                       if(psl.getImgs() == null){
                            out.println("<div class='table-data'>"+ " <img src='img/user.jpg'  width= \"60\" height=\"70\" >"+"</div>");
                       }else{
                        String encode = Base64.getEncoder().encodeToString(psl.getImgs());
                        out.println("<div class='table-data'>"+ " <img src='data:image/jpeg;base64, "+ encode + " '  width= \"100\" height=\"100\" >"+"</div>");
                       }
                       
                       out.println("<div class='table-data'>"+ psl.getName()+"</div>" );
                       out.println("<div class='table-data'>"+ psl.getDescription() +"</div>");
                       out.println("<div class='table-data'>"+ psl.getGender() +"</div>");
                       out.println("<div class='table-data'>"+ psl.getState() +"</div>");
                       out.println("<div class='table-data'><a href='ShowRecordController?action=showt&id=" + psl.getid() + "'>Show Booking</a></div>");
                       out.println("</div>");
                   }
                 }else{
                        out.println("No information!");
                  }
            
           %>
           </div>  
   </div>  
        </div>
    </body>
</html>
