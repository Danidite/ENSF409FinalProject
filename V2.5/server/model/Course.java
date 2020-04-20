package server.model;
import java.util.ArrayList;
/**
 * This class takes care of a course, a things such as the name, id,
 * the different lectures offered by the course etc.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class Course {
	private String courseName;
	private int courseNum;
	@SuppressWarnings("unused")
	private ArrayList<Course> preReq;
	private ArrayList<CourseOffering> offeringList;

	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	@Override
	public String toString () {
		String st = "\n";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nAll course sections:\n";
		st += "-------\n";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\n-------\n";
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
