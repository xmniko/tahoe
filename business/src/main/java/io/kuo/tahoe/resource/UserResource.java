package io.kuo.tahoe.resource;

import io.kuo.tahoe.entity.User;
import io.kuo.tahoe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by nikog on 1/30/2015.
 */
@Path("/v1/users")
public class UserResource {

    @Autowired
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<User> getUsers() {
        return userService.loadAll();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") Long userId) {
        return userService.loadById(userId);
    }
}