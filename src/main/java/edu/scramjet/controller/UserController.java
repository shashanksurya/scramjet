package edu.scramjet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.scramjet.entity.User;
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
	public User create(@RequestBody User u)
	{
		return userService.create(u);
	}
	
	@RequestMapping(method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> listUsers()
	{
		return userService.listUsers();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public User deleteUserbyID(@PathVariable("id") int id) throws UserNotFoundException
	{
		return userService.delete(id);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public User updateUser(@RequestBody User u) throws UserNotFoundException
	{
		return userService.update(u);
	}
	
	@RequestMapping(value="/email/{email}/",method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUserByEmail(@PathVariable("email") String email, HttpServletRequest request) throws UserNotFoundException
	{
		System.out.println(request.getHeader("Accept"));
		return userService.getUserByEmail(email);
	}
}
