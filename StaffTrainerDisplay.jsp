<%-- 
    Document   : StaffTrainerDisplay
    Created on : 2022年4月30日, 下午03:11:24
    Author     : LCF
--%>

<%@page import="java.util.Base64"%>
<%@page import="ict.bean.Trainer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trainer</title>
        <link rel="stylesheet" href="css/accountTable.css" />
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        
        <style>
            
            .action{
                text-decoration: none;
            }
            
            .action:visited{
                color:#fff;
            }
            
            a{
                 text-decoration: none;
            }
            
            a:visited{
               color: blue;
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
        <%@ include file="StaffMenu.jsp" %>
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
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >Price</a></div>
                     <div class="header__item"><a id="losses" class="filter__link filter__link--number" >Option</a></div>
		</div>
            <div class="table-content">
           <%
               if(trainers.size() != 0){
                   for(int i =0; i <trainers.size();i++){
                       Trainer psl = (Trainer)trainers.get(i);          
                       out.println("<div class='table-row'>");
                       out.println("<div class='table-data'>"+psl.getid()+"</div>");
                       if(psl.getImgs() == null){   
                            out.println("<div class='table-data'>"+ " <img src='img/user.jpg'   width= \"100\" height=\"100\" >"+"</div>");
                       }else{   
                        String encode = Base64.getEncoder().encodeToString(psl.getImgs());
                        out.println("<div class='table-data'>"+ " <img src='data:image/jpeg;base64, "+ encode + " '   width= \"100\" height=\"100\" >"+"</div>");
                       }                      
                       out.println("<div class='table-data'>"+ psl.getName()+"</div>" );
                       out.println("<div class='table-data'>"+ psl.getDescription() +"</div>");
                       out.println("<div class='table-data'>"+ psl.getGender() +"</div>");
                       out.println("<div class='table-data'>"+ psl.getState() +"</div>");
                       out.println("<div class='table-data'>"+ psl.getPrice() +"</div>");
                    
                       out.println("<div class='table-data'><a href='PersonalTrainerController?action=edit&id=" + psl.getid() + "'>Edit</a>  / <a href='PersonalTrainerController?action=delete&id=" + psl.getid() + "'>Delete</a> </div>");
                     
                 
                       out.println("</div>");
                   }
                 }else{
                               out.println("<div class='table-row'>");
                               out.println("<div class='table-data'></div>");
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
         <button type='button' class="button">
             <spand class='button__text'><a href="PersonalTrainerController?action=add" class="action">Create</a></spand>
               <spand class='button__icon'>
                    <ion-icon name="add-outline"></ion-icon>
               </spand>
                      
           </button>
    </center>    
    </body>
</html>
