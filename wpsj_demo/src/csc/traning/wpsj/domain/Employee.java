package csc.traning.wpsj.domain;

/**
 * 
 * @author dangthehuynh
 *
 */
public class Employee{
	private String firstName;
	private String lastName;
	private int employeeId;
	private String cellphone;
	private String department;
	private int sex;

	public Employee(String firstName, String lastName, int employeeId,
			String cellphone, String department, int sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
		this.cellphone = cellphone;
		this.department = department;
		this.sex = sex;
	}
	
	public Employee(){}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	
}
