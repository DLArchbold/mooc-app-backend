package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/oauth/application_user")
@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//Can either be declared at interface or method level
public interface ApplicationUserApiService {

	@GET
	@Path("/get/usingemail/{email}")
	ApplicationUserRest getUserDetails(@PathParam("email") String email);
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/verify-password/{email}")
	VerifyPasswordResponse verifyUserPassword(@PathParam("email") String email,
			String password);
	
	
}
