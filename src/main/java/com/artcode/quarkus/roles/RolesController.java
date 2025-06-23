package com.artcode.quarkus.roles;

import java.util.UUID;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.PathParam;

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolesController {

    @POST
    @Transactional
    @RolesAllowed({"ADMIN", "USER"})
    public Response createRole(Roles role) {
        role.setId(UUID.randomUUID().toString());
        if (Roles.findByName(role.getName()) != null) {
            return Response.status(Response.Status.CONFLICT)
                           .entity("Role with name '" + role.getName() + "' already exists.")
                           .build();
        }
        role.persist();
        return Response.status(Response.Status.CREATED).entity(role).build();
    }

    @GET
    @RolesAllowed({"ADMIN", "USER"})
    public Response getAllRoles() {
        return Response.ok(Roles.listAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "USER"})
    public Response updateRole(@PathParam("id") String id, Roles role) {
        Roles existingRole = Roles.findById(id);
        if (existingRole == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Role with ID '" + id + "' not found.")
                           .build();
        }
        existingRole.setName(role.getName());
        existingRole.persist();
        return Response.ok(existingRole).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public Response getRoleById(@PathParam("id") String id) {
        Roles role = Roles.findById(id);
        if (role == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Role with ID '" + id + "' not found.")
                           .build();
        }
        return Response.ok(role).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "USER"})
    public Response deleteRole(@PathParam("id") String id) {
        Roles role = Roles.findById(id);
        if (role == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Role with ID '" + id + "' not found.")
                           .build();
        }
        role.delete();
        return Response.noContent().build();
    }
}
