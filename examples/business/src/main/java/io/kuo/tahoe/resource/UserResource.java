package io.kuo.tahoe.resource;

import io.kuo.tahoe.entity.User;
import io.kuo.tahoe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by nikog on 1/30/2015.
 */
@Path("/user")
public class UserResource {

    @Autowired
    UserService userService;

    @GET
    @Path("/{userId}")
    @Produces("application/json")
    public User getUserById(@PathParam("userId") Long userId) {
        return userService.loadById(userId);
    }

    @GET
    @Path("s")
    @Produces("application/json")
    public String getIt() {
        return "Got It!";
    }
}