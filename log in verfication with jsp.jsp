<%-- 
    Document   : loginjsp
    Created on : Mar 22, 2017, 9:41:54 AM
    Author     : admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
         // String username=request.getParameter("username"));
          // String password=request.getParameter("password"));
           try
           {
        
       
         Class.forName("com.mysql.jdbc.Driver");//Loading the driver

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root" , "");//Establishing the connection
           


System.out.println("connetion Established");


           
            Statement stm1=con.createStatement();//creating the statement

    ResultSet rs=stm1.executeQuery("select * from registerlogin");//executing the query
  String u=request.getParameter("username") ;
  String p=request.getParameter("password");
  
  while(rs.next())//processing the result
{
    if( rs.getString(1).equals(u) && rs.getString(2).equals(p))
    {
   out.println("valid user");
       
        
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


 %>  