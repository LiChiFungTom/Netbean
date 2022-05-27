/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Trainer;
import ict.bean.UserInfo;
import ict.db.BookingDB;
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

/**
 *
 * @author User
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
private UserDB db;
private TrainerDB trainerdb;
private BookingDB bookingdb;
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
        if(action == null){
            ArrayList users = new ArrayList();
            users = db.getUsers();
            request.setAttribute("users", users);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/seniorCreateAndDelete.jsp");
            rd.forward(request, response);  
        }else if(action.equals("delete")){
            int id = Integer. parseInt(request.getParameter("id"));
            if(trainerdb.delRecord(id)){
                if(db.delRecord(id)){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('User deleted!');");
                out.println("location='UserController';");
                out.println("</script>");
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('delete not complete!');");
                out.println("location='UserController';");
                out.println("</script>");
            }
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('delete trainer not complete!');");
                out.println("location='UserController';");
                out.println("</script>");
            }
            
        }else if(action.equals("trainerBookingRequest")){
            int id = Integer.parseInt(request.getParameter("TrainerID"));
            ArrayList trainerBookings = new ArrayList();
            trainerBookings = bookingdb.getTrainerBookings(id);
            request.setAttribute("bookings", trainerBookings);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/trainerBookingRecords.jsp");
            rd.forward(request, response);  
        }else if(action.equals("staffBookingRequest")){
            ArrayList bookings = new ArrayList();
            bookings = bookingdb.getBooking();
            request.setAttribute("bookings", bookings);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/staffBookingRecords.jsp");
            rd.forward(request, response);  
        }else if(action.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            UserInfo user = db.queryCustById(id);
            if(user != null){
                if(user.getState().equals("trainer")){
                    Trainer tr = trainerdb.getTrainerById(id);
                    request.setAttribute("trainer", tr);
                     out.println(tr.getDescription());
                }
                request.setAttribute("user", user);
                RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/seniorCreate.jsp");
                rd.forward(request, response); 
            }
        }else{
                out.println("dw");
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
        processRequest(request, response);
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
        db = new UserDB (dbUrl, dbUser, dbPassword);
        trainerdb = new TrainerDB (dbUrl, dbUser, dbPassword);
        bookingdb = new BookingDB (dbUrl, dbUser, dbPassword);
     }
}
