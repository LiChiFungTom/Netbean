/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Trainer;
import ict.bean.UserInfo;
import ict.db.TrainerDB;
import ict.db.UserDB;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author User
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
@MultipartConfig
public class RegisterController extends HttpServlet {
    private UserDB db;
    private TrainerDB trainerDB;

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
         String targetURL;
         if(action.equals("edit")){
              int id = Integer.parseInt(request.getParameter("id"));
              String Email = request.getParameter("email");
              String Username = request.getParameter("username");
              String Password = request.getParameter("password");
              String Tel = request.getParameter("tel");
              String State = request.getParameter("type");
              UserInfo user = new UserInfo(id,Email,Username,Password,Tel,State);
              boolean successful = false;
              if(db.editRecord(user)){
                  successful = true;
                  if(user.getState().equals("trainer")){
                      boolean check = trainerDB.checkIsExitTrainer(id);
                      boolean trainerSucc = false;
                      // before is trainer
                      if(check){
                        Trainer trainer = trainerDB.getTrainerById(id);
                        String trainerName = request.getParameter("trainName");  
                        String trainerDes = request.getParameter("trainDes");
                        String trainerGender = request.getParameter("gender");
                        if(trainerDB.updateTrainerSenior(id, trainerName, trainerDes, trainerGender)){
                            trainerSucc = true;
                        }
                        }else{
                            String trainerName = request.getParameter("trainName");
                               InputStream inputStream = null;
                               //Part img = request.getPart("img");
                               //inputStream = img.getInputStream();
                               String trainerDes = request.getParameter("trainDes");
                               String trainerGender = request.getParameter("gender");
                               String tstate = "Free";
                               int price = 0;
                               if(trainerDB.AddTrainerByWithID(id,inputStream, trainerName, trainerDes,trainerGender,tstate,price)){
                                   successful = true;
                               }else{
                                   successful = false;
                               }
                        }
                      
                  }else{
                      boolean check = trainerDB.checkIsExitTrainer(id);
                      // before is trainer
                      if(check){
                          if(trainerDB.delRecord(id)){
                              successful = true;
                          }else{
                              successful = false;
                          }
                      }else{
                          
                      }
                  }
                  if(successful){
                      out.println("<script type=\"text/javascript\">");
                  out.println("alert('Account Updated!');");
                  out.println("location='UserController';");
                  out.println("</script>");
                  }
                  
              }
            }if(action.equals("custEdit")){
              int id = Integer.parseInt(request.getParameter("id"));
              String Email = request.getParameter("email");
              String Username = request.getParameter("username");
              String Password = request.getParameter("password");
              String Tel = request.getParameter("tel");
              String State = "customer";
              UserInfo user = new UserInfo(id,Email,Username,Password,Tel,State);
              if(db.editRecord(user)){
                HttpSession session = request.getSession(true);
                session.setAttribute("userInfo", user);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Update Successful!');");
                out.println("location='custEditPro.jsp';");
                out.println("</script>");
              }
            }else if(action.equals("staffEdit")){
              int id = Integer.parseInt(request.getParameter("id"));
              String Email = request.getParameter("email");
              String Username = request.getParameter("username");
              String Password = request.getParameter("password");
              String Tel = request.getParameter("tel");
              String State = "staff";
              
              UserInfo user = new UserInfo(id,Email,Username,Password,Tel,State);
              if(db.editRecord(user)){
                HttpSession session = request.getSession(true);
                session.setAttribute("userInfo", user);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Update Successful!');");
                out.println("location='staffEditPro.jsp';");
                out.println("</script>");
              }
             
            }else if(action.equals("seniorEdit")){
              int id = Integer.parseInt(request.getParameter("id"));
              String Email = request.getParameter("email");
              String Username = request.getParameter("username");
              String Password = request.getParameter("password");
              String Tel = request.getParameter("tel");
              String State = "senior";
              
              UserInfo user = new UserInfo(id,Email,Username,Password,Tel,State);
              if(db.editRecord(user)){
                HttpSession session = request.getSession(true);
                session.setAttribute("userInfo", user);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Update Successful!');");
                out.println("location='seniorEditPro.jsp';");
                out.println("</script>");
              }
             
            }else if(action.equals("TrainerEdit")){
              int id = Integer.parseInt(request.getParameter("id"));
              String Email = request.getParameter("email");
              String Username = request.getParameter("username");
              String Password = request.getParameter("password");
              String Tel = request.getParameter("tel");
              String State = "trainer";
              
              UserInfo user = new UserInfo(id,Email,Username,Password,Tel,State);
              if(db.editRecord(user)){
                HttpSession session = request.getSession(true);
                session.setAttribute("userInfo", user);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Update Successful!');");
                out.println("location='TrainerEditPro.jsp';");
                out.println("</script>");
              }
             
            }
            
            else if(action.equals("create")){
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String comPassword = request.getParameter("comPassword");
            String tel = request.getParameter("tel");
            String state = request.getParameter("type");
             if(email.equals("") || username.equals("") || password.equals("") || comPassword.equals("") || tel.equals("")){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Please fill all information!');");
                out.println("location='seniorCreate.jsp';");
                out.println("</script>");
            }else{
                if(password.equals(comPassword)){
                    if(action.equals("create")){
                        boolean successful = false;
                       if(db.addUserInfo(email, username, tel, password, state)){
                           successful = true;
                           if(state.equals("trainer")){
                               int id = db.getUserIdByEmail(email);
                               String trainerName = request.getParameter("trainName");
                               InputStream inputStream = null;
                               //Part img = request.getPart("img");
                               //inputStream = img.getInputStream();
                               String trainerDes = request.getParameter("trainDes");
                               String trainerGender = request.getParameter("gender");
                               String tstate = "Free";
                               int price = 0;
                               if(trainerDB.AddTrainerByWithID(id,inputStream, trainerName, trainerDes,trainerGender,tstate,price)){
                                   successful = true;
                               }else{
                                   successful = false;
                               }
                              
                           }
                           if(successful){
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Account Created!');");
                            out.println("location='UserController';");
                            out.println("</script>");
                           }
                           
                       }
                   }
          }else{
              out.println("</script>");
          }
        }
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
         PrintWriter out = response.getWriter();
         String action = request.getParameter("action");
         if(action == null){
             String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String comPassword = request.getParameter("comPassword");
            String tel = request.getParameter("tel");
            String state = "customer";
             if(email.equals("") || username.equals("") || password.equals("") || comPassword.equals("") || tel.equals("")){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Please fill all information!');");
                out.println("location='seniorCreate.jsp';");
                out.println("</script>");
            }else{
                 boolean successful = false;
                if(password.equals(comPassword)){
                    if(db.addUserInfo(email, username, tel, password, state)){
                        successful = true;
                    }
                }
                if(successful){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Account Created!');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                }
            }
         }else{
              processRequest(request, response);
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
        db = new UserDB (dbUrl, dbUser, dbPassword);
        trainerDB = new TrainerDB (dbUrl, dbUser, dbPassword);
        db.CreateUserInfoTable();
        trainerDB.CreateTrainerTable();
     }

}
