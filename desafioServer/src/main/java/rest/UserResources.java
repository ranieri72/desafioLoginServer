package main.java.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import main.java.controller.UserController;
import main.java.entity.RequestLogin;
import main.java.entity.User;
import main.java.entity.UserSession;
import main.java.util.RandomToken;

@Path("/user")
public class UserResources {

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response login(RequestLogin login) {
		UserController userController = new UserController();
		login = userController.login(login);
		return Response.ok(login).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response testUser() {
		try {
			RequestLogin login = new RequestLogin();
			
			User user = new User();
			user.setEmail("Ranieri");
			user.setPassword("123");
			
			UserSession session = RandomToken.generateToken(user, 7);
			
			login.setUser(user);
			login.setSession(session);
			
			UserController userController = new UserController();
			login = userController.login(login);
			return Response.ok(login).build();
		} catch (Exception e) {
			RequestLogin login = new RequestLogin();
			login.setError(e.getLocalizedMessage());
			return Response.ok(login).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/register")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response registerUser() {
		try {			
			User user = new User();
			user.setEmail("Ranieri");
			user.setPassword("123");
			
			UserController userController = new UserController();
			userController.register(user);
			return Response.ok().build();
		} catch (Exception e) {
			RequestLogin login = new RequestLogin();
			login.setError(e.getLocalizedMessage());
			return Response.ok(login).status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}