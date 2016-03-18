package edu.scramjet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.scramjet.dao.IUserDAO;
import edu.scramjet.dao.UserDAO;
import edu.scramjet.entity.User;
import edu.scramjet.exception.UserNotFoundException;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public User create(User u) {
		return userDAO.create(u);
	}

	@Override
	public User update(User u) throws UserNotFoundException {
		User user = userDAO.update(u);
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		else
			return user;
	}

	@Override
	public User delete(int id) throws UserNotFoundException {
		User user = userDAO.delete(id);
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		else
			return user;
	}

	@Override
	public List<User> listUsers() {
		return userDAO.listUsers();
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException{
		User user = userDAO.getUserByEmail(email);
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		else
			return user;
	}
}
