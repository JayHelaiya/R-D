package com.jay.swagger.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jay.swagger.dao.model.User;


@Component
public class UserDao {
	
	private static List<User> users=new ArrayList();
	
	private static int userCount=3;
	
	static {
		users.add(new User(1, "jay", new Date()));
		users.add(new User(2, "dip", new Date()));
		users.add(new User(3, "anu", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		
		if(user.getUserId()==0){
			user.setUserId(++userCount);
		}	
		users.add(user);
		return user;	
	}
	
	public User findOne(int id) {
		
		for (User user : users) {
			if(user.getUserId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		
		Iterator<User> iterator = users.iterator();

		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getUserId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
