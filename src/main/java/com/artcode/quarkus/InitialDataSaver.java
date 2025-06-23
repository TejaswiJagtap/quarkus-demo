package com.artcode.quarkus;

import java.util.Set;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.artcode.quarkus.roles.Roles;
import com.artcode.quarkus.user.User;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Startup
@Slf4j
public class InitialDataSaver {

    @Transactional
    public void onStart(@Observes StartupEvent event) {
        log.info("🟢 InitialDataSaver started");

        if (Roles.count() == 0) {
            Roles adminRole = new Roles(UUID.randomUUID().toString(), "ADMIN");
            Roles userRole = new Roles(UUID.randomUUID().toString(), "USER");
            adminRole.persist();
            userRole.persist();

            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(BCrypt.hashpw("12345", BCrypt.gensalt()));
            admin.setRoles(Set.of(adminRole, userRole));
            admin.persist();
        }
    }
}
