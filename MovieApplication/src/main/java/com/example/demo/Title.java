package com.example.demo;

import javax.validation.constraints.NotEmpty;

public class Title {
	private long id;
	@NotEmpty
	private String title;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
