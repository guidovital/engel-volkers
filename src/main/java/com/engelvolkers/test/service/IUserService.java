package com.engelvolkers.test.service;

import com.engelvolkers.test.domain.entity.User;

public interface IUserService {

	User findByEmailAndPassword(String email, String pass);

}
