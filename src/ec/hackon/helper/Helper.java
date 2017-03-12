package ec.hackon.helper;

import java.util.Observer;

import org.json.JSONObject;

public class Helper {
	
	public static String JSONSuccess(){
		
		JSONObject object=new JSONObject();
		
		object.put("result", "success");
		
		return object.toString();
	}
	
public static String JSONFailure(){
		
		JSONObject object=new JSONObject();
		
		object.put("result", "failure");
		
		return object.toString();
	}
public static String JSONPassword(){
	
	JSONObject object=new JSONObject();
	
	object.put("result", "Password has arrived");
	
	return object.toString();
}

public static String JSONAlreadyRegistered(){
	
	JSONObject object=new JSONObject();
	
	object.put("result", "already-registered");
	
	return object.toString();
}
public static String JSONVehicalNotFound(){
	
	JSONObject object=new JSONObject();
	
	object.put("result", "Vehicle not found");
	
	return object.toString();
}
public static String JSONVehicalTooManyColor(){
	
	JSONObject object=new JSONObject();
	
	object.put("result", "There are too many vehicle of this color");
	
	return object.toString();
}
public static String JSONVehicalTooManySpeed(){
	
	JSONObject object=new JSONObject();
	
	object.put("result", "There are too many vehicle in this speed range");
	
	return object.toString();
}
public static String JSONVehicalTooManyBrand(){
	
	JSONObject object=new JSONObject();
	
	object.put("result", "Be more specific about brand");
	
	return object.toString();
}
}
