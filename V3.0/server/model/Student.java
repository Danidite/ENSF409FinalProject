package server.model;
import java.util.ArrayList;
/**
 * this class takes care of all information for the student.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class Student {
	/**
	 * The first name of the student
	 */
	private String firstName;
	/**
	 * The last name of the student
	 */
	private String lastName;
	/**
	 * The id of the student
	 */
	private int studentId;
	/**
	 * A list of the students registration
	 */
	private ArrayList<Registration> studentRegList;

	/**
	 * Constructs a student with the parameters for
	 * first name, last name and student id
	 * @param firstName the first name of the student
	 * @param lastName the last name of the student
	 * @param studentId the id of the student
	 */
	public Student (String firstName, String lastName, int studentId) {
		this.setStudentName(firstName, lastName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}
	
	/**
	 * The to string method of the class
	 */
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}
	/**
	 * adds a registrations to the student
	 * @param registration the registration.
	 */
	public void addRegistration(Registration registration) {
		studentRegList.add(registration);
	}
	/**
	 * shows all the courses taken by the student
	 * @return return the String, with all the courses taken by the student.
	 */
	public String viewAllCoursesTakenByStudent() {
		String s ="Student: "+getStudentName()+"\nCourses:\n";
		for (int i=0; i<studentRegList.size();i++) {
			s+=studentRegList.get(i).viewAllCoursesTakenByStudent()+"\n";
		}
		s+="\n-------\n";
		return s;
	}
	/**
	 * deletes a course offering from this student list.
	 * @param courseName the name of the course
	 * @param courseNum the course ID
	 * @return true if the course has been removed, else false.
	 */
	public boolean deleteOffering(String courseName, int courseNum) {
		Registration temp=null;
		int b=0;
		int i;
		for (i=0; i<studentRegList.size();i++) {
			if (studentRegList.get(i).getTheOffering().getTheCourse().getCourseName().toLowerCase().equals(courseName.toLowerCase())&&
					studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum()==courseNum) {
				temp=studentRegList.get(i);
				b=i;
			}
		}
		if (temp!=null) {
			temp.terminate();
			studentRegList.remove(b);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * checks to see if the student is already enrolled in the course
	 * @param courseName name of the course
	 * @param courseNum ID of the course
	 * @return true if they already have the course, else false
	 */
	public boolean alreadyEnrolled(String courseName, int courseNum) {
		for (int i=0; i<studentRegList.size();i++) {
			if (studentRegList.get(i).getTheOffering().getTheCourse().getCourseName().toLowerCase().equals(courseName.toLowerCase())&&
					studentRegList.get(i).getTheOffering().getTheCourse().getCourseNum()==courseNum) {
				return true;
			}
		}
		return false;
	}
	/**
	 * checks if the student has reached the limit of 6 courses.
	 * @return true if they have 6 courses, else false.
	 */
	public boolean full() {
		if (studentRegList.size()>5) {
			//If a student has 6 classes or above
			return true;
		}
		return false;
	}
	
	/**
	 * Get the full name of the student
	 * @return the full name of the student
	 */
	public String getStudentName() {
		return firstName+" "+lastName;
	}
	
	/**
	 * Set both the first and last name of a student
	 * @param firstName the first name of a student
	 * @param lastName the last name of a student
	 */
	public void setStudentName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Get the first name of the student 
	 * @return the first name of the student
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Get the id of the student
	 * @return the id of a student
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * Set the id of the student
	 * @param studentId the id of a student
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
