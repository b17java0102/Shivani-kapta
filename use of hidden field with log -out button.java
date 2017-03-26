//use of hidden field with button

//loginform.html
<!DOCTYPE html>
<html>
    <head>
        <title>log in</title>
    </head>
     <body>
    <form action ="loginservlet">
       
        <font size=6 color="red" >UserName</font>
        <input type="text"  name="username" value="" required="" ><br><br>
        <font size=6 color="red" >Password</font>
        <input type="password" name="password" value="" required="" ><br><br>
         
            <font size=6 color="red" >favorite color</font>    
            <select name="combo">
              <option value="blue" style="background:blue">blue</option>
              <option value="red" style="background:red">red</option>
              <option value="pink" style="background:pink">pink</option>
              <option value="purple" style="background:purple">purple</option>
            </select><br/><br/>
             <input type="submit"  value="login"            </form>
    </body>
    
    
</html

//loginservlet

package pac1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginservlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            PrintWriter out = response.getWriter();
            try
            {
         
             String u=request.getParameter("username");
             String p=request.getParameter("password");
           Class.forName("com.mysql.jdbc.Driver");//Loading the driver

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root" , "");//Establishing the connection
           


out.println("connetion Established");


           
            Statement stm1=con.createStatement();//creating the statement

  //  ResultSet rs=stm1.executeQuery("select * from registerlogin");//executing the query
      PreparedStatement ps=con.prepareStatement("select * from registerlogin where username=? and password=?");
      
      
      ps.setString(1,u);
      ps.setString(2,p);
      ResultSet rs=ps.executeQuery();
while(rs.next())//processing the result
{
    if( rs.getString(1).equals(u) && rs.getString(2).equals(p))
    {
        out.println("valid user");
        HttpSession ses=request.getSession(true);
        ses.setAttribute("id",u);
       
       ses.setAttribute("ps",p);
        
         RequestDispatcher rd=request.getRequestDispatcher("/homepage.jsp");
        rd.forward(request,response);
    }
    else
    {
        out.println("invalid or invalid password");
    }
}
        }
         catch(Exception e)
                 {
                 out.println(e);
                 }
    
                 } 
                
                
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}



//homepage.jsp
<%-- 
    Document   : homepage
    Created on : Mar 22, 2017, 1:43:46 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
  
        
          String u=request.getParameter("username");
           String p=request.getParameter("password");
           HttpSession ses1=request.getSession(false);
           if(ses1!=null && ses1.getAttribute("id").equals(u) &&ses1.getAttribute("ps").equals(p))
           {
            u=(String)ses1.getAttribute("id");}
           else
           {
           out.println("invalid user");}%>
           
           <form action="logout1">
               <input type="hidden" name="u" value="<%=u%>">
               <input type="submit" value="logout">
               
                          
           </form>
           
                    
    </body>
</html>
%>
//logout1 servlet

package pac1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class logout1 extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        PrintWriter out = response.getWriter(); 
     try
        {
            
            
             HttpSession ses1=request.getSession(true);
            
            String s=request.getParameter("u");
             
        
    
           if(s!=null)
           {
             out.println(s);
           }
          s=null;
           
             response.sendRedirect("loginform.html");
             
           
        }
        
           catch(Exception e)
           {
                   out.println(e);
                   }
    }
 
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
