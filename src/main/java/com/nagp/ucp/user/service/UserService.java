package com.nagp.ucp.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.ucp.common.exception.UCPException;
import com.nagp.ucp.user.controller.UserController;
import com.nagp.ucp.user.entity.User;
import com.nagp.ucp.user.entity.UserTypeEnum;
import com.nagp.ucp.user.repository.UserRepository;
import com.nagp.ucp.user.request.AddUserRequest;
import com.nagp.ucp.user.request.EditUserRequest;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	public User getUserById(final int id) throws UCPException {

		if (userRepository.existsById(id)) {
			return userRepository.findById(id).get();
		} else {
			throw new UCPException("ucp.service.user.001", "User Does Not Exist");
		}

	}

	public void deleteUserById(final int id) throws UCPException {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new UCPException("ucp.service.user.001", "User Does Not Exist");
		}

	}

	public List<User> getUsersByPincode(final int code) {
		return userRepository.getUsersByPincode(code);
	}

	public User getProvider(final int providerId) {
		User user = userRepository.getProviderById(providerId);
		if (null == user) {
			throw new UCPException("ucp.service.user.001", "Provider Does Not Exist");
		}
		return user;
	}

	public List<User> getUsersByType(final String type) {
		return userRepository.getUsersByType(type);
	}

	public void updateUser(final EditUserRequest request) throws UCPException {

		try {
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
		} catch (Exception e) {
			throw new UCPException("ucp.service.user.002", "Error While Updating User Details");
		}

	}

	public void createUser(final AddUserRequest request) throws UCPException {

		if (userExistsByContactOrEmail(request.getContact(), request.getEmail())) {
			throw new UCPException("ucp.service.user.003", "Contact Already Exists");
		}

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

	private boolean userExistsByContactOrEmail(String contact, String email) {
		List<User> user = userRepository.getUsersByContactOrEmail(contact, email);
		return (null != user && !user.isEmpty());

	}

}
