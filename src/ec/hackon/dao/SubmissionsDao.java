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
import ec.hackon.model.SubmissionDetails;
import ec.hackon.model.User;

public class SubmissionsDao {

	private static final String DRIVER_NAME="com.mysql.jdbc.Driver";//JDBC Driver name
	private static final String URL="jdbc:mysql://localhost:3306/";//Database URL
	private static final String DB_NAME="hackon_db";
	//Database credentials
	private static final String USER_NAME="root";
	private static final String PASSWORD="WELcome@123";
	
	Connection connection;
	
	public List<User> getSubmissions(){
		PreparedStatement stmt=null;
		List<User> userSubmissions = new ArrayList<>();
		ResultSet resultSet=null;
		 try{
			//Register JDBC Driver
				Class.forName(DRIVER_NAME).newInstance();
				//open a connection
				connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			
				//Create Statement
				//stmt1=connection.prepareStatement(query1);
			   String query="SELECT logs.`name`,`ans_table`.`ch_name`,logs.`status` FROM "
			   		+ "`logs` INNER JOIN `ans_table` ON logs.`ch_id`=ans_table.`ch_id`"
			   		+ " ORDER BY logs.`time_stamp` DESC LIMIT 10;";
			   stmt=connection.prepareStatement(query);
			  
			   resultSet=stmt.executeQuery();
			   while(resultSet.next()){
				 User user=new User();
				 PersonalDetails personalDetails=new PersonalDetails();
				 SubmissionDetails submissionDetails=new SubmissionDetails();
				 personalDetails.setName(resultSet.getString(1));
				 user.setPersonalDetails(personalDetails);
				 submissionDetails.setChallangeName(resultSet.getString(2));
				 submissionDetails.setStatus(resultSet.getBoolean(3));
				 user.setSubmissionDetails(submissionDetails);
				userSubmissions.add(user);
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
		return userSubmissions;
	}
	
	public int addSubmission(String userId,int challengeId,String answer){
		PreparedStatement stmt=null;
		int result=0;
	
		 try{
			//Register JDBC Driver
				Class.forName(DRIVER_NAME).newInstance();
				//open a connection
				connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			
				//Create Statement
				//stmt1=connection.prepareStatement(query1);
			   String query="INSERT INTO logs(`user_id`,NAME,ch_id,ch_name,answer,status)"
			   		+ " SELECT ?,`users`.`name`,?,`ans_table`.`ch_name`,?,"
			   		+ "(SELECT (CASE WHEN ?=`ans_table`.`answer` THEN 1 ELSE 0 END) FROM `ans_table` WHERE ch_id=?) "
			   		+ "FROM `users`,`ans_table` WHERE `users`.`user_id`=?  AND `ans_table`.`ch_id`=?;";
			   stmt=connection.prepareStatement(query);
			   stmt.setString(1, userId);
			   stmt.setInt(2, challengeId);
			   stmt.setString(3, answer);
			   stmt.setString(4, answer);
			   stmt.setInt(5, challengeId);
			   stmt.setString(6, userId);
			   stmt.setInt(7, challengeId);
			   System.out.println(stmt.toString());
			   result=stmt.executeUpdate();
			 System.out.println("Result is executed");
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
	public int updateUserScore(String challengeColumn,String userId){
		int result=0;
	PreparedStatement stmt=null;
		 try{
			//Register JDBC Driver
				Class.forName(DRIVER_NAME).newInstance();
				//open a connection
				connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			
				
			   String query="UPDATE `users_score` SET tot_score=IF("+challengeColumn+"=0,tot_score+100,tot_score), "+challengeColumn+"=1 WHERE user_id=?;";
			   stmt=connection.prepareStatement(query);
			   stmt.setString(1, userId);
			   result=stmt.executeUpdate();
			 System.out.println("Result is executed");
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
