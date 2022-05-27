<%-- 
    Document   : seniorCreate
    Created on : 2022/3/20, 下午 02:26:25
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/Create.css">
    </head>
    <body>
        <jsp:useBean id="user" scope="request" class="ict.bean.UserInfo" />
        <jsp:useBean id="trainer" scope="request" class="ict.bean.Trainer" />
        <%
           String type = user.getID()+"" != null ? "edit":"create";
           int id = user.getID() != 0 ? user.getID() : 0;
           if(id != 0 && type.equals("edit")){
               out.println(" <div class='container'>"
                       + "<div class='title'>Edit Account</div>"
                       + "<form method='post' action='RegisterController' enctype='multipart/form-data'>");
               out.println("<input type='hidden' name='id' value='"+user.getID()+"'>");
               out.println("<input type='hidden' name='action' value='edit'>");
               out.println("<div class='user-details'>"
                       + "<div class='input-box'>");
               out.println("<span class='details'>Email : </span><input type='text' name='email'value='"+user.getEmail()+"' readonly>  "
                       + "</div> ");
               out.println("<div class='input-box'>");
               out.println("<span class='details'>User Name :</span> <input type='text' name='username' value='"+user.getUsername()+"'> </div> ");
                out.println("<div class='input-box'>");
               out.println("<span class='details'>Phone number :</span> <input type='text' name='tel' value='"+user.getTel()+"'> </div> ");
               out.println("<div class='input-box'>");
               out.println("<span class='details'>Password :</span> <input type='password' name='password' value='"+user.getPassword()+"'> </div> ");
               out.println("<div class='input-box'>");
               out.println("Account Type:");
               out.println("<select name='type' id='state' onchange='select(this);'>");
               if(user.getState().equals("customer")){
                   out.println("<option value='customer' selected='selected'>Customer</option>");
                   out.println("<option value='senior'>Senior</option>");
                   out.println("<option value='staff'>Staff</option>");
                   out.println("<option value='trainer'>Trainer</option>");
                   out.println("</select>");
                   out.println("</div>");
               }else if(user.getState().equals("senior")){
                   out.println("<option value='customer' >Customer</option>");
                   out.println("<option value='senior' selected='selected' >Senior</option>");
                   out.println("<option value='staff'>Staff</option>");
                   out.println("<option value='trainer'>Trainer</option>");
                   out.println("</select></div>");
               }else if(user.getState().equals("staff")){
                   out.println("<option value='customer' >Customer</option>");
                   out.println("<option value='senior'  >Senior</option>");
                   out.println("<option value='staff' selected='selected'>Staff</option>");
                   out.println("<option value='trainer'>Trainer</option>");
                   out.println("</select> </div>");
               }else if(user.getState().equals("trainer")){
                   out.println("<option value='customer' >Customer</option>");
                   out.println("<option value='senior'  >Senior</option>");
                   out.println("<option value='staff' >Staff</option>");
                   out.println("<option value='trainer' selected='selected'>Trainer</option>");
                   out.println("</select> </div>");
                }
                out.println("</div>");
               if(user.getState().equals("trainer")){
                   out.println("<div id='trainerInfo'>");
                   out.println("<div class='user-details'>");
                   out.println("<div class='input-box'>"
                           + "<span class='details'>Trainer Full Name :</span>  <input type='text' name='trainName'  value='"+trainer.getName()+"'>  </div> ");
                   out.println("<div class='input-box'>");
                   out.println("<span class='details'>Trainer Description :</span>  <textarea id='textarea' name='trainDes' rows='4' cols='40' >"+trainer.getDescription()+"</textarea> </div> ");
                   out.println("<div class='input-box'>");
                   out.println("Trainer Gender : ");
                   out.println("<select name='gender' id='gender'>");
                   if(trainer.getGender().equals("man")){
                       out.println("<option value='man' selected='selected'>Man</option>");
                       out.println("<option value='female' >Female</option>");
                   }else{
                       out.println("<option value='man' >Man</option>");
                       out.println("<option value='female' selected='selected'>Female</option>");
                   }
                   out.println("</select> </div></div>");
               }else{
                   out.println("<div id='trainerInfo' style='display: none;'>");
                   out.println("<div class='user-details'>");
                   out.println("<div class='input-box'>"
                           + "<span class='details'>Trainer Full Name :</span>  <input type='text' name='trainName'  >  </div> ");
                   out.println("<div class='input-box'>");
                   out.println("<span class='details'>Trainer Description :</span>  <textarea id='textarea' name='trainDes' rows='4' cols='40' ></textarea> </div> ");
                   out.println("<div class='input-box'>");
                   out.println("Trainer Gender : ");
                   out.println("<select name='gender' id='gender'>");

                       out.println("<option value='man' >Man</option>");
                       out.println("<option value='female' >Female</option>");
                   out.println("</select> </div></div>");
               }
                
                  
               out.println("</div> <div class=button><input type='submit' value='Update'></form>"
                       + "</div>");
               out.println("</div>");
               
                out.println("<script type='text/javascript'>");
                   out.println(" function select(type) {");
                       out.println(" if (type.value == 'trainer') { ");
                         out.println(" document.getElementById('trainerInfo').style.display = 'block';");
                       out.println("  }else{ ");
                         out.println("document.getElementById('trainerInfo').style.display = 'none'; ");
                       out.println("  }} </script>");
           }else{
         %>
         <div class="container">
        <div class="title">Create Account</div>
       <form method="post" action="RegisterController" enctype="multipart/form-data">
            <input type="hidden" name="action" value="create">
            <div class="user-details">
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
                    Account Type:
                    <select name="type" id="state" onchange="select(this);">
                            <option value="customer">Customer</option>
                            <option value="senior">Senior</option>
                            <option value="staff">Staff</option>
                            <option value="trainer">Trainer</option>
                          </select> 
           
           </div>
                <div class="input-box">  
                   <span class="details">Password : </span>
                  <input type="password" name="password"  >                   
                </div>
                <div class="input-box">  
                   <span class="details">Confirm password : </span>
                  <input type="password" name="comPassword"  >                 
                </div>
               </div>  
          
            <div id="trainerInfo" style="display: none;">
    <div class="user-details">
            <div class="input-box">  
                   <span class="details">Trainer Full Name :</span>
                  <input type="text" name="trainName" >               
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
                  <textarea id="textarea" name="trainDes" rows="4" cols="50"></textarea>
           </div>
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
            
                <script type="text/javascript">
                    function select(type) {
                        if (type.value == "trainer") {
                            document.getElementById('trainerInfo').style.display = "block";
                        }else{
                            document.getElementById('trainerInfo').style.display = "none";
                        }
                    }
                    
                </script>
                
    </body>
</html>
