package com.examtracking.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.examtracking.bean.StudentDetails;
import com.examtracking.util.DBConnection;

/**
 * This class is used to set the student details from the database
 * @author BATCH-'C' 
 */
public class StudentData {

	/**
	 * This method return List Object Stored in file
	 * @throws FileNotFoundException
	 */
	public static List<StudentDetails> getStudentList() {
		//creating list to store student information
		List<StudentDetails> list2 = new ArrayList<StudentDetails>();
		Connection con = null;
		Statement st = null;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			st = con.createStatement();
			
			//preparing and executing query
			ResultSet rs = st.executeQuery("select * from student_details");
			
			//as per query result storing result into student object
			while (rs.next()) {
				StudentDetails student = new StudentDetails();
				student.setStudent_id(rs.getInt(1));
				student.setFirstname(rs.getString(2));
				student.setLastname(rs.getString(3));
				student.setEmail_id(rs.getString(4));
				student.setAddress(rs.getString(5));
				student.setMobile_no(rs.getString(6));
				student.setParent_no(rs.getString(7));
				student.setBranch(rs.getString(8));
				student.setDate_of_birth(rs.getString(9));
				student.setDate_of_joining(rs.getString(10));
				
				//adding student object into list
				list2.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//returning list which contains student object
		return list2;
	}

	/**
	 * This method used insert student details into database
	 * @param student list
	 */
	public static boolean saveStudentRecord(List<StudentDetails> list) {
		
		Iterator<StudentDetails> itr = list.iterator();
		//Establishing connection with database
		Connection con = DBConnection.getDBConnection();
		int std_id = 0;
		Statement st = null;
		boolean result = false;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(student_id) from student_details");
			while(rs.next()) {
				std_id = rs.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while (itr.hasNext()) {
			try {
				
				
				//preparing sql query to save academic info into database
				String query = " insert into student_details (student_id,first_name,last_name,email_id,mobile_no,parent_no,address,branch,dob,doj)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				// create the my sql insert prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query);
				StudentDetails student = new StudentDetails();
				student = (StudentDetails) itr.next();
				preparedStmt.setInt(1, (std_id + 1));
				preparedStmt.setString(2, student.getFirstname());
				preparedStmt.setString(3, student.getLastname());
				preparedStmt.setString(4, student.getEmail_id());
				preparedStmt.setString(5, student.getMobile_no());
				preparedStmt.setString(6, student.getParent_no());
				preparedStmt.setString(7, student.getAddress());
				preparedStmt.setString(8, student.getBranch());
				preparedStmt.setString(9, student.getDate_of_birth());
				preparedStmt.setString(10, student.getDate_of_joining());
				
				// execute the prepared statement
				int i = preparedStmt.executeUpdate();
				if(i > 0) {
					result = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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
	 * This method update student by id into databse
	 * 
	 * @param id
	 * @param value
	 * @param colName
	 */
	public static boolean  upDateStudentById(StudentDetails student) {
		boolean result=false;
		Connection con = null;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			long id=student.getStudent_id();
			String mobile_num=student.getMobile_no();
			String parent_num=student.getParent_no();
			String email_id=student.getEmail_id();
			String addresss=student.getAddress();
			//preparing sql query to save academic info into database
			PreparedStatement ps = con
					.prepareStatement("update student_details set mobile_no=?,parent_no=?,email_id=?,address=? where student_id = ?");
			
			//setting value to query 
			ps.setString(1, mobile_num);
			ps.setString(2, parent_num);
			ps.setString(3, email_id);
			ps.setString(4, addresss);
			ps.setLong(5, id);
			
			//Executing query
			int count = ps.executeUpdate();
			if(count>0) {
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	 return result;
	}
	public  StudentDetails getStudentByid(long stud_id) {
		StudentDetails student=null;
				Connection con = null;
				Statement st = null;
				try {
					//Establishing connection with database
					con = DBConnection.getDBConnection();
					st = con.createStatement();
					
					//preparing and executing query
					ResultSet rs = st.executeQuery("select * from student_details where student_id="+stud_id);
					
					//as per query result storing result into student object
					while (rs.next()) {
						student = new StudentDetails();
						student.setStudent_id(rs.getInt(1));
						student.setFirstname(rs.getString(2));
						student.setLastname(rs.getString(3));
						student.setEmail_id(rs.getString(4));
						student.setAddress(rs.getString(5));
						student.setMobile_no(rs.getString(6));
						student.setParent_no(rs.getString(7));
						student.setBranch(rs.getString(8));
						student.setDate_of_birth(rs.getString(9));
						student.setDate_of_joining(rs.getString(10));
						
						
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						//closing connection
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				System.out.println(student);
				//returning list which contains student object
				return student;
			}
	public static void main(String[] args) {
	
		new StudentData().getStudentByid(101);
	}
		
	}


