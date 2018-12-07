import java.sql.*;

public class Main {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/myDBChwalczuk";

   static final String USER = "user";
   static final String PASS = "pass123";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");

      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      stmt = conn.createStatement();
      String sql1, sql2, sql3;
      sql1 = "CREATE TABLE Persons (PersonID int, LastName varchar(255), FirstName varchar(255))";
      sql2 = "INSERT INTO Persons (PersonID, LastName, FirstName) VALUES (1, 'Mateusz', 'Chwalczuk')";
      sql3 = "SELECT ID, FirstName, LastName FROM Persons";

      ResultSet rs1 = stmt.executeQuery(sql2);
      ResultSet rs2 = stmt.executeQuery(sql3);
      ResultSet rs3 = stmt.executeQuery(sql3);

      while(rs3.next()){
         int id  = rs3.getInt("PersonID");
         String first = rs3.getString("FirstName");
         String last = rs3.getString("LastName");
		 
         System.out.println("ID: " + id);
         System.out.println(", First: " + first);
         System.out.println(", Last: " + last);
      }
      rs3.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}
