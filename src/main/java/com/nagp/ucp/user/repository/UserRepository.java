package com.nagp.ucp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagp.ucp.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select u from User u where u.pincode = ?1")
	public List<User> getUsersByPincode(int code);
}
