package com.example.springJDBC.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springJDBC.books.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByRole(String role);

}
