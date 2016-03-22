package edu.scramjet.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.scramjet.entity.User;
import edu.scramjet.exception.BadUserException;
import edu.scramjet.exception.UserNotFoundException;
import edu.scramjet.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody User u) throws BadUserException
	{
		return userService.create(u);
	}
	
	@RequestMapping(method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> listUsers()
	{
		return userService.listUsers();
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public User updateUser(@RequestBody User u) throws UserNotFoundException, BadUserException
	{
		return userService.update(u);
	}
}
