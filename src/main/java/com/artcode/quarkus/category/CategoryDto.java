package com.artcode.quarkus.category;

import java.util.UUID;

import lombok.Data;

@Data
public class CategoryDto {

	private UUID id;

	private String name;

	public CategoryDto() {
		super();
	}

	public CategoryDto(UUID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
