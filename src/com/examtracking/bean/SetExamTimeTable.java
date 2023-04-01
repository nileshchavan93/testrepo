package com.examtracking.bean;

import java.io.Serializable;

/**
 * this class is used to set the timetable of different examinations for
 * students
 * 
 * @author BATCH-'C"
 *
 */

public class SetExamTimeTable implements Serializable {
	/**
	 * this are getters setters method for the student set timetable
	 */
	private static final long serialVersionUID = 1L;
	
	private String date;
	private String subject;
	private String time;
	private String venue;
	private int timeTableId;
	private int examTypeId;
	private int branchId;
	/**
	 * 
	 * @return date
	 */
	
	public String getDate() {
		return date;
	}

	public int getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(int timeTableId) {
		this.timeTableId = timeTableId;
	}

	public int getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(int examTypeId) {
		this.examTypeId = examTypeId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 
	 * @return time
	 */
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 
	 * @return venue
	 */
	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	@Override
	public String toString() {
		return "SetExamTimeTable [date=" + date + ", subject=" + subject + ", time=" + time + ", venue=" + venue
				+ ", timeTableId=" + timeTableId + ", examTypeId=" + examTypeId + ", branchId=" + branchId + "]";
	}
	
}
