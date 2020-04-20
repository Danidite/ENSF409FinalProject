package server.model;
/**
 * 
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 * this class deals with registering a student into a class, and keeping track of their grades(which is not 
 * something we care about in milestone II).
 */
public class Registration {
	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;
	
	public Registration(Student st, CourseOffering of) {
		completeRegistration (st, of);
	}
	
	void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration ();
	}
	
	private void addRegistration () {
		//theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	public Student getTheStudent() {
		return theStudent;
	}
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString () {
		String st = "\n";
		st += "Student Name: " + getTheStudent() + "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;
		
	}

	public String viewAllCoursesTakenByStudent() {
		return theOffering.viewAllCoursesTakenByStudent();
	}

	public void terminate() {
		theOffering.removeRegistration(this);
	}
}
