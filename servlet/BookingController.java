/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Booking;
import ict.bean.GymVenue;
import ict.bean.Trainer;
import ict.bean.UserInfo;
import ict.db.BookingDB;
import ict.db.GymCenterDB;
import ict.db.TrainerDB;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "BookingController", urlPatterns = {"/BookingController"})
public class BookingController extends HttpServlet {
    private BookingDB bookingdb;
    private GymCenterDB centerDB;
    private UserDB userdb;
    private TrainerDB trainerdb;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action"); 
        PrintWriter out = response.getWriter();
        if(action.equals("confirmBooking")){
                String bookingID = request.getParameter("BookingID"); 
                String trainerid = request.getParameter("TrainerID");
                if(bookingdb.upDateBooking(bookingID,"confirmed")){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Booking is confirmed!');");
                    out.println("location='UserController?action=trainerBookingRequest&TrainerID="+trainerid+"';");
                    out.println("</script>");
                }
        }else if(action.equals("declineBooking")){
                String bookingID = request.getParameter("BookingID"); 
                String trainerid = request.getParameter("TrainerID");
                if(bookingdb.upDateBooking(bookingID,"decline")){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Booking is decline!');");
                    out.println("location='UserController?action=trainerBookingRequest&TrainerID="+trainerid+"';");
                    out.println("</script>");
                }
                              
        }
        else if(action.equals("StaffconfirmBooking")){
                String bookingID = request.getParameter("BookingID"); 
                String trainerid = request.getParameter("TrainerID");
                if(bookingdb.upDateBooking(bookingID,"confirmed")){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Booking is confirmed!');");
                    out.println("location='UserController?action=staffBookingRequest';");
                    out.println("</script>");
          }
      }
         else if(action.equals("StaffdeclineBooking")){
                String bookingID = request.getParameter("BookingID"); 
                String trainerid = request.getParameter("TrainerID");
                if(bookingdb.upDateBooking(bookingID,"decline")){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Booking is confirmed!');");
                    out.println("location='UserController?action=staffBookingRequest';");
                    out.println("</script>");
          }
      } 
        
        else if(action.equals("edit")){
                String bookingID = request.getParameter("bookingid"); 
                Booking booking = bookingdb.getBookingByID(bookingID);
                HttpSession session = request.getSession(true);
                session.setAttribute("booking", booking);
                 RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/cutBookingEdit.jsp");
                rd.forward(request, response); 
        }else if(action.equals("delete")){
                String bookingID = request.getParameter("BookingID"); 
                String trainerid = request.getParameter("TrainerID");
                //if(bookingdb.upDateBooking(bookingID)){
                  //  out.println("<script type=\"text/javascript\">");
                   // out.println("alert('Booking is confirmed!');");
                    //out.println("location='UserController?action=trainerBookingRequest&TrainerID="+trainerid+"';");
                    //out.println("</script>");
                //}
        }else if(action.equals("trainerIncome")){
            String date = request.getParameter("date"); 
            int trainerID = Integer.parseInt(request.getParameter("trainerID"));
            ArrayList bookingRate = bookingdb.getBookingByDate(trainerID, date);
            Trainer trainer = trainerdb.getTrainerById(trainerID);
            ArrayList bookingCenters = null;
            HttpSession session = request.getSession();
                session.setAttribute("bookings", bookingRate);
                session.setAttribute("centerBookings", bookingCenters);
                session.setAttribute("trainer", trainer);
                session.setAttribute("date", date);
                 RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/seniorIncome.jsp");
                rd.forward(request, response); 
            
        }else if(action.equals("centerIncome")){
            String date = request.getParameter("date"); 
            String centerID = request.getParameter("centerID");
            GymVenue center = centerDB.queryGymcenter(centerID);
            ArrayList bookingRate = null;
            ArrayList bookingCenters = bookingdb.getBookingByDateWithCenterID(centerID, date);
            HttpSession session = request.getSession();
                session.setAttribute("bookings", bookingRate);
                session.setAttribute("centerBookings", bookingCenters);
                session.setAttribute("center", center);
                session.setAttribute("date", date);
                 RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/seniorIncome.jsp");
                rd.forward(request, response); 
            
        }
        else if(action.equals("centerRate")){
            String date = request.getParameter("date"); 
            String centerID = request.getParameter("centerID");
            GymVenue center = centerDB.queryGymcenter(centerID);
            ArrayList bookingRate = null;
            ArrayList bookingCenters = bookingdb.getBookingByDateWithCenterID(centerID, date);
            HttpSession session = request.getSession();
                session.setAttribute("bookings", bookingRate);
                session.setAttribute("centerBookings", bookingCenters);
                session.setAttribute("center", center);
                session.setAttribute("date", date);
                 RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/seniorBookingRate.jsp");
                rd.forward(request, response); 
            
        }else if(action.equals("trainerRate")){
            String date = request.getParameter("date"); 
            int trainerID = Integer.parseInt(request.getParameter("trainerID"));
            ArrayList bookingRate = bookingdb.getBookingByDate(trainerID, date);
            Trainer trainer = trainerdb.getTrainerById(trainerID);
            ArrayList bookingCenters = null;
            HttpSession session = request.getSession();
                session.setAttribute("bookings", bookingRate);
                session.setAttribute("centerBookings", bookingCenters);
                session.setAttribute("trainer", trainer);
                session.setAttribute("date", date);
                 RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/seniorBookingRate.jsp");
                rd.forward(request, response); 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action"); 
        PrintWriter out = response.getWriter();
        if(action.equals("booking")){
            String date = request.getParameter("bookingDate") + " " + request.getParameter("bookingTime");
            int custID = Integer.parseInt(request.getParameter("userID"));
            int amount = Integer.parseInt(request.getParameter("amount"));
            String trainerID = request.getParameter("trainer"); 
            String custName = request.getParameter("bookingName"); 
            String custEmail = request.getParameter("bookingEmail");
            String custPhone = request.getParameter("bookingPhone");
            String centerID = request.getParameter("centerID");
            String state = "processing";
            GymVenue venue = centerDB.queryGymcenter(centerID);
            String address = venue.getAddress();
            if(bookingdb.getAvailableCenter(centerID, date)){
                if(!bookingdb.havBooked(custID, centerID, date)){
                    if(bookingdb.AddBooking(custID, trainerID,centerID, custName, custEmail, custPhone, address, date, state, amount)){
                        UserInfo user = userdb.queryCustById(custID);
                        HttpSession session = request.getSession(true);
                        session.setAttribute("userInfo", user);
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Booking Successful!');");
                        out.println("location='customerHome.jsp';");
                        out.println("</script>");
                }else{
                    UserInfo user = userdb.queryCustById(custID);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert(Booking Error');");
                    out.println("location='cutBooking.jsp';");
                    out.println("</script>");
                    }
            }else{
                     UserInfo user = userdb.queryCustById(custID);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Repeat Booking! "+date+" in "+address+" Booking Time was booked before!');");
                    out.println("location='cutBooking.jsp';");
                    out.println("</script>");
                    

            }

                }else{
              
                    UserInfo user = userdb.queryCustById(custID);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('"+date+" in "+address+" Booking Time is full! Please select other time!');");
                    out.println("location='cutBooking.jsp';");
                    out.println("</script>");

                }
               
             
        }else if(action.equals("custEdit")){
                String bookingID = request.getParameter("bookingID"); 
                String eDate = request.getParameter("bookingDate") + " " + request.getParameter("bookingTime");
                String eTrainerID = request.getParameter("trainer"); 
                String eCustName = request.getParameter("bookingName"); 
                String eCustEmail = request.getParameter("bookingEmail");
                String eCustPhone = request.getParameter("bookingPhone");
                String eCenterID = request.getParameter("centerID");
                GymVenue venue2 = centerDB.queryGymcenter(eCenterID);
                String eAddress = venue2.getAddress();
                int eCustID = Integer.parseInt(request.getParameter("custID"));
                int eAmount = Integer.parseInt(request.getParameter("amount"));
                if(bookingdb.editRecord(bookingID, eCustID, eTrainerID, eCenterID, eCustName, eCustEmail, eCustPhone, eAddress, eDate, eAmount)){
                    UserInfo user = userdb.queryCustById(eCustID);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Booking update Successful!');");
                    out.println("location='cutBookingList.jsp';");
                    out.println("</script>");
                }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        bookingdb = new BookingDB (dbUrl, dbUser, dbPassword);
        centerDB = new GymCenterDB (dbUrl, dbUser, dbPassword);
        userdb = new UserDB (dbUrl, dbUser, dbPassword);
        trainerdb = new TrainerDB (dbUrl, dbUser, dbPassword);
        bookingdb.CreateBookingTable();
     }

}
