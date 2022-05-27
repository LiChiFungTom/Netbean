  <%@page import="java.util.Base64"%>
<%-- 
    Document   : CenterCreate
    Created on : 2022年3月27日, 下午03:54:02
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Center</title>
        <link rel="stylesheet" href="css/Create.css">
        <style>
            textarea{
                resize: none; 
                
            }
        </style>
    </head>
    <body>
        
        <jsp:useBean id="center" scope="request" class="ict.bean.GymVenue" />
        
        <%
            String type = center.getid() != null ? "edit":"create";
            String id = center.getid() != null ? center.getid() : null;
            if(id != null && type.equals("edit")){
               out.println("<div class=\"container\">");
               out.println(" <div class=\"title\">Edit</div>");
               out.println("<form method='post' action='CreateCenterController' enctype='multipart/form-data' >");
               out.println("<input type='hidden' name='id' value='"+center.getid()+"'>");
               out.println("<input type='hidden' name='action' value='edit'>");
               out.println("<div class=\"user-details\">");
                                          
               if(center.getImgs() == null ){
                  out.println(" <div class=\"input-box\" > ");
                  out.println(" <span class=\"details\"> img : </span><input type='file' name='img' >");
                  out.println("</div>");             
               }else{
                  String encode = Base64.getEncoder().encodeToString(center.getImgs());
                  out.println(" <div class=\"input-box\" > ");
                  out.println(" <span class=\"details\"> <img src='data:image/jpeg;base64, "+ encode + " '   width= \"100\" height=\"100\" >");
                  out.println("</div>");
               }
               
               
               out.println(" <div class=\"input-box\" > ");
               out.println(" <span class=\"details\"> Name : </span> <input type='text' name='name' maxlength='20' size='20' value='"+center.getName()+"'> ");
               out.println("</div>");
               
               
               out.println(" <div class=\"input-box\" > ");
               out.println("<span class=\"details\"> Description : </span> <textarea id='textarea' name='description' rows='4' cols='40' >"+center.getDescription()+"</textarea> ");
               out.println("</div>");
                            
               out.println(" <div class=\"input-box\" > ");
               out.println("<span class=\"details\">  Address : </span> <input type='text' name='address'  maxlength='30' size='30' value='"+center.getAddress()+"' > ");
               out.println("</div>");
               
                out.println(" <div class=\"input-box\" > ");
               out.println("<span class=\"details\">  Price : </span> <input type='text' name='price'  maxlength='10' size='10' value='"+center.getPrice()+"' > ");
               out.println("</div>");
               
               out.println(" <div class=\"input-box\" > ");      
               out.println("State Type:");
               out.println("<select name='type' id='state'>");
               if(center.getState().equals("opening")){
                   out.println("<option value='opening' selected='selected'>opening</option>");
                   out.println("<option value='close'>close</option>");       
               }else{
                   out.println("<option value='close' selected='selected'>close</option>");
                   out.println("<option value='opening'>opening</option>");     
               }
               out.println("</select>");
               out.println("</div>");
               out.println("</div>");
              
               out.println("<div class=\"button\">");
               out.println("<input type='submit' value='Update'>");     
               out.println("</div>");
               out.println("</form>");
               out.println("</div>");
            }else{
        %>
 
        <div class="container">
        <div class="title">Add Center</div>
         <form method="post" action="CreateCenterController" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add" >
            <div class="user-details">
            <div class="input-box">                
                <span class="details">Img : </span>
                <input type="file" id="img" name="img">
             </div>
               <div class="input-box">  
                  <span class="details">Name :</span>
                  <input type="text" name="name" maxlength="10" size="15">                       
                </div>
                 <div class="input-box">  
                     <span class="details">Description :</span>
                     <textarea name="description" rows="4" cols="40"></textarea>
                         
                </div>   
                
                <div class="input-box">  
                  <span class="details">Address : </span>
                  <input type="text" name="address" >
                </div> 
                
                <div class="input-box">  
                  <span class="details">Price : </span>
                  <input type="text" name="price" >
                </div> 
                
                <div class="input-box">  
                    Center state: 
                    <select name="type" id="state">
                            <option value="opening">Opening</option>
                            <option value="close">Close</option>                       
                     </select> 
                    </div>
                </div>  
            <div class="button">
                    <input type="submit" value="Create">
             </div>
            </form>
          </div>      
         
         <%
                }
                %>
        
    </body>
</html>
