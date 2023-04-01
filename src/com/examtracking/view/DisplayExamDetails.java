package com.examtracking.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.examtracking.bean.AcademicInfo;
import com.examtracking.bean.SetExamTimeTable;
import com.examtracking.bean.SetMarkSheet;
import com.examtracking.dao.ExamData;
import com.examtracking.util.DBConnection;

/**
 * @author BATCH-'C' this class is used to display the student records & rank &
 *         eligibility records by Id
 */
public class DisplayExamDetails {

	/**
	 * This method is used to Display the student timetable list
	 * 
	 * @param branchId
	 * @param examChoice
	 */
	public static void displayTimeTable(int branchId, int examChoice) {
		// creating the list for setexamtimetable from the database
		List<SetExamTimeTable> list = new ArrayList<SetExamTimeTable>();

		list = new ExamData().getExamTimeTable(branchId, examChoice);
		String typeOfExam = null;
		// selecting exam choice1 you can display the first midtermexam
		if (examChoice == 1) {
			typeOfExam = "First Midterm Exam";
		}
		// selecting exam choice1 you can display the first midtermexam
		else if (examChoice == 2) {
			typeOfExam = "Second Midterm Exam";
		}
		// selecting exam choice1 you can display the first midtermexam
		else {
			typeOfExam = "Final Exam";
		}
		if (list.size() == 0) {
			System.out.println(typeOfExam + "Time Table Not Upadated");
		} else {
			// to display the timetable
			Iterator<SetExamTimeTable> it = list.iterator();
			System.out.println("Time Table for " + typeOfExam);
			System.out.println("=========================================================================================================");
			System.out.printf("%20s %20s %20s %20s ", "Date", "Time", "Subject", "Venue");
			System.out.println();
			System.out.println("=========================================================================================================");
			while (it.hasNext()) {
				SetExamTimeTable setTime = (SetExamTimeTable) it.next();
				System.out.format("%20s %20s %20s %20s", setTime.getDate(), setTime.getTime(), setTime.getSubject(),
						setTime.getVenue());
				System.out.println();
			}
			System.out.println("---------------------------------------------------------------------------------------------------------");
		}
	}

	/**
	 * this method is used to display the marks sheet of the student
	 * 
	 * @param exam_type
	 * @param branch_id
	 * @return list if marks is available in the list
	 */
	@SuppressWarnings("unused")
	public static List<SetMarkSheet> mpcMarks(int exam_type, int branch_id) {
		List<SetMarkSheet> list = new ArrayList<SetMarkSheet>();

		try {
			Connection conn = DBConnection.getDBConnection();
			Statement stmt = conn.createStatement();
			String sql = "select marksheet_id,student_id,branch_id,exam_id,total_marks,out_of_marks from mark_sheet where exam_id = ? and branch_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, exam_type);
			pst.setInt(2, branch_id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SetMarkSheet set = new SetMarkSheet();
				set.setMarkSheet_id(rs.getInt(1));
				set.setStudent_id(rs.getInt(2));
				set.setBranch_id(rs.getInt(3));
				set.setExam_id(rs.getInt(4));
				set.setTotalmarks(rs.getInt(5));
				set.setOutOfMarks(rs.getInt(6));
				list.add(set);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// creating the list for MPC fro database
	static List<SetMarkSheet> MPClist = new ArrayList<SetMarkSheet>();

	/**
	 * This method Displays Mark sheet by taking id as parameter
	 * 
	 * @param id
	 */
	public void displayStudentMarksById(int choice) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		// entering the id to display the marks
		System.out.println("Enter Student Id to View Mark Sheet");
		long stud_id = 0;
		try {
			stud_id = sc.nextInt();
		} catch (Exception e) {
			System.out.println("please enter the valid choice");
		}

		List<SetMarkSheet> markList = ExamData.getMarksheetById(stud_id, choice);

		System.out.println("List SIze " + markList.size());
		System.out.println(" Student Mark Sheet");
		System.out.println("========================================================================================================================================================");

		System.out.printf("%15s %15s %15s %15s %15s  %15s %15s %15s %15s ", "Name", "Biology/Math", "Chemistry",
				"Physics", "English", "Sanskrit", "Out Of", " Total", "Percentage");
		System.out.println();
		System.out.println("========================================================================================================================================================");
		for (SetMarkSheet marks : markList) {
			System.out.format("%15s %15s %15s %15s  %15s %15s %15s %15s %15s",
					marks.getFirstName() + " " + marks.getLastName(), marks.getMaths(), marks.getChemistry(),
					marks.getPhysics(), marks.getEnglish(), marks.getSanskrit(), marks.getOutOfMarks(),
					marks.getTotalmarks(), marks.getPercentage());
			System.out.println("\n");

		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");

	}

	/**
	 * This method is used to display all the marks for student according to their
	 * branch
	 * 
	 * @param examChoice is parameter to choose the type of exam
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public void displayAllMarksSheetByExam(int examChoice) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		Connection conn = DBConnection.getDBConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		@SuppressWarnings("rawtypes")
		List MpcList = new ArrayList();
		@SuppressWarnings("rawtypes")
		List BpcList = new ArrayList();

		// after selecting this student can view all details of the firstmidterm exam
		// studentmarks
		if (examChoice == 4) {

			MpcList = DisplayExamDetails.mpcMarks(1, 1);

			BpcList = DisplayExamDetails.mpcMarks(1, 2);

		}
		// after selecting this student can view all details of the second midterm exam
		// studentmarks
		else if (examChoice == 5) {
			MpcList = DisplayExamDetails.mpcMarks(2, 1);

			BpcList = DisplayExamDetails.mpcMarks(2, 2);

		}
		// after selecting this student can view all details of the final exam
		// studentmarks

		else if (examChoice == 6) {
			MpcList = DisplayExamDetails.mpcMarks(3, 1);

			BpcList = DisplayExamDetails.mpcMarks(3, 2);

		}

		// crating the list for the Setmarks from database
		List<SetMarkSheet> combineList = new ArrayList<SetMarkSheet>();
		combineList.addAll(MpcList);
		combineList.addAll(BpcList);
		System.out.println(combineList.size());
		System.out.println(" Student Mark Sheet");
		System.out.println("=================================================================================================================");

		System.out.printf("%15s %15s %15s %15s %15s  ", "marksheet_id", "student_id", "branch_id", "total_marks",
				"out_of_marks\n");
		System.out.println("=================================================================================================================");
		for (SetMarkSheet marks : combineList) {

			System.out.format("%15s %15s %15s %15s %15s", marks.getMarkSheet_id(), marks.getStudent_id(),
					marks.getBranch_id(), marks.getTotalmarks(), marks.getOutOfMarks() + "\n");

			System.out.println("-------------------------------------------------------------------------------------------------------------");
		}
	}

	/**
	 * This method display the rank of a student
	 * 
	 * @param choice according to the branch
	 */
	public void displayAllStudentByRank(int choice) {
		// creating the setmarks sheet from database
		ArrayList<SetMarkSheet> list = new ArrayList<SetMarkSheet>();

		// get the list from database
		list = (ArrayList<SetMarkSheet>) ExamData.getAllStudentMarksList(choice);

		Collections.sort(list, SetMarkSheet.totalMarks);
//		System.out
//				.println("-------------------------------------------------------------------------------------------");
//		System.out.printf("%15s %15s  %15s %15s ", "Rank", "Name", "Marks", "Percentage");
//		System.out.println();
//		System.out
//				.println("-------------------------------------------------------------------------------------------");
//		for (SetMarkSheet marks : list) {
//			int rankIndex = list.indexOf(marks) + 1;
//			System.out.printf("%15s %15s  %15s %15s ", rankIndex, marks.getFirstName() + " " + marks.getLastName(),
//					marks.getTotalmarks(), marks.getPercentage());
//			System.out.println();
//		}
//		System.out
//				.println("-------------------------------------------------------------------------------------------");
		System.out
				.println("============================================================");
		System.out.printf("%15s %18s  %15s ", "Rank", "Name", "Marks");
		System.out.println();
		System.out
				.println("============================================================");
		for (SetMarkSheet marks : list) {
			int rankIndex = list.indexOf(marks) + 1;
			System.out.printf("%15s %18s  %15s ", rankIndex, marks.getFirstName() + " " + marks.getLastName(),
					marks.getTotalmarks());
			System.out.println();
		}
		System.out
				.println("-------------------------------------------------------------");
	}

	// creating list academic info from the database
	static List<AcademicInfo> list = new ArrayList<AcademicInfo>();

	/**
	 * this method to print all records in the all student Details
	 */
	public static void printEligibleAllRecords() {
		// getting the list from the database
		list = ExamData.getAcademicList();

		if (list.size() == 0) {
			System.out.println("Student List is Empty");
		} else {

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%5s %28s %28s %25s", "Student ID", "Attendance percenatge", "fee", "eligible check");
			System.out.println();
			System.out.println(

					"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			Iterator<AcademicInfo> it = list.iterator();
			while (it.hasNext()) {
				AcademicInfo st1 = (AcademicInfo) it.next();
				long percentage = (long) ((st1.getAttendance() * 100) / 150.0);
				System.out.format("%5s %30s %30s %25s ", st1.getStudent_id(), percentage, st1.getFee(), st1.getCheck());
				System.out.println();
			}
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}

	/**
	 * this method to print particular student Details
	 */
	public static void printEligibleRecords() {

		boolean flag = false;
		// to get the academic list from the database
		list = ExamData.getAcademicList();

		System.out.println("enter the student id to view the eligibility check");
		long id = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try {
			id = sc.nextLong();
		} catch (Exception e) {
			System.err.println("Enter Integers only");
			printEligibleRecords();
		}
		if (list.size() == 0) {
			System.out.println("Student List is Empty");
		} else {
			Iterator<AcademicInfo> it = list.iterator();
			while (it.hasNext()) {
				AcademicInfo st1 = (AcademicInfo) it.next();
				if (id == st1.getStudent_id()) {
					System.out.println(
							"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.printf("%5s %28s %28s %28s ", "Student ID", "Attendance", "fee", "eligible check");
					System.out.println();
					System.out.println(
							"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.format("%5s %30s %30s %30s ", st1.getStudent_id(), st1.getAttendance(), st1.getFee(),
							st1.getCheck());
					flag = true;
					break;

				}

			}
			if (flag == false) {
				System.out.println("Id not found,Enter again");
				printEligibleRecords();
			}

			System.out.println(
					"\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}

	}

}
