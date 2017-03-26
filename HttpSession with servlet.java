//Http session through servlet.
package pak1;

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
             String s=request.getParameter("combo");
             String u=request.getParameter("user");
             String p=request.getParameter("pwd");
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
        ses.setAttribute("col",s);
        ses.setAttribute("ps",p);
        
        
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
         RequestDispatcher rd=request.getRequestDispatcher("/checkservlet1");
         //rd.include(request,response);
         rd.forward(request,response);
        // response.sendRedirect("checkservlet1");
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


//checkservlet 
package pak1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class checkservlet1 extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
          try
        
        {
           String u=request.getParameter("user");
           
           String p=request.getParameter("pwd");
           
           String s=request.getParameter("combo");
           
           
           HttpSession ses1=request.getSession(false);
           
           
           
           if(ses1!=null && ses1.getAttribute("id").equals(u) &&ses1.getAttribute("ps").equals(p))
           {
              // String s1=(String)ses1.getAttribute("col");
             //  out.println("username=" +ses1.getAttribute("id"));
               out.println("user_name="+u);
               out.println("fav_color="+s);
               
               out.println("<html>");
                out.println("<body bgcolor="+s+">");
                 out.println("</body>");
                  out.println("</html>)");
           }
           else
           {
               out.println("invalid user");
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

     IOException if an I/O error occurs
     */
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
