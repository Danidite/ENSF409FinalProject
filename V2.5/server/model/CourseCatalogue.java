package server.model;
import java.util.ArrayList;
/**
 * This is the course catalogue, it keeps track of each course.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class CourseCatalogue {
	private ArrayList <Course> courseList;
	
	public CourseCatalogue () {
		courseList = new ArrayList<Course>();
	}
	
	public void addCourse(String courseName, int courseID) {
		courseList.add(new Course (courseName, courseID));
	}
	
	/**
	 * lists all the courses that can be run, meaning they have at least 8 students, not used 
	 * in milestone II. 
	 */
	public void printRunableCourses() {
		System.out.println("\nEach course must have 8 students to be able to run.");
		System.out.println("Here is a list of all the courses, their student number and if they can run.\n");
		
		for (int i=0; i<courseList.size();i++) {
			System.out.println("Course Name: "+courseList.get(i).getCourseName()+" "+courseList.get(i).getCourseNum());
			System.out.println("There are a total of "+courseList.get(i).registratedStudents()+" registrated students.");
			if (courseList.get(i).registratedStudents()>=8) {
				System.out.println("This course is able to run!");
			} else {
				System.out.println("This course have too little students to run!");
			}
			System.out.println("\n");
		}
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
	
	
	//Typically, methods that are called from other methods of the class
	//are private and are not exposed for use by other classes.
	//These methods are refereed to as helper methods or utility methods
	private void displayCourseNotFoundError() {
		// TODO Auto-generated method stub
		System.err.println("Course was not found!");
		
	}
	public ArrayList <Course> getCourseList() {
		return courseList;
	}


	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
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
