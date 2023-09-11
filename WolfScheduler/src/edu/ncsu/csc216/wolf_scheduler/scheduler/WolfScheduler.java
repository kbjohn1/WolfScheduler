package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfScheduler reads in and stores as a list all of the Course records stored
 * in a file (UC 1: Load Course Catalog). Additionally, WolfScheduler creates a
 * schedule for the current user (a student) and provides functionality for
 * naming the schedule ([UC 2: Rename Schedule]), adding a Course to the
 * schedule ([UC 4: Add Course to Schedule]), removing a Course from the
 * schedule ([UC 5: Remove Course from Schedule]), resetting the schedule ([Use
 * Case 8: Reset Schedule])
 * 
 * @author Kevin John
 */
public class WolfScheduler {

	/**
	 * holds the catalog in an array list
	 */
	ArrayList<Course> catalog;

	/**
	 * holds the schedule in an array list
	 */

	ArrayList<Course> schedule;

	/**
	 * name for schedule, default My Schedule
	 */
	String title;

	/**
	 * WolfScheduler constructor
	 * 
	 * @param fileName file name that should be read in and stored in array list
	 */
	public WolfScheduler(String fileName) {

		catalog = new ArrayList<Course>();
		schedule = new ArrayList<Course>();
		title = "My Schedule";

		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Retrieves the course catalog as a 2D array of strings.
	 *
	 * @return A 2D array where each row represents a course with columns for name,
	 *         section, and title.
	 */
	public String[][] getCourseCatalog() {
		String[][] getCourseCatalog = new String[catalog.size()][3];
		for (int i = 0; i < catalog.size(); i++) {
			getCourseCatalog[i][0] = catalog.get(i).getName();
			getCourseCatalog[i][1] = catalog.get(i).getSection();
			getCourseCatalog[i][2] = catalog.get(i).getTitle();
		}
		return getCourseCatalog;
	}

	/**
	 * Retrieves the scheduled courses as a 2D array of strings.
	 *
	 * @return A 2D array where each row represents a scheduled course with columns
	 *         for name, section, and title.
	 */
	public String[][] getScheduledCourses() {

		String[][] getScheduledCourses = new String[schedule.size()][3];
		for (int i = 0; i < schedule.size(); i++) {
			getScheduledCourses[i][0] = schedule.get(i).getName();
			getScheduledCourses[i][1] = schedule.get(i).getSection();
			getScheduledCourses[i][2] = schedule.get(i).getTitle();
		}
		return getScheduledCourses;
	}

	/**
	 * Retrieves the full scheduled courses as a 2D array of strings.
	 *
	 * @return A 2D array where each row represents a scheduled course with columns
	 *         for name, section, and title.
	 */
	public String[][] getFullScheduledCourses() {

		String[][] getFullScheduledCourses = new String[schedule.size()][6];
		for (int i = 0; i < schedule.size(); i++) {
			getFullScheduledCourses[i][0] = schedule.get(i).getName();
			getFullScheduledCourses[i][1] = schedule.get(i).getSection();
			getFullScheduledCourses[i][2] = schedule.get(i).getTitle();
			getFullScheduledCourses[i][3] = schedule.get(i).getCredits() + "";
			getFullScheduledCourses[i][4] = schedule.get(i).getInstructorId();
			getFullScheduledCourses[i][5] = schedule.get(i).getMeetingString();
		}
		return getFullScheduledCourses;
	}

	/**
	 * Retrieves the schedule title
	 *
	 * @return a string for the title
	 */
	public String getScheduleTitle() {

		return title;
	}

	/**
	 * exports the schedule using writeCourseRecords()
	 * 
	 * @param fileName the file name
	 */
	public void exportSchedule(String fileName) {

		try {
			CourseRecordIO.writeCourseRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

	/**
	 * Retrieves a course from the catalog based on course name and section number
	 * 
	 * @param name    course name
	 * @param section section number
	 *
	 * @return null
	 */
	public Course getCourseFromCatalog(String name, String section) {

		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * a true or false statement to whether or not you can add a course to your
	 * schedule
	 * 
	 * @param name    course name
	 * @param section section number
	 *
	 * @return false if null | true if not already enrolled
	 * 
	 * @throws IllegalArgumentException if already enrolled
	 * 
	 * 
	 */
	public boolean addCourseToSchedule(String name, String section) {

		Course addCourseToSchedule = getCourseFromCatalog(name, section);
		if (addCourseToSchedule == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(name)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		schedule.add(addCourseToSchedule);
		return true;
	}

	/**
	 * gets index of name and section of the course you want removed and removes it
	 * 
	 * @param name    course name
	 * @param section section number
	 *
	 * @return false if it did not remove
	 * 
	 * 
	 * 
	 */
	public boolean removeCourseFromSchedule(String name, String section) {

		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(name) && schedule.get(i).getSection().equals(section)) {
				schedule.remove(schedule.get(i));
				return true;
			}
		}
		return false;
	}

	/**
	 * checks if title is null
	 * 
	 * @param title schedule title
	 *
	 */
	public void setScheduleTitle(String title) {

		if (title == null /* || title.length() < 0 || title.isEmpty() */ ) {
			throw new IllegalArgumentException("Title cannot be null.");
		}

		this.title = title;
	}

	/**
	 * resets schedule by creating a new empty list
	 *
	 */
	public void resetSchedule() {

		schedule = new ArrayList<Course>();

	}

}
