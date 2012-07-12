package csc.traning.wpsj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import csc.traning.wpsj.domain.Employee;

/**
 * 
 * @author dangthehuynh
 *
 */
public class EmployeesDB {
	private final static String username = "hdang";
	private final static String password = "123455";
	private final static String dbName = "demodb";
	private final static String tableName = "employees";
	private final static String url = "jdbc:derby:" + dbName;
	private final static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	  
	public static List<Employee> getEmployeeList() throws Exception{
		List<Employee> employees =  new ArrayList<Employee>();
		try {
		      Connection connection = getConnection();
		      Statement statement = connection.createStatement();
		      String query = String.format("SELECT * FROM %s", tableName);
		      ResultSet resultSet = statement.executeQuery(query);
		      while(resultSet.next()) {
		        int id = resultSet.getInt("id");
		        String firstName = resultSet.getString("firstname");
		        String lastName = resultSet.getString("lastname");
		        String cellphone = resultSet.getString("cellphone");
		        String department = resultSet.getString("department");
		        int sex = resultSet.getInt("sex");
		        
		        employees.add(new Employee(firstName, lastName, id, cellphone, department, sex));

		      }
		      connection.close();
		    } catch (SQLException sqle) {
		      throw sqle;
		    }
		return employees;
	}
	
	public static Employee getEmployeeById(int employeeId) throws Exception{
		for(Employee e: getEmployeeList()){
			if(e.getEmployeeId() == employeeId){
				return e;
			}
		}
		return null;
	}
	
	public static void addEmployee(Employee e) throws Exception{
		if(e != null){
			String insertSql =
		        String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?,?)", tableName);
			Connection connection = getConnection();
			PreparedStatement inserter = connection.prepareStatement(insertSql);
			
			inserter.setInt(1, e.getEmployeeId());
	    	inserter.setString(2, e.getFirstName());
	    	inserter.setString(3, e.getLastName());
	    	inserter.setString(4, e.getCellphone());
	    	inserter.setString(5, e.getDepartment());
	    	inserter.setInt(6, e.getSex());
	    	inserter.execute();
	    	
	    	// Close stm and con
	    	inserter.close();
	    	connection.close();
		}
	}
	
	public static void updateEmployee(Employee e) throws Exception{
		if(e != null){
			String updateSql = String.format("UPDATE %s SET firstname =?, " +
					"lastname=?, cellphone=?,department=?,sex=? WHERE id=?", tableName);
			Connection connection = getConnection();
			PreparedStatement updator = connection.prepareStatement(updateSql);
			
			updator.setString(1, e.getFirstName());
			updator.setString(2, e.getLastName());
			updator.setString(3, e.getCellphone());
			updator.setString(4, e.getDepartment());
			updator.setInt(5, e.getSex());
			updator.setInt(6, e.getEmployeeId());
			updator.execute();
			
			// Close stm and con
			updator.close();
	    	connection.close();
		}
	}
	
	public static Connection getConnection() 
		      throws Exception {
		    // Load database driver if it's not already loaded.
		    // Not needed in JDBC 4 (Java SE 6). Uncomment for earlier versions.
		    Class.forName(driver);
		    
		    // Establish network connection to database.
		    Properties userInfo = new Properties();
		    userInfo.put("user", username);
		    userInfo.put("password", password);
		    Connection connection =
		      DriverManager.getConnection(url, userInfo);
		    return(connection);
		  }

}
