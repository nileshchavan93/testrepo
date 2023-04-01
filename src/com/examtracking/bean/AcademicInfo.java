package com.examtracking.bean;

import java.io.Serializable;

/**
 * 
 * @author BATCH-'C'
 *
 * This is class is used to check the eligibility of student Academic
 * information
 */

public class AcademicInfo implements Serializable {
	/**
	 * This method is used to declare the student id attendance and fee to check the
	 * eligibility of the student
	 */
	private static final long serialVersionUID = 1L;
	
	// variables to declare the student id and attendance ,fee for eligibility check
	private long student_id;
	private long attendance;
	private String fee;
	private String check;

	// getter setter methods for Academic info
	/**
	 * @return student_id
	 */
	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	/**
	 * @return attendance
	 */
	public long getAttendance() {
		return attendance;
	}

	public void setAttendance(long attendance) {
		this.attendance = attendance;
	}

	/**
	 * 
	 * @return fee
	 */
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	/**
	 * 
	 * @return check
	 */
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "AcademicInfo [student_id=" + student_id + ", attendance=" + attendance + ", fee=" + fee + ", check="
				+ check + "]";
	}
	
}
