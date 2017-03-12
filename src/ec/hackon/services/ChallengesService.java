package ec.hackon.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import ec.hackon.dao.ChallengesDao;
import ec.hackon.dao.LeaderboardDao;
import ec.hackon.dao.LoginDao;
import ec.hackon.dao.SubmissionsDao;
import ec.hackon.helper.Helper;
import ec.hackon.model.ScoreAndRank;
import ec.hackon.model.SubmissionDetails;
import ec.hackon.model.User;

@Path("/{id}")
public class ChallengesService {

	@GET
	@Path("/challenges")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDoneChallenges(@PathParam("id") String userId){
		System.out.println("User Id is "+userId);
		
		ChallengesDao challengesDao=new ChallengesDao();
		//String userId=request.getParameter("id");
		ScoreAndRank scoreAndRank=challengesDao.getDoneChallenges(userId);		
		JSONObject object=new JSONObject();
		//object.put("id", userId);
		object.put("totalScore",scoreAndRank.getScore());
		object.put("doneChallenges", scoreAndRank.getDoneQuestions());
		return object.toString();
		
	}
	@POST
	@Path("challenge/{chId}/{answer}")
	public String checkChallengeAnswer(@PathParam("id") String userId,@PathParam("chId") String chId,@PathParam("answer") String answer){
		int challengeId=Integer.parseInt(chId);
		System.out.println(userId);
		ChallengesDao challengesDao=new ChallengesDao();
		SubmissionsDao submissionsDao=new SubmissionsDao();
		
		submissionsDao.addSubmission(userId,challengeId,answer);
		answer=answer.trim().replaceAll("\\s+", "").toLowerCase();
		System.out.println(answer);
		int result=challengesDao.checkAnswer(challengeId, answer);
		System.out.println("Answer is matched");
		if(result==1){
			String challengeColumn="ch"+challengeId;
			submissionsDao.updateUserScore(challengeColumn,userId);
		}
		return null;
		
	}
	
	@GET
	@Path("/password")
	public Response getPassword(){
		System.out.println("Password is sent");
		return Response.ok().entity(Helper.JSONPassword()).cookie(new NewCookie("password", "cookie")).build();
	}
	
}
