<%-- 
    Document   : CreateTrainer
    Created on : 2022年3月28日, 下午09:42:18
    Author     : LCF
--%>

<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trainer</title>
        <link rel="stylesheet" href="css/Create.css">
        <style>
            textarea{
                resize: none; 
                
            }
                    
        </style>
    </head>
    <body>
       
        <jsp:useBean id="trainer" scope="request"   class="ict.bean.Trainer"/>
        
        <%
            String type = trainer.getid()+"" != null ? "edit":"create";
            int id = trainer.getid() != 0 ? trainer.getid() : 0;
            if(id != 0 && type.equals("edit")){
               out.println("<div class=\"container\">");
               out.println(" <div class=\"title\">Edit</div>");
               out.println("<form method='post' action='TrainerController' enctype='multipart/form-data' >");
               out.println("<input type='hidden' name='id' value='"+trainer.getid()+"'>");
               out.println("<input type='hidden' name='action' value='edit'>");     
               out.println("<div class=\"user-details\">");
                                  
               
               if(trainer.getImgs() == null ){
                  out.println(" <div class=\"input-box\" > ");
                  out.println(" <span class=\"details\"> img : </span><input type='file' name='img' >");
                  out.println("</div>");             
               }else{
                  String encode = Base64.getEncoder().encodeToString(trainer.getImgs());
                  out.println(" <div class=\"input-box\" > ");
                  out.println(" <span class=\"details\"> <img src='data:image/jpeg;base64, "+ encode + " '   width= \"100\" height=\"100\" >");
                  out.println("</div>");
               }
               
           
               
               out.println(" <div class=\"input-box\" > ");
               out.println("<span class=\"details\">Name :</span> <input type='text' name='name' maxlength='20' size='20' value='"+trainer.getName()+"'> ");
               out.println("</div>");
               
               out.println(" <div class=\"input-box\" > ");
               out.println("<span class=\"details\"> Price : </span>  <input type='text' name='price' value='"+trainer.getPrice()+"'>    </td> ");
               out.println("</div>");  
              
               out.println(" <div class=\"input-box\" > ");
               out.println("<span class=\"details\"> Description : </span> <textarea id='textarea' name='description' rows='4' cols='40' >"+trainer.getDescription()+"</textarea>  </td> ");
               out.println("</div>");
               
               
               out.println("</div>");
               
              
               
               
               out.println(" <div class=\"input-box\" > ");
               out.println("Gender : ");
               
               out.println("<select name='type' id='gender'>");
               if(trainer.getGender().equals("man")){
                   out.println("<option value='man' selected='selected'>Male</option>");
                   out.println("<option value='female'>Female</option>");       
               }else{
                   out.println("<option value='man'>Male</option>");
                   out.println("<option value='female' selected='selected' >Female</option>");     
               }            
               out.println("</select>");
               
               out.println("State : ");
               
               out.println("<select name='tstate' id='state'>");
               if(trainer.getState().equals("On Work")){
                   out.println("<option value='on work' selected='selected'>On Work</option>");
                   out.println("<option value='free'>Free</option>");       
               }else{
                   out.println("<option value='on work'>On Work</option>");
                   out.println("<option value='free' selected='selected' >Free</option>");     
               }            
               out.println("</select>");
               out.println("</div>"); 
               
               out.println("<div class=\"button\">");
               out.println("<input type='submit' value='Update'>");     
               out.println("</div>");
               out.println("</form>");
               out.println("</div>");
            }else{
        %>
 
        <div class="container">
        <div class="title">Add Trainer</div>
        <form method="post" action="TrainerController" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add">
            <div class="user-details">
                
                
            <div class="input-box">                
                <span class="details">Img : </span>
                <input type="file" id="img" name="img" accept="image/*">                        
             </div>
                                            
           
              <div class="input-box">                
                <span class="details">Email : </span>
                <input type="text" name="email" >                     
             </div>
                
               <div class="input-box">  
                   <span class="details">User Name :</span>
                  <input type="text" name="username" >                       
                </div>
                
                <div class="input-box">  
                   <span class="details">Phone number : </span>
                  <input type="text" name="tel" maxlength="8" size="15">                    
                </div>
                
                <div class="input-box">  
                   <span class="details">Password : </span>
                  <input type="password" name="password"  >                   
                </div>
                
                <div class="input-box">  
                   <span class="details">Confirm password : </span>
                  <input type="password" name="comPassword"  >                 
                </div>
                
            <div class="input-box">  
                   <span class="details">Trainer Full Name :</span>
                  <input type="text" name="trainName" >               
                </div>
            
              <div class="input-box">  
                  <span class="details">Price</span>
                  <input type="text" name="price" >               
             </div>
             
             <div class="input-box">  
                  State :
                  <select name="state">
                            <option value="work">Work</option>
                            <option value="Free">Free</option>                       
                  </select>             
             </div>    
                               
                
              <div class="input-box">  
                    Trainer Gender : 
                    <select name="gender" id="gender" >
                            <option value="man">Man</option>
                            <option value="female">Female</option>
                    </select> 
           
           </div>
             <div class="input-box">  
                  <span class="details">Trainer Description :</span>
                  <textarea name="description" rows="4" cols="50"></textarea>
            </div>
       
                               
            </div>
            
          
            
            
             <div class="button">
                 <input type="submit"  value="Create">
             </div>
            
 
            </form>
          </div>      
         
         <%
                }
                %>
        
        
        
    </body>
</html>
