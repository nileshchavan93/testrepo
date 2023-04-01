package com.examtracking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.examtracking.bean.AdminLoginDetails;
import com.examtracking.bean.SetLoginDetails;
import com.examtracking.dao.LoginDetailsData;
import com.examtracking.service.UserLoginImpl;
import com.examtracking.util.FieldValidation;

/**
 * class containing main method making admin or student to login into their
 * details
 * 
 * @author BATCH-'C'
 *
 */
public class LoginUser {

	// variable count is for providing the user limitation of entering the invalid credentials maximum of upto three times

	static int count = 0;

	/**
	 * main method for selecting user and making user to login
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void loginUser() {
		//initializing the scanner class
		Scanner sc = new Scanner(System.in);
		
		System.out.println("============================");
		System.out.println("Select User");
		System.out.println("============================");
		System.out.println("1) Admin");
		System.out.println("2) Student");
		System.out.println("-----------------------------");
		//declaring the choice variable to select the user
		int choice = 0;
		try {
			choice = Integer.parseInt(sc.nextLine());
			while (choice <= 0 || choice > 2) {
				System.err.println("please select valid option");
				choice = sc.nextInt();
			}
		} catch (Exception e) {
			System.err.println("please enter integers only");
			LoginUser.loginUser();
		}
	
		switch (choice) {
		case 1: {
			try {
				// Validation checking
				LoginUser adminLoginDetailsSet = new LoginUser();
				for (int i = 0; i <= 3; i++) {
					boolean result = adminLoginDetailsSet.setloginDetails();
					if (result) {
						// Creating Admin object for performing admin functions
						AdminMenu menu = new AdminMenu();
						menu.adminFunctions();

					} else {
						//providing the user limitation of entering the invalid credentials maximum of upto three times
						if (i != 3) {
							System.out.println("please enter valid credentials");
						} else if (i == 3) {
							for (int j = 0; j <= 3; j++) {
								System.out.println("please answer the security question to display the password ");
								boolean result1 = adminLoginDetailsSet.resetPassword();
								if (result1) {
									System.out.println("please login to continue");
									LoginUser.loginUser();
								} else if (j != 3) {
									System.out.println("please enter valid credentials");
								} else if (j == 3) {
									System.out.println("Too many attempts please try after sometime");
								}
							}
						}
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			break;
		}
		case 2: {

			try {
				// Validation checking
				LoginUser studentDetailsSet = new LoginUser();
				for (int i = 0; i <= 3; i++) {
					boolean result = studentDetailsSet.studentLoginDetails();
					if (result) {

						// Creating Admin object for performing admin functions
						StudentMenu menu = new StudentMenu();
						menu.studentMenu();

					} else {
						//providing the user limitation of entering the invalid credentials maximum of upto three times
						if (i != 3) {
							System.out.println("please enter valid credentials");
						} else if (i == 3) {
							for (int j = 0; j <= 3; j++) {
								System.out.println("please answer the security question to display the password ");
								boolean result1 = studentDetailsSet.studentResetPassword();
								if (result1) {
									System.out.println("please login to continue");
									LoginUser.loginUser();
								} else if (j != 3) {
									System.out.println("please enter valid credentials");
								} else if (j == 3) {
									System.out.println("Too many attempts please try after sometime");
								}
							}
						}
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			break;
		}
		default: {
			System.out.println("You have Entered wrong choice");
		}
		}// end of switch

		// Scanner Close
		sc.close();
	}
	/**
	 * This method is used for the admin to set the signup details for the student
	 */
	public static void studentsignup() {
		
		//declaring the student id variable to set the login credentials
		long stud_id=0;
		
		//initializing the scanner class
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter the student id to set the password");
		try {
			stud_id = sc.nextLong();
		}
		catch(Exception e) {
			System.err.println("Enter integers only");
			studentsignup();
		}
		
		try {
			//Method call  to set the login credentials
			String str = UserLoginImpl.SignUpStudent1(stud_id);
			System.out.println(str);
			//If the user has entered the wrong id providing the option to enter the id again
			if ((str.equalsIgnoreCase("Id not found,please enter the correct student id for signup"))) {
				studentsignup();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	/**
	 * This method is used to set the login details and return boolean if details are already entered
	 */
	public boolean setloginDetails() {
		
		//initializing the scanner class
		Scanner sc = new Scanner(System.in);
		
		//providing the user to enter the login credentials from console
		System.out.println("enter the user name");
		
		String userName = sc.nextLine();
		System.out.println("enter the password");
		String password = sc.nextLine();
		
		// creating the UserLoginImpl class to set the credentials of student
		UserLoginImpl adminLoginDetailsImpl = new UserLoginImpl();
		try {
			//Method call to set the credentials of student and checking if whether the student shas already signup details
			boolean result = adminLoginDetailsImpl.setAdminDetails(userName, password);
			if (result) {
				System.out.println("Welcome " + userName);
				return true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This method is used to reset password
	 * 
	 * @return true only if the admin get through his credentials
	 */
	public boolean resetPassword() {
		//initializing the scanner class
		Scanner sc = new Scanner(System.in);
		
		//providing the user to enter the login credentials from console
		System.out.println("enter the user name");
		String userName = sc.nextLine();
		System.out.println("please enter your favorite color");
		String securityQues = sc.nextLine();
		
		// creating the UserLoginImpl class object to dislpay the credentials
//		UserLoginImpl adminLoginDetailsImpl = new UserLoginImpl();
//		try {
////			AdminLoginDetails result = adminLoginDetailsImpl.resetPassword(userName, securityQues);
//			//Displaying the user name and password if security answer and username are valid
//			if (result != null) {
//				System.out.println("Your username is : " + result.getUserName());
//				System.out.println("Your password is : " + result.getPassword());
//				return true;
//			}
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
	

	/**
	 * this method is set the login details of the student
	 * 
	 * @return true when student login credentials are valid
	 */

	public boolean studentLoginDetails() {
		//initializing the scanner class
		Scanner sc = new Scanner(System.in);
		
		//providing the user to enter the login credentials from console
		System.out.println("enter the user name");
		String userName = sc.nextLine();
		System.out.println("enter the password");
		String password = sc.nextLine();
		
		// creating the UserLoginImpl class object to check the credentials of student
		UserLoginImpl adminLoginDetailsImpl = new UserLoginImpl();
		try {
			boolean result = adminLoginDetailsImpl.studentLoginDetails1(userName, password);
			if (result) {
				//Returning true if user name and password are valid
				System.out.println("Welcome " + userName);
				return true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	/**
	 * this method used to reset and retrieve the student credentials if the student
	 * forgets his login details
	 * 
	 * @return true if student gets his valid credentials
	 */

	public boolean studentResetPassword() {
		//initializing the scanner class
		Scanner sc = new Scanner(System.in);
		
		//providing the user to enter the login credentials from console
		System.out.println("enter the user name");
		String userName = sc.nextLine();
		System.out.println("please enter your favorite color");
		String securityQues = sc.nextLine();
		
		// creating the UserLoginImpl class object to dislpay the credentials
		UserLoginImpl adminLoginDetailsImpl = new UserLoginImpl();
		try {
			SetLoginDetails result = adminLoginDetailsImpl.studentResetPassword(userName, securityQues);
			if (result != null) {
				//Displaying the user name and password if security answer and username are valid
				System.out.println("Your username is : " + result.getUsername());
				System.out.println("Your password is : " + result.getPassword());
				return true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	/**
	 * this method is used to set the password and re-login with student credentials
	 */
	public static void setStudentResetPassword() {
		//initializing the scanner class
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		SetLoginDetails setLogin = new SetLoginDetails();
		List<SetLoginDetails> list = new ArrayList<SetLoginDetails>();
		//providing the user to enter the login credentials from console to reset the password
		System.out.println("enter the id to reset the password");
		int stud_id = Integer.parseInt(sc.nextLine());
		boolean check = LoginDetailsData.CheckIdRegistration(stud_id);
		if (check) {
			//providing the user to enter the  previous login credentials from console to reset the password
			System.out.println("enter the previous username");
			String username = sc.nextLine();

			System.out.println("enter the previous password");
			String password = sc.nextLine();
			boolean result = UserLoginImpl.checkPrevious(username, password);
			
			
			// re-login into admin after fetching the valid studentCredentials
			if (result) {
				System.out.println("enter the new username");
				String username1 = sc.nextLine();
				setLogin.setUsername(username1);

				System.out.println("enter the new password");
				String password1 = sc.nextLine();
				setLogin.setPassword(password1);

				System.out.println("enter the your favorite color for security question");
				String securityQues = sc.nextLine();
				setLogin.setSecurityQues(securityQues);
				list.add(setLogin);
				UserLoginImpl.resetPassword(list, stud_id);

			} else {
				System.out.println("please enter valid credentials to reset password");
				setStudentResetPassword();
			}

		} else {
			System.out.println("please enter correct id to reset password");
			setStudentResetPassword();
		}

	}
	public static SetLoginDetails loginUtility(long stud_id) {
		Scanner sc = new Scanner(System.in);
		SetLoginDetails student = new SetLoginDetails();

		// if student present , then setting new credentials to student
		System.out.println("Enter the username");
		String userName = sc.next();
		student.setUsername(userName);
		// validating the password for student
		FieldValidation validate = new FieldValidation();
		System.out.println("Enter the password");
		String password = sc.next();
		boolean flag = true;
		do {
			if (validate.validatePassword(password)) {
				student.setPassword(password);
				flag = false;
				break;
			} else {
				System.out.println("Password must contains following Characters");
				System.out.println(
						"One lowercase , one uppercase , one special symbol , one number and length between 6 to 20");
				password = sc.next();
			}
		} while (flag);
		student.setPassword(password);

		String securityQues = "***";
		student.setSecurityQues(securityQues);
		student.setStud_id(stud_id);
		return student;
	}

}
