<%-- 
    Document   : seniorTrainerRate
    Created on : 2022/4/20, 下午 05:34:45
    Author     : User
--%>

<%@page import="ict.bean.GymVenue"%>
<%@page import="ict.bean.Trainer"%>
<%@page import="ict.bean.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        * {
          box-sizing: border-box;
        }

        #myInput {
          background-image: url('img/search.jpg');
          background-position: 10px 10px;
          background-size: 20px;
          background-repeat: no-repeat;
          width: 100%;
          font-size: 16px;
          padding: 12px 20px 12px 40px;
          border: 1px solid #ddd;
          margin-bottom: 12px;
        }

        #myTable {
          border-collapse: collapse;
          width: 100%;
          border: 1px solid #ddd;
          font-size: 18px;
        }

        #myTable th, #myTable td {
          text-align: left;
          padding: 12px;
        }

        #myTable tr {
          border-bottom: 1px solid #ddd;
        }

        #myTable tr.header, #myTable tr:hover {
          background-color: #f1f1f1;
        }


.topbar{
    
    width: 100%;
    justify-content: space-between;
    display: flex;
    align-items: center;
    padding: 0 10px;
}


.toggle{
    position: relative;
    width: 60px;
    height: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2.5em;
    cursor: pointer;
}



.cardBox{
    position: relative;
    width: 100%;
    padding: 20px;
    display: grid;
    grid-template-columns: repeat(4,1fr);
 
}

.cardBox .card{
    position: relative;
    background: var(--white);
    padding: 30px;
    box-shadow: 0 7px 25px rgba(0,0, 0, 0.08);
    justify-content: space-between;
    display: flex;
    border-radius: 20px;
    
}
.cardBox .card .Name,
.cardBox .card .classNumber
{
    position: relative;
    font-weight: 500;
    font-size: 2.5m;
    color: var(--blue);
}


.className
{
    position: absolute;

    width: 200px;

}







    </style>
    <script type="text/javascript" src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
       $(document).ready(function(){  
            $("#myInput").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
    <body>
        <%@ taglib uri="/WEB-INF/tlds/income.tld" prefix="ict" %>
        <%
            ArrayList bookings = (ArrayList)session.getAttribute("bookings");
            ArrayList centerBookings = (ArrayList)session.getAttribute("centerBookings");
            String date = session.getAttribute("date")+"";
            
            int size = 0;
            if(centerBookings == null){
                Trainer trainer = (Trainer)session.getAttribute("trainer");
                int income = trainer.getPrice();
                size = bookings.size();
            %>
        <div class="topbar">
        </div>
 
        <div class="cardBox">

            <div class="card">
                <div class="Name">Trainer Name :</div>     
            </div>

            <div class="card">
                <div class="Name"><%= trainer.getName()%></div>     
            </div>

            <div class="card">
                <div class="Name">Trainer Number :</div>     
            </div>
            
            <div class="card">
                <div class="classNumber"><%= trainer.getid()%></div>
            </div>
            <div class="card">
                <div class="Name"><%= date%> Income</div>     
            </div>

            <div class="card">
                <div class="classNumber"><img src='img/income.jpg'  width= "30" height="30" > <b style="color:green"><ict:toIncome num1="<%= income%>" num2="<%= size%>" /></b></div>
            </div>
        </div>         
            <input type="text" id="myInput"  placeholder="Search for detail.." title="Type in a name">
            <table id="myTable">
                <thead>
                   <tr class="header">
                     <th style="width:10%;">Trainer ID</th>
                     <th style="width:10%;">Booking ID</th>
                     <th style="width:20%;">Center Address</th>
                     <th style="width:20%;">Date</th>
                   </tr>
                   </thead>
            <%
                  if(bookings.size() != 0){
                      for(int i =0; i <bookings.size();i++){
                          Booking psl = (Booking)bookings.get(i);
                          out.println(" <tr>");
                          out.println("<td>"+psl.getTrainerID()+"</td>");
                          out.println("<td>"+ psl.getBookingID()+"</td>" );
                          out.println("<td>"+ psl.getCenterAddress()+"</td>");
                          out.println("<td>"+ psl.getDate()+"</td>");
                          out.println("</tr>");
                      }
                    }
               
              %>
             </table>
     <%
         }else{
                GymVenue center = (GymVenue)session.getAttribute("center");
                int income = Integer. parseInt(center.getPrice());
                int cSize = centerBookings.size();
                 %>
                 <div class="topbar">
        </div>
 
        <div class="cardBox">

            <div class="card">
                <div class="Name">Center Name :</div>     
            </div>

            <div class="card">
                <div class="Name"><%= center.getName()%></div>     
            </div>

            <div class="card">
                <div class="Name">Center Number :</div>     
            </div>

            <div class="card">
                <div class="classNumber"><%= center.getid()%></div>
            </div>
            <div class="card">
                <div class="Name"><%= date%> Income</div>     
            </div>

            <div class="card">
                <div class="classNumber"><img src='img/income.jpg'  width= "30" height="30" > <b style="color:green"><ict:toIncome num1="<%= income%>" num2="<%= cSize%>" /></b></div>
            </div>
        </div>         
                 <input type="text" id="myInput"  placeholder="Search for detail.." title="Type in a something">
            <table id="myTable">
                <thead>
                   <tr class="header">
                     <th style="width:10%;">Booking ID</th>
                     <th style="width:10%;">Trainer ID</th>
                     <th style="width:20%;">Center Address</th>
                     <th style="width:20%;">Date</th>
                   </tr>
                   </thead>
            <%
                  if(centerBookings.size() != 0){
                      for(int i =0; i <centerBookings.size();i++){
                          Booking psl = (Booking)centerBookings.get(i);
                          out.println(" <tr>");
                          out.println("<td>"+ psl.getBookingID()+"</td>" );
                          out.println("<td>"+psl.getTrainerID()+"</td>");
                          out.println("<td>"+ psl.getCenterAddress()+"</td>");
                          out.println("<td>"+ psl.getDate()+"</td>");
                          out.println("</tr>");
                      }
                    }
       
              %>
             </table>


                <%
                 }
         %>
    </body>
</html>
