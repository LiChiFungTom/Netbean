/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.GymVenue;
import ict.bean.Trainer;
import ict.db.GymCenterDB;
import ict.db.TrainerDB;
import java.io.IOException;
import java.io.InputStream;
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
@WebServlet(name = "EDBookingListController", urlPatterns = {"/EDBookingListController"})
public class EDBookingListController extends HttpServlet {

    private GymCenterDB db;
    private TrainerDB tdb;
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
            ArrayList c = new ArrayList();
            ArrayList t = new ArrayList();
            c= db.getCenter();
            t = tdb.getTrainers();
            request.setAttribute("centers", c);
            request.setAttribute("trainers",t);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/enDisTranAndCenter.jsp");
            rd.forward(request, response);                   
        }else if(action.equals("Enable")){
            String id = request.getParameter("id");
            GymVenue gyc = db.queryGymcenter(id);
            String state = "close";
            gyc.setState(state);          
            if(db.updataState(gyc)){
                      out.println("<script type=\"text/javascript\">");
                      out.println("alert('Center Updated!');");
                      out.println("location='EDBookingListController';");
                      out.println("</script>");         
                     
                 } 
            
        }else if(action.equals("Disable")){
            String id = request.getParameter("id");
            GymVenue gyc = db.queryGymcenter(id);
            String state = "opening";
            gyc.setState(state);
            if(db.updataState(gyc)){
                      out.println("<script type=\"text/javascript\">");
                      out.println("alert('Center Updated!');");
                      out.println("location='EDBookingListController';");
                      out.println("</script>");         
                     
                 } 
        }else if(action.equals("TEnable")){
            int id = Integer.parseInt(request.getParameter("id"));
            Trainer trainer = tdb.getTrainerById(id);
            String state = "Free";
            trainer.setState(state);
            if(tdb.updataState(trainer)){
                out.println("<script type=\"text/javascript\">");
                      out.println("alert('Trainer Updated!');");
                      out.println("location='EDBookingListController';");
                      out.println("</script>");    
            }
        }else if(action.equals("TDisable")){
            //trainer disable
            int id = Integer.parseInt(request.getParameter("id"));
            Trainer trainer = tdb.getTrainerById(id);
            String state = "On Work";
            trainer.setState(state);
            if(tdb.updataState(trainer)){
                out.println("<script type=\"text/javascript\">");
                      out.println("alert('Trainer Updated!');");
                      out.println("location='EDBookingListController';");
                      out.println("</script>");    
            }
        }
        
        
    }

    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new GymCenterDB(dbUrl, dbUser, dbPassword);
        tdb = new TrainerDB(dbUrl, dbUser, dbPassword);
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
