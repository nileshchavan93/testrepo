package com.examtracking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.examtracking.bean.StudentDetails;
import com.examtracking.dao.StudentData;
import com.examtracking.service.ExamDetailsImpl;
import com.examtracking.util.FieldValidation;
import com.examtracking.view.DisplayExamDetails;

import com.examtracking.view.DisplayStudentInfo;

/**
 * 
 * @author BATCH-'C' this class is used to login into student menu
 */
public class StudentMenu {
	/**
	 * this method is used to display the student menu
	 * 
	 * @throws IOException
	 */
	public void studentMenu() {

		String choiceToContinue = null;
		do {
			//initializing the scanner class
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// Displaying Menu to student
			System.out.println("==============================");
			System.out.println("Welcome to student portal");
			System.out.println("==============================");
			System.out.println("Select operation");
			System.out.println("-------------------------------");
			System.out.println("1) Reset Password");
			System.out.println("2) View marks");
			System.out.println("3) View Rank");
			System.out.println("4) View Attendance and Fee status");
			System.out.println("5) View TimeTable");
			System.out.println("6) View and Update Personal Info");
			System.out.println("7) Go back to Main Menu");
			System.out.println("-------------------------------");

			// providing the user to enter the choice
			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("please enter the valid option");
				studentMenu();

			}
			switch (choice) {
			case 1: {
				// after selecting this option user can reset the password
				LoginUser.setStudentResetPassword();
				break;
			}
			case 2: {
				// after selecting this type of option user can view the marks
				ExamPortal.viewMarksMenu();
				break;
			}
			case 3: {
				System.out.println("select the branch\n1.Mpc\n2.Bpc");
				int choice1 = 0;
				try {
					choice1 = sc.nextInt();
					while (choice1 <= 0 || choice1 > 2) {
						System.err.println("please select valid option");
						choice1 = sc.nextInt();
					}
				} catch (Exception e) {
					System.err.println("please enter integers only");
					studentMenu();
				}

				new DisplayExamDetails().displayAllStudentByRank(choice1);
				break;
			}
			case 4: {
				// after selecting this type of option student can view the attendance and fee
				DisplayExamDetails.printEligibleRecords();
				break;

			}
			case 5: {
				// after selecting this type of option user can view the timetable
				System.out.println("enter the branch to view the time table\n1.Mpc\n2.Bpc");
				int branchChoice = 0;
				try {
					branchChoice = sc.nextInt();
					while (branchChoice <= 0 || branchChoice > 2) {
						branchChoice = sc.nextInt();
					}
				} catch (Exception e) {
					System.err.println("Enter Integers only");
					studentMenu();
				}
				try {
					if (branchChoice == 1) {
						DisplayExamDetails.displayTimeTable(branchChoice, 1);
						DisplayExamDetails.displayTimeTable(branchChoice, 2);
						DisplayExamDetails.displayTimeTable(branchChoice, 3);
					} else {
						DisplayExamDetails.displayTimeTable(branchChoice, 1);
						DisplayExamDetails.displayTimeTable(branchChoice, 2);
						DisplayExamDetails.displayTimeTable(branchChoice, 3);
					}
					// MidTermExam1.displayTimeTable();
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;
			}
			case 6: {
				// after selecting this type of option user can update the personal info
				System.out.println("select operation");
				System.out.println("-------------------------------");
				System.out.println("1) View details");
				System.out.println("2) Update details");
				int choice1 = 0;
				try {
					choice1 = sc.nextInt();
					while (choice1 <= 0 || choice1 > 2) {
						System.out.println("please select valid option");
						choice1 = sc.nextInt();
					}
				} catch (Exception e) {
					System.err.println("please enter integers only");
					studentMenu();

				}

				if (choice1 == 1) {
					DisplayStudentInfo.printStudentAllRecords();
				}
				if (choice1 == 2) {
					StudentMenu.studentDetailsUpdation();
				}

				break;
			}

			case 7: {
				// after selecting this option user can go back to main menu
				LoginUser.loginUser();

			}
			}
			// here can choose option to continue
			System.out.println("Do You want to continue1(yes/no)");
			choiceToContinue = sc.next();
		} while (choiceToContinue.equalsIgnoreCase("yes"));
	}
	/**
	 * This method Used to Add new Student Record
	 *
	 */
	public void enRollNewStudent() {
		
		//creating the list object to enroll new student into list
		List<StudentDetails> list = new ArrayList<StudentDetails>();
		
		//initializing the scanner class
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
        //creating the studentdetails object to set and get the details of student during registration 
		StudentDetails student = new StudentDetails();
		
		
		//creating fieldvalidation object to provide the validation for mobile number,email
		FieldValidation validate = new FieldValidation();

		//Declaring the student_id variable to set the id 
	
		long student_id = 0;	
		student.setStudent_id(student_id);

		
		//Declaring the firstname variable to set the firstname
		System.out.println("Enter the First Name");
		String firstName = sc.next();
		student.setFirstname(firstName);

		
		//Declaring the lasttname variable to set the lastname
		System.out.println("Enter the Last Name");
		String lastName = sc.next();
		student.setLastname(lastName);

		//Declaring the emailid variable to set the emailid
		System.out.println("Enter the emailid");
		String email_id = sc.next();
		boolean flag = true;
		do {
			if (validate.isValidEmailId(email_id)) {
				student.setEmail_id(email_id);
				flag = false;
				break;
			} else {
				System.out.println("Please Enter valid Email Address");
				email_id = sc.next();
			}
		} while (flag);

		//Declaring the mobile_num variable to set the mobile_num
		System.out.println("Enter the Mobile Number");
		String mobile_no = sc.next();
		boolean flag1 = true;
		do {
			if (validate.isValidMobileNumber(mobile_no)) {
				student.setMobile_no(mobile_no);
				flag1 = false;
				break;
			} else {
				System.out.println("Please Enter Correct Mobile Number");
				mobile_no = sc.next();
			}
		} while (flag1);

		//Declaring the parent_num variable to set the parent_num
		System.out.println("Enter the parent no");
		String parent_no = sc.next();
		boolean flag2 = true;
		do {
			if (validate.isValidMobileNumber(parent_no)) {
				student.setParent_no(parent_no);
				flag2 = false;
				break;
			} else {
				System.out.println("Please Enter Correct Mobile Number");
				parent_no = sc.next();
			}
		} while (flag2);

		//Declaring the address variable to set the address
		System.out.println("Enter the address");
		String address = sc.next();
		student.setAddress(address);

		//Declaring the branch variable to set the branch
		System.out.println("Enter the branch(mpc/bpc)");
		String branch = sc.next();
		while (!(branch.equalsIgnoreCase("mpc")) && !(branch.equalsIgnoreCase("bpc"))) {
			System.out.println("please enter valid branch(MPC/BPC)");
			branch = sc.next();
		}
		student.setBranch(branch);
		//Declaring the date of birth variable to set the date of birth
		System.out.println("Enter the date of birth (DD/MM/YYYY)");
		FieldValidation datevalid = new FieldValidation();
		int choice = 1;
		String date_of_birth = datevalid.isThisDateValid(choice);
		student.setDate_of_birth(date_of_birth);
		
		//Declaring the date of joining variable to set the date of joining
		System.out.println("Enter the date of joining");
		String date_of_joining = datevalid.isThisDateValid(choice);
		student.setDate_of_joining(date_of_joining);

		System.out.println("New Student Added Successfully.");
		//adding the student details into list
		list.add(student);
		
		//Method call for saving the student record into the list
		StudentData.saveStudentRecord(list);

	}
	
	/**
	 * This method is used to update student information by his id
	 * @return boolean
	 */

	public static boolean studentDetailsUpdation() {
		
		//initializing the scanner class
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		//decalaring the id variable to update the info
		long id = 0;
		System.out.println("Enter Student id to update");
		try {
			id = sc.nextLong();
		} catch (Exception e) {
			System.err.println("enter integers only");
			studentDetailsUpdation();

		}
		//Method call for whether id is present or not
		boolean result = ExamDetailsImpl.checkStudentForUpdate(id);
		return result;
	}

	/**
	 * this method used to update his personal details
	 * 
	 * @param id
	 */
	public static void getStudentForUpdate(long id) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		FieldValidation validate = new FieldValidation();
		System.out.println("select operation");
		System.out.println("1) Update mobile number");
		System.out.println("2) update parent mobile number");
		System.out.println("3) update email id");
		System.out.println("4) update address");
		// providing the user to enter his choice
		int choice = 0;
		try {
			choice = sc.nextInt();
			while (choice <= 0 || choice > 4) {
				choice = sc.nextInt();
			}
		} catch (Exception e) {
			System.err.println("please enter integers only");
			getStudentForUpdate(id);
		}
		// after selecting user can update the mobile number
		if (choice == 1) {
			System.out.println("Enter Updated mobile Number");
			String mobile_no = sc.next();
			String colName = "mobile_no";
			boolean flag1 = true;
			do {
				if (validate.isValidMobileNumber(mobile_no)) {
					StudentData.upDateStudentById(id, mobile_no, colName);
					flag1 = false;
					break;
				} else {
					System.out.println("Please Enter Correct Mobile Number");
					mobile_no = sc.next();
				}
			} while (flag1);
		}
		// after selecting user can update his parent details
		if (choice == 2) {
			System.out.println("Enter Updated parent Number");
			String parent_no = sc.next();
			String colName = "parent_no";
			boolean flag2 = true;
			do {
				if (validate.isValidMobileNumber(parent_no)) {
					StudentData.upDateStudentById(id, parent_no, colName);
					flag2 = false;
					break;
				} else {
					System.out.println("Please Enter Correct Mobile Number");
					parent_no = sc.next();
				}
			} while (flag2);
		}
		// after selecting user can update his Email details
		if (choice == 3) {
			System.out.println("Enter Update Email Id");
			String email_id = sc.next();
			String colName = "email_id";
			boolean flag = true;
			do {
				if (validate.isValidEmailId(email_id)) {
					StudentData.upDateStudentById(id, email_id, colName);
					flag = false;
					break;
				} else {
					System.out.println("Please Enter valid Email Address");
					email_id = sc.next();
				}
			} while (flag);
		}
		// after selecting user can update his address
		if (choice == 4) {
			System.out.println("Enter New address");
			String address = sc.next();
			String colName = "address";
			StudentData.upDateStudentById(id, address, colName);
		}

	}
}
