package com.bookish.engineering.server.model;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class BookState {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String id; 
	@Persistent
	private BookStateEnum state;
	@Persistent
	private User user;
	
	public BookState(User user, BookStateEnum bookState) {
		this.user = user;
		this.state = bookState;
	}
	
	public BookStateEnum getState() {
		return state;
	}
	public void setState(BookStateEnum state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
}
