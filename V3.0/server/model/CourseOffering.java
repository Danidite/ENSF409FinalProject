package server.model;
import java.util.ArrayList;
/**
 * this class keeps track of the different course offerings for a course.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class CourseOffering {
	/**
	 * The section number
	 */
	private int secNum;
	/**
	 * The section capacity
	 */
	private int secCap;
	/**
	 * The course offered
	 */
	private Course theCourse;
	/**
	 * List of all registration of the course
	 */
	private ArrayList <Registration> offeringRegList;
	
	/**
	 * Contructs a course offering with the input parameters
	 * @param secNum the section number of the offering
	 * @param secCap the capacity of the offering
	 */
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	/**
	 * Returns the section number
	 * @return the section number
	 */
	public int getSecNum() {
		return secNum;
	}
	/**
	 * Set the section number
	 * @param secNum the section number
	 */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	/**
	 * Get the section capactity
	 * @return the section capacity
	 */
	public int getSecCap() {
		return secCap;
	}
	/**
	 * Set the section capacity
	 * @param secCap the capacity of a section
	 */
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	/**
	 * Get the course offered
	 * @return the course offered
	 */
	public Course getTheCourse() {
		return theCourse;
	}
	/**
	 * Set the course offered
	 * @param theCourse the course offered
	 */
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	/**
	 * Sent all the relevant immformation about the course offering in a string
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\n";
		st += "Current students: "+registratedStudents()+"\n";
		if (registratedStudents()>=8) {
			st += "This section has enough students! \n";
		} else {
			st += "This section need more students! \n";
		}
		
		return st;
	}
	/**
	 * adds a registration
	 * @param registration the registration
	 */
	public void addRegistration(Registration registration) {
		offeringRegList.add(registration);
	}
	/**
	 * return all the offering of the course
	 * @return the offerings of a course
	 */
	public String searchCatalogueCourses() {
		String s ="";
		for (int i=0; i< offeringRegList.size();i++) {
			s+=offeringRegList.get(i);
		}
		return s;
	}
	/**
	 * View all the course taken by the student
	 * @return the course taken by the students
	 */
	public String viewAllCoursesTakenByStudent() {
		return theCourse.getCourseName()+" "+theCourse.getCourseNum()+", Section Number: "+secNum;	
	}
	
	/**
	 * Get the size of the number of students registered
	 * @return the number of students registered in the offering
	 */
	public int registratedStudents() {
		return offeringRegList.size();
	}
	
	/**
	 * removes a registration
	 * @param registration the registration
	 */
	public void removeRegistration(Registration registration) {
		for (int i=0; i<offeringRegList.size(); i++) {
			if (offeringRegList.get(i).equals(registration)) {
				offeringRegList.remove(i);
			}
		}
	}
}
