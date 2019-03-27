package main.java.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.controller.UserController;
import main.java.entity.User;

@Path("/user")
public class UserResources {

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response login(User user) {
		UserController userController = new UserController();
		user = userController.login(user);
		return Response.ok(user).build();
	}
}