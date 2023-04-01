package com.examtracking.controller;

import java.util.Scanner;
import com.examtracking.view.DisplayStudentInfo;

/**
 * 
 * @author BATCH-'C'
 *
 *         This class Displays Admin Functionality
 */
public class AdminMenu {
	/**
	 * This method Displays All Functions which is perform by Admin
	 */
	public void adminFunctions() {

		String choiceToContinue = null;
		do {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// Displaying Menu to Admin
			System.out.println("==============================");
			System.out.println("Welcome to Admin portal");
			System.out.println("==============================");
			System.out.println("select operation");
			System.out.println("-------------------------------");
			System.out.println("1) Enroll New Student");
			System.out.println("2) Update Student");
			System.out.println("3) Examination Portal");
			System.out.println("4) View All Students");
			System.out.println("5) Enroll signup for student");
			System.out.println("6) view signup details of student");
			System.out.println("7) Go back to main menu");
			System.out.println("-------------------------------");
			//Creating choice variable to select the functionality
			int choice = 0;
			try {
				choice = sc.nextInt();
				while (choice <= 0 || choice > 7) {
					System.err.println("please select valid option");
					choice = sc.nextInt();
				}
			} catch (Exception e) {
				System.err.println("please enter integers only");
				adminFunctions();
			}
			switch (choice) {
			case 1: {
				// creating student class object and calling method to enroll a student database
				StudentMenu enollNewStudent = new StudentMenu();
				try {
					enollNewStudent.enRollNewStudent();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			case 2: {
				boolean isUpdate = false;
				try {
					//Upadting the student details
					isUpdate = StudentMenu.studentDetailsUpdation();
					if (isUpdate) {
						System.out.println("Student Updated Successfully.");
					} else {
						System.out.println("Student Record not Available.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			case 3: {
				// select type of exam this method set exam time table
				ExamPortal.getMasterMenuForExam();
				break;
			}
			case 4: {
				// This method prints all student present in a list
				DisplayStudentInfo.printStudentAllRecords();
				break;
			}
			case 5: {
				// if student is new to portal and if student is present in a list
				// then this method provides facility to sign up

				LoginUser.studentsignup();
				break;
			}
			case 6: {
				// After selecting this type to print all the records of the student
				DisplayStudentInfo.printLoginAllRecords();
				break;

			}
			case 7: {
				new LoginUser();
				// After selecting this type you can goback to login page
				LoginUser.loginUser();

			}
			}
			// Here can choose option to continue
			System.out.println("Do You want to continue(yes/no)");
			choiceToContinue = sc.next();
		} while (choiceToContinue.equalsIgnoreCase("yes"));

	}
}
