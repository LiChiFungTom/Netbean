/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author User
 */
public class income extends SimpleTagSupport{
    private int num1;
    private int num2;
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            int result = num1*num2;
            out.println(result);
        } catch (IOException e) {
            
        }
     
    }
    public void setNum1(int num1){
        this.num1=num1;
    }
     public void setNum2(int num2){
        this.num2=num2;
    }
     
     public int getNum1(){
         return num1;
     }
     public int getNum2(){
         return num2;
     }

}
