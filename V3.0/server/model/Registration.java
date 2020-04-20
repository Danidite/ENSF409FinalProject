package server.model;
/**
 * 
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 * this class deals with registering a student into a class, and keeping track of their grades(which is not 
 * something we care about in milestone II).
 */
public class Registration {
	/**
	 * The student of the registration
	 */
	private Student theStudent;
	/**
	 * The course offering the registration is registered to 
	 */
	private CourseOffering theOffering;
	/**
	 * The grade of this registration
	 */
	private char grade;
	
	/**
	 * Contructs a registration
	 * @param st the student of the registration
	 * @param of the offering of the registration
	 */
	public Registration(Student st, CourseOffering of) {
		completeRegistration (st, of);
	}
	
	/**
	 * Complete this registration
 	 * @param st the student of the registration
	 * @param of the offering of the registration
	 */
	void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration ();
	}
	
	/**
	 * Add a registration to the course offering
	 */
	private void addRegistration () {
		theOffering.addRegistration(this);
	}
	
	/**
	 * Return the student of the registration
	 * @return the student of the registration
	 */
	public Student getTheStudent() {
		return theStudent;
	}
	
	/**
	 * Set the student of the registration
	 * @param theStudent the student to be set
	 */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	
	/**
	 * Get the course offering of the registration
	 * @return the course offering of the registration
	 */
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	
	/**
	 * Set the course offering of the registration
	 * @param theOffering the course offering of the registration
	 */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	
	/**
	 * Get the letter grade of registration
	 * @return the grade of the registration
	 */
	public char getGrade() {
		return grade;
	}
	
	/**
	 * Set the grade of the registration
	 * @param grade the grade of the registration
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	/**
	 * Put all the relevant information of this registration to a string
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += "Student Name: " + getTheStudent() + "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;
		
	}

	/**
	 * View all courses taken by student
	 * @return the string representation of all the courses taken by the student
	 */
	public String viewAllCoursesTakenByStudent() {
		return theOffering.viewAllCoursesTakenByStudent();
	}

	/**
	 * Get the course offering to remove this registration
	 */
	public void terminate() {
		theOffering.removeRegistration(this);
	}
}
