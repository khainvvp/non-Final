package com.example.springJDBC.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springJDBC.books.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
