package com.examtracking.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.examtracking.bean.AcademicInfo;
import com.examtracking.bean.SetExamTimeTable;
import com.examtracking.bean.StudentDetails;
import com.examtracking.service.ExamDetailsImpl;


public class ExamDetailsImplTest {
	ExamDetailsImpl exam = null;
	
	@Before
	public void setUp() {
		exam = new ExamDetailsImpl();
	}
	
	@After
	public void tearDown() {
		exam = null;
	}
	/**
	 * This Method test positive scenario 
	 */
	@Ignore
	@Test
	public void successSetTimeTableTest() {
		List<SetExamTimeTable> list = new ArrayList<SetExamTimeTable>();
		SetExamTimeTable set = new SetExamTimeTable();
		set.setDate("23/01/2019");
		set.setSubject("physics");
		set.setTime("10:00to11:30");
		set.setVenue("hall1");
		set.setExamTypeId(1);
		set.setBranchId(2);
		list.add(set);
		assertTrue(exam.SetExamTimeTable(list));
	}
	/**
	 * This Method test positive scenario 
	 */
	@Test
	public void successEligibleLogic() {
		AcademicInfo info=new AcademicInfo();
		info.setStudent_id(105);
		info.setAttendance(130);
		info.setFee("paid");
		info.setCheck("yes");
		
		assertTrue(ExamDetailsImpl.setStudentEligibility(info));
	}
	
	/**
	 * This Method test positive scenario 
	 */
	@Ignore
	@Test
	public void successRegisterNewStudent() {

		StudentDetails student = new StudentDetails();
		student.setFirstname("Raghu");
		student.setLastname("rr");
		student.setMobile_no("9874563210");
		student.setParent_no("9874583210");
		student.setEmail_id("raghu@gmail.com");
		student.setBranch("BPC");
		student.setDate_of_birth("01/05/1996");
		student.setDate_of_joining("15/06/2018");		
		assertTrue(exam.registerNewStudent(student));
	}
	/**
	 * This Method test positive scenario 
	 */
	@Test
	public void successCheckMarksTest() {	
		assertTrue(exam.checkMarks(101, 2));
	}
}