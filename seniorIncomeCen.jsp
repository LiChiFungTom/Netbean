<%-- 
    Document   : seniorBookingRate
    Created on : 2022/4/20, 下午 04:43:35
    Author     : User
--%>

<%@page import="java.util.Base64"%>
<%@page import="ict.bean.GymVenue"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
.imgWrap:hover {
    background-color: #696969;
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
</head>
<body>
 <%@ include file="seniorMenu.jsp" %>
 <jsp:useBean id="centers" scope="request" class="ArrayList" />
<br/><br/><br/><br/><br/>
<br/><br/>
<center><h1>Centers</h1></center>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">

<table id="myTable">
    
  <tr class="header">
    <th style="width:10%;">Center ID</th>
    <th style="width:20%;">Image</th>
    <th style="width:10%;">Name</th>
    <th style="width:30%;">Address</th>
    <th style="width:15%;">Year/Month</th>
    <th style="width:10%;">Option</th>
  </tr>
   <%
               if(centers.size() != 0){
                   for(int i =0; i <centers.size();i++){
                       GymVenue psl = (GymVenue)centers.get(i);
                        String encode = Base64.getEncoder().encodeToString(psl.getImgs());
                       out.println(" <tr>");
                       out.println("<td>"+psl.getid()+"</td>");
                       out.println("<td>"+ " <img src='data:image/jpeg;base64, "+ encode + " '  width= \"80\" height=\"70\" >"+"</td>");
                       out.println("<td>"+ psl.getName()+"</td>" );
                       out.println("<td>"+ psl.getDescription()+"</td>");
                       out.println("<td><input type='month' min='2022-04' value='2022-04' onchange='getDate(this)'></td>");
                       out.println("<td><span class='imgWrap'><img src='img/money.jpg' onclick=\"view('"+psl.getid()+"')\" width= \"30\" height=\"25\" ></span> / <span class='imgWrap'><img src='img/rate.jpg' onclick=\"rate('"+psl.getid()+"')\" width= \"30\" height=\"25\" ></span></td>");
                       out.println("</tr>");
                   }
                 }
           %>
  
</table>

<script>
    var date = "2022-04";
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
function getDate(selectDate) {
    date = selectDate.value;
}
function view(id){
     window.location.href = "BookingController?action=centerIncome&date=" + date + "&centerID=" + id;
}
function rate(id){
     window.location.href = "BookingController?action=centerRate&date=" + date + "&centerID=" + id;
}
</script>

</body>
</html>
