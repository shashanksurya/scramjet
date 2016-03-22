package edu.scramjet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scramjet.dao.IUserDAO;
import edu.scramjet.entity.User;
import edu.scramjet.exception.BadUserException;
import edu.scramjet.exception.UserNotFoundException;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public User create(User u) throws BadUserException {
		if(!verifyUser(u)||!(userDAO.getUserByPhone(u.getPhone())==null))
		{
			System.out.println("here");
			throw new BadUserException();
		}
		else
			return userDAO.create(u);
	}

	private boolean verifyUser(User u) {
		if(!u.getFirstname().matches("[a-zA-Z ]+"))
		{
			System.out.println("firstname wrong");
			return false;
		}
			
		if(!u.getLastname().matches("[a-zA-Z ]+"))
		{
			System.out.println("lastname wrong");
			return false;
		}
		if(u.getMiddlename()!=null)
			if(!u.getMiddlename().matches("[a-zA-Z ]+"))
			{
				System.out.println("middlename wrong"+u.getMiddlename());
				return false;
			}
		//Assuming humans cannot live more than 150 years
		if(u.getAge()<0 || u.getAge()>150)
		{
			System.out.println("age wrong");
			return false;
		}
		if(!(u.getGender().equals("M") || u.getGender().equals("F")))
		{
			System.out.println("gender wrong "+u.getGender());
			return false;
		}
		if(u.getPhone()<0 || (int)(Math.log10(u.getPhone())+1)!=10)
		{
			System.out.println("phone wrong");
			return false;
		}
		return true;
	}

	@Override
	public User update(User u) throws UserNotFoundException, BadUserException {
		User user = userDAO.getUserByID(u.getUuid());
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		else if(verifyUser(u))
			return userDAO.update(u);
		else
			throw new BadUserException();
	}

	@Override
	public List<User> listUsers() {
		return userDAO.listUsers();
	}
}
