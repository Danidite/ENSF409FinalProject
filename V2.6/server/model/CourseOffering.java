package server.model;
import java.util.ArrayList;
/**
 * this class keeps track of the different course offerings for a course.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class CourseOffering {
	
	private int secNum;
	private int secCap;
	private Course theCourse;
	private ArrayList <Registration> offeringRegList;
	
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	public int getSecNum() {
		return secNum;
	}
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	public int getSecCap() {
		return secCap;
	}
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	public Course getTheCourse() {
		return theCourse;
	}
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	@Override
	public String toString () {
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\n";
		//We also want to print the names of all students in the section
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
	 * 
	 * @return the course taken by the students
	 */
	public String viewAllCoursesTakenByStudent() {
		return theCourse.getCourseName()+" "+theCourse.getCourseNum()+", Section Number: "+secNum;	
	}
	
	
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
