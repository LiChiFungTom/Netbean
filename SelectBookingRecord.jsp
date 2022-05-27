<%-- 
    Document   : SelectBookingRecord
    Created on : 2022年3月30日, 下午08:41:28
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Booking Recorde</title>
         <link rel="stylesheet" href="css/Create.css">
    </head>
    <body>
        <div class="container">
        <div class="title">Select a Booking Recorde</div>
        <form method="post" action="ShowRecordController">
            
            <div class="input-box">  
                    Booking Records: 
                    <select name="type" id="Records">
                            <option value="Center" >Gym Center</option>
                            <option value="Trainer">Personal Trainer</option>                       
                     </select> 
           </div>
            
              <div class="button">
                    <input type="submit" value="Show">
             </div>
            
        </form>    
        </div>
      
    </body>
</html>
