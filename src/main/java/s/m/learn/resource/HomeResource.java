package s.m.learn.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class HomeResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String home() {
        return "Prod-Consumer app example live.";
    }
}
