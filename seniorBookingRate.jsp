<%-- 
    Document   : seniorBookingRate
    Created on : 2022/4/21, 上午 10:34:50
    Author     : User
--%>

<%@page import="ict.bean.GymVenue"%>
<%@page import="ict.bean.Trainer"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="ict.bean.Booking"%>
<%@page import="java.time.YearMonth"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
 

 
<!DOCTYPE HTML>
<html>
    <%
        Gson gsonObj = new Gson();
        Map<Object,Object> map = null;
        List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
        String date = session.getAttribute("date")+"";
        ArrayList bookings = (ArrayList)session.getAttribute("bookings");
        ArrayList centerBookings = (ArrayList)session.getAttribute("centerBookings");
        if(centerBookings != null){
            if(centerBookings.size() != 0){
            String[] ymd = date.split("-");
            int year = Integer.parseInt(ymd[0]);
            int month = Integer.parseInt(ymd[1]);
            YearMonth yearMonthObject = YearMonth.of(year, month);
            int daysInMonth = yearMonthObject.lengthOfMonth();
            String theDay="";
            double no = 0.0;
            for(int day = 1; day <= daysInMonth; day++){
                double percentage = 0.0;
                String Day="";
                String Month="";
                String nothing = "";
                if(day < 10){
                    Day = "0"+day;
                }else{
                    Day = day+"";
                }
                if(month < 10){
                    Month = "0"+month;
                }else{
                    Month = month+"";
                }
                theDay = year+"-"+Month+"-"+Day;
                boolean include = false;
                for(int i = 0; i<centerBookings.size();i++){
                     Booking psl = (Booking)centerBookings.get(i);
                     if(psl.getDate().contains(theDay)){
                         percentage++;
                         include = true;
                     }
                }
                if(include){
                    double val1 = percentage / centerBookings.size() *100;
                    BigDecimal bd = new BigDecimal(val1).setScale(1, RoundingMode.HALF_UP);
                    double result = bd.doubleValue();
                    map = new HashMap<Object,Object>(); map.put("label", month+"/"+Day); map.put("y", result); list.add(map);
                }
            }
            }else{
                map = new HashMap<Object,Object>(); map.put("label", "No Booking"); map.put("y", 100); list.add(map);
            }
        }else{
            if(bookings.size() != 0){
            String[] ymd = date.split("-");
            int year = Integer.parseInt(ymd[0]);
            int month = Integer.parseInt(ymd[1]);
            YearMonth yearMonthObject = YearMonth.of(year, month);
            int daysInMonth = yearMonthObject.lengthOfMonth();
            String theDay="";
            double no = 0.0;
            for(int day = 1; day <= daysInMonth; day++){
                double percentage = 0.0;
                String Day="";
                String Month="";
                String nothing = "";
                if(day < 10){
                    Day = "0"+day;
                }else{
                    Day = day+"";
                }
                if(month < 10){
                    Month = "0"+month;
                }else{
                    Month = month+"";
                }
                theDay = year+"-"+Month+"-"+Day;
                boolean include = false;
                for(int i = 0; i<bookings.size();i++){
                     Booking psl = (Booking)bookings.get(i);
                     if(psl.getDate().contains(theDay)){
                         percentage++;
                         include = true;
                     }
                }
                if(include){
                    double val1 = percentage / bookings.size() *100;
                    BigDecimal bd = new BigDecimal(val1).setScale(1, RoundingMode.HALF_UP);
                    double result = bd.doubleValue();
                    map = new HashMap<Object,Object>(); map.put("label", month+"/"+Day); map.put("y", result); list.add(map);
                }
            }
            }else{
                map = new HashMap<Object,Object>(); map.put("label", "No Booking"); map.put("y", 100); list.add(map);
            }
        }
        String dataPoints = gsonObj.toJson(list);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
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
<script type="text/javascript">
window.onload = function() { 
var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light2", // "light1", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "<%= date%> Booking Rate"
	},
	data: [{
		type: "pie",
		toolTipContent: "<b>{label}</b>: {y}%",
		indexLabelFontSize: 16,
		indexLabel: "{label} - {y}%",
		dataPoints: <%out.print(dataPoints);%>
	}]
});
chart.render();
 
}
</script>
</head>
<body>
   <%
       if(centerBookings == null){
           Trainer trainer = (Trainer)session.getAttribute("trainer");
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
                <div class="Name">Booking Rate</div>     
            </div>

            <div class="card">
                <div class="classNumber"><img src='img/calendar.jpg'  width= "30" height="20" > <%= date%></div>
            </div>
        </div>        
    <%       
       }else{
        GymVenue center = (GymVenue)session.getAttribute("center");
           %>
            <div class="topbar">
        </div>
 
        <div class="cardBox">

            <div class="card">
                <div class="Name">Trainer Name :</div>     
            </div>

            <div class="card">
                <div class="Name"><%= center.getName()%></div>     
            </div>

            <div class="card">
                <div class="Name">Trainer Number :</div>     
            </div>

            <div class="card">
                <div class="classNumber"><%= center.getid()%></div>
            </div>
            <div class="card">
                <div class="Name">Booking Rate</div>     
            </div>

            <div class="card">
                <div class="classNumber"><img src='img/calendar.jpg'  width= "30" height="20" > <%= date%></div>
            </div>
        </div>     
           <%
       }
       %>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>     
