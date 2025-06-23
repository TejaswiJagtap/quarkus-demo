package com.artcode.artcode.level;

import com.artcode.quarkus.category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Level {

	@Id
	private String id;

	private String name;

	private String image;

	private String shortName;

	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Level(String id) {
		super();
		this.id = id;
	}

}
