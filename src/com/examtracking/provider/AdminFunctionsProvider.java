package com.examtracking.provider;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.examtracking.bean.SetLoginDetails;
import com.examtracking.bean.SetMarkSheet;
import com.examtracking.bean.StudentDetails;
import com.examtracking.dao.LoginDetailsData;
import com.examtracking.dao.StudentData;
import com.examtracking.service.ExamDetailsImpl;
import com.examtracking.service.UserLoginImpl;
import com.examtracking.util.JwtImpl;
import com.examtracking.view.DisplayStudentInfo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/AdminFunctionsProvider")
public class AdminFunctionsProvider {

	/**
	 * This resource method return student as per given id
	 * 
	 * @param stud_id
	 * @return
	 */
	@GET
	@Path("/searchbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getStudentById(@PathParam("id") long stud_id) {
		Gson gson = new Gson();

		// got id from client and searching student in database
		StudentDetails result = new StudentData().getStudentByid(stud_id);

		// converting student in JSON format
		String studentJsonString = gson.toJson(result);

		// returning student to clien
		return studentJsonString;

	}

	/**
	 * This is resource method upadates student personal information
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@PUT
	@Path("/updateStudent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateStudent(StudentDetails student) {
		Gson gson = new Gson();
		// Getting student details from Client and updating student
		boolean result = ExamDetailsImpl.upDateStudent(student);

		if (result) {
			// converting student list into JSON object
			String studentJsonString = gson.toJson(student);
			return studentJsonString;
		}
		return null;
	}

	/**
	 * This is resource method which performs register the student operation
	 * 
	 * @param student json object
	 * @return
	 */
	@POST
	@Path("/registerStudent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerStudent(String student) {
		Gson gson = new Gson();

		// converting JSON object to original student object
		StudentDetails studentData = gson.fromJson(student, StudentDetails.class);

		// Storing student into database
		boolean result = new ExamDetailsImpl().registerNewStudent(studentData);
		if (result) {
			// converting student in JSON format
			String studentJsonString = gson.toJson(student);
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
	@Path("/setLoginDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String setLoginDetails(String student) {

		String result = null;
		Gson gson = new Gson();

		// getting student JSON object from client
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(student);
		JsonObject jsonObject = jsonTree.getAsJsonObject();

		// converting student id into int format
		JsonElement f1 = jsonObject.get("stud_id");
		long stud_id = f1.getAsLong();

		List<SetLoginDetails> list = new ArrayList<SetLoginDetails>();

		// converting JSON object into original form
		SetLoginDetails studentData = gson.fromJson(student, SetLoginDetails.class);
		studentData.setStud_id(stud_id);
		list.add(studentData);

		// updating student in database
		result = UserLoginImpl.SignUpStudent1(stud_id);

		if (result.equalsIgnoreCase("true")) {
			result = LoginDetailsData.saveStudentRecord(list);
		}

		if (result != null) {
			// converting student list into JSON object
			String studentJsonString = gson.toJson(studentData);

			return studentJsonString;
		}
		return null;
	}

	/**
	 * This is resource method displays student details
	 * 
	 * @return student details
	 */
	@POST
	@Path("/displaystudentdetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String displayStudentDetails() {

		// getting all students from database
		List<StudentDetails> studentDetails = DisplayStudentInfo.printStudentAllRecords();

		if (studentDetails != null) {
			Gson gson = new Gson();
			// converting student in JSON format
			String studentJsonString = gson.toJson(studentDetails);
			return studentJsonString;
		}
		return null;
	}

	/**
	 * This resource method gets all students from database
	 * 
	 * @return list of students
	 */
	@GET
	@Path("/getAllStudents")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAllStudents() {

		// getting all students from database
		List<StudentDetails> list = new ExamDetailsImpl().getAllStudents();
		Gson gson = new Gson();

		// converting student list into JSON object
		String studentJsonString = gson.toJson(list);

		// returning JSON to client
		return studentJsonString;
	}

	/**
	 * This resource method gets Marks from database by Id
	 * 
	 * @param exma_id
	 * @return list of marks
	 */
	@GET
	@Path("/getMarksById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getMarksById(@PathParam("id") int exam_id) {

		Gson gson = new Gson();

		// Getting list of student along with rank
		List<SetMarkSheet> list = new ExamDetailsImpl().getMarkSheetByExamId(exam_id);
		if (list.size() > 0) {
			// converting list in JSON object
			String studentJsonString = gson.toJson(list);

			// return list to client
			return studentJsonString;
		} else {
			return "Mark Sheet not found!!";
		}
	}

	/**
	 * This is resource method displays rank of the student
	 * 
	 * @param exam id
	 * @return rank
	 */
	@GET
	@Path("/getRankOfStudents/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getRankOfStudents(@PathParam("id") int exam_id) {

		// Getting list of student along with rank
		List<String> list = new ExamDetailsImpl().displayAllStudentByRank(exam_id);

		if (list.size() > 0) {
			// converting list in JSON object
			Gson gson = new Gson();
			String studentJsonString = gson.toJson(list);

			// return list to client
			return studentJsonString;
		} else {
			return "Student Not Found!!";
		}

	}

	/**
	 * This is resource displays Login credentials
	 * 
	 * @return
	 */
	@POST
	@Path("/displayLoginDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String printlogindetails() {
		// Getting list of student login details
		List<SetLoginDetails> academic = DisplayStudentInfo.printLoginAllRecords();

		// returning the list of student login details
		if (academic != null) {
			Gson gson = new Gson();
			// converting student in JSON format
			String studentJsonString = gson.toJson(academic);
			return studentJsonString;
		}
		return null;
	}

}
