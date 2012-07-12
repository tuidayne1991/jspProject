package csc.traning.wpsj.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import csc.traning.wpsj.dao.Counter;
import csc.traning.wpsj.domain.User;


/**
 * 
 * @author dangthehuynh
 *
 */
public class SessionCreationListener implements HttpSessionListener{


	@Override
	public void sessionCreated(HttpSessionEvent event) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		User user = (User)session.getAttribute("user");
		if(user != null){
			session.removeAttribute("user");
			Counter.remove(user.getUserName());
		}
		
		session.invalidate();
	}


}
