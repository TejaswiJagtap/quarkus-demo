package com.artcode.quarkus.category;

import lombok.Data;

@Data
public class CategoryDto {

	private String id;

	private String name;

	public CategoryDto() {
		super();
	}

	public CategoryDto(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
