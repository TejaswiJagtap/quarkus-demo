package com.artcode.quarkus.category;

import java.util.List;
import java.util.UUID;

import io.quarkus.panache.common.Page;
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
		}
		category.setName(dto.getName());
		category.setDeleted(false);
		category.persist();

		return category;
	}

	public CategoryDto getById(UUID id) {
		CategoryDto dto;
		Category category = Category.findActiveById(id)
				.orElseThrow(() -> new NotFoundException("Category not found or deleted"));
		if (category == null) {
			throw new NotFoundException("Category not found");
		}
		dto = new CategoryDto(id, category.getName());
		return dto;
	}
	
	public List<Category> getAll() {
        return Category.list("isDeleted = false");
    }

    // Get paginated categories
    public List<Category> getAllPaginated(int page, int size) {
        return Category.find("isDeleted = false")
                       .page(Page.of(page, size))
                       .list();
    }

    // Optional: count total for pagination metadata
    public long countActive() {
        return Category.count("isDeleted = false");
    }
}
