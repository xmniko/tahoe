package io.kuo.tahoe.resource;

import io.kuo.tahoe.entity.Task;
import io.kuo.tahoe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by nikog on 5/3/2015.
 */
@Path("/v1/tasks")
public class TaskResource {

    @Autowired
    private TaskService taskService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Task> getTasks() {
        return taskService.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Task createTask(Task task) {
        task.setCreatedBy("Niko.G");
        task.setCreatedAt(new Date());
        return taskService.save(task);
    }

    @GET
    @Path("/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Task getTaskById(@PathParam("taskId") Long taskId) {
        return taskService.findById(taskId);
    }

    @PUT
    @Path("/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Task updateTask(Task task) {
        task.setUpdatedBy("Niko.G");
        task.setUpdatedAt(new Date());
        taskService.save(task);
        return task;
    }

    @DELETE
    @Path("/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTaskById(@PathParam("taskId") Long taskId) {
        taskService.deleteById(taskId);
        return Response.status(200).build();
    }
}
