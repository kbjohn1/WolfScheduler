package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Writes activities to file.
 * 
 * @author Kevin John
 */
public class ActivityRecordIO {

	/**
	 * Writes a list of activity records to a file.
	 *
	 * @param fileName   The name of the file to which the activity records will be
	 *                   written.
	 * @param activities An ArrayList of Activity objects to be written to the file.
	 * @throws IOException If an I/O error occurs while writing to the file.
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> activities) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (Activity a : activities) {
			fileWriter.println(a.toString());
		}

		fileWriter.close();
	}

}