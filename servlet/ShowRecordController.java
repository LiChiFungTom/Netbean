/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.BookingDB;
import ict.db.GymCenterDB;
import ict.db.TrainerDB;
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
 * @author LCF
 */
@WebServlet(name = "ShowRecordController", urlPatterns = {"/ShowRecordController"})
public class ShowRecordController extends HttpServlet {

    private GymCenterDB db;
    private TrainerDB tdb;
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
        String records = request.getParameter("type");      
        PrintWriter out = response.getWriter();        
        if(action == null){
            if(records.equals("Center")){
                 ArrayList c = new ArrayList();
                 c= db.getCenter();
                 request.setAttribute("center", c);
                 RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/CenterBookingRecords.jsp");
                 rd.forward(request, response);
            }else{
                   if(records.equals("Trainer")){
                       ArrayList t = new ArrayList();
                       t = tdb.getTrainers();
                       request.setAttribute("trainers", t);
                       RequestDispatcher rd = this.getServletContext()
                            .getRequestDispatcher("/ShowTrainerRecords.jsp");
                       rd.forward(request, response);
                   }
                
              }
          
                    
        }else if(action.equals("show")){
            String id = request.getParameter("id");
            ArrayList c = new ArrayList();
            c = bookingdb.getGymBooking(id);

            request.setAttribute("center", c);
                 RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/ShowCenterRecords.jsp");
                 rd.forward(request, response);
        }else if(action.equals("showt")){
            int id = Integer. parseInt ( request.getParameter("id") );
            ArrayList t = new ArrayList();
            t = tdb.getTrainerBookings(id);
            request.setAttribute("bookings", t);
                       RequestDispatcher rd = this.getServletContext()
                            .getRequestDispatcher("/trainerBookingRecords.jsp");
            rd.forward(request, response);
        }
    }
    
    
     public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new GymCenterDB(dbUrl, dbUser, dbPassword);
        tdb = new TrainerDB(dbUrl, dbUser, dbPassword);
        bookingdb = new BookingDB(dbUrl, dbUser, dbPassword);
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

}
