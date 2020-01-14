package com.example.demo;

import javax.validation.constraints.NotEmpty;

public class Comment {
	private long id;
	@NotEmpty
	private String comment;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
