
package pack2;

import java.sql.*;

public class jdbcinsertion {


    public static void main(String[] args) {
         try
        {
         Class.forName("com.mysql.jdbc.Driver");//Loading the driver

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root" , "");//Establishing the connection
           


System.out.println("connetion Established");


           
          Statement stm1=con.createStatement();//creating the statement

   // int row =stm1.executeUpdate( "insert into student values(106,'abhi','mca')");//executing the query
   //int  row =stm1.executeUpdate( "insert into student values(108,'abhilasha','mca')");//executing the query
    //int row =stm1.executeUpdate( "insert into student values(109,'abhi','mca')");
    String sql=("UPDATE student "+
            "SET name= 'shivala' where rollno  in(106)");
   
   stm1.executeUpdate(sql);
 /*  if(row>0) //processing query
    {
    System.out.print("r0w inserted") ;  
    }*/
/*while(row.next())//processing the result 
{
System.out.println(row.getInt(1)+" "+row.getString(2)+" "+row.getString(3));

}  */                                                                          


        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
}    