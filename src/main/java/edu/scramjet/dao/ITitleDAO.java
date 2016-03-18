package edu.scramjet.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.scramjet.entity.Title;

@Repository
public interface ITitleDAO {
	public Title addTitle(Title t);

	public Title updateTitle(Title t);

	public Title removeTitle(int id);

	public List<Title> listTitles();

	public Title getTitleById(int id);
}
