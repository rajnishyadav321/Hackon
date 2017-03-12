package ec.hackon.services;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import ec.hackon.dao.LoginDao;
import ec.hackon.helper.Helper;
import ec.hackon.model.Json;
import ec.hackon.model.PersonalDetails;
import ec.hackon.model.User;


@Path("/login")
public class LoginService {
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response login(Json json){
		LoginDao loginDao=new LoginDao();
		
		
		String userId=json.getId();
		String name=json.getName();
		String email=json.getEmail();
		System.out.println(userId+" "+name+" "+email);
		User user=new User();
		user.setUserId(userId);
		PersonalDetails personalDetails=new PersonalDetails();
		personalDetails.setName(name);
		personalDetails.setEmail(email);
		user.setPersonalDetails(personalDetails);
		int result=loginDao.addUser(user);
	/*	if(result==1)
		return Helper.JSONSuccess();
		else{
			return Helper.JSONAlreadyRegistered();
		}*/
		return Response.ok().entity(Helper.JSONSuccess()).cookie(new NewCookie("password", "assassin")).build();
	}
	
	
	@GET
	@Path("/me")
	@Produces("application/json")
	public Response getMe(){
		return Response.ok().entity(Helper.JSONSuccess()).cookie(new NewCookie("password", "assassin")).build();
	}

	}


