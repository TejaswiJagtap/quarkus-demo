package com.artcode.quarkus.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
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
	@RolesAllowed({ "ADMIN", "USER" })
	public Response getById(@PathParam("id") UUID id) {
		try {
			CategoryDto dto = categoryService.getById(id);
			return Response.ok(dto).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/all")
	@RolesAllowed({"ADMIN","USER"})
	public Response getAll() {
		return Response.ok(categoryService.getAll()).build();
	}

	@GET
	@RolesAllowed({"ADMIN","USER"})
	public Response getAllPaginated(@QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size) {

		List<Category> categories = categoryService.getAllPaginated(page, size);
		long total = categoryService.countActive();

		Map<String, Object> response = new HashMap<>();
		response.put("data", categories);
		response.put("page", page);
		response.put("size", size);
		response.put("total", total);

		return Response.ok(response).build();
	}

}
