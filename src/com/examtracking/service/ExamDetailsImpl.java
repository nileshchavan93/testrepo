package com.examtracking.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.examtracking.bean.AcademicInfo;
import com.examtracking.bean.SetExamTimeTable;
import com.examtracking.bean.SetMarkSheet;
import com.examtracking.bean.StudentDetails;
import com.examtracking.controller.ExamPortal;
import com.examtracking.controller.StudentMenu;
import com.examtracking.dao.ExamData;
import com.examtracking.dao.StudentData;

/**
 * 
 * @author BATCH-'C' this class used to get and set the examination details like
 *         eligibility check, timetable,mark sheet of the student
 *
 */
public class ExamDetailsImpl {
	public static boolean upDateStudent(StudentDetails student) {
		boolean result = StudentData.upDateStudentById(student);
		return result;

	}

	/**
	 * This method used to update the student details
	 * 
	 * @return true if the student details are updated
	 */
	public static boolean checkStudentForUpdate(long id) {
		List<StudentDetails> list = new ArrayList<StudentDetails>();

		boolean flag = false;
		try {
			list = StudentData.getStudentList();
			Iterator<StudentDetails> it = list.iterator();

			while (it.hasNext()) {
				StudentDetails st1 = (StudentDetails) it.next();
				if (st1.getStudent_id() == id) {
					flag = true;
					StudentMenu.getStudentForUpdate(id);
					break;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return flag;
	}

	public boolean registerNewStudent(StudentDetails studentData) {
		List<StudentDetails> list = new ArrayList<StudentDetails>();
		list.add(studentData);
		boolean result = StudentData.saveStudentRecord(list);
		return result;
	}

	/**
	 * This method for checking attendance for eligibility of exam or not
	 * 
	 * @param attendance
	 * @return percentage
	 */
	public static double setAttendance(long attendance) {

		double percentage = (attendance * 100.0) / 150;

		return percentage;

	}

	/**
	 * This method for checking fee details of student for eligibility of exam or
	 * not
	 * 
	 * @param fee
	 * @return true if student is eligible otherwise return false
	 */
	public static boolean setFee(String fee) { // method to check fee status of a student eligibility of exam or no
		if (fee.equalsIgnoreCase("paid")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * this method used to check both attendance and fee details of the student to
	 * check the eligibility check
	 * 
	 * @param setattendance
	 * @param setFee
	 * @return true if student is eligible otherwise return false
	 */
	public static boolean check(boolean setattendance, boolean setFee) {
		if (setattendance && setFee) {
			return true;
		}
		return false;
	}

	/**
	 * this method display the eligibility criteria of the student
	 * 
	 * @param academicInfo
	 */
	public static boolean setStudentEligibility(AcademicInfo academicInfo) {

		boolean result = ExamData.setAcademicRecord(academicInfo);
		return result;
	}

	/**
	 * this method is used to set the examtimetable
	 * 
	 * @param list
	 */
	public boolean SetExamTimeTable(List<com.examtracking.bean.SetExamTimeTable> list) {
		boolean result = false;
		if (checkTimeTable(list)) {
			result = new ExamData().saveExamTimeTable(list);
		}
		return result;
	}

	/**
	 * This method return time table of specific branch and specific exam
	 * 
	 * @param branch_id
	 * @param exam_choice
	 * @return
	 */
	public List<SetExamTimeTable> getExamTimeTable(int branch_id, int exam_choice) {
		List<SetExamTimeTable> list = new ExamData().getExamTimeTable(branch_id, exam_choice);
		return list;
	}

	/**
	 * This method return exams time tables present in database
	 * 
	 * @param branch_id
	 * @param exam_choice
	 * @return
	 */
	public List<SetExamTimeTable> getAllExamTimeTable() {
		List<SetExamTimeTable> list = new ExamData().getTimeTableOfAllExam();
		return list;
	}

	/**
	 * This get Gets student basic information
	 * 
	 * @param id
	 * @return First name , last name , branch
	 */
	public static String[] getStudentNameBranch(long id) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<StudentDetails> list = new ArrayList();
		list = StudentData.getStudentList();
		String[] stdDetails = new String[3];
		if (list.size() != 0) {
			Iterator<StudentDetails> it = list.iterator();
			while (it.hasNext()) {
				StudentDetails st1 = (StudentDetails) it.next();
				if (st1.getStudent_id() == id) {
					stdDetails[0] = st1.getFirstname();
					stdDetails[1] = st1.getLastname();
					stdDetails[2] = st1.getBranch();
				}
			}
		}
		return stdDetails;

	}

	/**
	 * This Method used to set the timetable based on the branch choice and exam
	 * choice If timetable is already created providing the option to reset the
	 * timetable
	 * 
	 * @param branchChoice
	 * @param examChoice
	 */
	@SuppressWarnings("resource")
	public boolean checkTimeTable(List<com.examtracking.bean.SetExamTimeTable> list) {
		@SuppressWarnings("unused")
		int branchChoice = 0;
		int examChoice = 0;
		boolean result = false;
//		Iterator<com.examtracking.bean.SetExamTimeTable> itr = list.iterator();
		for (SetExamTimeTable time : list) {
			branchChoice = time.getBranchId();
			examChoice = time.getExamTypeId();
		}
		// creating the list for exam time table to retrieve the data
		List<SetExamTimeTable> timeList = ExamData.getAllTimeTable();

		// checking if the list size is empty or not to set the time table
		if (timeList.size() <= 0) {
			result = true;
		}
		// if the list is not empty providing the option to reset the timetable

		else {
			Iterator<SetExamTimeTable> itr = timeList.iterator();
			boolean flag = false;
			while (itr.hasNext()) {
				SetExamTimeTable tb = itr.next();
				if (tb.getExamTypeId() == examChoice && tb.getBranchId() == branchChoice) {
					result = false;
					break;
				} else {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * This method checks whether student attended exam or not
	 * 
	 * @param examChoice
	 */
	public boolean checkMarks(int student_id, int examChoice) {
		boolean result = false;
		if (new ExamData().checkMarkSheet(student_id, examChoice)) {
			result = true;
		}
		return result;
	}

	/**
	 * This method used to set the marks for the student
	 * 
	 * @param examchoice
	 * @param id
	 */
	public boolean setMarkSheetForStudent(SetMarkSheet marks, int student_id, int examChoice) {
		List<SetMarkSheet> list = new ArrayList<SetMarkSheet>();
		list.add(marks);
		boolean result = ExamData.saveAllStudentMarksList(list, student_id, examChoice);
		return result;
	}

	/**
	 * This method used to get the marks for the student by student id
	 * 
	 * @param examchoice
	 * @param id
	 */
	public List<SetMarkSheet> getMarkSheetById(long student_id) {
		List<SetMarkSheet> list = ExamData.getMarksheetById(student_id);
		return list;
	}

	/**
	 * This method used to get the marks for the student by Exam id
	 * 
	 * @param exam_id
	 */
	public List<SetMarkSheet> getMarkSheetByExamId(int exam_id) {
		List<SetMarkSheet> list = ExamData.getMarksheetByExamId(exam_id);
		return list;
	}

	/**
	 * This method get the list of all student present in database
	 */
	public List<StudentDetails> getAllStudents() {
		List<StudentDetails> list = StudentData.getStudentList();
		return list;

	}

	/**
	 * This method display the rank of a student
	 * 
	 * @param choice according to the branch
	 */
	public List<String> displayAllStudentByRank(int exam_id) {
		// creating the setmarks sheet from database
		List<SetMarkSheet> list = null;
		// get the list from database
		list = (ArrayList<SetMarkSheet>) ExamData.getAllStudentMarksList(exam_id);
		ArrayList<String> rankList = new ArrayList<String>();
		Collections.sort(list, SetMarkSheet.totalMarks);
		for (SetMarkSheet marks : list) {
			int rankIndex = list.indexOf(marks) + 1;
			rankList.add("" + rankIndex);
			rankList.add(marks.getFirstName());
			rankList.add(marks.getLastName());
			rankList.add("" + marks.getTotalmarks());
		}
		return rankList;
	}

	public Map<String, String> getPerformanceReport(long id) {

		Map<String, String> map = new HashMap<String, String>();
		// getting branch and type of exam from student id
		// getting branch of student
		List stdInfoList = ExamData.getBranchId(id);
		long branch_id = 0;
		long examChoice = 0;
		if (stdInfoList.size() > 0) {

			String branch = (String) stdInfoList.get(0);
			// as per branch setting branch id
			if (branch.equalsIgnoreCase("mpc")) {
				branch_id = 1;
				examChoice = 3;
			} else {
				branch_id = 2;
				examChoice = 6;
			}
		}

		List<SetMarkSheet> list = new ExamData().performanceTracking(id, branch_id, examChoice);
		SetMarkSheet lastYear = null;
		SetMarkSheet currentYear = null;

		if (list.size() > 0) {
			Iterator<SetMarkSheet> itr = list.iterator();
			lastYear = list.get(0);
			currentYear = list.get(1);

			double perIncDec = 0.0;
			double previousfinal = lastYear.getPercentage();
			double presentfinal = currentYear.getPercentage();
			String perChanges = null;
			if (previousfinal > presentfinal) {
				perIncDec = previousfinal - presentfinal;
				perChanges = "decrease";
			} else {
				perIncDec = presentfinal - previousfinal;
				perChanges = "increase";
			}

			Map<String, Integer> marksList = new HashMap<String, Integer>();

			marksList.put("Chemistry", currentYear.getChemistry());
			marksList.put("English", currentYear.getEnglish());
			marksList.put("Maths", currentYear.getMaths());
			marksList.put("Physics", currentYear.getPhysics());
			marksList.put("Sanskrit", currentYear.getSanskrit());

			Map<String, Integer> sortedByCount = sortByValue(marksList);

			String strongSubjects = null;
			String weakSubjects = null;

			@SuppressWarnings("unchecked")
			List keys = new ArrayList(sortedByCount.keySet());
			strongSubjects = (String) keys.get(0);
			weakSubjects = (String) keys.get(keys.size() - 1);

			map.put(perChanges, "" + perIncDec);
			map.put("Weak Subject", weakSubjects);
			map.put("Strong Subject", strongSubjects);
			map.put("Name", currentYear.getFirstName() + " " + currentYear.getLastName());
		}
		return map;
	}
	/**
	 * This Method sort Map collection based on values
	 * @param wordCounts
	 * @return sorted map
	 */
	public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
		return wordCounts.entrySet().stream().sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}
