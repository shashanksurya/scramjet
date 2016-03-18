package edu.scramjet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import edu.scramjet.entity.Title;
import edu.scramjet.entity.User;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@Entity
@Table(name = "Rating")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Data public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "rating")
	private int rating;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "title_Id", nullable = false)
	private Title title;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_Id", nullable = false)
	private User user;
}
