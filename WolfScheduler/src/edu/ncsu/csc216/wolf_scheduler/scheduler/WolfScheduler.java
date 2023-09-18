package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
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
	 * holds the course catalog in an array list
	 */
	ArrayList<Course> catalog;

	/**
	 * holds the schedule in an array list
	 */

	ArrayList<Activity> schedule;

	/**
	 * name for schedule, default My Schedule
	 */
	String title;

	/**
	 * Constructs a WolfScheduler object by reading course information from a file.
	 *
	 * @param fileName The name of the file containing course information to
	 *                 initialize the catalog.
	 * @throws IllegalArgumentException If the specified file cannot be found or if
	 *                                  there are any other exceptions during file
	 *                                  reading.
	 */
	public WolfScheduler(String fileName) {

		catalog = new ArrayList<Course>();
		schedule = new ArrayList<Activity>();
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
		String[][] courseCatalog = new String[catalog.size()][4];

		for (int i = 0; i < catalog.size(); i++) {
			Course a = catalog.get(i);
			courseCatalog[i][0] = a.getShortDisplayArray()[0];
			courseCatalog[i][1] = a.getShortDisplayArray()[1];
			courseCatalog[i][2] = a.getShortDisplayArray()[2];
			courseCatalog[i][3] = a.getShortDisplayArray()[3];
		}

		return courseCatalog;
	}

	/**
	 * Retrieves the scheduled courses as a 2D array of strings.
	 *
	 * @return A 2D array where each row represents a scheduled course with columns
	 *         for name, section, and title.
	 */
	public String[][] getScheduledActivities() {

		String[][] getScheduledActivities = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			getScheduledActivities[i][0] = a.getShortDisplayArray()[0];
			getScheduledActivities[i][1] = a.getShortDisplayArray()[1];
			getScheduledActivities[i][2] = a.getShortDisplayArray()[2];
			getScheduledActivities[i][3] = a.getShortDisplayArray()[3];

		}
		return getScheduledActivities;
	}

	/**
	 * Retrieves the full scheduled courses as a 2D array of strings.
	 *
	 * @return A 2D array where each row represents a scheduled course with columns
	 *         for name, section, and title.
	 */
	public String[][] getFullScheduledActivities() {

		String[][] getFullScheduledCourses = new String[schedule.size()][7];

		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			getFullScheduledCourses[i][0] = a.getLongDisplayArray()[0];
			getFullScheduledCourses[i][1] = a.getLongDisplayArray()[1];
			getFullScheduledCourses[i][2] = a.getLongDisplayArray()[2];
			getFullScheduledCourses[i][3] = a.getLongDisplayArray()[3];
			getFullScheduledCourses[i][4] = a.getLongDisplayArray()[4];
			getFullScheduledCourses[i][5] = a.getLongDisplayArray()[5];
			getFullScheduledCourses[i][6] = a.getLongDisplayArray()[6];

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
	 * Exports the schedule of activities to a file.
	 *
	 * @param fileName The name of the file to which the schedule of activities will
	 *                 be exported.
	 * @throws IllegalArgumentException If an I/O error occurs while writing to the
	 *                                  file.
	 */
	public void exportSchedule(String fileName) {

		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
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
	 * Adds a course to the schedule based on its name and section.
	 *
	 * @param name    The name of the course to be added to the schedule.
	 * @param section The section of the course to be added to the schedule.
	 * @return true if the course was successfully added to the schedule, false if
	 *         the course was not found in the catalog.
	 * @throws IllegalArgumentException If the course with the same name and section
	 *                                  is already in the schedule.
	 */
	public boolean addCourseToSchedule(String name, String section) {

		Course courseAdded = getCourseFromCatalog(name, section);
		if (courseAdded == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(courseAdded)) {
				throw new IllegalArgumentException("You are already enrolled in " + courseAdded.getName());
			}
		}
		schedule.add(courseAdded);
		return true;
	}

	/**
	 * gets index of name and section of the course you want removed and removes it
	 * 
	 * @param idx index of the activity to be removed
	 * 
	 * @return false if it did not remove
	 * 
	 * 
	 * 
	 */
	public boolean removeActivityFromSchedule(int idx) {

		if (schedule.size() > idx) {
			schedule.remove(idx);
			return true;
		}
		return false;
	}

	/**
	 * Sets the title for the schedule.
	 *
	 * @param title The title to be set for the schedule. It should not be null.
	 * @throws IllegalArgumentException If the provided title is null.
	 */
	public void setScheduleTitle(String title) {

		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}

		this.title = title;
	}

	/**
	 * resets schedule by creating a new empty list
	 *
	 */
	public void resetSchedule() {

		schedule = new ArrayList<Activity>();

	}

	/**
	 * Adds an event to the schedule with the specified details.
	 *
	 * @param eventTitle       The title of the event to be added to the schedule.
	 * @param eventMeetingDays The days on which the event occurs, represented as a
	 *                         string.
	 * @param eventStartTime   The start time of the event in the format HHMM (e.g.,
	 *                         0930 for 9:30 AM).
	 * @param eventEndTime     The end time of the event in the format HHMM (e.g.,
	 *                         1430 for 2:30 PM).
	 * @param eventDetails     Additional details or description of the event.
	 * @throws IllegalArgumentException If an event with the same title already
	 *                                  exists in the schedule or if the provided
	 *                                  event details are null.
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			String eventDetails) {
		Event eventToAdd = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);

		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(eventToAdd) || eventToAdd == null) {
				throw new IllegalArgumentException("You have already created an event called " + eventToAdd.getTitle());
			}
		}

		schedule.add(eventToAdd);
	}

}
