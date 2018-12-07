import java.sql.*;

public class Main {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/myDBChwalczuk";

   static final String USER = "MChwalczuk";
   static final String PASS = "password";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   Boolean baseExist = false;
   String sql;
  
   try{
      Class.forName("com.mysql.cj.jdbc.Driver");
	   
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);	   
      	   
      System.out.println("Check if table in base exist");
      DatabaseMetaData md = conn.getMetaData();
      ResultSet rs = md.getTables(null, null, "Info", null);
      while (rs.next()) {
            System.out.println("Table Exist");
	    baseExist = true;
      }
      rs = null;  
      if(!baseExist){
      	System.out.println("Creating table");
      	stmt = conn.createStatement();
      	sql = "CREATE TABLE Info (PersonID int, LastName varchar(255), FirstName varchar(255))";
      	stmt.executeUpdate(sql);
      	stmt = null;
      }
	   
      stmt = conn.createStatement();
      System.out.println("Inserting data to table");
      sql = "INSERT INTO Info (PersonID, LastName, FirstName) VALUES (1, 'Mateusz', 'Chwalczuk')";
      stmt.executeUpdate(sql);	 
      stmt = null;
	   
      stmt = conn.createStatement();
      sql = "SELECT PersonID, FirstName, LastName FROM Info";
      rs = stmt.executeQuery(sql);

      while(rs.next()){
         int id  = rs.getInt("PersonID");
         String firstName = rs.getString("FirstName");
         String lastName = rs.getString("LastName");

	 System.out.println("Load informations from database");
         System.out.println("ID: " + id);
         System.out.println("First name: " + firstName);
         System.out.println("Last name: " + lastName);
      }
      rs.close();
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
	 if(conn!=null)
            conn.close();
      }catch(SQLException ex){
      }
   }
 }
}
