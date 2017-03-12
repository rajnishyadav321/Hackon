package ec.hackon.services.part.challenge;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.hackon.helper.Helper;

@Path("/vehicle")
public class VehicleService {

	private static final String MODEL="model";
	private static final String SPEED="speed";
	private static final String COLOR="color";
	private static final String BRAND="brand";
	
	@GET
	@Produces("application/json")
	public String getVehicle(@QueryParam("by") String by){
	
		if(by.trim().equals(MODEL)){
			return Helper.JSONVehicalNotFound();
		}
		else if(by.trim().equals(SPEED)){
			return Helper.JSONVehicalTooManySpeed();
		}
		else if(by.trim().equals(COLOR)){
			return Helper.JSONVehicalTooManyColor();
		}
		else if(by.trim().equals(BRAND)){
			return Helper.JSONVehicalTooManyBrand();
		}
		return Helper.JSONVehicalNotFound();
	}
}
