package com.artcode.quarkus.user;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jose4j.lang.JoseException;
import org.mindrot.jbcrypt.BCrypt;

import com.artcode.quarkus.security.JwtService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    private JwtService jwtService;
    
    @POST
    @Path("/login")
    public Response login(LoginRequest request) throws JoseException {
        Optional<User> optionalUser = User.find("email", request.email).firstResultOptional();
        if (optionalUser.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email or password").build();
        }
        User user = optionalUser.get();
        if (!BCrypt.checkpw(request.password, user.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email or password").build();
        }
        // ✅ In a real app, you would generate a JWT token here
        Set<String> roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet());
        String token = jwtService.generateToken(user.getEmail(), roles);

        return Response.ok(new LoginResponse(token, user.getName(), user.getEmail(), roles)).build();
    }
}
