package com.examtracking.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examtracking.bean.SetLoginDetails;
import com.examtracking.bean.StudentDetails;
import com.examtracking.dao.LoginDetailsData;
import com.examtracking.dao.StudentData;
import com.examtracking.util.DBConnection;

/**
 * @author BATCH -'C' this class is used to display the student
 *         information(Login details&details)
 *
 */
public class DisplayStudentInfo {
	static List<StudentDetails> list = new ArrayList<StudentDetails>();

	public static List<StudentDetails> printStudentAllRecords() {
		List<StudentDetails> list=StudentData.getStudentList();
		return list;
	}

	/**
	 * This method is used to display the studentLOginDetails
	 * 
	 */
	public static List<SetLoginDetails> printLoginAllRecords() {
		List<SetLoginDetails> list=LoginDetailsData.getStudentCredentials();
		return list;
	}
}
