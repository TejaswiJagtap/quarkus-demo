package com.artcode.quarkus.roles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Column;
import java.util.Set;
import com.artcode.quarkus.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Roles extends PanacheEntityBase{

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<User> users;

    public static Roles findByName(String name){
        return find("name", name).firstResult();
    }

    public Roles(String string, String string2) {
        this.id = string;
        this.name = string2;
    }
}




