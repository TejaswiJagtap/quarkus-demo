package com.artcode.quarkus.subcategory;

import com.artcode.quarkus.category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {

	@Id
	private String id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private boolean isDeleted;

	public SubCategory(String id) {
		super();
		this.id = id;
	}

}
