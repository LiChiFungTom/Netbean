/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.UserInfo;
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
 * @author LCF
 */
@WebServlet(name = "LoginController", urlPatterns = {"/home"})
public class LoginController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            doPost(request, response);
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
        String targetURL;
        
        if (!isAuthenticated(request)&& 
           !("authenticate".equals(action))) {
            doLogin(request, response);
            return;
        }
        if ("authenticate".equals(action)) {
                doAuthenticate(request, response);
        } else if ("logout".equals(action)) {
                doLogout(request, response);
        } else {
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

     private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        String targetURL="";
        boolean isValid = db.isValidUser(username, password);
        // need to add check state (customer/staff/senior) 
        if(isValid){
            UserInfo user = db.getUserByEmailAndPsw(username, password);
            if(db.getUserType(username, password).equals("senior")){
                targetURL = "seniorHome.jsp";
            }else if(db.getUserType(username, password).equals("customer")){
                targetURL = "customerHome.jsp";
            }else if(db.getUserType(username, password).equals("staff")  ){
                targetURL = "StaffHome.jsp";
            }else if(db.getUserType(username, password).equals("trainer") ){
                targetURL = "trainerHome.jsp";
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("userInfo", user);
        }else{
            targetURL = "loginError.jsp";
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request,response);
    }
    
      private boolean isAuthenticated(HttpServletRequest request){
        boolean result = false;
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfo") != null){
            result = true;
        }
        return result;
    }
      
      
       private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String targetURL = "login.jsp";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request,response);
        
    }
       
       private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute("userInfo");
            session.invalidate();
        }
        doLogin(request,response);
    }
    
      private UserDB db;
     
     public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new UserDB (dbUrl, dbUser, dbPassword);
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
