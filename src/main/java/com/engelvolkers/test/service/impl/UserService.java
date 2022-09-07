/**
 * 
 */
package com.engelvolkers.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engelvolkers.test.domain.entity.User;
import com.engelvolkers.test.repository.IUserRepository;
import com.engelvolkers.test.service.IUserService;

import lombok.AllArgsConstructor;

/**
 * @author Guilherme Vital
 *
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements IUserService {
	
	private IUserRepository repository;
	
	@Override
	public User findByEmailAndPassword(String email, String pass) {
		return repository.findByEmailAndPassword(email, pass);
	}

}
