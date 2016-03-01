package com.bookish.engineering.client.model;

public class BookDetail {
	private String title;
	private String author;
	private String link;
	private String user;
	private String upvote;
	
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUpvote() {
		return upvote;
	}
	public void setUpvote(String upvote) {
		this.upvote = upvote;
	}
	
	@Override
	public int hashCode() {
		int result = 7;
		result = 31*result + title.hashCode();
		result = 31*result + author.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if((obj == null) || (obj.getClass() != this.getClass())) { return false; }
		BookDetail other = (BookDetail) obj;
		if (this.title == null? other.title != null : !this.title.equalsIgnoreCase(other.title)) { return false; }
		if (this.author == null? other.author != null : !this.author.equalsIgnoreCase(other.author)) { return false; }
		return true;
	}
}
