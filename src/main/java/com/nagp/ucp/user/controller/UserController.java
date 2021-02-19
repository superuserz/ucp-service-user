package com.nagp.ucp.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.ucp.common.exception.UCPException;
import com.nagp.ucp.common.responses.BaseResponse;
import com.nagp.ucp.user.entity.User;
import com.nagp.ucp.user.request.AddUserRequest;
import com.nagp.ucp.user.request.EditUserRequest;
import com.nagp.ucp.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	@ApiOperation(value = "Gets User Details by ID")
	public BaseResponse<User> getUser(@PathVariable int id) throws UCPException {
		return new BaseResponse<>(userService.getUserById(id));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes a User by ID")
	public void deleteUser(@PathVariable int id) throws UCPException {
		userService.deleteUserById(id);
	}

	@PostMapping
	@ApiOperation(value = "Edit Existing User")
	public void updateUser(@RequestBody EditUserRequest request) throws UCPException {
		userService.updateUser(request);
	}

	@PutMapping
	@ApiOperation(value = "Create a New User")
	public void createUser(@RequestBody AddUserRequest request) throws UCPException {
		userService.createUser(request);
	}

	@GetMapping("/pincode/{pincode}")
	@ApiOperation(value = "Gets User Details by Pincode")
	public List<User> getUserByPincode(@PathVariable int pincode) throws UCPException {
		return userService.getUsersByPincode(pincode);
	}

	@GetMapping("/{type}/all")
	@ApiOperation(value = "Gets Users belonging to a particular type")
	public List<User> getUsersByType(@PathVariable String type) throws UCPException {
		return userService.getUsersByType(type);
	}

}
