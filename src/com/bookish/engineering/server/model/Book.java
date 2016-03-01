package com.bookish.engineering.server.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Book {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String title;
	@Persistent
	private String author;
	@Persistent
	private String link;
	@Persistent
	private Date createDate;
	@Persistent
	private Integer upvote;
	@Persistent
	private String user;
	@Persistent
	private BookState bookstate;
	
	public Book() {
		this.bookstate = new BookState(new User("none"), BookStateEnum.ORPHAN);
		this.upvote = 0;
		this.createDate = new Date();
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public BookState getBookstate() {
		return bookstate;
	}

	public void setBookstate(BookState bookstate) {
		this.bookstate = bookstate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Integer getUpvote() {
		return upvote;
	}

	public void setUpvote(Integer upvote) {
		this.upvote = upvote;
	}

	public String getUser() {
		return this.bookstate.getUser().getName();
	}
}
