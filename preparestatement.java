//jdbc connection with preparedStatement  
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class preparestatement {

  
    public static void main(String[] args) {
     
        try
        {
            Class.forName("com.mysql.jdbc.Driver");//Loading the driver

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root" , "");//Establishing the connection
           


System.out.println("connetion Established");


           
            Statement stm1=con.createStatement();//creating the statement

    ResultSet rs=stm1.executeQuery("select * from registerlogin");//executing the query
           
while(rs.next())
{
System.out.println(rs.getString(1)+" "+rs.getString(2));

}
/*int row=stm1.executeUpdate("insert into student values(101,'Alisha')");//executing the query
if(row>0)
    System.out.println("Record inserted successfully");
  else
    System.out.println("Record not inserted successfully");
        */
   PreparedStatement pstmt=con.prepareStatement("insert into registerlogin values(?,?,?)");
   BufferedReader br=new BufferedReader(new InputStreamReader(System.in));    
   System.out.println("Enter Roll Number");
   int r=Integer.parseInt(br.readLine());
   System.out.println("Enter Name");
   String n=br.readLine();
    System.out.println("Enter class");
    String c=br.readLine();
   pstmt.setInt(1, r);
   pstmt.setString(2, n);
   pstmt.setString(2,c);
   int res=pstmt.executeUpdate();
        
        
        
        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       
    }
   
}



