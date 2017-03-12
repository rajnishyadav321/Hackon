package ec.hackon.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

import ec.hackon.dao.LeaderboardDao;
import ec.hackon.model.User;

@Path("/leaderboard")
public class LeaderboardService {

	@GET
	@Produces("application/json")
	public List<User> getLeaderboard(){
		LeaderboardDao leaderboardDao=new LeaderboardDao();
		return leaderboardDao.getLeaderboard();
		/*JSONArray jsonArray=new JSONArray();
		for(User user:leaderboardDao.getLeaderboard()){
			JSONObject ob=new JSONObject();
			ob.put("rank", user.getScoreAndRank().getRank());
			ob.put("name", user.getPersonalDetails().getName());
			ob.put("score", user.getScoreAndRank().getScore());
			jsonArray.put(ob.toString());
			
		}
		return jsonArray.toString();*/
		
	}
}
