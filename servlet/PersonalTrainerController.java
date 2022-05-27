/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Trainer;
import ict.db.TrainerDB;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LCF
 */
@WebServlet(name = "PersonalTrainerController", urlPatterns = {"/PersonalTrainerController"})
@MultipartConfig
public class PersonalTrainerController extends HttpServlet {

    private TrainerDB db;
    
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
        if(action == null ){
            ArrayList trainers = new ArrayList();
            trainers= db.getTrainers();
            request.setAttribute("trainers", trainers);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/PersonalTrainer.jsp");
            rd.forward(request, response);        
        }else if(action.equals("edit")){
            int id = Integer. parseInt(request.getParameter("id"));
            Trainer trainers = db.getTrainerById(id);
            if(trainers != null){
                request.setAttribute("trainer", trainers);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/CreateTrainer.jsp");
                rd.forward(request, response);
            }else{
                out.println("error");
            }
        
        }else if(action.equals("delete")){
             int id = Integer.parseInt(request.getParameter("id"));
             if( db.delRecord(id)){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Trainer deleted!');");
                out.println("location='PersonalTrainerController?action=staffTrainer';");
                out.println("</script>");
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('delete not complete!');");
                out.println("location='PersonalTrainerController?action=staffTrainer';");
                out.println("</script>");
            }
        
        }
        
          
        else if(action.equals("trainerDetail")){
            out.println("Good");
        }
        else if(action.equals("seniorRate")){
            ArrayList trainers = new ArrayList();
            trainers= db.getTrainers();
            request.setAttribute("trainers", trainers);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/seniorIncomeTra.jsp");
            rd.forward(request, response);    
        }else if(action.equals("staffTrainer")){
            ArrayList trainers = new ArrayList();
            trainers= db.getTrainers();
            request.setAttribute("trainers", trainers);
            RequestDispatcher rd = this.getServletContext()
              .getRequestDispatcher("/StaffTrainerDisplay.jsp");
            rd.forward(request, response); 
        }
        else if(action.equals("add")){
        
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/CreateTrainer.jsp");
                rd.forward(request, response);
           
        
        }
       
        
    }
    
     public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new TrainerDB(dbUrl, dbUser, dbPassword);
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
