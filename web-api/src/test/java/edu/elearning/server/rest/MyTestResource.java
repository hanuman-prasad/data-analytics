package edu.elearning.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class MyTestResource {
    @GET
    @Produces("text/html")
    public String getSampleMessage() {
        return "Welcome to rest easy application.";
    }
}
