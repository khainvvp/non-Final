package com.example.springJDBC.books.service;
import com.example.springJDBC.books.model.User;
public interface UserService {

	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(User user);
}
