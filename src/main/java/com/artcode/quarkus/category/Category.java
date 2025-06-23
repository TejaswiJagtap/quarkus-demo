package com.artcode.quarkus.category;

import java.util.Optional;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.hibernate.generator.annotations.UUIDGenerator;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends PanacheEntityBase {

	@Id
	@GeneratedValue
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@UUIDGenerator(style = UUIDGenerator.Style.AUTO)
	private String id;

	private String name;

	private boolean isDeleted;

	public Category(String id) {
		super();
		this.id = id;
	}

	public static Optional<Category> findActiveById(String id) {
	    return find("id = ?1 and isDeleted = false", id).firstResultOptional();
	}
}
