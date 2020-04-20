package server.model;
import java.util.ArrayList;
/**
 * This is the course catalogue, it keeps track of each course.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class CourseCatalogue {
	/**
	 * The list of student in the catalogue
	 */
	private ArrayList <Course> courseList;
	
	/**
	 * Construct a course catalogue without any courses
	 */
	public CourseCatalogue () {
		courseList = new ArrayList<Course>();
	}
	
	/**
	 * Add a course to the course catalogue
	 * @param courseName the name of the course
	 * @param courseID the id of the course
	 */
	public void addCourse(String courseName, int courseID) {
		courseList.add(new Course (courseName, courseID));
	}
	
	/**
	 * this will create a course offering, for the given course.
	 * @param c the course
	 * @param secNum section number (lecture number)
	 * @param secCap section Capacity.
	 */
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	/**
	 * search the catalogue for a specific course
	 * @param courseName course name
	 * @param courseNum course ID
	 * @return the course
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		displayCourseNotFoundError();
		return null;
	}
	
	
	/**
	 * Display a course not found error message to the console, used for debugging
	 */
	private void displayCourseNotFoundError() {
		System.err.println("Course was not found!");
		
	}
	
	/**
	 * Get the list of the course
	 * @return
	 */
	public ArrayList <Course> getCourseList() {
		return courseList;
	}
	
	/**
	 * Set the course list
 	 * @param courseList a list of courses
	 */
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * Put all the courses and there information in a string format
	 */
	@Override
	public String toString () {
		String st = "\nAll courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\n";
		}
		st+="\n";
		return st;
	}
	
	/**
	 * searches for a specific course
	 * @param courseName name of course
	 * @param id id of course
	 * @return the course
	 */
	public Course getCourse(String courseName, int id) {
		for (int i=0; i<courseList.size();i++) {
			if (courseName.toLowerCase().equals(courseList.get(i).getCourseName().toLowerCase())&&id==courseList.get(i).getCourseNum()) {
				return courseList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * return all the courses in the catalogue
	 * @return the courses in the catalogue.
	 */
	public String searchCatalogueCourses() {
		String s="";
		for (int i=0; i< courseList.size();i++) {
			s+=courseList.get(i).searchCatalogueCourses();
		}
		return s;
	}
}
