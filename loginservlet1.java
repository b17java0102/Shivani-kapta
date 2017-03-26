
package pak1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginservlet1 extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
           PrintWriter out = response.getWriter();
            try
            {
           Class.forName("com.mysql.jdbc.Driver");//Loading the driver

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root" , "");//Establishing the connection
           


System.out.println("connetion Established");


           
            Statement stm1=con.createStatement();//creating the statement

    ResultSet rs=stm1.executeQuery("select * from registerlogin");//executing the query
  String s=request.getParameter("user") ;
  String p=request.getParameter("pwd");
while(rs.next())//processing the result
{
if(s.equals(rs.getString(1)))
{
    out.println("valid user name");
}
else
{
    out.println("invlid user");
}
if(p.equals(rs.getString(2)))
{ 
     out.println("valid user password");
}
else
{
    out.println("invlid password");
}
}
}


        
        catch(Exception e)
        {
            System.out.println(e);
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
