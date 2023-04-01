package com.examtracking.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class used to provide the field validation
 * @author BATCH-C
 *
 */
public class FieldValidation {
	
	/**
	 * This method used to provide the field validation for date
	 * @param choice
	 *
	 */
	public String isThisDateValid(int choice){
		String date_to_validate1=null;
		//initializing the scanner class
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		 try { 
			//providing the user to entry the date in string format
			 String dateToValidate=sc.next();
			//Checking whether datetovalidate in date format or not using date class
		    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(dateToValidate);  
		    Date date=new Date();
		    //if choice=1 represents the date of birth,date of joining should be of before todays date
		    if(choice==1) {
		    if(date.after(date1)) {
		    	return dateToValidate ;
		    }
		    else {
				System.out.println("please enter date before todays date");
		    	isThisDateValid(choice);
		    }
		    //if choice=1 represents the date of birth,date of joining should be of after todays date
		    }
		    if(choice==2) {
		    	if(date.before(date1)) {
		    	return dateToValidate;
		    }
		    else {
		    	System.out.println("please enter date after todays date");
		    	isThisDateValid(choice);
		    }
		    }
		    
	         }
	        catch(Exception e){
		          System.out.println("please enter correct valid date format");
		          date_to_validate1= isThisDateValid(choice);
	         }
		return date_to_validate1;
	}
	/**
	 * This method checks whether Given mobile number is valid or not
	 */
	public boolean isValidMobileNumber(String s) { 
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
        Matcher m = p.matcher(s); 
        return (m.find() && m.group().equals(s)); 
    }
	
	/**
	 * This method checks whether Given Email Id is valid or not
	 */
	public boolean isValidEmailId(String email) { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" +  "A-Z]{2,7}$";                     
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	  
	  /**
	   * Validate password with regular expression
	   * @param password password for validation
	   * @return true valid password, false invalid password
	   */
	  public boolean validatePassword(String password){
		  final String PASSWORD_PATTERN = 
		            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
		  Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		  Matcher matcher = pattern.matcher(password);
		  return matcher.matches();
	    	    
	  }
}
