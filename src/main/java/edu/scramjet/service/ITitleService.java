package edu.scramjet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.scramjet.entity.Title;
import edu.scramjet.exception.TitleNotFoundException;


@Service
public interface ITitleService {
	public Title addTitle(Title t);

	public Title updateTitle(Title t) throws TitleNotFoundException;

	public Title removeTitle(int id) throws TitleNotFoundException;

	public List<Title> listTitles();

	public Title getTitleById(int id) throws TitleNotFoundException;
}
