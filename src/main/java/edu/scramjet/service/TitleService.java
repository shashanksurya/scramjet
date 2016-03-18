package edu.scramjet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scramjet.dao.ITitleDAO;
import edu.scramjet.entity.Title;
import edu.scramjet.exception.TitleNotFoundException;

@Service
public class TitleService implements ITitleService {

	@Autowired
	private ITitleDAO titleDao;
	
	@Override
	public Title addTitle(Title t) {
		return titleDao.addTitle(t);
	}

	@Override
	public Title updateTitle(Title t) throws TitleNotFoundException{
		Title title = titleDao.updateTitle(t);
		if(title==null)
		{
			throw new TitleNotFoundException();
		}
		else
			return title;
	}

	@Override
	public Title removeTitle(int id) throws TitleNotFoundException{
		Title title =  titleDao.removeTitle(id);
		if(title==null)
		{
			throw new TitleNotFoundException();
		}
		else
			return title;
	}

	@Override
	public List<Title> listTitles() {
		return titleDao.listTitles();
	}

	@Override
	public Title getTitleById(int id) throws TitleNotFoundException{
		Title title= titleDao.getTitleById(id);
		if(title==null)
		{
			throw new TitleNotFoundException();
		}
		else
			return title;
	}

}
