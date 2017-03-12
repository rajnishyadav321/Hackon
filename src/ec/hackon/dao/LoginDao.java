package ec.hackon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ec.hackon.model.User;

public class LoginDao {

	private static final String DRIVER_NAME="com.mysql.jdbc.Driver";//JDBC Driver name
	private static final String URL="jdbc:mysql://localhost:3306/";//Database URL
	private static final String DB_NAME="hackon_db";
	//Database credentials
	private static final String USER_NAME="root";
	private static final String PASSWORD="WELcome@123";
	
	Connection connection;
	
	public int addUser(User user){
		int result1=0;
		int result2=0;
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		String query1="insert into users(user_id,email,name) values(?,?,?)";
		String query2="insert into users_score(user_id) values(?)";
		try{
			//Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			//open a connection
			connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			 connection.setAutoCommit(false);
			//Create Statement
			stmt1=connection.prepareStatement(query1);
			stmt1.setString(1,user.getUserId());
			stmt1.setString(2, user.getPersonalDetails().getEmail());
			stmt1.setString(3, user.getPersonalDetails().getName());
			result1=stmt1.executeUpdate();//Execute insert statement
			stmt2=connection.prepareStatement(query2);
			stmt2.setString(1, user.getUserId());
			result2=stmt2.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			  if (connection != null) {
		            try {
		                System.err.print("Transaction is being rolled back");
		                connection.rollback();
		            } catch(SQLException excep) {
		                //JDBCTutorialUtilities.printSQLException(excep);
		            }
		        }
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
		finally{
			try {
				if(connection!=null){
				    connection.setAutoCommit(true);
					connection.close();
				}
				if(stmt2!=null){
					stmt2.close();
				}
				if(stmt1!=null){
					stmt1.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(result1==1&&result2==1){
			return 1;
		}
		else{
			return 0;
		}
		
		
	}
	
	
}
