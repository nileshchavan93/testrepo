package com.examtracking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.examtracking.bean.AcademicInfo;
import com.examtracking.bean.SetExamTimeTable;
import com.examtracking.bean.SetMarkSheet;
import com.examtracking.util.DBConnection;

/***
 * This class stores and gets all data related to Examination from database
 * @author BATCH-C
 *
 */
public class ExamData {
	/**
	 * this method is used to get the timetable
	 * 
	 * @return list if the branch id and exam id is true
	 */

	public static List<SetExamTimeTable> getAllTimeTable() {
		Connection con = null;
		Statement st = null;
		//declaring list to store time table data from database
		List<SetExamTimeTable> list2 = new ArrayList<SetExamTimeTable>();
		try {
			
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			st = con.createStatement();
			
			//Query to get all data from time_table table
			ResultSet rs = st.executeQuery("select * from time_table");
			while (rs.next()) {
				//getting all time table data and storing into examTimeTable object
				SetExamTimeTable timeTable = new SetExamTimeTable();
				timeTable.setExamTypeId(rs.getInt("exam_id"));
				timeTable.setBranchId(rs.getInt("branch_id"));
				
				//adding object into list
				list2.add(timeTable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//closing connection object
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//returning list which contains time table data
		return list2;
	}

	/**
	 * This method is used to set the Exam timetable details
	 * 
	 * @param list is parameter to insert the timetable values
	 */
	public boolean saveExamTimeTable(List<SetExamTimeTable> list) {
		
		//here list object contains tame table data 
		//using that creating iterating list to store in database
		Iterator<SetExamTimeTable> itr = list.iterator();
		Connection con = null;
		PreparedStatement pst = null;
		while (itr.hasNext()) {
			try {
				//Establishing connection with database
				con = DBConnection.getDBConnection();
				
				//preparing query to insert time table data into database
				String sql = "insert into time_table (date,subject,venue,time,exam_id,branch_id) values (?, ?, ?, ?, ?, ?)";
				pst = con.prepareStatement(sql);
				
				//by iterating list , setting values to query
				SetExamTimeTable tb = itr.next();
				
				pst.setString(1, tb.getDate());
				pst.setString(2, tb.getSubject());
				pst.setString(3, tb.getVenue());
				pst.setString(4, tb.getTime());
				pst.setInt(5, tb.getExamTypeId());
				pst.setInt(6, tb.getBranchId());
				
				//executing query
				pst.executeUpdate();

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
		return true;
	}

	/**
	 * If time table for particular examination is already set then,
	 * This method is used to reset the timetable
	 * @param examChoice
	 * @param branchChoice
	 * @return result when the database is empty
	 */
	public boolean resetTimeTable(int examChoice, int branchChoice) {
		boolean result = false;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			
			//If time table is already set for particular Exam then,
			//Preparing query for delete time table
			String sql = "delete from time_table where exam_id = ? and branch_id = ?";
			pst = con.prepareStatement(sql);
			
			//Setting parameter to the query
			pst.setInt(1, examChoice);
			pst.setInt(2, branchChoice);
			
			//if execute successfully return true result
			int rows = pst.executeUpdate();
			if (rows > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//closing connection object
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * This method is used to get the timetable for particular Exam from database
	 * @param branchId
	 * @param examChoice
	 * @return list
	 */
	public List<SetExamTimeTable> getExamTimeTable(int branchId, int examChoice) {
		//creating list to store time table 
		List<SetExamTimeTable> list = new ArrayList<SetExamTimeTable>();
		Connection con = null;
		Statement st = null;
		PreparedStatement pst = null;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			String sql = "select * from time_table where exam_id=? and branch_id=?";
			pst = con.prepareStatement(sql);
			
			//setting parameter to query
			pst.setInt(1, examChoice);
			pst.setInt(2, branchId);
			ResultSet rs = pst.executeQuery();
			//preparing query as per the given parameter and execute
			while (rs.next()) {
				
				//if data is there , setting data to setTimeTable object
				SetExamTimeTable timeTable = new SetExamTimeTable();
				
				timeTable.setDate(rs.getString("date"));
				timeTable.setSubject(rs.getString("subject"));
				timeTable.setTime(rs.getString("time"));
				timeTable.setVenue(rs.getString("venue"));
				timeTable.setExamTypeId(rs.getInt("exam_id"));
				timeTable.setBranchId(rs.getInt("branch_id"));
				
				//adding setTimeTable into list
				list.add(timeTable);
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
		
		//returning list which contains timetable object
		return list;
	}
	
	/**
	 * This method is used to get the timetable for particular Exam from database
	 * @param branchId
	 * @param examChoice
	 * @return list
	 */
	public List<SetExamTimeTable> getTimeTableOfAllExam() {
		//creating list to store time table 
		List<SetExamTimeTable> list = new ArrayList<SetExamTimeTable>();
		Connection con = null;
		Statement st = null;
		PreparedStatement pst = null;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			String sql = "select * from time_table";
			pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			//preparing query as per the given parameter and execute
			while (rs.next()) {
				
				//if data is there , setting data to setTimeTable object
				SetExamTimeTable timeTable = new SetExamTimeTable();
				
				timeTable.setDate(rs.getString("date"));
				timeTable.setSubject(rs.getString("subject"));
				timeTable.setTime(rs.getString("time"));
				timeTable.setVenue(rs.getString("venue"));
				timeTable.setExamTypeId(rs.getInt("exam_id"));
				timeTable.setBranchId(rs.getInt("branch_id"));
				
				//adding setTimeTable into list
				list.add(timeTable);
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
		
		//returning list which contains timetable object
		return list;
	}
	/**
	 * If Student gone through Eligibility criteria and if student eligible then,
	 * this method is used to insert the student academic details into database
	 */

	public static boolean setAcademicRecord(AcademicInfo list) {
		//Establishing connection with database
		Connection conn = DBConnection.getDBConnection();
		PreparedStatement ps = null;
		boolean result=false;
		try {
			//preparing sql query to save academic info into database
			String sql = "insert into academic_info values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			//Getting data from AcademicInfo object and setting to query
			ps.setLong(1, list.getStudent_id());
			ps.setLong(2, list.getAttendance());
			ps.setString(3, list.getFee());
			ps.setString(4, list.getCheck());
			
			//Executing query
			int i=ps.executeUpdate();
			if(i>0) {
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
		return result;	
	}

	/**
	 * This method is used to retrieve academic details from database
	 * 
	 * @return list
	 */
	public static List<AcademicInfo> getAcademicList() {
		//creating list object to hold student academic information from datase
		List<AcademicInfo> list = new ArrayList<AcademicInfo>();
		
		//Establishing connection with database
		Connection conn = DBConnection.getDBConnection();
		Statement stmt = null;
		try {
			//preparing sql query to get academic info from database
			String sql = "select * from academic_info";
			stmt = conn.createStatement();
			
			//Executing sql query
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//creating AcademicInfo object to hold query result
				AcademicInfo academicInfo = new AcademicInfo();
				academicInfo.setStudent_id(rs.getInt(1));
				academicInfo.setAttendance(rs.getInt(2));
				academicInfo.setFee(rs.getString(3));
				academicInfo.setCheck(rs.getString(4));
				
				//adding AcademicInfo object into list
				list.add(academicInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//returning list which contains AcademicInfo object
		return list;
	}
    /**
	 * This method takes the branch as parameter as per that ,
	 * get the marks list of particular branch from the database.
	 */
	public static List<SetMarkSheet> getAllStudentMarksList(int choice)  {
		Connection con = null;
		//creating list object to hold all students mark sheets
		List<SetMarkSheet> list = new ArrayList<SetMarkSheet>();
		PreparedStatement pst = null;
		String firstName = null; 
		String lastName =  null;
		double percentage = 0.0;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			
			//as per branch choice preparing sql query
			String sql = "select * from mark_sheet where exam_id = ?";
			pst = con.prepareStatement(sql);
			
			//setting parameter to query
			pst.setInt(1, choice);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				//if data found creating MarkSheet class object to hold query result
				SetMarkSheet mark = new SetMarkSheet();
				
				//geting id from mark_sheet, and as per id getting student information from master table
				int id = rs.getInt("student_id");
				
				//preparing query as per id
				String sql1 = "select first_name, last_name from student_details where student_id = ?";
				pst = con.prepareStatement(sql1);
				
				//setting parameter to query
				pst.setInt(1, id);
				ResultSet rs1 = pst.executeQuery();
				
				//getting result from query
				while(rs1.next()) {
					
					firstName = rs1.getString(1);
					lastName = rs1.getString(2);
				}
				mark.setFirstName(firstName);
				mark.setLastName(lastName);
				mark.setOutOfMarks(rs.getInt("out_of_marks"));
				mark.setTotalmarks(rs.getInt("total_marks"));
				
				// Calculating percentage of a particular student
				percentage = (rs.getInt("total_marks") * 100 ) / rs.getInt("out_of_marks");
				mark.setPercentage(percentage);
				
				//adding object into list
				list.add(mark);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//returning list which contains marksheet of student
		return list;
	}
	/**
	 * This method takes list as parameter which contains mark sheet data,
	 * and stores that data into database
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public static boolean saveAllStudentMarksList(List<SetMarkSheet> list, long id , int examChoice) {
		Iterator<SetMarkSheet> itr = list.iterator();
		Connection con = null;
		boolean result = false;
		PreparedStatement pst = null;
		
		//getting branch of student
		List stdInfoList =  getBranchId(id);
		int branch_id = 0;
		String branch = (String) stdInfoList.get(0);
		
		//as per branch setting branch id
		if(branch.equalsIgnoreCase("mpc")) {
			branch_id = 1;
		}else {
			branch_id = 2;
		}
		
		//checking whether mark sheet of a particular student is already created or not
		//if created then it will not save marks into database
		if(!new ExamData().checkMarkSheet(id, examChoice)) {
			
			//if mark sheet not created iterating list
			while (itr.hasNext()) {
				try {
					//Establishing connection with database
					con = DBConnection.getDBConnection();
					
					//preparing query
					String sql = "insert into mark_sheet (student_id,branch_id,exam_id,subject,physics,chemistry,english,sanskrit,out_of_marks,total_marks) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					pst = con.prepareStatement(sql);
					SetMarkSheet tb = itr.next();
					
					//setting parameter to sql query
					pst.setLong(1,id);
					pst.setInt(2, branch_id);
					pst.setInt(3, examChoice);
					pst.setInt(4, tb.getMaths());
					pst.setInt(5, tb.getPhysics());
					pst.setInt(6, tb.getChemistry());
					pst.setInt(7, tb.getEnglish());
					pst.setInt(8, tb.getSanskrit());
					pst.setInt(9, tb.getOutOfMarks());
					pst.setInt(10, tb.getTotalmarks());
					@SuppressWarnings("unused")
					
					//executing query
					int i = pst.executeUpdate();
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
		}
		return result;
	}
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	/**
	 * This method is used to get the branch and name of the student
	 * @param id
	 * @return list
	 */
	public static List getBranchId(long id) {
		Connection con = null;
		String branch = null;
		
		//creating list to hold student basic info
		List list = null;
		int branch_id = 0;
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			Statement st = con.createStatement();
			
			//preparing and executing query with student id 
			ResultSet rs = st.executeQuery("select branch, first_name, last_name from student_details where student_id = " + id);
			while(rs.next()) {
				list = new ArrayList();
				 // Adding result ino list
				 list.add(rs.getString("branch"));
				 list.add(rs.getString("first_name"));
				 list.add(rs.getString("last_name"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//returning list which contains student basic info
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * This method takes id as parameter and as per that get the marks sheet of the student
	 * @param id
	 * @param examChoice
	 * @return list
	 */
	public static List<SetMarkSheet> getMarksheetByExamId(int exam_id) {
		Connection con = null;
		PreparedStatement pst = null;
		// creating list to hold particular student mark sheet
		List<SetMarkSheet> list = new ArrayList<SetMarkSheet>();
		
		double percentage = 0.0;
		
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			@SuppressWarnings("unused")
			
			//preparing sql query as per given parameter
			String sql = "select * from mark_sheet where exam_id = ?";
			pst = con.prepareStatement(sql);
			
			//setting parameter to query
			pst.setLong(1, exam_id);
			
			//Executing query
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				//creating mark sheet object to hold mark sheet data from database
				SetMarkSheet mark = new SetMarkSheet();
				List stdInfoList =  getBranchId(rs.getInt("student_id"));
				mark.setFirstName((String) stdInfoList.get(1));
				mark.setLastName((String) stdInfoList.get(2));
				mark.setMaths(rs.getInt(5));
				mark.setPhysics(rs.getInt(6));
				mark.setChemistry(rs.getInt(7));
				mark.setEnglish(rs.getInt(8));
				mark.setSanskrit(rs.getInt(9));
				mark.setOutOfMarks(rs.getInt("out_of_marks"));
				mark.setTotalmarks(rs.getInt("total_marks"));
				percentage = (rs.getInt("total_marks") * 100 ) / rs.getInt("out_of_marks");
				mark.setPercentage(percentage);
				
				//adding mark sheet object into list
				list.add(mark);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//returning list to which contains mark sheet data
		return list;
		
	}
	
	/**
	 * This method takes id as parameter and as per that get the marks sheet of the student
	 * @param id
	 * @param examChoice
	 * @return list
	 */
	public static List<SetMarkSheet> getMarksheetById(long id) {
		Connection con = null;
		PreparedStatement pst = null;
		// creating list to hold particular student mark sheet
		List<SetMarkSheet> list = new ArrayList<SetMarkSheet>();
		
		// creating list to hold basic information of a particular student
		List stdInfoList =  getBranchId(id);
		String firstName =  (String) stdInfoList.get(1);
		String lastName =  (String) stdInfoList.get(2);
		double percentage = 0.0;
		
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			@SuppressWarnings("unused")
			
			//preparing sql query as per given parameter
			String sql = "select * from mark_sheet where student_id = ?";
			pst = con.prepareStatement(sql);
			
			//setting parameter to query
			pst.setLong(1, id);
			
			//Executing query
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				//creating mark sheet object to hold mark sheet data from database
				SetMarkSheet mark = new SetMarkSheet();
				mark.setFirstName(firstName);
				mark.setLastName(lastName);
				mark.setMaths(rs.getInt(5));
				mark.setPhysics(rs.getInt(6));
				mark.setChemistry(rs.getInt(7));
				mark.setEnglish(rs.getInt(8));
				mark.setSanskrit(rs.getInt(9));
				mark.setOutOfMarks(rs.getInt("out_of_marks"));
				mark.setTotalmarks(rs.getInt("total_marks"));
				percentage = (rs.getInt("total_marks") * 100 ) / rs.getInt("out_of_marks");
				mark.setPercentage(percentage);
				
				//adding mark sheet object into list
				list.add(mark);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				//closing connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//returning list to which contains mark sheet data
		return list;
		
	}
	/**
	 * As per given parameter this method checks whether mark sheet of particular student and
	 * Particular exam is created or not
	 * @param id
	 * @param branch_id
	 * @param examChoice
	 * @return True/False
	 */
	public boolean checkMarkSheet(long id, int examChoice) {
		Connection con = null;
		PreparedStatement pst = null;
		boolean result = false; 
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			@SuppressWarnings("unused")
			
			//preparing query as per given parameter
			String sql = "select * from mark_sheet where student_id = ? and exam_id = ?";
			pst = con.prepareStatement(sql);
			
			//setting parameter to query
			pst.setLong(1, id);
			pst.setInt(2, examChoice);
			ResultSet rs = pst.executeQuery();
			
			//if mark sheet found returning true
			while(rs.next()) {
				result = true;
			}
		
		}catch(Exception e) {
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
		//returning true/false
		return result;
	}
	
	public List<SetMarkSheet> performanceTracking(long  id,long branch_id,long examChoice) {
		Connection con = null;
		PreparedStatement pst = null;
		double percentage=0.0;
		List<SetMarkSheet> list=new ArrayList<SetMarkSheet>();
		try {
			//Establishing connection with database
			con = DBConnection.getDBConnection();
			@SuppressWarnings("unused")
			
			//preparing query as per given parameter
			String sql = "select * from mark_sheet where student_id=? and exam_id=? and branch_id=?";
			pst = con.prepareStatement(sql);
			
			//setting parameter to query
			pst.setLong(1, id);
			pst.setLong(2,examChoice);
			pst.setLong(3,branch_id);
			
			ResultSet rs = pst.executeQuery();
			//if mark sheet found returning true
			while(rs.next()) {
				//creating mark sheet object to hold mark sheet data from database
				SetMarkSheet mark = new SetMarkSheet();
				List stdInfoList =  getBranchId(rs.getInt("student_id"));
				mark.setFirstName((String) stdInfoList.get(1));
				mark.setLastName((String) stdInfoList.get(2));
				mark.setMaths(rs.getInt("subject"));
				mark.setPhysics(rs.getInt("physics"));
				mark.setChemistry(rs.getInt("chemistry"));
				mark.setEnglish(rs.getInt("english"));
				mark.setSanskrit(rs.getInt("sanskrit"));
				mark.setOutOfMarks(rs.getInt("out_of_marks"));
				mark.setTotalmarks(rs.getInt("total_marks"));
				percentage = (rs.getInt("total_marks") * 100 ) / rs.getInt("out_of_marks");
				mark.setPercentage(percentage);
				mark.setBranch_id(rs.getLong("branch_id"));
				mark.setYear(rs.getInt("year"));
				//adding mark sheet object into list
				list.add(mark);
			}
			System.out.println(list);
		}catch(Exception e) {
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
		//returning list
		return list;
	}

}
