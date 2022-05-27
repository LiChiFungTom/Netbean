<%-- 
    Document   : GymCenter
    Created on : 2022年3月26日, 下午02:11:42
    Author     : LCF
--%>

<%@page import="ict.bean.Booking"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="ict.db.BookingDB"%>
<%@page import="java.util.Base64"%>
<%@page import="ict.bean.GymVenue"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gym Center List</title>
        <link rel="stylesheet" href="css/accountTable.css" />
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <style>  
              button{
                display: inline-flex;
                height: 30px;
                background: #009578;
                border: none;
                outline: none;
                border-radius: 5px;
                overflow: hidden;
                font-family: 'Quicksand', sans-serif;
                font-size: 16px;
                font-weight: 500;
                cursor: pointer;
              
            }
            
            button:hover{
                background: #008168;
            }
            
            button:active{
                background: #006e58;
            }
            
           .button__text,
           .button__icon{
               display: inline-flex;
               align-items: center;
               padding: 0 24px;
               color: #fff;
               height: 100%;
           }
           
           .button__icon{
               font-size:1.5em;
               background: rgb(0,0,0,0.08);
           }
            
        </style>
    </head>
    <body>
      
        <h1>Gym Venue</h1>
        <jsp:useBean id="centers" scope="request" class="ArrayList" />
        <div class="container">   
        <div class="table">
		<div class="table-header">

			<div class="header__item"><a id="wins" class="filter__link filter__link--number" >Img</a></div>
			<div class="header__item"><a id="draws" class="filter__link filter__link--number" >Name</a></div>
			<div class="header__item"><a id="losses" class="filter__link filter__link--number" >Description</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >Address</a></div>
			<div class="header__item"><a id="total" class="filter__link filter__link--number" >State</a></div>
                     <div class="header__item"><a id="total" class="filter__link filter__link--number" >Price</a></div>
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
                       out.println("<div class='table-data'>"+ gyv.getState() +"</div>");
                       out.println("<div class='table-data'>"+ gyv.getPrice() +"</div>");
                       out.println("</div>");
                   }
                 }else{
                               out.println("<div class='table-row'>");

                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'>No Information!</div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("<div class='table-data'></div>");
                               out.println("</div>");
                             }
            
           %>
           </div>  
   </div>  
        </div>
    <center>
            <button class="button" onclick="window.location.href='customerHome.jsp'">
             <spand class='button__text'>Back To Home Page</spand>
             <spand class='button__icon'>
                    <ion-icon name="add-outline"></ion-icon>
             </spand>
    </center>
         
         </button>
           
           
    </body>
</html>
