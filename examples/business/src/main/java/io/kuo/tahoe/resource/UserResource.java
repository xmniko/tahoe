package io.kuo.tahoe.resource;

import io.kuo.tahoe.entity.User;
import io.kuo.tahoe.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by nikog on 1/30/2015.
 */
@Path("/user")
public class UserResource {

    @Autowired
    UserService userService;

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") Long userId) {
        return userService.loadById(userId);
    }
}