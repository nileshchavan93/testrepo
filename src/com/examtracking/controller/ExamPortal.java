package com.examtracking.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.examtracking.bean.AcademicInfo;
import com.examtracking.bean.SetExamTimeTable;
import com.examtracking.bean.SetMarkSheet;
import com.examtracking.dao.ExamData;
import com.examtracking.dao.LoginDetailsData;
import com.examtracking.service.ExamDetailsImpl;
import com.examtracking.util.FieldValidation;
import com.examtracking.view.DisplayExamDetails;

/**
 * This class Displays Examination portal Functionality
 * 
 * @author BATCH-'C'
 *
 */
public class ExamPortal {

	/**
	 * This method is used to display the examination functionalities of the
	 * students performed by the Admin
	 */
	public static void getMasterMenuForExam() {

		String choiceToContinue = null;
		do {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// Displaying Menu of Examination portal
			System.out.println("==============================================");
			System.out.println("\nWelcome to Examination portal");
			System.out.println("==============================================");
			System.out.println("select operation");
			System.out.println("----------------------------------------------");
			System.out.println("1) Set Exam Time Table ");
			System.out.println("2) Eligibility check for examination");
			System.out.println("3) Set and View Marks of Students");
			System.out.println("4) Go Back To Admin Menu");
			System.out.println("----------------------------------------------");
			//declaring the choice variable to select the examination menu functionality
			int choice = 0;
			try {
				choice = sc.nextInt();
				while (choice <= 0 || choice > 5) {
					System.err.println("please select valid option");
					choice = sc.nextInt();
				}
			} catch (Exception e) {
				System.err.println("please enter integers only");
				getMasterMenuForExam();
			}

			switch (choice) {
			case 1: {
				// after selection type of exam this method set exam time table
				ExamPortal.getExaminationMenu();
				break;
			}
			case 2: {
				// set and view the eligibility criteria for the studentExam
				try {
					System.out.println("==============================");
					System.out.println("Welcome to Eligibility check portal");
					System.out.println("==============================");
					System.out.println("select operation");
					System.out.println("-------------------------------");

					System.out.println("1)set the eligibility check of student");
					System.out.println("2)view the eligibility check of student");
					System.out.println("3)view the eligibility check of all student");
					// providing the user to enter the choice
					int choice1 = 0;
					try {
						choice1 = sc.nextInt();
						while (choice1 <= 0 || choice1 > 3) {
							System.err.println("please select valid option");
							choice1 = sc.nextInt();
						}
					} catch (Exception e) {
						System.err.println("please enter integers only");
						getMasterMenuForExam();
					}

					switch (choice1) {
					// set the eligibility check for the student
					case 1:
						ExamPortal eligibleCheck = new ExamPortal();
						eligibleCheck.setEligibilityForExam();
						break;
					case 2:
						// to view the eligibility criteria for the individual student
						DisplayExamDetails.printEligibleRecords();
						break;
					case 3:
						// to view the eligibility criteria for all the students
						DisplayExamDetails.printEligibleAllRecords();
						break;

					default:
						System.out.println("you have enterted the wrong choice:");
						break;
					}
				} catch (Exception e) {

					e.printStackTrace();
				}

				break;
			}
			case 3: {
				// set and view marks for the student
				ExamPortal.SetMarksSubMenu();
				break;
			}

			case 4: {
				// go back to main menu
				AdminMenu menu = new AdminMenu();
				try {
					menu.adminFunctions();
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;
			}
			}
			// here can choose option to continue
			System.out.println("Do You want to continue(yes/no)");
			choiceToContinue = sc.next();
			
		} while (choiceToContinue.equalsIgnoreCase("yes"));
	}
	
	/**
	 * The method is to used to display the time table for students
	 * 
	 * @param branchChoice parameter to choose the branch
	 * 
	 */
	public static void MPCExamMenu(int branchChoice) {
		String choiceToContinue = null;
		do {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// Displaying Menu to Admin
			System.out.println("==============================================");
			System.out.println("\nWelcome to Examination portal");
			System.out.println("==============================================");
			System.out.println("Select the Exam Type");
			System.out.println("----------------------------------------------");
			System.out.println("1) First Mid Term  ");
			System.out.println("2) Second Mid Term");
			System.out.println("3) Final Exam");
			System.out.println("4) View All Time Table");
			System.out.println("5) Go Back");
			System.out.println("----------------------------------------------");
			// providing the user to enter the choice
			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("enter the valid choice");
				MPCExamMenu(branchChoice);
			}
			int examChoice = choice;
			switch (choice) {
			case 1: {
                //Method call to set the timetable for first mid term
				new ExamPortal().checkTimeTable(examChoice, branchChoice);
				break;
			}
			case 2: {
				 //Method call to set the timetable for second mid term
				new ExamPortal().checkTimeTable(examChoice, branchChoice);
			

				break;
			}
			case 3: {
				 //Method call to set the timetable for final exam
				new ExamPortal().checkTimeTable(examChoice, branchChoice);
				break;
			}
			case 4: {
				//Method call to view the timetable according to the branch choice
				try {
					if (branchChoice == 1) {
						DisplayExamDetails.displayTimeTable(branchChoice, 1);
						System.out.println();
						DisplayExamDetails.displayTimeTable(branchChoice, 2);
						System.out.println();
						DisplayExamDetails.displayTimeTable(branchChoice, 3);
						System.out.println();
					} else {
						DisplayExamDetails.displayTimeTable(branchChoice, 1);
						System.out.println();
						DisplayExamDetails.displayTimeTable(branchChoice, 2);
						System.out.println();
						DisplayExamDetails.displayTimeTable(branchChoice, 3);
						System.out.println();
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;
			}
			case 5: {
				getExaminationMenu();
				break;
			}
			}
			// Here can choose option to continue
			System.out.println("Do You want to continue(yes/no)");
			choiceToContinue = sc.next();
			
		} while (choiceToContinue.equalsIgnoreCase("yes"));
	}
	
	/**
	 * This method is used to select the branch from examination portal
	 * 	and set the marks according to branch
	 */

	public static void getExaminationMenu() {

		String choiceToContinue = null;
		do {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// Displaying Menu of Examination portal
			System.out.println("==============================================");
			System.out.println("\nWelcome to Examination portal");
			System.out.println("==============================================");
			System.out.println("Select the Branch");
			System.out.println("----------------------------------------------");
			System.out.println("1) MPC ");
			System.out.println("2) BPC");
			System.out.println("3) Go Back");
			System.out.println("4) Go Back To Admin Menu");
			System.out.println("----------------------------------------------");
			//Creating choice variable to select the branch
			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception e) {

				System.out.println(" please enter the valid choice");
				
				getExaminationMenu();
			}
			switch (choice) {
			case 1: {
				//Method call for mpcExamMenu to set the marks for mpc students 
				MPCExamMenu(choice);
				break;
			}
			case 2: {
				//Method call for mpcExamMenu to set the marks for mpc students 
				MPCExamMenu(choice);
				break;
			}
			case 3: {
				try {
					//Method call for going back to examination menu
					ExamPortal.getMasterMenuForExam();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				break;
			}
			case 4: {
				//Method call for going back to admin menu
				AdminMenu menu = new AdminMenu();
				try {
					menu.adminFunctions();
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;
			}
			}
			// here can choose option to continue
			System.out.println("Do You want to continue(yes/no)");
			choiceToContinue = sc.next();
			
		} while (choiceToContinue.equalsIgnoreCase("yes"));
	}
	/**
	 * This Method used to set the timetable based on the branch choice and exam choice
	 * If timetable is already created providing the option to reset the timetable
	 * 
	 * @param branchChoice
	 * @param examChoice
	 */
	@SuppressWarnings("resource")
	public void checkTimeTable(int examChoice, int branchChoice) {
		@SuppressWarnings("unused")
		int count = 0;
		//Initializing the scanner class
		Scanner sc = new Scanner(System.in);
		
		//creating the list for exam time table to retrieve the data
		List<SetExamTimeTable> timeList = ExamData.getAllTimeTable();
		
		Iterator<SetExamTimeTable> itr = timeList.iterator();
		//checking if the list size is empty or not to set the time table
		
		
		if (timeList.size() <= 0) {
			updateMidTerm1TimeTable(examChoice, branchChoice);
		} 
		//if the list is not empty providing the option to reset the timetable
		else {
			boolean flag = false;
			while (itr.hasNext()) {
				
				SetExamTimeTable tb = itr.next();
				
				if (tb.getExamTypeId() == examChoice && tb.getBranchId() == branchChoice) {
					System.out.println("Time Table Already created do u want to reset (yes/no)");
					
					String choice = sc.next();
					
					if (choice.equals("yes")) {
						@SuppressWarnings("unused")
						boolean flag1 = new ExamData().resetTimeTable(examChoice, branchChoice);
					} else {
						ExamPortal.MPCExamMenu(branchChoice);
					}
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
			
			if (flag) {
				updateMidTerm1TimeTable(examChoice, branchChoice);
			}
		}
	}

	/**
	 * This Method used to set time table for Different Examination
	 * 
	 * @param branchChoice
	 * @param examChoice
	 */
	@SuppressWarnings("resource")
	public static void updateMidTerm1TimeTable(int examChoice, int branchChoice) {
		//creating the list for exam time table to set  the data
		ArrayList<SetExamTimeTable> midTerm1List = new ArrayList<SetExamTimeTable>();
		
		//Initializing the scanner class
		Scanner sc = new Scanner(System.in);
		
		//providing the option for user to set the number of subjects
		System.out.println("How many subject you want add");
		int subSize = 0;
		try {
			subSize = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Please Enter Integers only");
			System.exit(0);
		}

		int choice = 2;
        // according to number of subjects setting the details of timetable for that subjects
		for (int i = 0; i < subSize; i++) {
			
			SetExamTimeTable setMid1 = new SetExamTimeTable();
			
			System.out.println("Enter Date");

			FieldValidation dateValid = new FieldValidation();
			
			String date = dateValid.isThisDateValid(choice);
			
			setMid1.setDate(date);

			System.out.println("Enter Subject");
			
			String subject = sc.next();
			
			setMid1.setSubject(subject);

			System.out.println("Enter Time");
			
			String time = sc.next();
			
			setMid1.setTime(time);

			System.out.println("Enter Venue");
			
			String venue = sc.next();
			
			setMid1.setVenue(venue);

			setMid1.setBranchId(branchChoice);
			
			
			setMid1.setExamTypeId(examChoice);

			midTerm1List.add(setMid1);
		}
		try {
//			ExamDetailsImpl.SetExamTimeTable(midTerm1List);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * this method is to used to set the marks for students
	 * 
	 * @param branchChoice parameter to choose the branch
	 * 
	 */
	@SuppressWarnings("unused")
	public static void SetMarksSubMenu() {
		String choiceToContinue = null;
		do {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// Displaying Menu of examination portal to set the marks based on the exam type
			System.out.println("==============================================");
			System.out.println("\nWelcome to Examination portal");
			System.out.println("==============================================");
			System.out.println("Select the Exam Type");
			System.out.println("----------------------------------------------");
			System.out.println("1) Set Marks for First Mid Term Exam");
			System.out.println("2) Set Marks for Second Mid Term Exam");
			System.out.println("3) Set Marks for Final Exam");
			System.out.println("4) View Student Marks");
			System.out.println("5) Go Back");
			System.out.println("----------------------------------------------");
			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("please enter the valid option");
				SetMarksSubMenu();

			}
			ExamDetailsImpl setMark = new ExamDetailsImpl();
			switch (choice) {
			case 1: {
				// set the marks for the first mid term examination
				try {
					new ExamPortal().setMarks(choice);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			case 2: {
				// set the marks for the second mid term examination
				try {
					new ExamPortal().setMarks(choice);
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			}
			case 3: {

				// set the marks for the final mid term examination
				try {
					new ExamPortal().setMarks(choice);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			case 4: {
				// view the student marks
				viewMarksMenu();
				break;
			}
			case 5: {
				// go back to examination menu
				ExamPortal.getMasterMenuForExam();
				break;
			}
			}
			// here can choose option to continue
			System.out.println("Do You want to continue(yes/no)");
			choiceToContinue = sc.next();
			
		} while (choiceToContinue.equalsIgnoreCase("yes"));
	}

	/**
	 * this method is to view the marks of the student by the admin
	 */
	public static void viewMarksMenu() {
		String choiceToContinue = null;
		do {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// view menu according to exam type for both individal id and all students
			System.out.println("==============================================");
			System.out.println("\nWelcome to Examination portal");
			System.out.println("==============================================");
			System.out.println("Select the Exam Type to view Marks");
			System.out.println("----------------------------------------------");
			System.out.println("1) View Marks for First Mid Term Exam By Id");
			System.out.println("2) View Marks for Second Mid Term Exam By Id");
			System.out.println("3) View Marks for Final Exam By Id");
			System.out.println("          --------------------           ");
			System.out.println("4) View All Marks for First Mid Term Exam");
			System.out.println("5) View All Marks for Second Mid Term Exam");
			System.out.println("6) View All Marks for Final Exam");
			System.out.println("7) View Rank of the Student");
			System.out.println("8) Go Back");
			System.out.println("----------------------------------------------");
			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("please enter the valid choice");
				viewMarksMenu();
			}
            //creating the object for displayexamdetails  to display the marks according to the choice
			DisplayExamDetails viewMark = new DisplayExamDetails();
			
			switch (choice) {
			case 1: {
				try {
					// View Marks for First Mid Term Exam By Id
					viewMark.displayStudentMarksById(choice);
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;
			}
			case 2: {
				try {
					// View Marks for second Mid Term Exam By Id
					viewMark.displayStudentMarksById(choice);
				} catch (Exception e) {

					e.printStackTrace();
				}

				break;
			}
			case 3: {
				// View Marks for finalExam By Id
				try {
					viewMark.displayStudentMarksById(choice);
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;
			}
			case 4: {
				// View All Marks for First Mid Term Exam
				viewMark.displayAllMarksSheetByExam(choice);
				break;
			}
			case 5: {
				// View All Marks for second Mid Term Exam
				viewMark.displayAllMarksSheetByExam(choice);
				break;
			}
			case 6: {
				// View All Marks for final Exam
				viewMark.displayAllMarksSheetByExam(choice);
				break;
			}
			case 7: {
				// view the rank of the student
				new ExamPortal().displayRankMenu();
				break;
			}
			case 8: {
				try {
					// go back to examinationmenu
					SetMarksSubMenu();
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;
			}
			}
			// here can choose option to continue
			System.out.println("Do You want to continue(yes/no)");
			choiceToContinue = sc.next();
			
		} while (choiceToContinue.equalsIgnoreCase("yes"));
	}

	/**
	 * this method is used to view the rank of the student by admin
	 */

	public void displayRankMenu() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		// Menu for displaying the rank opf the student according to branch
		System.out.println("==============================================");
		System.out.println("\nWelcome to Examination portal");
		System.out.println("==============================================");
		System.out.println("Select the Branch to view Rank of the student");
		System.out.println("----------------------------------------------");
		System.out.println("1) MPC");
		System.out.println("2) BPC");
		System.out.println("2) Go Back");
		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (Exception e) {
			
			System.out.println("please enter the valid option");
			
			displayRankMenu();
		}
		DisplayExamDetails viewMark = new DisplayExamDetails();
		
		switch (choice) {
		case 1: {
			// view the rank of the MPC student
			viewMark.displayAllStudentByRank(choice);
			
			break;
		}
		case 2: {
			// view the rank of the BPC student
			viewMark.displayAllStudentByRank(choice);
			
			break;
		}
		case 3: {
			// go back to marks menu
			viewMarksMenu();
			break;
		}
		}
	}

	
	

	/**
	 * This method to view the eligible check for student
	 */
	public void setEligibilityForExam() {

		// creating a object for academic details of student
		AcademicInfo academicInfo = new AcademicInfo();
		
		// creating a list for Academic info
		 List<AcademicInfo> list = new ArrayList<AcademicInfo>();
		String check = null;
		
		//initialzing the scanner class
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		boolean setattendance = false;

		System.out.println("enter the student id to set the eligibility criteria");
		long stud_id = Long.parseLong(sc.nextLine());
		
		boolean checkid = LoginDetailsData.checkIdInLogins(stud_id);
		
		boolean checkid11 = true;
		
		if (checkid) {
			Iterator<AcademicInfo> it = list.iterator();
			while (it.hasNext()) {
				AcademicInfo st1 = (AcademicInfo) it.next();
				if (stud_id == st1.getStudent_id()) {
					checkid11 = false;
				}
			}
			//  set the eligibility check for the student according to attendance and fee
			if (checkid11) {
				System.out.println("set the attendance for " + stud_id + "\n");
				
				System.out.println("enter the number of days present by student ");
			
				//setting the attendance by number of days present by the student
				int attendance = Integer.parseInt(sc.nextLine());

				while (attendance < 0 || attendance > 150) {
					System.out.println(" pls enter the range between 0 and 150 to set attendance");
					attendance = Integer.parseInt(sc.nextLine());
				}
				academicInfo.setAttendance(attendance);
				
				double setattendance1 = ExamDetailsImpl.setAttendance(attendance);

				if (setattendance1 > 50) {
					setattendance = true;
				}
				
				
				//setting the fee status of the student
				System.out.println("Set student fee Status\n1.paid\n2.Due");
				String fee = sc.nextLine();
				
				while (!(fee.equalsIgnoreCase("paid")) && !(fee.equalsIgnoreCase("due"))) {
					System.out.println("Set student fee Status\n1.paid\n2.Due");
					fee = sc.nextLine();
				}
				academicInfo.setFee(fee);
				
				boolean setFee = ExamDetailsImpl.setFee(fee);
				
				academicInfo.setStudent_id(stud_id);
				
				
				
				//setting  the eligibilty of the student
				boolean check1 = ExamDetailsImpl.check(setattendance, setFee);
				if (check1) {
					check = "yes";
				} else {
					check = "no";
				}
				academicInfo.setCheck(check);
				ExamDetailsImpl.setStudentEligibility(academicInfo);
				
				
				
				//providing the eligibilty of the student based on attendance and fee status
				if (setattendance && setFee) {
					System.out.println("student is eligible for exam");
					list = ExamData.getAcademicList();
				} else if (setattendance || !(setFee)) {
					System.out.println("student is not eligible for exam");

					list = ExamData.getAcademicList();

					System.out.println("fee is pending");

					System.out.println("oops!!!Better luck next time");

				} else if (!(setattendance) || (setFee)) {

					System.out.println("student is not eligible for exam");

					list = ExamData.getAcademicList();

					System.out.println("attendance is less than 50 your are not eligible for examination");

				}

			} else {
				System.out.println("student status is already set");
			}
		}

		else {
			System.out.println("Id not found,please enter the correct student  id for signup");
			setEligibilityForExam();
		}
	}
	// creating a list for MpC marks
	static List<SetMarkSheet> MPClist = new ArrayList<SetMarkSheet>();

	/**
	 * This method checks whether student attended exam or not
	 * 
	 * @param examChoice
	 */
	public void setMarks(int examChoice) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the student id");
		long stud_id = Long.parseLong(sc.nextLine());
		if (new ExamPortal().checkEligible(stud_id)) {

			new ExamPortal().setMarkSheetForStudent(stud_id, examChoice);
		} else {
			System.out.println("Student is not eligible for exam.");
		}
	}
	/**
	 * This method checks whether student eligible for exam or not
	 * 
	 * @return true is eligible
	 * 
	 */
	public boolean checkEligible(long stud_id) {
		
		List<AcademicInfo> list = new ArrayList<AcademicInfo>();
		list = ExamData.getAcademicList();
		if (list.size() == 0) {
			System.out.println("Student List is Empty");
		} else {
			Iterator<AcademicInfo> it = list.iterator();
			
			while (it.hasNext()) {
				AcademicInfo st1 = (AcademicInfo) it.next();
				if (stud_id == st1.getStudent_id()) {
					if (st1.getCheck().equalsIgnoreCase("yes")) {

						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method used to set the marks for the student
	 * 
	 * @param examchoice
	 * @param id
	 */
	public void setMarkSheetForStudent(long stud_id, int examChoice) {

		// creating the list to set the marks sheet
		List<SetMarkSheet> list = new ArrayList<SetMarkSheet>();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		// creating the object class for the setMarks sheet

		SetMarkSheet mpc = new SetMarkSheet();

		// after selecting this choice admin can set the marks for the student for MPC
		if (examChoice == 3) {
			mpc.setStudent_id(stud_id);
			// enter the admin to set maths marks
			System.out.println("Enter Marks for Mathematics");
			int math = sc.nextInt();
			
			
			while (math <= 0 || math > 75) {
				System.out.println("Enter Mark Between 0 to 75");
				math = sc.nextInt();
			}
			mpc.setMaths(math);

			// enter the admin to set physics marks
			System.out.println("Enter Marks for Physics");
			int physics = sc.nextInt();
			
			while (true) {
				if (physics <= 0 || physics > 75) {
					System.out.println("Enter Mark Between 0 to 75");
					physics = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setPhysics(physics);

			// enter the admin to set Chemistry marks
			System.out.println("Enter Marks for Chemistry");
			int chemistry = sc.nextInt();
			
			while (true) {
				if (chemistry <= 0 || chemistry > 75) {
					System.out.println("Enter Mark Between 0 to 75");
					chemistry = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setChemistry(chemistry);

			// enter the admin to set English marks
			System.out.println("Enter Marks for English");
			int english = sc.nextInt();
			
			while (true) {
				if (english <= 0 || english > 75) {
					System.out.println("Enter Mark Between 0 to 75");
					english = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setEnglish(english);

			// enter the admin to set sanskrit marks
			System.out.println("Enter Marks for Sanskit");
			int sanskrit = sc.nextInt();
			
			while (true) {
				if (sanskrit <= 0 || sanskrit > 75) {
					System.out.println("Enter Mark Between 0 to 75");
					sanskrit = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setSanskrit(sanskrit);

			// total marks calculation
			int total = math + chemistry + physics + english + sanskrit;
			
			mpc.setTotalmarks(total);
			
			mpc.setOutOfMarks(425);

		} else {
			mpc.setStudent_id(stud_id);

			System.out.println("Enter Marks for Mathematics");
			int math = sc.nextInt();
			
			
			
			while (true) {
				if (math <= 0 || math > 25) {
					System.out.println("Enter Mark Between 0 to 25");
					math = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setMaths(math);

			System.out.println("Enter Marks for Physics");
			int physics = sc.nextInt();
			
			
		
			while (true) {
				if (physics <= 0 || physics > 25) {
					System.out.println("Enter Mark Between 0 to 25");
					physics = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setPhysics(physics);

			System.out.println("Enter Marks for Chemistry");
			int chemistry = sc.nextInt();
			
			while (true) {
				if (chemistry <= 0 || chemistry > 25) {
					System.out.println("Enter Mark Between 0 to 25");
					chemistry = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setChemistry(chemistry);

			System.out.println("Enter Marks for English");
			int english = sc.nextInt();
			while (true) {
				if (english <= 0 || english > 25) {
					System.out.println("Enter Mark Between 0 to 25");
					english = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setEnglish(english);

			System.out.println("Enter Marks for Sanskrit");
			int sanskrit = sc.nextInt();
			while (true) {
				if (sanskrit <= 0 || sanskrit > 25) {
					System.out.println("Enter Mark Between 0 to 25");
					sanskrit = sc.nextInt();
				} else {
					break;
				}
			}
			mpc.setSanskrit(sanskrit);

			int total = (math + chemistry + physics + english + sanskrit);
			mpc.setTotalmarks(total);
			mpc.setOutOfMarks(125);

		}
		// common values for both branches(MPC&BPC)
		list.add(mpc);
		try {
			ExamData.saveAllStudentMarksList(list, stud_id, examChoice);
			System.out.println("Marks saved Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

}
