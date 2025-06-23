package com.artcode.quarkus.subcategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDto {

	private String id;

	private String name;

	private String categoryId;

	private String categoryName;
	
}
