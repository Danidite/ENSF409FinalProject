package server.model;
import java.util.ArrayList;
/**
 * This class takes care of a course, a things such as the name, id,
 * the different lectures offered by the course etc.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class Course {
	/**
	 * The name of the course
	 */
	private String courseName;
	/**
	 * The id of a course
	 */
	private int courseNum;
	/**
	 * The list of prerequisites for the course
	 */
	private ArrayList<Course> preReq;
	/**
	 * The list of all offering of the course
	 */
	private ArrayList<CourseOffering> offeringList;
	
	/**
	 * Construct a course object with specified parameters
	 * @param courseName the name of the course
	 * @param courseNum the id of the course
	 */
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}
	
	/**
	 * Add a prerequisite to the course
	 * @param req the prerequisite to be added
	 */
	public void addPrerequisite (Course req) {
		preReq.add(req);
	}
	
	/**
	 * returns the number of registered students.
	 * @return registered students
	 */
	public int registratedStudents() {
		int sum=0;
		for (int i=0; i<offeringList.size();i++) {
			sum+=offeringList.get(i).registratedStudents();
		}
		return sum;
	}
	
	/**
	 * used to add a variation of this course that are offered
	 * @param offering a course offering.
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			
			offeringList.add(offering);
		}
	}
	
	/**
	 * Return the name of the course
	 * @return the name of the course
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Set the name of the course
	 * @param courseName the name of the course
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Get the course id
	 * @return the id of the course
	 */
	public int getCourseNum() {
		return courseNum;
	}

	/**
	 * Set the course id
	 * @param courseNum the id of the course
	 */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	/**
	 * Sents all the information of the course in a string format
	 */
	@Override
	public String toString () {
		String st = "";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nCurrent students: "+registratedStudents()+"\n";
		
		st += "\nAll course sections:";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\n------------------------------------------\n";
		return st;
	}
	/**
	 * looks for a specific course offering, and returns it if it exists
	 * @param i the section number.
	 * @return the offering.
	 */
	public CourseOffering getCourseOfferingAt(int i) {
		for (int j=0; j< offeringList.size(); j++) {
			if (offeringList.get(j).getSecNum()==i) {
				return offeringList.get(j);
			}
		}
		return null;
	}

	/**
	 * returns all the sections numbers in this course's catalogue.
	 * @return all the offerings of this course.
	 */
	public String searchCatalogueCourses() {
		String s = "";
		for (int i=0; i< offeringList.size();i++) {
			s+=offeringList.get(i).searchCatalogueCourses();
		}
		return s;
	}
	
}
