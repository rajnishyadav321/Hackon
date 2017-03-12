package ec.hackon.services.part.challenge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;

@Path("/addEmail")
public class DrugService {

	@GET
	@Path("/{email}")
	@Produces("application/json")
	public String addEmail(@PathParam("email") String email){
		
JSONObject object=new JSONObject();
		
		object.put("result", "Wrong entry in the table email");
		
		return object.toString();
	}
	
}
