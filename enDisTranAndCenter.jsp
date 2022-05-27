<%-- 
    Document   : ManagementBookingList
    Created on : 2022年4月5日, 上午09:27:05
    Author     : LCF
--%>

<%@page import="java.util.Base64"%>
<%@page import="ict.bean.Trainer"%>
<%@page import="ict.bean.GymVenue"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enable/Disable List</title>
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
         <%@ include file="StaffMenu.jsp" %>
    <center><h1>Gym Center</h1></center>
        <jsp:useBean id="centers" scope="request" class="ArrayList" />
        <div class="container">   
        <div class="table">
		<div class="table-header">
			<div class="header__item"><a id="name" class="filter__link" href="#">ID</a></div>
			<div class="header__item"><a id="wins" class="filter__link filter__link--number" >Img</a></div>
			<div class="header__item"><a id="draws" class="filter__link filter__link--number" >Name</a></div>
			<div class="header__item"><a id="losses" class="filter__link filter__link--number" >Description</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >Address</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >Price</a></div>
                      <div class="header__item"><a id="losses" class="filter__link filter__link--number" >State</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >Enable/Disable</a></div>
		</div>
         <div class="table-content">
           <%
               if(centers.size() != 0){
                   for(int i =0; i <centers.size();i++){
                       GymVenue gyv = (GymVenue)centers.get(i);
                       String encode = Base64.getEncoder().encodeToString(gyv.getImgs());
                       out.println("<div class='table-row'>");
                       out.println("<div class='table-data'>"+gyv.getid()+"</div>");                     
                       out.println("<div class='table-data'>"+ " <img src='data:image/jpeg;base64, "+ encode + " '  width= \"100\" height=\"100\" >"+"</div>");
                       out.println("<div class='table-data'>"+ gyv.getName()+"</div>" );
                       out.println("<div class='table-data'>"+ gyv.getDescription() +"</div>");
                       out.println("<div class='table-data'>"+ gyv.getAddress() +"</div>");                                     
                       out.println("<div class='table-data'>"+ gyv.getPrice() +"</div>");      
                       out.println("<div class='table-data'>"+ gyv.getState() +"</div>");
                        if(gyv.getState().equals("opening")){
                           out.println("<div class='table-data'>"+" <a href='EDBookingListController?action=Enable&id="+gyv.getid()+"'>Disable</a> " +"</div>");
                       }else{
                           out.println("<div class='table-data'>"+" <a href='EDBookingListController?action=Disable&id="+gyv.getid()+ "'>Enable</a> " +"</div>");
                       }
                       
                       out.println("</div>");
                   }
                 }else{
                               out.println("<div class='table-row'>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'>No Information</div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("</div>");
                             }
            
           %>
           </div>  
   </div>  
        </div>
            
      <br/><br/><br/><br/>
      <center><h1>Trainer</h1></center>
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
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >Enable/Disable</a></div>
			              
		</div>
            <div class="table-content">
           <%
               if(trainers.size() != 0){
                   for(int i =0; i <trainers.size();i++){
                       Trainer psl = (Trainer)trainers.get(i);
                       String encode = Base64.getEncoder().encodeToString(psl.getImgs());
                       out.println("<div class='table-row'>");
                       out.println("<div class='table-data'>"+psl.getid()+"</div>");
                       out.println("<div class='table-data'>"+ " <img src='data:image/jpeg;base64, "+ encode + " '  width= \"100\" height=\"100\" >"+"</div>");
                       out.println("<div class='table-data'>"+ psl.getName()+"</div>" );
                       out.println("<div class='table-data'>"+ psl.getDescription() +"</div>");
                       out.println("<div class='table-data'>"+ psl.getGender() +"</div>");
                       out.println("<div class='table-data'>"+ psl.getState() +"</div>");                    
                       if(psl.getState().equals("On Work")){
                           out.println("<div class='table-data'>"+" <a href='EDBookingListController?action=TEnable&id="+psl.getid()+ "'>Enable</a> " +"</div>");
                       }else{
                          out.println("<div class='table-data'>"+" <a href='EDBookingListController?action=TDisable&id="+psl.getid()+"'>Disable</a> " +"</div>");
                       }
                      
                       out.println("</div>");
                   }
                 }else{
                               out.println("<div class='table-row'>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'>No Information</div>");
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
