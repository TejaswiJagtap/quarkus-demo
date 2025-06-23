package com.artcode.quarkus.category;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoryService {

	public Category saveOrUpdate(CategoryDto dto) {
		Category category;

		if (dto.getId() != null) {
			category = Category.findActiveById(dto.getId())
					.orElseThrow(() -> new NotFoundException("Category not found or deleted"));
		} else {
			category = new Category();
			category.setId(UUID.randomUUID().toString());
		}
		category.setName(dto.getName());
		category.setDeleted(false);
		category.persist();

		return category;
	}

	public CategoryDto getById(String id) {
		CategoryDto dto;
		Category category = Category.findActiveById(id)
				.orElseThrow(() -> new NotFoundException("Category not found or deleted"));
		if (category == null) {
			throw new NotFoundException("Category not found");
		}
		dto = new CategoryDto(id, category.getName());
		return dto;
	}
}
