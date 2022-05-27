var homeli = document.getElementById("home");

            var accountMan = document.getElementById("accountMan");
            var displayBookGym = document.getElementById("displayBookGym");
            var trainerBookingIn = document.getElementById("trainerBookingIn");
            var centerBookingIn = document.getElementById("centerBookingIn");
            centerBookingIn.addEventListener('click',centerBooking);
            accountMan.addEventListener('click',accountManagement);
            homeli.addEventListener('click',home);
            displayBookGym.addEventListener('click',displayBookingG);
            trainerBookingIn.addEventListener('click',trainerBooking);
var logout = document.getElementById("logout");
logout.addEventListener('click',out);
 function out(){
                window.location.href = "login.jsp";
            }
            function centerBooking(){
                window.location.href = "GymVenueController?action=seniorRateCen";
            }

            function accountManagement(){
                window.location.href = "UserController";
            }
 var profile = document.getElementById("profile");
            profile.addEventListener('click',editpro);
            function editpro(){
                window.location.href = "seniorEditPro.jsp";
            }

            function home(){
                window.location.href = "seniorHome.jsp";
            }

            function displayBookingG(){
                window.location.href="SelectBookingRecord.jsp"; 

            }
            function trainerBooking(){
                window.location.href="PersonalTrainerController?action=seniorRate"; 

            }