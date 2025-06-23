package com.artcode.quarkus.subcategory;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.artcode.quarkus.category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
	@GeneratedValue
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private boolean isDeleted;

	public SubCategory(UUID id) {
		super();
		this.id = id;
	}

}
