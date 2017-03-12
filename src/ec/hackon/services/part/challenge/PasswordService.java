package ec.hackon.services.part.challenge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ec.hackon.model.part.challenge.Password;

@Path("/password")
public class PasswordService {

	@GET
	@Produces("application/json")
	public Password sendPassword(){
		Password password=new Password();
		return password;
	}
}
