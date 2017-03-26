
package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class logincontx extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
            PrintWriter out = response.getWriter();
             try 
        {
           // String u=request.getParameter("username");
                 ServletConfig config=getServletConfig();
                 String oil= config.getInitParameter("oil");
                  String shirt= config.getInitParameter("shirt");
                   out.println("**** init parameter***");
                        out.println("<br>");
                         
                      out.println("first init parameter oil="+oil); 
                      out.println("<br>");
                         
                        out.println("second init parameter shirt="+shirt); 
                        
                        
                    ServletContext context=config.getServletContext();
                     String username =context.getInitParameter("username");
                     
                     out.println("<br>");
                     
                     out.println("**** context parameter***");
                     out.println("<br>");
                        
                     out.println("context first parameter username="+username);     
                        
                       
                       
        }
         
             catch(Exception e)
             {
                 out.println(e);
             }
              RequestDispatcher rd = request.getRequestDispatcher("/servletcontx");
        rd.include(request,response);
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
