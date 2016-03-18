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

import edu.scramjet.entity.Title;
import edu.scramjet.exception.TitleNotFoundException;
import edu.scramjet.service.TitleService;

@RestController
@RequestMapping(value="/titles")
public class TitleController {

	@Autowired
	private TitleService titleService;
	
	@RequestMapping(method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public Title create(@RequestBody Title t)
	{
		return titleService.addTitle(t);
	}
	
	@RequestMapping(method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Title> listTitles()
	{
		return titleService.listTitles();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public Title deleteTitleByID(@PathVariable("id") int id) throws TitleNotFoundException
	{
		return titleService.removeTitle(id);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public Title updateTitle(@RequestBody Title t) throws TitleNotFoundException
	{
		return titleService.updateTitle(t);
	}
	
	@RequestMapping(value="/id/{id}/",method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Title getTitleByID(@PathVariable("id") int id, HttpServletRequest request) throws TitleNotFoundException
	{
		//System.out.println(request.getHeader("Accept"));
		return titleService.getTitleById(id);
	}
}
