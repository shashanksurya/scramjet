package edu.scramjet.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.scramjet.dao.UserDAO;
import edu.scramjet.entity.User;
import edu.scramjet.exception.BadUserException;
import edu.scramjet.exception.UserNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {
	
	@Mock
	private UserDAO userdao;
	
	@InjectMocks
	private UserService userservice = new UserService();
	
	private User user;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setUuid("ff808181539fab2e1539fab35d90000");
		user.setFirstname("Shashank");
		user.setMiddlename("gg");
		user.setLastname("Timmaraju");
		user.setAge(27);
		user.setGender("M");
		user.setPhone(8504056225L);
		user.setZip("32304");
		
	}
	
	@Test
	public void createUser() throws BadUserException{
		Mockito.when(userdao.getUserByPhone(user.getPhone())).thenReturn(null);
		user.setUuid("0fda");
		userservice.create(user);
		Mockito.verify(userdao).create(user);
	}
	
	@Test(expected=BadUserException.class)
	public void createUserException() throws BadUserException{
		Mockito.when(userdao.getUserByPhone(user.getPhone())).thenReturn(user);
		userservice.create(user);
		Mockito.verify(userdao).create(user);
	}
	
	@Test(expected=BadUserException.class)
	public void createUserExceptionNoPhone() throws BadUserException{
		Mockito.when(userdao.getUserByPhone(user.getPhone())).thenReturn(null);
		user.setPhone(0);
		userservice.create(user);
		Mockito.verify(userdao).create(user);
	}
	
	@Test
	public void updateUser() throws UserNotFoundException, BadUserException{
		Mockito.when(userdao.getUserByID(user.getUuid())).thenReturn(user);
		user.setFirstname("Shashank Surya");
		userservice.update(user);
		Mockito.verify(userdao).update(user);
	}
	
	
	@Test(expected=UserNotFoundException.class)
	public void updateUserDoesNotExist() throws UserNotFoundException, BadUserException{
		Mockito.when(userdao.getUserByID(user.getUuid())).thenReturn(null);
		userservice.update(user);
		Mockito.verify(userdao).update(user);
	}
	
	@Configuration
	public static class TestConfig {
		
	}

}
