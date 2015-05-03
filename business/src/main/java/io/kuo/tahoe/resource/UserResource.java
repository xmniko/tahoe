package io.kuo.tahoe.resource;

import io.kuo.tahoe.entity.User;
import io.kuo.tahoe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

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
        return userService.findAll();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") Long userId) {
        return userService.findById(userId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(@FormParam("nickName") String nickName, @FormParam("email") String email, @FormParam("password") String password) {
        User user = new User();
        user.setNickName(nickName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedBy("Niko.G");
        user.setCreatedAt(new Date());

        return userService.save(user);
    }

    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUserById(@PathParam("userId") Long userId, @FormParam("nickName") String nickName, @FormParam("email") String email, @FormParam("password") String password) {
        User user = userService.findById(userId);
        if (user != null) {
            user.setNickName(nickName);
            user.setEmail(email);
            user.setPassword(password);
            userService.save(user);
        }
        return user;
    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserById(@PathParam("userId") Long userId) {
        userService.deleteById(userId);
        return Response.status(200).build();
    }
}