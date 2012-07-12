package csc.traning.wpsj.dao;

import java.util.ArrayList;
import java.util.List;

import csc.traning.wpsj.domain.User;

/**
 * 
 * @author dangthehuynh
 *
 */
public class UserDB {
	static List<User> users =  new ArrayList<User>();
	static{
		users.add(new User("hdang","123455","Huynh","Dang","0903888999","hdang@test.com","employee"));
		users.add(new User("htran","123455","Hung","Tran","0903888777","htran@test.com","employee"));
		users.add(new User("dle","123455","Dat","Le","0903888666","dle@test.com","admin"));
	}
	
	public static List<User> getUserList(){
		return users;
	}
	
	public static User getUser(String userName, String password){
		for(User e: users){
			if(e.getUserName().equals(userName) && e.getPassword().equals(password)){
				return e;
			}
		}
		return null;
	}
	
	public static void addUser(User e){
		if(e != null){
			users.add(e);
		}
	}
}
