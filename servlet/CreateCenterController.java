/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.GymVenue;
import ict.db.GymCenterDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author LCF
 */
@WebServlet(name = "CreateCenterController", urlPatterns = {"/CreateCenterController"})
@MultipartConfig
public class CreateCenterController extends HttpServlet {

    private final static Logger LOGGER =
            Logger.getLogger(CreateCenterController.class.getCanonicalName());
    
    private GymCenterDB db;
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
        
            PrintWriter out = response.getWriter();
            String action = request.getParameter("action");      
            if(action.equals("add")){           
                InputStream inputStream = null;
                Part img = request.getPart("img");
                inputStream = img.getInputStream();
                String name = request.getParameter("name");
                String description = request.getParameter("description");     
                String address = request.getParameter("address");  
                String state = request.getParameter("type");
                String price = request.getParameter("price");             
                if(img.equals("") || name.equals("") || description.equals("") || address.equals("")){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Please fill all information!');");
                    out.println("location='GymVenueController?action=add';");
                    out.println("</script>");
                }
                else if(db.AddGymCenter(inputStream , name, description, address, state , price)) {
                     out.println("<script type=\"text/javascript\">");
                     out.println("alert('Add Sucessful');");
                     out.println("location='GymVenueController?action=staffGymVenue';");
                     out.println("</script>");  
                 }
            }else if(action.equals("edit")){
               InputStream inputStream = null;
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String address = request.getParameter("address");
                String state = request.getParameter("type");
                String price = request.getParameter("price");
                GymVenue gyv = new GymVenue(id , name , description , address , state, price);             
                if(db.editRecord(gyv)){
                      out.println("<script type=\"text/javascript\">");
                      out.println("alert('Center Updated!');");
                      out.println("location='GymVenueController?action=staffGymVenue';");
                      out.println("</script>");                    
                     
                 } 
                
                
                
                     
            }
    }
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new GymCenterDB(dbUrl, dbUser, dbPassword);
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
