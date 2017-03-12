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

public class ChallengesDao {

	private static final String DRIVER_NAME="com.mysql.jdbc.Driver";//JDBC Driver name
	private static final String URL="jdbc:mysql://localhost:3306/";//Database URL
	private static final String DB_NAME="hackon_db";
	//Database credentials
	private static final String USER_NAME="root";
	private static final String PASSWORD="WELcome@123";
	
	Connection connection;
	
	
	public ScoreAndRank getDoneChallenges(String userId){
		PreparedStatement stmt=null;
		ScoreAndRank scoreAndRank=null;
		ResultSet resultSet=null;
		boolean flag=false;
		 try{
			//Register JDBC Driver
				Class.forName(DRIVER_NAME).newInstance();
				//open a connection
				connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			
				//Create Statement
				//stmt1=connection.prepareStatement(query1);
			   String query="SELECT * FROM users_score WHERE user_id=?";
			   stmt=connection.prepareStatement(query);
			   stmt.setString(1, userId);
			   resultSet=stmt.executeQuery();
			   while(resultSet.next()){
				   System.out.println("Hello");
				scoreAndRank=new ScoreAndRank();
				List<Integer> doneCh=new ArrayList<>();
				 flag=resultSet.getBoolean(2);
				 if(flag){
					 doneCh.add(1);
				 }
				 flag=resultSet.getBoolean(3);
				 if(flag){
					 doneCh.add(2);
				 }
				 flag=resultSet.getBoolean(4);
				 if(flag){
					 doneCh.add(3);
				 }
				 flag=resultSet.getBoolean(5);
				 if(flag){
					 doneCh.add(4);
				 }
				 flag=resultSet.getBoolean(6);
				 if(flag){
					 doneCh.add(5);
				 }
				 flag=resultSet.getBoolean(7);
				 if(flag){
					 doneCh.add(6);
				 }
				 flag=resultSet.getBoolean(8);
				 if(flag){
					 doneCh.add(7);
				 }
				 flag=resultSet.getBoolean(9);
				 if(flag){
					 doneCh.add(8);
				 }
				 scoreAndRank.setDoneQuestions(doneCh);
				 scoreAndRank.setScore(resultSet.getInt(10)); 
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
		return scoreAndRank;
		
	}
	
	public int checkAnswer(int challengeId,String answer){
		int result=0;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		 try{
			//Register JDBC Driver
				Class.forName(DRIVER_NAME).newInstance();
				//open a connection
				connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			
				//Create Statement
				//stmt1=connection.prepareStatement(query1);
			   String query="SELECT * FROM ans_table WHERE ch_id=? AND answer=?";
			   stmt=connection.prepareStatement(query);
			  stmt.setInt(1, challengeId);
			  stmt.setString(2, answer);
			   resultSet=stmt.executeQuery();
			   if(resultSet.next()){
				 result=1;
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
		return result;
		
	}
}
