package com.visitpi.visitor.service;

import java.util.List;

import com.visitpi.visitor.exception.CollectionException;
import com.visitpi.visitor.model.User;

public interface UserService {
	
public List<User> getAllUsers();
	
	public User getSingleUser(String id) throws CollectionException;
	
	public void createUser(User user) throws CollectionException;
	
	public void updateUser(String id, User user) throws CollectionException;
	
	public void deleteUserById(String id) throws CollectionException;

}
