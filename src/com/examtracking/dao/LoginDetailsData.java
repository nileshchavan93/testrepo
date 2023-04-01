package com.examtracking.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.examtracking.bean.AdminLoginDetails;
import com.examtracking.bean.SetLoginDetails;
import com.examtracking.util.AES;
import com.examtracking.util.DBConnection;

public class LoginDetailsData {
	/**
	 * This method is used to retrieve the admin login details from the database
	 * @return list
	 */
	public static List<AdminLoginDetails> getAdminList() {
		Connection con = null;
		//creating list to hold admin credentials
		List<AdminLoginDetails> list2 = new ArrayList<AdminLoginDetails>();
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			Statement stmt = con.createStatement();
			
			//preparing and executing query
			ResultSet rs = stmt.executeQuery("select * from admincredentials");
			while (rs.next()) {
				//creating AdminLoginDetails object to hold admin credentials
				AdminLoginDetails adminDetails = new AdminLoginDetails();
				adminDetails.setUserName(rs.getString(2));
				adminDetails.setPassword(rs.getString(3));
				adminDetails.setSecurityQues(rs.getString(4));
				
				//adding admin AdminLoginDetails object into list
				list2.add(adminDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//returning list which holds AdminLoginDetails object
		return list2;
	}
	/**
	 * This method is used to retrieve the student login credentials details 
	 * from the database
	 * @return list
	 */
	public static List<SetLoginDetails> getStudentCredentials(){
		List<SetLoginDetails> list = new ArrayList<SetLoginDetails>();
		
		//Establishing connection with database
		Connection conn = DBConnection.getDBConnection();
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			
			//Preparing and executing query
			rs = stmt.executeQuery("select * from student_credentials");
			while (rs.next()) {
				//creating SetLoginDetails object to hold student login credentials
				SetLoginDetails setLogin = new SetLoginDetails();
				setLogin.setStud_id(rs.getInt(1));
				setLogin.setUsername(rs.getString(2));
				setLogin.setPassword(rs.getString(3));
				setLogin.setSecurityQues(rs.getString(4));
				
				//adding SetLoginDetails object into list
				list.add(setLogin);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				//closing connection
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//returning list which holds SetLoginDetails object
		return list;
	}

	/**
	 * This method is used to reset the password and store the student information
	 * from the database
	 * @param list
	 * @param studentid
	 */
	public static boolean  setStudentresetPassword(List<SetLoginDetails> list, long studentid) {
		//Establishing connection with database
		boolean result=false;
		Connection conn = DBConnection.getDBConnection();
		PreparedStatement stmt1 = null;
		Iterator<SetLoginDetails> itr = list.iterator();
		while (itr.hasNext()) {
			try {
				SetLoginDetails student = itr.next();
				
				//Preparing and executing query
				stmt1 = conn.prepareStatement(
						"update student_credentials set username=?,password=?,security_ques=? where student_id=?");
				//setting parameter to query
				stmt1.setString(1, student.getUsername());// 1 specifies the first parameter in the query i.e. user name
				stmt1.setString(2, student.getPassword());
				stmt1.setString(3, student.getSecurityQues());
				stmt1.setLong(4, studentid);
				
				//Executing query
				int count=stmt1.executeUpdate();
				if(count>0) {
					result=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					//closing connection
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		 return result;
	}
	/**
	 * This method fetching student login credentials from the database
	 * @return
	 */
	public static List<SetLoginDetails> getLoginDetails(){
		
		List<SetLoginDetails> list2= new ArrayList<SetLoginDetails>();;
		
		try{  
			Connection con = DBConnection.getDBConnection();
			
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from student_credentials");
	
			while(rs.next()) {
				SetLoginDetails studentdetails = new SetLoginDetails();
				studentdetails.setStud_id(rs.getInt(1));
				studentdetails.setUsername(rs.getString(2));
//				String password = new AES().getDecryptedPassword(rs.getString(3));
				studentdetails.setPassword(rs.getString(3));
				studentdetails.setSecurityQues(rs.getString(4));
				list2.add(studentdetails);
			}
			con.close();  
			}
		catch(Exception e){ e.printStackTrace();}
		return list2;
		
	}
	
	/**
	 * This method to adding and updating  list objects into the database
	 * @param list
	 */
	public static String saveStudentRecord(List<SetLoginDetails> list) {
		String result=null;
		//Establishing connection with database
		Connection con = DBConnection.getDBConnection();
		Iterator<SetLoginDetails> itr = list.iterator();
		while (itr.hasNext()) {
			//Preparing and executing query
			String sql = "insert into student_credentials(student_id,username,password,security_ques) values(?,?,?,?)";
			PreparedStatement preparedStmt;
			try {
				preparedStmt = con.prepareStatement(sql);
				//creating student credential class object it holds login credentials
				SetLoginDetails student = new SetLoginDetails();
				student = (SetLoginDetails) itr.next();
//				String password = new AES().getEncryptedPassword(student.getPassword());
				//setting parameter to query
				preparedStmt.setLong(1, student.getStud_id());
				preparedStmt.setString(2, student.getUsername());
				preparedStmt.setString(3, student.getPassword());
				preparedStmt.setString(4, student.getSecurityQues());
				
				//executing query
				preparedStmt.executeUpdate();
					result="student";
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					//closing connection
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * This method is to check if student is present sign up for the student
	 * @param std_id
	 * @return true if student is available
	 */
	public static boolean checkIdInLogins(long std_id) {
		Connection con = null;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			Statement stmt = con.createStatement();
			
			//Preparing and executing query
			ResultSet rs = stmt.executeQuery("select * from student_credentials");
			
			//checking if student id found returning true
			while (rs.next()) {
				if (std_id == rs.getInt(1))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//as per query result returning true/false
		return false;
	}
	/**
	 * This method is to check id if student is present sign up for the student
	 * 
	 * @param std_id
	 * @return true if student is available
	 * 
	 */
	public static boolean CheckIdRegistration(long std_id) {
		
		Connection con = null;
		boolean result = false;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			Statement stmt = con.createStatement();
			
			//Preparing and executing query
			ResultSet rs = stmt.executeQuery("select * from student_details where student_id="+std_id);
			
			//checking if student id found returning true
			while (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
