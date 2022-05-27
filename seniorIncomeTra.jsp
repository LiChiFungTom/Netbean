<%-- 
    Document   : seniorBookingRate
    Created on : 2022/4/20, 下午 04:43:35
    Author     : User
--%>

<%@page import="java.util.Base64"%>
<%@page import="ict.bean.Trainer"%>
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

</head>
<body>
 <%@ include file="seniorMenu.jsp" %>
 <jsp:useBean id="trainers" scope="request" class="ArrayList" />
<br/><br/><br/><br/><br/>
<br/><br/>
<center><h1>Trainers</h1></center>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">

<table id="myTable">
  <tr class="header">
    <th style="width:10%;">Trainer ID</th>
    <th style="width:30%;">Image</th>
    <th style="width:15%;">Name</th>
    <th style="width:15%;">Gender</th>
    <th style="width:20%;">Year/Month</th>
    <th style="width:10%;">Income/Rate</th>
  </tr>
   <%
               if(trainers.size() != 0){
                   for(int i =0; i <trainers.size();i++){
                       Trainer psl = (Trainer)trainers.get(i); 
                       out.println(" <tr>");
                       out.println("<td>"+psl.getid()+"</td>");
                       if(psl.getImgs() == null){
                            out.println("<td>"+ " <img src='img/user.jpg'  width= \"60\" height=\"70\" >"+"</td>");
                       }else{
                        String encode = Base64.getEncoder().encodeToString(psl.getImgs());
                        out.println("<td>"+ " <img src='data:image/jpeg;base64, "+ encode + " '  width= \"80\" height=\"70\" >"+"</td>");
                       }
                       out.println("<td>"+ psl.getName()+"</td>" );
                       out.println("<td>"+ psl.getGender()+"</td>");
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
     window.location.href = "BookingController?action=trainerIncome&date=" + date + "&trainerID=" + id;
}
function rate(id){
     window.location.href = "BookingController?action=trainerRate&date=" + date + "&trainerID=" + id;
}
</script>

</body>
</html>
