package server.model;

import java.util.ArrayList;
/**
 * this class is similar to our data base class, as it adds and creates all of the course offering
 * for our courses and the students. The server talks to this class which controls everything happening 
 * in the backend.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class Model {
	private CourseCatalogue cat;
	private ArrayList<Student> students;
	
	public Model () {
		cat = new CourseCatalogue ();
		students = new ArrayList<Student>();
	}
	
	public void addStudent(int id, String firstName, String lastName) {
		//System.out.println("Student added, id: "+id+", name: "+ firstName+" "+lastName);
		students.add(new Student (firstName, lastName, id));
	}
	
	public void addCourseOffering(String courseName, int courseID, int sectionID, int sectionCapacity) {
		//System.out.println("The course offering on course: "+courseName+" "+ courseID+", section id: "+sectionID+", section cap: "+sectionCapacity);
		Course myCourse = cat.searchCat(courseName, courseID);
		cat.createCourseOffering(myCourse, sectionID, sectionCapacity);
	}
	
	public void addCourse(String courseName, int courseID) {
		//System.out.println("The course: "+courseName+" "+ courseID+" has been added!");
		cat.addCourse(courseName, courseID);
	}

	/**
	 * if course exists, return 0, else 1.
	 * @param courseName name of course
	 * @param courseID ID of course
	 * @return 1 or 0
	 */
	public String courseExist(String courseName, int courseID) {
		if (getCourse(courseName, courseID)==null) {
			return ("1");
		}
		return ("0");
	}
	/**
	 * if student exists, return 0, else 1
	 * @param studentName name of student
	 * @return 0 or 1
	 */
	public String studentExist(String studentName) {
		if (getStudent(studentName)==null) {
			return ("1");
		}
		return ("0");
	}
	/**
	 * if student can enroll in any class, has less than 6 classes, return 0, else 1
	 * @param studentName name of student
	 * @return 0 ot 1
	 */
	public String studentCanEnroll(String studentName) {
		if (getStudent(studentName).full()==true) {
			return ("1");
		}
		return ("0");
	}
	/**
	 * remove a course from a student
	 * @param studentName name of student 
	 * @param courseName name of course
	 * @param courseID id of course
	 * @return 0 if course removed, 1 otherwise.
	 */
	public String removeCourse(String studentName, String courseName, int courseID) {
		Student student = getStudent(studentName);
		if (student.deleteOffering(courseName,courseID)==false) {
			//Course was not taken by student
			return ("1");
		} 
		return ("0");
	}
	/**
	 * checks to see if student can enroll in course
	 * @param studentName name of student
	 * @param courseName course name
	 * @param courseID course ID
	 * @param sectionID section ID
	 * @return a digit, depends on the outcome. refer to comments
	 */
	public String canEnroll(String studentName, String courseName, int courseID, int sectionID) {
		Course course = getCourse(courseName, courseID);

		if (course==null) {
			//No such course exist in the system
			return ("1");
		}
		
		CourseOffering courseOffering = course.getCourseOfferingAt(sectionID);
		
		if (courseOffering==null) {
			//Section not available
			return ("2");
		}
		
		Student student = getStudent(studentName);
		
		if (student.alreadyEnrolled(courseName, courseID)) {
			//Student already in the course
			return ("3");
		}
		
		//Can Enroll
		return ("0");
	}
	
	/**
	 * searches for course in the catalogue
	 * @param courseName course name
	 * @param courseID course ID
	 * @return the course
	 */
	public String searchCatalogue(String courseName, int courseID) {
		Course course = getCourse(courseName, courseID);
		return (""+course);
	}
	/**
	 * adds a course to a student
	 * @param studentName student name
	 * @param courseName course name
	 * @param courseID course ID
	 * @param sectionID section number
	 */
	public String addCourse(String studentName, String courseName, int courseID, int sectionID) {
		Student student = getStudent(studentName);
		
		Course course = getCourse(courseName, courseID);

		CourseOffering courseOffering = course.getCourseOfferingAt(sectionID);

		student.addRegistration(new Registration(student,courseOffering));
		
		return student.getStudentName();
	}
	/**
	 * returns the catalogue
	 * @return the cataloque
	 */
	public String viewCourseCatalogue() {
		return (""+cat);
	}
	/**
	 * returns all courses taken by the students 
	 * @return the list all courses taken.
	 */
	public String viewCourseTaken() {
		String list = "";
		for (int i=0; i<students.size();i++) {
			list += students.get(i).viewAllCoursesTakenByStudent() + "\n";
		}
		return (""+list);
	}
	/**
	 * gives the list of students
	 * @return the list
	 */
	public String viewStudentList() {
		String list ="";
		for (Student s: students) {
			list += s + "\n";
		}
		return list;
	}
	/**
	 * return the student you are asking for, else null
	 * @param name name of the student you want.
	 * @return the student, or null
	 */
	private Student getStudent(String name) {
		Student student = getStudentFromFullName(name);
		
		if (student==null) {
			student = getStudentFromFirstName(name);
		}
		
		return student;
	}
	
	private Student getStudentFromFullName(String name) {
		for (int i=0; i< students.size(); i++) {
			if (students.get(i).getStudentName().toLowerCase().equals(name.toLowerCase())) {
				return students.get(i);
			}
		}
		
		return null;
	}
	
	private Student getStudentFromFirstName(String name) {
		for (int i=0; i< students.size(); i++) {
			if (students.get(i).getFirstName().toLowerCase().equals(name.toLowerCase())) {
				return students.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * gets a course if it exists
	 * @param courseName the name of the course
	 * @param id the id of the course
	 * @return the course
	 */
	private Course getCourse(String courseName, int id) {
		return cat.getCourse(courseName, id);
	}
	
	public String getStudentFullName(String studentName) {
		return getStudent(studentName).getStudentName();
	}
}
