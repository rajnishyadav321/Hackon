package ec.hackon.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ec.hackon.dao.SubmissionsDao;
import ec.hackon.model.User;

@Path("/submissions")
public class SubmissionService {

	
	@GET
	@Produces("application/json")
	public List<User> getSubmissions(){
		
		SubmissionsDao submissionsDao=new SubmissionsDao();
		return submissionsDao.getSubmissions();
		
	}
}
