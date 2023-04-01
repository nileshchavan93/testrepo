package com.examtracking.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.examtracking.service.UserLoginImpl;

public class LoginTest {
	UserLoginImpl user = null;
	
	@Before
	public void setUp() {
		user = new UserLoginImpl();
	}
	
	@After
	public void tearDown() {
		user = null;
	}
	/**
	 * This Method test positive scenario 
	 */
	@Test
	public void successLoginAdminTest() {
		
		assertTrue(user.loginUser("varun","varun123"));
	}
	
	/**
	 * This Method test negative scenario 
	 */
	@Test
	public void failureLoginAdminTest() {
		
		assertFalse(user.loginUser("varun11","varun123"));
	}
	/**
	 * This Method test negative scenario 
	 */
	@Test
	public void successRestPasswordTest() {
		
		assertNotNull(user.resetPassword("varun","black"));
	}
	/**
	 * This Method test negative scenario 
	 */
	@Test
	public void failureRestPasswordTest() {
		
		assertNull(user.resetPassword("varun1","black"));
	}
	/**
	 * This Method test negative scenario 
	 */
	@Test
	public void successLoginStudentTest() {
		
		assertTrue(user.studentLoginDetails1("varun","gowtham"));
	}
	/**
	 * This Method test negative scenario 
	 */
	@Test
	public void failureLoginStudentTest() {
		
		assertFalse(user.studentLoginDetails1("varun1","black"));
	}
	/**
	 * This Method test negative scenario 
	 */
	@Test
	public void successResetStudentTest() {
		
		assertNotNull(user.studentResetPassword("varun","black"));
	}
	/**
	 * This Method test negative scenario 
	 */
	@Test
	public void failureResetStudentTest() {
		
		assertNull(user.studentResetPassword("varun1","black"));
	}
	
}
