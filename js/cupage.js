var homeli = document.getElementById("home");
            var booking = document.getElementById("booking");
            var bookingList = document.getElementById("bookingList");
            var Logout = document.getElementById("logout");
            var reminder = document.getElementById("reminder");
            //Logout.addEventListener('click',l);
            bookingList.addEventListener('click',bookingLists);
            homeli.addEventListener('click',home);
            booking.addEventListener('click',bookings);
            reminder.addEventListener('click',calender);

            function bookings(){
                window.location.href = "cutBooking.jsp";
            }

            function bookingLists(){
                window.location.href = "cutBookingList.jsp";
            }


            function home(){
                window.location.href = "seniorHome.jsp";
            }

            function calender(){
                window.location.href="cutReminder.jsp"; 
            }