import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Simple_Java_Sparrow_Database_Access extends JFrame {

	public static void main(String[] args)   {
        try 
        {
        // loading thejdbc odbc driver
        //  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        // creating connection toth data base
      	
        Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:/LAMAR/Fall 2016/Database Design/Project Part-1/Project Part 2/sparrow_organization.accdb");
        System.out.println("connection established.");
        
        Statement st = con.createStatement();
        // create an execute sql command on database    
        ResultSet rs = st.executeQuery("Select * from Apartment, Payment");
        ResultSetMetaData rsmd = rs.getMetaData();
        // this getColumnCount return the number of column in the selected table
        
        
        int numberOfColumns = rsmd.getColumnCount();
       // while loop and with while loop code use for print the data
       while (rs.next()) 
            {
            for (int i = 1; i <= numberOfColumns; i++) 
                 {
                     if (i > 1)
                    //System.out.print(numberOfColumns);
                     
                    System.out.print(" , ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                 }
             System.out.println("");
             }
      st.close();
      con.close();
         } catch (Exception ex)
                   {
                System.err.print("Exception: ");
                System.err.println(ex.getMessage());
                   }
 }
}
