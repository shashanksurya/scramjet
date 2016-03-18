package edu.scramjet.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.scramjet.entity.User;

@Repository
public interface IUserDAO {
	public User create(User u);

	public User update(User u);

	public User delete(int id);

	public List<User> listUsers();

	public User getUserByEmail(String email);
}
