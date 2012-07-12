package csc.traning.wpsj.dao;

import java.util.HashSet;

/**
 * 
 * @author dangthehuynh
 *
 */
public class Counter {
	static HashSet<String> set = new HashSet<String>();
	
	public static void add(String id){
		set.add(id);
	}
	
	public static int size(){
		return set.size();
	}
	
	public static void remove(String id){
		set.remove(id);
	}
}
