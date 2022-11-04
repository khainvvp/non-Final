package com.example.springJDBC.books.service;

import java.util.Arrays;
import java.util.HashSet;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springJDBC.books.model.Role;
import com.example.springJDBC.books.model.User;
import com.example.springJDBC.books.repository.RoleRepository;
import com.example.springJDBC.books.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
