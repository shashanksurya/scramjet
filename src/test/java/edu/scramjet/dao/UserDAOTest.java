package edu.scramjet.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
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

import edu.scramjet.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserDAOTest {
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Query query;
	
	@InjectMocks
	private UserDAO userdao = new UserDAO();
	
	private User user;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		user = new User();
		user.setUuid("ff808181539fab2e1539fab35d90000");
		user.setFirstname("Shashank");
		user.setMiddlename("");
		user.setLastname("Timmaraju");
		user.setAge(27);
		user.setGender("M");
		user.setPhone(8504056225L);
		user.setZip("32304");
		
	}
	
	@Test
	public void testCreateUser () {
		userdao.create(user);
		Mockito.verify(session).persist(user);
	}
	
	@Test
	public void testUpdateUser () {
		userdao.update(user);
		Mockito.verify(session).update(user);
	}
	
	@Test
	public void testListUser() {
		List<User> expected = Arrays.asList(user);
		Mockito.when(session.createQuery("from User")).thenReturn(query);
		Mockito.when(query.list()).thenReturn(expected);
		List<User> actual = userdao.listUsers();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUserById () {
		Mockito.when(session.get(User.class, user.getUuid())).thenReturn(user);
		User actual = userdao.getUserByID((user.getUuid()));
		Assert.assertEquals(user, actual);
	}
	
	@Test
	public void testGetUserByPhone(){
		List<User> expected = Arrays.asList(user);
		Mockito.when(session.createQuery("from User where phone = :searchbyphone")).thenReturn(query);
		Mockito.when(query.setParameter("searchbyphone", user.getPhone())).thenReturn(query);
		Mockito.when(query.list()).thenReturn(expected);
		User actual =  userdao.getUserByPhone(user.getPhone());
		Assert.assertEquals(expected.get(0), actual);
	}
	
	@Configuration
	public static class TestConfig {
		
	}
}
