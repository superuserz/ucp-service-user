package com.nagp.ucp.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.ucp.common.exception.UCPException;
import com.nagp.ucp.user.entity.User;
import com.nagp.ucp.user.entity.UserTypeEnum;
import com.nagp.ucp.user.repository.UserRepository;
import com.nagp.ucp.user.request.AddUserRequest;
import com.nagp.ucp.user.request.EditUserRequest;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserById(int id) throws UCPException {
		if (userRepository.existsById(id)) {
			return userRepository.findById(id).get();
		} else {
			throw new UCPException("user.not.exist");
		}

	}

	public void deleteUserById(int id) throws UCPException {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new UCPException("user.not.exist");
		}

	}

	public List<User> getUsersByPincode(int code) {
		return userRepository.getUsersByPincode(code);
	}

	public List<User> getUsersByType(String type) {
		return userRepository.getUsersByType(type);
	}

	public void updateUser(EditUserRequest request) throws UCPException {

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
			throw new UCPException("Error While Creating User", e);
		}

	}

	public void createUser(AddUserRequest request) throws UCPException {

		if (userExistsByContactOrEmail(request.getContact(), request.getEmail())) {
			throw new UCPException("user.contact.exist");
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
