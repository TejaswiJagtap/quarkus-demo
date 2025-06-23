package com.artcode.quarkus.user;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.artcode.quarkus.roles.Roles;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
   

    @POST
    @Transactional
    @Path("/signup") 
    @RolesAllowed({"ADMIN", "USER"})
    public Response createUser(UserRequest userRequest) {
        User user = User.findByEmail(userRequest.getEmail());
        if (user != null) {
            return Response.status(Response.Status.CONFLICT)
                           .entity("User with this email already exists")
                           .build();
        }
        user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        Roles userRole = Roles.find("name", "USER").firstResult();
        if (userRole == null) {
            // Create default role if not found
            userRole = new Roles(UUID.randomUUID().toString(), "USER");
            userRole.persist();
        }
        user.setRoles(Set.of(userRole));
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.persist();
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @RolesAllowed({"ADMIN","USER"})
    public List<User> getUsers() {
        return User.listAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public User getUserById(@PathParam("id") String id) {
        User user = User.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "USER"})
    public User updateUser(@PathParam("id") String id, UserRequest updatedUser) {
        User existingUser = User.findById(id);
        if (existingUser == null) {
            throw new NotFoundException("User not found");
        }
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        return existingUser;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "USER"})
    public Response deleteUser(@PathParam("id") String id) {
        User user = User.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        user.delete();
        return Response.ok("Deleted successfully").build();
    }

    @GET
    @Path("/search/{email}")
    @RolesAllowed({"ADMIN", "USER"})
    public User search(@PathParam("email") String email) {
        return User.findByEmail(email);
    }
}
