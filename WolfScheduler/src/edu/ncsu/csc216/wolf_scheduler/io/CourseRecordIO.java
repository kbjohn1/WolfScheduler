/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Kevin John
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		ArrayList<Course> courses = new ArrayList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());
				// System.out.println(courses.size());
				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the ArrayList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the ArrayList with all the courses we read!
		return courses;
	}

	/**
	 * Reads course information from a file and creates a Course object.
	 *
	 * @param fileName The name of the file containing course information.
	 * @return A Course object constructed from the information in the file.
	 * @throws FileNotFoundException    If the specified file cannot be found.
	 * @throws IllegalArgumentException If the input format in the file is invalid
	 *                                  or if there are any other exceptions during
	 *                                  file reading.
	 */
	private static Course readCourse(String fileName) throws FileNotFoundException {
		try {
			// construct Scanner to process the line parameter
			Scanner fileReader = new Scanner(fileName);

			// set the delimiter to ","
			fileReader.useDelimiter(",");

			// read in read in tokens for name, title, section, credits, instructorId, and
			// meetingDays and store in local variables
			String name = fileReader.next();
			String title = fileReader.next();
			String section = fileReader.next();
			int credits = fileReader.nextInt();
			String instructorId = fileReader.next();
			String meetingDays = fileReader.next();

			if ("A".equals(meetingDays)) {
				// If meetingDays is "A", check if there are more tokens
				if (fileReader.hasNext()) {
					fileReader.close();
					throw new IllegalArgumentException("Invalid input format");
				} else {
					// Return a newly constructed Course object
					fileReader.close();
					return new Course(name, title, section, credits, instructorId, meetingDays);
				}
			} else {
				// Read tokens for startTime and endTime
				int startTime = fileReader.nextInt();
				int endTime = fileReader.nextInt();

				// If there are more tokens, throw an exception
				if (fileReader.hasNext()) {
					fileReader.close();
					throw new IllegalArgumentException("Invalid input format");
				} else {
					// Return a newly constructed Course object
					fileReader.close();
					return new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
				}
			}
		} catch (Exception e) {
			// Catch any exceptions and throw a new IllegalArgumentException
			throw new IllegalArgumentException("Invalid input format", e);
		}

//		try {
//			Scanner fileReader = new Scanner(fileName);
//			fileReader.useDelimiter(",");
//			String courseCode = fileReader.next();
//			String description = fileReader.next();
//			String section = fileReader.next();
//			int hours = fileReader.nextInt();
//			String instructorID = fileReader.next();
//			String meetingDays = fileReader.next();
//			if (("A").equals(meetingDays) && !fileReader.hasNext()) {
//				
//				fileReader.close();
//				
//				return new Course (courseCode, description, section, hours, instructorID, meetingDays);
//
//			} else if (("A").equals(meetingDays) && fileReader.hasNext()){
//				fileReader.close();
//				throw new IllegalArgumentException("Invalid input format");
//			}
//			
//			int startTime = fileReader.nextInt();
//			int endTime = fileReader.nextInt();
//			
//			fileReader.close();
//			
//			return new Course (courseCode, description, section, hours, instructorID, meetingDays, startTime, endTime);
//			
//		
//		} catch (Exception e) {
//			// Catch any exceptions and throw a new IllegalArgumentException
//			throw new IllegalArgumentException("Invalid input format", e);
//		}
	}

}
