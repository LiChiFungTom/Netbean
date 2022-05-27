var homeli = document.getElementById("home");
var searchMedicine = document.getElementById("userlist");
var aiBot = document.getElementById("aiBot");
var Logout = document.getElementById("logout");


Logout.addEventListener('click',l);
aiBot.addEventListener('click',chatBot);
homeli.addEventListener('click',home);
searchMedicine.addEventListener('click',searchPage);



function searchPage(){
    window.location.href = "UserController";
}

function chatBot(){
    window.location.href = "chatBot.html";
}
    

function home(){
    window.location.href = "seniorHome.jsp";
}

function l(){
    window.location.href="login.jsp"; 
    
}