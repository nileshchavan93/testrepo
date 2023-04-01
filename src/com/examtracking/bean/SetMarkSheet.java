package com.examtracking.bean;

import java.io.Serializable;
import java.util.Comparator;
/**
 * 
 * @author BATCH-'C'
 * this class is used to display the marks set by the admin
 *
 */
public class SetMarkSheet implements Serializable {
	/**
	 * getter setter method for mark sheet
	 */
	private static final long serialVersionUID = 1L;

	// variables to set marks 
	private long student_id;
	private long branch_id;
	private int maths;
	private int physics;
	private int chemistry;
	private int english;
	private int sanskrit;
	private long markSheet_id;
	private int totalmarks;
	private int outOfMarks;
	private String firstName;
	private String lastName;
	private double percentage;
	private int exam_id;
	private int year;
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * 
	 * @return exam_id
	 */
   public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	// getter setters method
	/**
	 * 
	 * @return student_id
	 */
	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	/**
	 * 
	 * @return maths
	 */
	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	/**
	 * 
	 * @return physics
	 */
	public int getPhysics() {
		return physics;
	}

	public void setPhysics(int physics) {
		this.physics = physics;
	}

	/**
	 * 
	 * @return chemistry
	 */
	public int getChemistry() {
		return chemistry;
	}

	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * 
	 * @return english
	 */
	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	/**
	 * 
	 * @return sanskrit
	 */
	public int getSanskrit() {
		return sanskrit;
	}

	public void setSanskrit(int sanskrit) {
		this.sanskrit = sanskrit;
	}
     /**
      * 
      * @return branch
      */
	
	public long getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(long branch_id) {
		this.branch_id = branch_id;
	}
     /**
      * 
      * @return firstname
      */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    /**
     * 
     * @return lastname
     */
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    /**
     * 
     * @return percentage
     */
	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
     /**
      * 
      * @return totalmarks to get the total marks
      */
	public static Comparator<SetMarkSheet> getTotalMarks() {
		return totalMarks;
	}

	public static void setTotalMarks(Comparator<SetMarkSheet> totalMarks) {
		SetMarkSheet.totalMarks = totalMarks;
	}

	/**
	 * 
	 * @return marksheet_id
	 */
	public long getMarkSheet_id() {
		return markSheet_id;
	}

	public void setMarkSheet_id(long markSheet_id) {
		this.markSheet_id = markSheet_id;
	}

	/**
	 * 
	 * @return totalmarks
	 */
	public int getTotalmarks() {
		return totalmarks;
	}

	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}


	/**
	 * 
	 * @return out ofMarks
	 */
	public int getOutOfMarks() {
		return outOfMarks;
	}

	public void setOutOfMarks(int outOfMarks) {
		this.outOfMarks = outOfMarks;
	}

	
	

	@Override
	public String toString() {
		return "SetMarkSheet [student_id=" + student_id + ", branch_id=" + branch_id + ", maths=" + maths + ", physics="
				+ physics + ", chemistry=" + chemistry + ", english=" + english + ", sanskrit=" + sanskrit
				+ ", markSheet_id=" + markSheet_id + ", totalmarks=" + totalmarks + ", outOfMarks=" + outOfMarks
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", percentage=" + percentage + ", exam_id="
				+ exam_id + ", year=" + year + "]";
	}




	/***
	 * This static class ordered student in descending order
	 */
	public static Comparator<SetMarkSheet> totalMarks = new Comparator<SetMarkSheet>() {

		public int compare(SetMarkSheet s1, SetMarkSheet s2) {

			int marks1 = s1.getTotalmarks();
			int marks2 = s2.getTotalmarks();

			/* For ascending order */
			return marks2 - marks1;
		}
	};

}