package ec.hackon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.hackon.model.PersonalDetails;
import ec.hackon.model.ScoreAndRank;
import ec.hackon.model.User;

public class LeaderboardDao {

	private static final String DRIVER_NAME="com.mysql.jdbc.Driver";//JDBC Driver name
	private static final String URL="jdbc:mysql://localhost:3306/";//Database URL
	private static final String DB_NAME="hackon_db";
	//Database credentials
	private static final String USER_NAME="root";
	private static final String PASSWORD="WELcome@123";
	
	Connection connection;
	
	public List<User> getLeaderboard(){
		int i=1;
		PreparedStatement stmt=null;
		List<User> leaderboardUsers=new ArrayList<>();
		ResultSet resultSet=null;
		 try{
			//Register JDBC Driver
				Class.forName(DRIVER_NAME).newInstance();
				//open a connection
				connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			
				//Create Statement
				//stmt1=connection.prepareStatement(query1);
			   String query="SELECT `users`.`name`,`users_score`.`tot_score` FROM users"
			   		+ " INNER JOIN `users_score` ON users.`user_id`=`users_score`.`user_id`"
			   		+ " ORDER BY tot_score DESC,last_submit;";
			   stmt=connection.prepareStatement(query);
			  
			   resultSet=stmt.executeQuery();
			   while(resultSet.next()){
				 User user=new User();
				 PersonalDetails personalDetails=new PersonalDetails();
				 ScoreAndRank scoreAndRank=new ScoreAndRank();
				 personalDetails.setName(resultSet.getString(1));
				 user.setPersonalDetails(personalDetails);
				 scoreAndRank.setScore(resultSet.getInt(2));
				 scoreAndRank.setRank(i++);
				 user.setScoreAndRank(scoreAndRank);
				leaderboardUsers.add(user);
			   }
		   }
		   catch(SQLException e){
			   e.printStackTrace();
		   }
			catch(InstantiationException e){
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			catch(IllegalAccessException e){
				e.printStackTrace();
			}
		   finally {
			
			   try{
				   if(resultSet!=null){
					   resultSet.close();
				   }
				   if(stmt!=null){
					   stmt.close();
				   }
				   if(connection!=null){
					   connection.close();
				   }
			   }
			   catch(SQLException e){
				   e.printStackTrace();
			   }
		}
		return leaderboardUsers;
	}
}
