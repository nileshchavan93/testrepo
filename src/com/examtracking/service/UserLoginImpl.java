package com.examtracking.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.examtracking.bean.AdminLoginDetails;
import com.examtracking.bean.SetLoginDetails;
import com.examtracking.bean.StudentDetails;
import com.examtracking.controller.LoginUser;
import com.examtracking.dao.LoginDetailsData;


/**
 * 
 * @author BATCH-'C' this class is used to set the USER(admin & student)Login
 *         details
 *
 */
public class UserLoginImpl {

	// list to set and get the adminlogindetails from file

	static List<AdminLoginDetails> list = new ArrayList<AdminLoginDetails>();
	static Scanner sc = new Scanner(System.in);
	/**
	 * This Method get login details from DAO object and check User
	 * @param username
	 * @param password
	 * @return
	 */
			
	public boolean loginUser(String username , String password) {
		boolean result = false;
		List<AdminLoginDetails> list = LoginDetailsData.getAdminList();
		Iterator<AdminLoginDetails> itr = list.iterator();
		while (itr.hasNext()) {
			AdminLoginDetails admin1 = (AdminLoginDetails) itr.next();
			if (username.equalsIgnoreCase(admin1.getUserName()) && password.equalsIgnoreCase(admin1.getPassword())) {
				result = true;
			}
		}
		return result;
	}
	/**
	 * This method is used to set login details ( hard-coded values) for admin
	 * 
	 * @return true if the username & password is valid from the list
	 */
	public boolean setAdminDetails(String userName, String password) {

		// getting list from admin database
		list = LoginDetailsData.getAdminList();
		Iterator<AdminLoginDetails> itr = list.iterator();
		while (itr.hasNext()) {
			AdminLoginDetails admin1 = (AdminLoginDetails) itr.next();
			if (userName.equalsIgnoreCase(admin1.getUserName()) && password.equalsIgnoreCase(admin1.getPassword())) {
				return true;
			}

		}
		return false;
	}

	/**
	 * this method used to reset the password for admin
	 * 
	 * @param userName
	 * @param securityQues
	 * @return admin details if he retrieves the valid details from the list
	 */
	public static AdminLoginDetails adminForgotPassword(String userName, String securityQues) {

		// getting list from admin database
		list = LoginDetailsData.getAdminList();

		Iterator<AdminLoginDetails> itr = list.iterator();
		while (itr.hasNext()) {
			AdminLoginDetails admin1 = (AdminLoginDetails) itr.next();
			if (userName.equalsIgnoreCase(admin1.getUserName())
					&& securityQues.equalsIgnoreCase(admin1.getSecurityQues())) {
				return admin1;
			}

		}
		return null;
	}

	/**
	 * this method is used to check the previous credentials of the user(student)to
	 * reset the password
	 * 
	 * @param username
	 * @param password
	 * @return true if the user enters the valid previous username & password
	 */
	public static boolean checkPrevious(String username, String password) {

		// creating the list for the get student credentials from database
		List<SetLoginDetails> list = LoginDetailsData.getStudentCredentials();

		Iterator<SetLoginDetails> itr = list.iterator();
		while (itr.hasNext()) {
			SetLoginDetails setLogin = itr.next();

			if (username.equals(setLogin.getUsername()) && password.equals(setLogin.getPassword())) {

				return true;
			}
		}
		return false;
	}

	/**
	 * this method is used to check the previous credentials of the student to reset
	 * the password
	 * 
	 * @param list
	 * @param studentid
	 */
	public static boolean resetPassword(List<SetLoginDetails> list, long studentid) {
		boolean result=LoginDetailsData.setStudentresetPassword(list, studentid);
		 return result;
	}

	// creating the list for the set login details from database
	static List<SetLoginDetails> list1 = new ArrayList<SetLoginDetails>();

	/**
	 * This method used to create student login credentials
	 * 
	 * @return message if the sign up student is not found
	 */
	public static String SignUpStudent1(long stud_id) {
		
		boolean checkid1 = LoginDetailsData.checkIdInLogins(stud_id);
		boolean checkid = LoginDetailsData.CheckIdRegistration(stud_id);
		if (list.size() >= 0) {
			if (checkid1) {
				return "student details already entered";
			} else {
				// checking whether student present or not
				if (checkid) {
					return "true";
				} else {
					return "Id not found,please enter the correct student id for signup";

				}

			}
		} else {
			return "list is empty";
		}

	}

	/**
	 * this method for the USER(student )login
	 * 
	 * @param userName
	 * @param password
	 * @return true if username & passsword is valid
	 * 
	 */
	public boolean studentLoginDetails1(String userName, String password) {
		// creating the list for setLogindetails from database
		List<SetLoginDetails> list = new ArrayList<SetLoginDetails>();

		// getting list from database
		list = LoginDetailsData.getLoginDetails();

		Iterator<SetLoginDetails> itr = list.iterator();
		while (itr.hasNext()) {
			SetLoginDetails student = (SetLoginDetails) itr.next();
			if (userName.equalsIgnoreCase(student.getUsername()) && password.equalsIgnoreCase(student.getPassword())) {
				return true;

			}
		}
		return false;
	}

	/**
	 * this method is used to reset the password for the student to reset the
	 * password
	 * 
	 * @param userName
	 * @param securityQues
	 * @return admin details if the student answers the security question otherwise
	 *         return null
	 */
	public static SetLoginDetails studentForgotPassword(String userName, String securityQues) {

		// creating the list for the setlogindetails from the database
		List<SetLoginDetails> list = new ArrayList<SetLoginDetails>();

		// getting list from database
		list = LoginDetailsData.getLoginDetails();

		Iterator<SetLoginDetails> itr = list.iterator();
		while (itr.hasNext()) {
			SetLoginDetails admin1 = (SetLoginDetails) itr.next();
			if (userName.equalsIgnoreCase(admin1.getUsername())
					&& securityQues.equalsIgnoreCase(admin1.getSecurityQues())) {
				return admin1;
			}

		}
		return null;
	}

}
