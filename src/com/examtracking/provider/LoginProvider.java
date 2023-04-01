package com.examtracking.provider;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.auth0.jwt.JWT;
import com.examtracking.bean.AdminLoginDetails;
import com.examtracking.bean.SetLoginDetails;
import com.examtracking.bean.StudentDetails;

import com.examtracking.dao.LoginDetailsData;
import com.examtracking.dao.StudentData;
import com.examtracking.service.ExamDetailsImpl;
import com.examtracking.service.UserLoginImpl;
import com.examtracking.util.JwtImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/login")
public class LoginProvider {
	/**
	 * This is resource method which performs login operation
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/adminlogin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(String deatils) {
		// converting user name & password into original format
		JsonObject jsonObject = new JwtImpl().getOriginalElement(deatils);

		JsonElement f1 = jsonObject.get("username");
		String username = f1.getAsString();

		JsonElement f2 = jsonObject.get("password");
		String password = f2.getAsString();

		boolean result = false;
		// creating UserLoginImpl object to check the user name and password
		UserLoginImpl login = new UserLoginImpl();

		result = login.loginUser(username, password);
		// returning the authentication state
		if (result) {
			return "login successful";
		} else {
			return "login unsuccessful";
		}
	}

	/**
	 * This is resource method which performs Student login operation
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/studentlogin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String studentLogin(String deatils) {
		// converting user name & password into original format
		JsonObject jsonObject = new JwtImpl().getOriginalElement(deatils);

		JsonElement f1 = jsonObject.get("username");
		String username = f1.getAsString();

		JsonElement f2 = jsonObject.get("password");
		String password = f2.getAsString();

		boolean result = false;
		// creating UserLoginImpl object to check the user name and password
		UserLoginImpl login = new UserLoginImpl();
		result = login.studentLoginDetails1(username, password);
		// returning the authentication state
		if (result) {
			return "login successful";
		} else {
			return "login unsuccessful";
		}
	}

	/**
	 * This is resource method which performs login operation
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/checkstudentcredentials")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkStudentCredentials(String resetLogin) {

		boolean result = false;
		// creating Gson object for convert the json to original format
		Gson gson = new Gson();

		// getting the details bean object from original format
		SetLoginDetails studentData = gson.fromJson(resetLogin, SetLoginDetails.class);

		// Retrieving the details from student object
		String username = studentData.getUsername();
		String password = studentData.getPassword();
		result = UserLoginImpl.checkPrevious(username, password);
		// returning the state of previous student credentials
		if (result) {
			return "true";
		}
		return "false";
	}

	/**
	 * This is resource method which performs login operation
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/studentresetpassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String resetPassword(String resetLogin) {
		boolean result = false;
		Gson gson = new Gson();

		// converting student id into original format
		JsonObject jsonObject = new JwtImpl().getOriginalElement(resetLogin);

		// converting the student id into original format
		JsonElement f1 = jsonObject.get("stud_id");
		long stud_id = f1.getAsLong();

		// creating the list to set the details of new login credentials
		List<SetLoginDetails> list = new ArrayList<SetLoginDetails>();

		// getting the details bean object from original format
		SetLoginDetails studentData = gson.fromJson(resetLogin, SetLoginDetails.class);
		studentData.setStud_id(stud_id);
		studentData.setUsername(studentData.getUsername());
		studentData.setPassword(studentData.getPassword());
		studentData.setSecurityQues(studentData.getSecurityQues());
		// adding the student credentials into list
		list.add(studentData);
		// calling the method reset password to perform operation and store into
		// database
		result = UserLoginImpl.resetPassword(list, stud_id);
		// returning the result whether password is updated or not
		if (result) {
			return "student_password_updated";
		}
		return "error";
	}

	/**
	 * This is resource method which performs login operation
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/studentForgotPassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String studentForgotPassword(String loginDetails) {
		// creating the instance of Gson to convert the format into original format
		Gson gson = new Gson();
		// converting user name & password into original format
		JsonObject jsonObject = new JwtImpl().getOriginalElement(loginDetails);
		// converting the user name into original format
		JsonElement obj = jsonObject.get("username");
		String username = obj.getAsString();
		// converting the security question into original format
		JsonElement obj2 = jsonObject.get("security_question");
		String security_ques = obj2.getAsString();
		// calling the studentforgotPassword and display the details
		SetLoginDetails logindetails = UserLoginImpl.studentForgotPassword(username, security_ques);
		// returning the login details in json format
		if (logindetails != null) {
			String studentJsonString = gson.toJson(logindetails);
			return studentJsonString;
		}
		return null;
	}

	/**
	 * This is resource method which performs login operation
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/adminForgotPassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String adminForgotPassword(String loginDetails) {
		// creating the instance of Gson to convert the format into original format
		Gson gson = new Gson();
		// converting user name & password into original format
		JsonObject jsonObject = new JwtImpl().getOriginalElement(loginDetails);

		// converting the user name into original format
		JsonElement obj = jsonObject.get("username");
		String username = obj.getAsString();
		
		// converting the security question into original format
		JsonElement obj2 = jsonObject.get("security_question");
		String security_ques = obj2.getAsString();
		
		// calling the adminforgotPassword and display the details
		AdminLoginDetails logindetails = UserLoginImpl.adminForgotPassword(username, security_ques);
		
		// returning the login details in json format
		if (logindetails != null) {
			return "admin_credentials_found";
		}
		return "error";
	}
}
