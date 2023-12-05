package fr.alom.auth.front;

import fr.alom.auth.common.User;
import fr.alom.auth.middle.AuthService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class AuthResource {

    private final AuthService authService = new AuthService();

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String authenticate(User user) {
        try {
            return authService.authenticate(user.getNickname(), user.getPassword());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.Status.UNAUTHORIZED.toString();
        }
    }
}
