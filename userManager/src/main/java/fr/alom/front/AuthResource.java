
package fr.alom.front;

import fr.alom.middle.AuthService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/user/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final AuthService authService;

    public AuthResource() {
        this.authService = new AuthService();
    }

    @POST
    public String authenticate(String nickname, String password) {
        return authService.authenticate(nickname, password).toString();
    }

    @GET
    public String getNickname(@QueryParam("token") UUID token) {
        return authService.getNickname(token);
    }
}
