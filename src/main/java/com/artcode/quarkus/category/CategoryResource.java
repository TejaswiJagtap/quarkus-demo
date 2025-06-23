package com.artcode.quarkus.category;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

	@Inject
	CategoryService categoryService;

	@POST
	@Path("/save")
	@RolesAllowed("ADMIN")
	@Transactional
	public Response saveOrUpdateCategory(CategoryDto dto) {
		try {
			Category category = categoryService.saveOrUpdate(dto);
			return Response.ok(category).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN","USER"})
	public Response getById(@PathParam("id") String id) {
		try {
			CategoryDto dto = categoryService.getById(id);
			return Response.ok(dto).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}
}
