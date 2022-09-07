package com.engelvolkers.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.engelvolkers.test.domain.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

	User findByEmailAndPassword(String email, String pass);

}
