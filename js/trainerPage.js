var homeli = document.getElementById("home");
var bookingRequest = document.getElementById("bookingRequest");
var aiBot = document.getElementById("aiBot");
var Logout = document.getElementById("logout");


Logout.addEventListener('click',l);
aiBot.addEventListener('click',chatBot);
homeli.addEventListener('click',home);
//bookingRequest.addEventListener('click',bRequest);


        function bRequest(ID){
            window.location.href = "UserController?action=trainerBookingRequest&TrainerID="+ID;
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