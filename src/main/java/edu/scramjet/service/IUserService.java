package edu.scramjet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.scramjet.entity.User;
import edu.scramjet.exception.BadUserException;
import edu.scramjet.exception.UserNotFoundException;


@Service
public interface IUserService {
	public User create(User u) throws BadUserException;

	public User update(User u) throws UserNotFoundException, BadUserException;

	public List<User> listUsers();
}
