package edu.elearning.service.datacache.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/entity")
public class EntityResource {
//    private final CacheQuery cacheQuery;
//
//    public EntityResource(CacheQuery cacheQuery) {
//        this.cacheQuery = cacheQuery;
//    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getEntity(){
        return Response.ok("successful").build();
    }
}
