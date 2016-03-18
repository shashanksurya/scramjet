package edu.scramjet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.scramjet.entity.User;
import edu.scramjet.exception.UserNotFoundException;


@Service
public interface IUserService {
	public User create(User u);

	public User update(User u) throws UserNotFoundException;

	public User delete(int id) throws UserNotFoundException;

	public List<User> listUsers();

	public User getUserByEmail(String email) throws UserNotFoundException;
}
