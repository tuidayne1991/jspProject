package csc.traning.wpsj.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import csc.traning.wpsj.domain.User;


public class UserProfilesTag extends SimpleTagSupport{
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		User user = getUser();
		if(user != null){
			String profiles = "<div style=\"float: left;width: 200px;text-align: center;\"><fieldset><legend><b>User Profiles</b></legend>";
			profiles +=	"<span style=\"font-size: 12\">" +user.getFirstName()+ " " + user.getLastName();
			profiles += "<br />"+user.getCellphone();
			profiles += "<br />"+user.getEmailAddress();
			profiles += "<br /><a href=\"#\"> Change Password</a>";
			profiles += "</span></fieldset></div>";
			 
			out.print(profiles);
		}
	}

}




	

