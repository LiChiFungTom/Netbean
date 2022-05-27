/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.GymVenue;
import ict.db.GymCenterDB;
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
@WebServlet(name = "GymVenueController", urlPatterns = {"/GymVenueController"})
public class GymVenueController extends HttpServlet {
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
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();        
        if(action == null){
            ArrayList c = new ArrayList();
            c= db.getCenter();
            request.setAttribute("centers", c);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/GymCenter.jsp");
            rd.forward(request, response);
                    
        }else if(action.equals("edit")){
            String id = request.getParameter("id");
            GymVenue center = db.queryGymcenter(id);
            if(center != null){
                request.setAttribute("center", center);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/CenterCreate.jsp");
                rd.forward(request, response);
            }else{
                out.println("error");
            }
        
        }else if(action.equals("add")){
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/CenterCreate.jsp");
            rd.forward(request, response);
            
        
        }else  if(action.equals("delete")){
            String id = request.getParameter("id");
            if( db.delRecord(id)){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Center deleted!');");
                out.println("location='GymVenueController?action=staffGymVenue';");
                out.println("</script>");
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('delete not complete!');");
                out.println("location='GymVenueController';");
                out.println("</script>");
            }
            // wait a min some thing i need to do 
        }else if(action.equals("centerDetail")){
            String id = request.getParameter("id");
            GymVenue center = db.queryGymcenter(id);
            if(center != null){
                request.setAttribute("center", center);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/cutCenterDetail.jsp");
                rd.forward(request, response);
            }else{
                out.println("error");
            }
        }else if(action.equals("seniorRateCen")){
            ArrayList centers = new ArrayList();
            centers= db.getCenter();
            request.setAttribute("centers", centers);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/seniorIncomeCen.jsp");
            rd.forward(request, response);    
        }else if(action.equals("cutList")  ){
            ArrayList c = new ArrayList();
            c= db.getCenter();
            request.setAttribute("centers", c);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/custGymCenter.jsp");
            rd.forward(request, response);
                    
        }else if(action.equals("staffGymVenue")){
            ArrayList c = new ArrayList();
            c= db.getCenter();
            request.setAttribute("centers", c);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/StaffGymCenter.jsp");
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
        db = new GymCenterDB(dbUrl, dbUser, dbPassword);
     }
    
  
}
