package com.nagp.ucp.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.ucp.user.entity.User;
import com.nagp.ucp.user.entity.UserTypeEnum;
import com.nagp.ucp.user.repository.UserRepository;
import com.nagp.ucp.user.request.AddUserRequest;
import com.nagp.ucp.user.request.EditUserRequest;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}

	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	public List<User> getUsersByPincode(int code) {
		return userRepository.getUsersByPincode(code);
	}

	public void updateUser(EditUserRequest request) {
		User user = new User();
		user.setId(request.getId());
		user.setName(request.getName());
		user.setCity(request.getCity());
		user.setContact(request.getContact());
		user.setEmail(request.getEmail());
		user.setPincode(request.getPincode());
		user.setState(request.getState());
		user.setWalletBalance(request.getWalletBalance());
		user.setPremium(request.isPremium());
		user.setType(UserTypeEnum.parse(request.getType()));

		userRepository.save(user);
	}

	public void createUser(AddUserRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setCity(request.getCity());
		user.setContact(request.getContact());
		user.setEmail(request.getEmail());
		user.setPincode(request.getPincode());
		user.setState(request.getState());
		user.setWalletBalance(request.getWalletBalance());
		user.setPremium(request.isPremium());
		user.setType(UserTypeEnum.parse(request.getType()));

		userRepository.save(user);
	}

}
