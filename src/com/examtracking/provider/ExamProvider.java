package com.examtracking.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.examtracking.bean.SetExamTimeTable;
import com.examtracking.bean.SetMarkSheet;
import com.examtracking.service.ExamDetailsImpl;
import com.examtracking.util.JwtImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Path("/examination")
public class ExamProvider {

	/**
	 * This is resource method which is used to add new Time table in database
	 * 
	 * @param time table
	 * @return
	 */
	@POST
	@Path("/setexamtimetable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String setTimeTable(String timetableList) {
		String str = null;

		// creating JSON object
		Gson gson = new Gson();
		SetExamTimeTable timeTableData = gson.fromJson(timetableList, SetExamTimeTable.class);

		// For testing purpose hardcoded values
		timeTableData.setBranchId(1);
		timeTableData.setExamTypeId(2);

		// getting arraylist object to store time table
		List<SetExamTimeTable> list = new ArrayList<SetExamTimeTable>();
		list.add(timeTableData);

		// adding new time table into database
		boolean result = new ExamDetailsImpl().SetExamTimeTable(list);
		if (result) {
			str = "time_table_created";
		}
		return str;
	}

	/**
	 * This is resource method get all time table as per given ids
	 * 
	 * @param branch_id , exam_choice
	 * @return
	 */
	@GET
	@Path("/getexamtimetable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getTimeTable(String timeTable) {

		// creating JSON object
		Gson gson = new Gson();

		// converting student id into int format
		JsonObject jsonObject = new JwtImpl().getOriginalElement(timeTable);
		JsonElement f1 = jsonObject.get("branch_id");
		int branch_id = f1.getAsInt();

		JsonElement f2 = jsonObject.get("exam_choice");
		int exam_choice = f2.getAsInt();

		// getting all time table present in databse as per given id
		List<SetExamTimeTable> list = new ExamDetailsImpl().getExamTimeTable(branch_id, exam_choice);

		// if converting and returning in JSON format
		if (list.size() > 0) {
			String studentJsonString = gson.toJson(list);
			return studentJsonString;
		} else {
			return "Time Table not Found!!";
		}

	}

	/**
	 * This is resource method which is return time table from database
	 * 
	 * @param time table
	 * @return
	 */
	@GET
	@Path("/getAllexamtimetable")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTimeTableOfAllExam() {

		// creating JSON object
		Gson gson = new Gson();

		// getting all records from database
		List<SetExamTimeTable> list = new ExamDetailsImpl().getAllExamTimeTable();

		// converting all record into JSON format
		String studentJsonString = gson.toJson(list);

		// returning JSON to client
		return studentJsonString;
	}

	/**
	 * This is resource method checks whether marks sheet already present or not
	 * 
	 * @param time table
	 * @return boolean
	 */
	@GET
	@Path("/checkmarkssheet")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkMarksSheet(String marksSheet) {

		// converting id into original format
		JsonObject jsonObject = new JwtImpl().getOriginalElement(marksSheet);
		JsonElement f1 = jsonObject.get("student_id");
		int stud_id = f1.getAsInt();

		JsonElement f2 = jsonObject.get("exam_choice");
		int exam_choice = f2.getAsInt();

		// calling check marks method it return true if found otherwise false
		boolean result = new ExamDetailsImpl().checkMarks(stud_id, exam_choice);
		if (result) {
			return "Already present";
		} else {
			return "Not present, you can set";
		}
	}

	/**
	 * This is resource method checks whether marks sheet already present or not if
	 * not it will add new mark sheet record into database
	 * 
	 * @param marksheet JSON object
	 * @return boolean
	 */
	@GET
	@Path("/setmarkssheet")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String setMarksSheet(SetMarkSheet marks) {
		boolean result = false;
		// extracting exam id & student Id from merksheet object
		int exam_choice = marks.getExam_id();
		int student_id = (int) marks.getStudent_id();

		// calling setMarkSheetForStudent method which insert new record in database
		result = new ExamDetailsImpl().setMarkSheetForStudent(marks, student_id, exam_choice);

		if (result) {
			return "Mark sheet created";
		} else {
			return "Something went wrong";
		}
	}

	/**
	 * This is resource method performance report of a student if not it will add
	 * new mark sheet record into database
	 * 
	 * @param marksheet JSON object
	 * @return boolean
	 */
	@GET
	@Path("/performancetracking/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String performancetracking(@PathParam("id") long stud_id) {
		// creating JSON object
		Gson gson = new Gson();
		// converting id into original format
//		JsonObject jsonObject = new JwtImpl().getOriginalElement(deatils);
//		JsonElement f1 = jsonObject.get("student_id");
//		long stud_id = f1.getAsLong();

		// calling setMarkSheetForStudent method which insert new record in database
		Map<String, String> map = new ExamDetailsImpl().getPerformanceReport(stud_id);
		// converting all record into JSON format
		
		if(map.size() > 0) {
			String studentJsonString = gson.toJson(map);
			
			// returning JSON to client
			return studentJsonString;
		}else {
			return "Student marks sheet not fount";
		}
		
	}
}
