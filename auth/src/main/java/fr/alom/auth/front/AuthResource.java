package fr.alom.auth.front;

import fr.alom.auth.middle.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class AuthResource {

    @Autowired
    private AuthService authService;
    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String authenticate(String nickname, String password) {
        try {
            return authService.authenticate(nickname, password);
        } catch (Exception e) {
            return Response.Status.UNAUTHORIZED.toString();
        }
    }
}
