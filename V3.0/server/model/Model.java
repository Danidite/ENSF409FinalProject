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
	/**
	 * The course catalogue
	 */
	private CourseCatalogue cat;
	/**
	 * The list of all students
	 */
	private ArrayList<Student> students;
	
	/**
	 * Create a model class with no catalogued courses and no students
	 */
	public Model () {
		cat = new CourseCatalogue ();
		students = new ArrayList<Student>();
	}
	
	/**
	 * Add a student to the student list
	 * @param id the id of the student
	 * @param firstName the first name of the student
	 * @param lastName the last name of the student
	 */
	public void addStudent(int id, String firstName, String lastName) {
		students.add(new Student (firstName, lastName, id));
	}
	
	/**
	 * Add a course offering to the course catalogue
	 * @param courseName the name of a course
	 * @param courseID the id of a course
	 * @param sectionID the section id of a course
	 * @param sectionCapacity the capacity of a course
	 */
	public void addCourseOffering(String courseName, int courseID, int sectionID, int sectionCapacity) {
		Course myCourse = cat.searchCat(courseName, courseID);
		cat.createCourseOffering(myCourse, sectionID, sectionCapacity);
	}
	
	/**
	 * Add a course to the course catalogue
	 * @param courseName the name of the course
	 * @param courseID the id of the course
	 */
	public void addCourse(String courseName, int courseID) {
		cat.addCourse(courseName, courseID);
	}

	/**
	 * Return a string representation about
	 * the existance of a course,
	 * if course exists, return 0, else 1.
	 * @param courseName name of course
	 * @param courseID ID of course
	 * @return 1 or 0 the string representation of a true or false value
	 */
	public String courseExist(String courseName, String courseIDInString) {
		int courseID = -1;
		try {
			courseID = Integer.parseInt(courseIDInString);
		} catch (NumberFormatException e) {
		    return ("1");
		}
		
		if (getCourse(courseName, courseID)==null) {
			return ("1");
		}
		
		return ("0");
	}
	/**
	 * Returns a string representation about the 
	 * existance of a student in the catalogue,
	 * if student exists, return 0, else 1
	 * @param studentName name of student
	 * @return 0 or 1 the string representation of a true or false value
	 */
	public String studentExist(String studentName) {
		if (getStudent(studentName)==null) {
			return ("1");
		}
		return ("0");
	}
	
	/**
	 * Returns a string representation of the ability for a student's ability to
	 * enroll in a course, if student can enroll in any class, has less than 6 classes,
	 *  return 0, else 1
	 * @param studentName full name of student
	 * @return 0 or 1 the string representation of a true or false value
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
	public String canEnroll(String studentName, String courseName, String courseIDInString, String sectionIDString) {
		int courseID = -1;
		try {
			courseID = Integer.parseInt(courseIDInString);
		} catch (NumberFormatException e) {
		    return ("1");
		}
		
		Course course = getCourse(courseName, courseID);
		
		if (course==null) {
			//No such course exist in the system
			return ("1");
		}
		
		int sectionID = -1;
		try {
			sectionID = Integer.parseInt(sectionIDString);
		} catch (NumberFormatException e) {
		    return ("2");
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
		
		if (courseOffering.registratedStudents()>=courseOffering.getSecCap()) {
			//The section is already full!
			return ("4");
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
	
	/**
	 * Get a student by the full name of it
	 * @param name the full name of a student
	 * @return the student with the full name specified
	 */
	private Student getStudentFromFullName(String name) {
		for (int i=0; i< students.size(); i++) {
			if (students.get(i).getStudentName().toLowerCase().equals(name.toLowerCase())) {
				return students.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Get a student by their first name
	 * @param name the first name of the student
	 * @return the student with the first name specified
	 */
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
	
	/**
	 * Get the full name of a student through its first name
	 * @param studentName the first name of a student
	 * @return the full name of a student
	 */
	public String getStudentFullName(String studentName) {
		return getStudent(studentName).getStudentName();
	}
}
