package csc.traning.wpsj.domain;

/**
 * 
 * @author dangthehuynh
 *
 */
public class User {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String cellphone;
	private String emailAddress;
	private String role;
	
	public User(String userName, String password, String firstName,
			String lastName, String cellphone, String emailAddress, String role) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cellphone = cellphone;
		this.emailAddress = emailAddress;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getRole(){
		return role;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}
