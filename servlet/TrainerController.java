/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Trainer;
import ict.db.TrainerDB;
import ict.db.UserDB;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Logger;
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
@WebServlet(name = "TrainerController", urlPatterns = {"/TrainerController"})
@MultipartConfig
public class TrainerController extends HttpServlet {
    
    private final static Logger LOGGER =
            Logger.getLogger(TrainerController.class.getCanonicalName());
    private TrainerDB db;
    private UserDB udb;
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
             Part img = request.getPart("img");
             InputStream inputStream = null;
             inputStream = img.getInputStream();                  
             String email = request.getParameter("email");            
             String name = request.getParameter("username");
             String tel = request.getParameter("tel");
             String pw = request.getParameter("password");
             String cpw = request.getParameter("comPassword");
             String trainName = request.getParameter("trainName");
             int price = Integer. parseInt(request.getParameter("price"));                    
             String description = request.getParameter("description");     
             String gender = request.getParameter("gender");
             String state = request.getParameter("state");            
             
//             out.println(email +"  "+ name +" " + tel +" " + pw +" " + cpw +" " + trainName + " " + price+" " +" "+description +" "+ gender +" "+ state);
                  
             if(name.equals("") || description.equals("") || state.equals("") || email.equals("") || tel.equals("") || pw.equals("") || cpw.equals("") || trainName.equals("") ){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Please fill all information!');");
                    out.println("location='PersonalTrainerController?action=add';");
                    out.println("</script>");
             
              }else if(udb.addUserInfo( email, name , tel , pw , "trainer")   ){
                     //add trainer table
                     int id = udb.getUserIdByEmail(email);
                     if(db.AddTrainerByWithID(id ,inputStream, trainName , tel, gender, state, price)){
                         out.println("<script type=\"text/javascript\">");
                         out.println("alert('Add Sucessful');");
                         out.println("location='PersonalTrainerController?action=staffTrainer';");
                         out.println("</script>");  
                     }
         }
                                       
                
            
             
         }else if(action.equals("list") ){
            response.setContentType("text/html;charset=UTF-8");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Trainers</title>");            
            out.println("<link rel=\"stylesheet\" href=\"css/trainerDetail.css\">"); 
            out.println("<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"testimonials\">");
            out.println("<div class=\"inner\">");
            out.println("<h1>Our Trainers</h1>");
            out.println("<div class=\"border\"></div>");
            out.println("<div class=\"row\">");
            ArrayList allTrainer = db.getTrainers();
            for(int i = 0; i < allTrainer.size(); i++){
                ict.bean.Trainer trainer = (ict.bean.Trainer) allTrainer.get(i);
                out.println("<div class='col'>");
                out.println("<div class='testimonial'>");
                if(trainer.getImgs() != null){
                    String encode = Base64.getEncoder().encodeToString(trainer.getImgs() );
                    out.println(" <img src='data:image/jpeg;base64, "+ encode + " ' alt='' >");
                }else{
                     out.println( " <img src='img/user.jpg'   >");
                }
                
                out.println("<div class='name'>"+trainer.getName()+"</div>");
                out.println("<div class='stars'><i class='fas fa-star'></i><i class='fas fa-star'></i><i class='fas fa-star'></i><i class='fas fa-star'></i><i class='fas fa-star'></i> </div>");
                out.println("<p>"+trainer.getName()+" is "+trainer.getDescription()+"</p>");
                out.println("</div></div>");
            }
            out.println("</div></div></div>");
            out.println("</body>");
            out.println("</html>");
         }else if(action.equals("edit") ){
                int id = Integer.parseInt(request.getParameter("id"));
                InputStream inputStream = null;
                Part img = request.getPart("img");
                if(img != null ){
                    inputStream = img.getInputStream();
                    String name = request.getParameter("name");
                    String description = request.getParameter("description");
                    String gender = request.getParameter("type");
                    String tstate = request.getParameter("tstate");  
                    int price = Integer. parseInt(request.getParameter("price"));
                    Trainer tr = new Trainer(id ,inputStream ,name,description,gender , tstate,price);
                    if(db.editRecord(tr,inputStream) ){
                         out.println("<script type=\"text/javascript\">");
                         out.println("alert('Trainer Updated!');");
                         out.println("location='PersonalTrainerController?action=staffTrainer';");
                         out.println("</script>");    

                    }                    
                }else{
                    String name = request.getParameter("name");
                    String description = request.getParameter("description");
                    String gender = request.getParameter("type");
                    String tstate = request.getParameter("tstate");  
                    int price = Integer. parseInt(request.getParameter("price"));
                    Trainer tr = new Trainer(id ,name,description,gender , tstate,price);
                    if(db.NotImg(tr) ){
                         out.println("<script type=\"text/javascript\">");
                         out.println("alert('Trainer Updated!');");
                         out.println("location='PersonalTrainerController?action=staffTrainer';");
                         out.println("</script>");    

                    } 
                }
              
         }
   
        
        
    }
    
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new TrainerDB(dbUrl, dbUser, dbPassword);
        udb = new UserDB(dbUrl, dbUser, dbPassword);
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
