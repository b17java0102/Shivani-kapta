//use of hidden field with butten
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

/**
 *
 * @author admin
 */
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
    
         //rd.include(request,response);
         //rd.forward(request,response);
        // response.sendRedirect("checkservlet1");
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
