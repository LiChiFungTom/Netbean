<%-- 
    Document   : seniorCreateAndDelete
    Created on : 2022/3/20, 上午 11:17:54
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/accountTable.css" />
        <title>Account Management</title>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <style>
            .imgWrap:hover {
                background-color: #696969;
            }
            
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
        <%@ include file="seniorMenu.jsp" %>
        <br/><br/><br/><br/><br/>
    <center><h1>Account</h1></center>
         <jsp:useBean id="users" scope="request" class="ArrayList" />
        <div class="container">
	
	<div class="table">
		<div class="table-header">
			<div class="header__item"><a id="name" class="filter__link" href="#">ID</a></div>
			<div class="header__item"><a id="wins" class="filter__link filter__link--number" >Email</a></div>
			<div class="header__item"><a id="draws" class="filter__link filter__link--number" >User Name</a></div>
			<div class="header__item"><a id="losses" class="filter__link filter__link--number" >Tel</a></div>
			<div class="header__item"><a id="total" class="filter__link filter__link--number" >State</a></div>
                        <div class="header__item"><a id="total" class="filter__link filter__link--number" >Password</a></div>
                        <div class="header__item"><a id="total" class="filter__link filter__link--number" >Delete/Edit</a></div>
		</div>
		<div class="table-content">
			 <%
                             if(users.size() != 0){
                                 for(int i = 0; i < users.size(); i++){
                               UserInfo user = (UserInfo)users.get(i);
                               out.println("<div class='table-row'>");
                               out.println("<div class='table-data'>"+user.getID()+"</div>");
                               out.println("<div class='table-data'>"+user.getEmail()+"</div>");
                               out.println("<div class='table-data'>"+user.getUsername()+"</div>");
                               out.println("<div class='table-data'>"+user.getTel()+"</div>");
                               out.println("<div class='table-data'>"+user.getState()+"</div>");
                               out.println("<div class='table-data'>"+user.getPassword()+"</div>");
                               out.println("<div class='table-data'><span class='imgWrap'><img src='img/del.jpg' onclick=\"del('"+user.getID()+"')\" width= \"30\" height=\"25\" ></span> / <span class='imgWrap'><img src='img/update.jpg' onclick=\"update('"+user.getID()+"')\" width= \"30\" height=\"25\" ></span></div>");
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
                               //out.println("<tr><td>" +user.getID() + "</td><td>"+user.getEmail()+"</td><td>"+user.getUsername()+"</td><td>"+user.getTel()+"</td><td>"+user.getState()+"</td><td>"+user.getPassword()+"</td><td><a href='DeleteController?ID="+user.getID()+"'>Delete</a></td></tr>");
                               out.println("</div>");
                             }
                           
                           
                           %>
         
			
		</div>	
	</div>
</div>
       
        
      
    <center>
         <button class="button" onclick="window.location.href='seniorCreate.jsp'">
             <spand class='button__text'>Create Account</spand>
             <spand class='button__icon'>
                    <ion-icon name="add-outline"></ion-icon>
             </spand>
         
         
         </button>
     </center>
      
      <script>
          function del(id){
              window.location.href = "UserController?action=delete&id=" + id;
          }
          function update(id){
              window.location.href = "UserController?action=edit&id=" + id;
          }
      </script>
    </body>
</html>
