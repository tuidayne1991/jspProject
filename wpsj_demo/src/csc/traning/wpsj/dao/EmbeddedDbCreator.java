package csc.traning.wpsj.dao;

import java.sql.*;
import java.util.*;

import csc.traning.wpsj.domain.Employee;

/**
 * 
 * @author dangthehuynh
 *
 */

public class EmbeddedDbCreator {
  // Driver class not needed in JDBC 4.0 (Java SE 6)
  private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
  private String protocol = "jdbc:derby:";
  private String username = "hdang";
  private String password = "123455";
  private String dbName = "demodb";
  private String tableName = "employees";
  private Properties userInfo;
  
  public EmbeddedDbCreator() {
    userInfo = new Properties();
    userInfo.put("user", username);
    userInfo.put("password", password);
  }
  
  public void createDatabase() throws Exception {

	  Employee[] employees = {
			  new Employee("Huynh", "Dang", 1119363, "0903853803", "Delivery 3", 1),
			  new Employee("Minh", "Tran", 1119362, "0903855755", "Human Resource", 1),
			  new Employee("Duyen", "Pham", 1119361, "0903665994", "Accounting", 0)
	  };
    try {
      String dbUrl = protocol + dbName + ";create=true";
      Class.forName(driver);
      Connection connection = 
        DriverManager.getConnection(dbUrl, userInfo);
      Statement statement = connection.createStatement();
      String format = "VARCHAR(20)";
      String tableDescription = 
        String.format
          ("CREATE TABLE %s" +
              "(id INT, firstname %s, lastname %s, " +
        		    "cellphone VARCHAR(11), department %s, sex INT)",
        	tableName, format, format, format);
      statement.execute(tableDescription);
      
      String template =
        String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?,?)",
                      tableName);
      PreparedStatement inserter = 
    	        connection.prepareStatement(template);
      for(Employee e: employees) {
    	  inserter.setInt(1, e.getEmployeeId());
    	  inserter.setString(2, e.getFirstName());
    	  inserter.setString(3, e.getLastName());
    	  inserter.setString(4, e.getCellphone());
    	  inserter.setString(5, e.getDepartment());
    	  inserter.setInt(6, e.getSex());
    	  
    	  inserter.executeUpdate();
          System.out.printf("Inserted %s %s.%n", 
                            e.getFirstName(), 
                            e.getLastName());
      }
      inserter.close();
      connection.close();
    } catch (SQLException sqle) {
      // If table already exists, then skip everything else
    	//sqle.printStackTrace();
    }
  }
  
  public void showTable() {
    try {
      String dbUrl = protocol + dbName;
      Connection connection = DriverManager.getConnection(dbUrl, userInfo);
      Statement statement = connection.createStatement();
      String query = String.format("SELECT * FROM %s", tableName);
      ResultSet resultSet = statement.executeQuery(query);
      while(resultSet.next()) {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("firstname");
        String lastName = resultSet.getString("lastname");

        System.out.printf("%s %s %s %n",
                          firstName, lastName, id);
      }
      connection.close();
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }  
  }
  
  public static void main(String[] args) {
    EmbeddedDbCreator tester = new EmbeddedDbCreator();
    //tester.createDatabase();
    tester.showTable();
  }
}
