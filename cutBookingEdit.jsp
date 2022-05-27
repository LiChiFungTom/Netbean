<%-- 
    Document   : cutBooking
    Created on : 2022/3/28, 上午 10:31:26
    Author     : User
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Booking</title>
<link rel="stylesheet" href="css/booking.css">
<!-- Font -->
<link href="https://fonts.googleapis.com/css?family=Raleway:400,600,700,900" rel="stylesheet">
</head>
    <body>
        <jsp:useBean id="booking" class="ict.bean.Booking" scope="session" />
        <%
            if(booking != null){
            %>
        <div class="container">
            
            <div class="container-time">
                <h2 class="heading">Time Open</h2>
                <h3 class="heading-days">Monday-Friday</h3>
                <p>11am - 23pm </p>
                <p>12pm - 1pm (lunch)</p>
                <h3>Saturday and sunday</h3>
                <p>11am - 23pm </p>
                <p>12pm - 1pm (lunch)</p>
                <h3 class="heading-days">Center Information</h3>
                <p>Gym center capacity:  10 people / timeslot </p>
                <p>1 timeslot =	  1 hour</p>
                <hr>
                <h4 class="heading-phone">Call Us: (123) 45-45-456</h4>
            </div>
        <div class="container-form">
            <form action="BookingController" method="post">
            <h2 class="heading heading-yellow" >Online Booking</h2>
            <input type='hidden' name='bookingID'  value=<%= booking.getBookingID()%>  />
            <input type='hidden' name='custID'  value=<%= booking.getCustID()%>  />
            <input type='hidden' name='action'  value='custEdit'  />
            <div class="form-field">
                <p>Your Name</p>
                <input type="text" placeholder="Your Name" name="bookingName" value= <%= booking.getCustName()%> />
            </div>
            <div class="form-field">
            <p>Your email</p>
            <input type="email" placeholder="Your email" name="bookingEmail" value= <%= booking.getCustEmail()%> />
            </div>
            <div class="form-field">
            <p>Contact Number</p>
            <input type="text" placeholder="Your phone number" name="bookingPhone" value= <%= booking.getCustPhone()%> />
            </div>
            <div class="form-field">
        
            <p>Date</p>
            <input type="date" name="bookingDate" />
            </div>
            <div class="form-field">
            <p>Time</p>
            <select name="bookingTime" id="#">
            <option value="11:00">11am - 12 am</option>
            <option value="13:00">01pm - 02 pm</option>
             <option value="14:00">02pm - 03 pm</option>
            <option value="15:00">03pm - 04 pm</option>
            <option value="16:00">04pm - 05 pm</option>
            <option value="17:00">05pm - 06 pm</option>
            <option value="18:00">06pm - 07 pm</option>
            <option value="19:00">07pm - 08 pm</option>
            <option value="20:00">08pm - 09 pm</option>
            <option value="21:00">09pm - 10 pm</option>
            <option value="22:00">10pm - 11 pm</option>
            </select>
            </div>
          
            <p>Trainer</p>
            <%
                String dbUser = this.getServletContext().getInitParameter("dbUser");
                String dbPassword = this.getServletContext().getInitParameter("dbPassword");
                String dbUrl = this.getServletContext().getInitParameter("dbUrl");
                ict.db.TrainerDB trainers = new ict.db.TrainerDB(dbUrl, dbUser, dbPassword);
                ArrayList allTrainer = new ArrayList();
                allTrainer= trainers.getAvaTrainers();
                if(allTrainer.size() != 0){
                    out.print("<select name='trainer' id='trainer' onchange='selectTrainer(this);'>");
                    out.println("<option value='null' >Need not</option>");
                    for(int i = 0; i < allTrainer.size(); i++){
                        ict.bean.Trainer trainer = (ict.bean.Trainer) allTrainer.get(i);
                        out.println("<option value='"+trainer.getid()+"' >"+trainer.getName()+"</option>");
                    }
                    out.print("</select>");
                }
            %>
            

             <a style="display: none;" id="trainerDetail" href='TrainerController?action=list'>Trainer Detail</a>
          
            <p>GYM Center</p>
            
            <%
                ict.db.GymCenterDB centerdb = new ict.db.GymCenterDB(dbUrl, dbUser, dbPassword);
                ArrayList allCenter = new ArrayList();
                allCenter= centerdb.getAvaCenters();
                ict.bean.GymVenue center0 = (ict.bean.GymVenue) allCenter.get(0);
                if(allCenter.size() != 0){
                    out.print("<select name='center' id='center' onchange='selectGym(this);'> ");
                    for(int i = 0; i < allCenter.size(); i++){
                        ict.bean.GymVenue center = (ict.bean.GymVenue) allCenter.get(i);
                        out.println("<option value='{\"centerID\":\""+center.getid()+"\",\"price\":\""+center.getPrice()+"\"}' >"+center.getAddress()+"+</option>");
                    }
                    out.print("</select>");
                } 
             %>
             <input type='hidden' name='centerID' id="centerID"  value=<%= center0.getid() %> />
            <a  id="centerDetail" style="display: none;">Center Detail</a>
            
            Total Amount: HKD<input type="text" style=" border: none; background: transparent; font-size:25px; text-decoration: underline;" readonly  name="amount" id="amount"  value=<%= center0.getPrice()%> />
            <button class="btn" >Submit</button>
            </form>
        </div>
            </div>
            <%
                int centerPrice =  Integer. parseInt(center0.getPrice());
                out.println("<script type='text/javascript'>");
                out.println("var amount = 0;");
                out.println("var trainerPrice = 0;");
                out.println("var centerPrice = "+centerPrice+";");
                out.println("function selectTrainer(trainer) {");
                out.println("if(trainer.value == 'null'){"
                        + "trainerPrice = 0;"
                        + "amount = trainerPrice + centerPrice;"
                        + "document.getElementById('amount').value =  amount;"
                        + "document.getElementById('trainerDetail').style.display = 'none';"
                        + "document.getElementById('hkd').style.display = 'block'; "
                        + " }else{ "
                        + "document.getElementById('trainerDetail').style.display = 'block'; "
                        + "document.getElementById('amount').style.display = 'block';"
                        + "trainerPrice = 150;"
                        + "amount = trainerPrice + centerPrice; "
                        + "document.getElementById('hkd').style.display = 'block';"
                        + "document.getElementById('amount').value =  amount; }");
                out.println("}");
                out.println("function selectGym(center) {");
                out.println("var myValue = JSON.parse(center.value);"
                        + "document.getElementById('centerID').value =  myValue.centerID;"
                        + "document.getElementById('centerDetail').style.display = 'block'; "
                        + "centerPrice = parseInt(myValue.price); "
                        + "amount = trainerPrice + centerPrice;"
                        + "document.getElementById('hkd').style.display = 'block';"
                        + "document.getElementById('amount').value = amount;"
                        + "document.getElementById('centerDetail').href='PersonalTrainerController?action=trainerDetail&id='+myValue.price;");
                out.println("}");
                out.println("</script>");
                
            %>
            
            <%
                }
            else{
                out.println("Sorry! something wrong!");
            }
             %>

            
            
    </body>
    
</html>
