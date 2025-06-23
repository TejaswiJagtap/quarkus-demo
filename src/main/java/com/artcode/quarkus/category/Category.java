package com.artcode.quarkus.category;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID id;

	private String name;

	private boolean isDeleted;

	public Category(UUID id) {
		super();
		this.id = id;
	}

	public static Optional<Category> findActiveById(UUID id) {
		return find("id = ?1 and isDeleted = false", id).firstResultOptional();
	}
}
