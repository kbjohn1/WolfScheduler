package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Abstract class that allows for Course and Event to specialize a common set of
 * attributes
 */
public abstract class Activity {

	/**
	 * upper hour described as a constant integer
	 */
	private static final int UPPER_HOUR = 24;
	/**
	 * upper minute described as a constant integer
	 */
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Activity constructor
	 * 
	 * @param title       - course's title
	 * @param meetingDays - course's meeting days
	 * @param startTime   - start time for course
	 * @param endTime     - end time for course
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * getter for short display array
	 * 
	 * @return Short display array
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * getter for long display array
	 * 
	 * @return long display array
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * checks for duplicates in Events and Courses
	 * 
	 * @param activity - activity object
	 * @return true if a duplicate is found
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Gets the Course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title associated with the course or an event.
	 *
	 * @param title The title to be set. It should not be empty or null.
	 *
	 * @throws IllegalArgumentException If the provided title is empty or null.
	 */
	public void setTitle(String title) {

		// checks empty or null object
		if (isEmptyChecker(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Gets the Course's meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meeting days and times for the course or an event.
	 *
	 * @param meetingDays The days of the week when the course or event meets,
	 *                    represented as a string. Each character represents a day
	 *                    of the week (M, T, W, H, F, S, U). Use "A" to indicate
	 *                    asynchronous or online events with no specific meeting
	 *                    days.
	 * @param startTime   The start time for the course or event in the format HHMM
	 *                    (e.g., 0930 for 9:30 AM).
	 * @param endTime     The end time for the course or event in the format HHMM
	 *                    (e.g., 1430 for 2:30 PM).
	 *
	 * @throws IllegalArgumentException If the provided meetingDays, startTime, or
	 *                                  endTime do not meet the validation criteria:
	 *                                  - meetingDays is empty, null, or contains
	 *                                  invalid characters. - meetingDays contains
	 *                                  duplicate weekday letters. - startTime or
	 *                                  endTime is not in the correct format (HHMM).
	 *                                  - startTime or endTime falls outside the
	 *                                  valid time range. - endTime is earlier than
	 *                                  startTime. - If "A" is provided as
	 *                                  meetingDays, startTime and endTime must be
	 *                                  0.
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// checks if the given parameters are empty or null
		if (isEmptyChecker(meetingDays) || isEmptyChecker("" + startTime) || isEmptyChecker("" + endTime)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		// if meeting day A, meeting time must not be listed. order flip to avoid null
		// exceptions with an empty meetingDays
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		} else {
			// holds letter count for each weekday letter
			int letterCountM = 0, letterCountT = 0, letterCountW = 0, letterCountH = 0, letterCountF = 0,
					letterCountS = 0, letterCountU = 0;

			// loops through all characters in meetingDays and increments weekday letter
			// counter if a letter is found
			for (int i = 0; i < meetingDays.length(); i++) {
				if ('M' == meetingDays.charAt(i)) {
					letterCountM++;
				}
				if ('T' == meetingDays.charAt(i)) {
					letterCountT++;
				}
				if ('W' == meetingDays.charAt(i)) {
					letterCountW++;
				}
				if ('H' == meetingDays.charAt(i)) {
					letterCountH++;
				}
				if ('F' == meetingDays.charAt(i)) {
					letterCountF++;
				}
				if ('S' == meetingDays.charAt(i)) {
					letterCountS++;
				}
				if ('U' == meetingDays.charAt(i)) {
					letterCountU++;
				}
				if (!('M' == meetingDays.charAt(i)) && !('T' == meetingDays.charAt(i))
						&& !('W' == meetingDays.charAt(i)) && !('H' == meetingDays.charAt(i))
						&& !('F' == meetingDays.charAt(i)) && !('S' == meetingDays.charAt(i))
						&& !('U' == meetingDays.charAt(i))) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			if (letterCountM > 1 || letterCountT > 1 || letterCountW > 1 || letterCountH > 1 || letterCountF > 1
					|| letterCountS > 1 || letterCountU > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// break apart startTime and endTime into hours and minutes
			int startHour = startTime / 100;
			int endHour = endTime / 100;

			int startMin = startTime % 100;
			int endMin = endTime % 100;
			// boundary tests for start and end times
			if (startHour < 0 || startHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (startMin < 0 || startMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endHour < 0 || endHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endMin < 0 || endMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endTime < startTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		// if flow control is not transfered, meeting days is set
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Gets the Course's starting time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the Course's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * gets the meeting days and times for a course and returns it as a string
	 * 
	 * @return a string describing what days and what time a course is meeting
	 *         unless it is arranged
	 */
	public String getMeetingString() {
		String getMeetingString;

		getMeetingString = "" + meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);

		if (!"A".equals(meetingDays)) {
			return getMeetingString;
		} else {
			return "Arranged";
		}
	}

	/**
	 * converts military to standard time
	 * 
	 * @param time the time value inputted for either start or end time
	 * @return standard time with AM or PM
	 */
	private String getTimeString(int time) {
		// military to standard hours. makes sure something like 15oclock gets turned to
		// 3 o'clock
		int hours = time / 100;
		if (hours > 12) {
			hours -= 12;
		}

		// military to standard minutes. 0 minutes gets converted to 00
		int minutes = time % 100;
		String doubleZero = "";
		if (minutes == 0) {
			doubleZero = "0";
		} else {
			doubleZero = "";
		}

		// checks for AM or PM
		String amOrPm;
		if (time < 1159) {
			amOrPm = "AM";
		} else {
			amOrPm = "PM";
		}

		return "" + hours + ":" + minutes + doubleZero + amOrPm;
	}

	/**
	 * Checks if a method's string parameter is empty or null
	 * 
	 * @param value string value from a method
	 * @return whether or not the inputed string parameter is empty or null
	 */
	protected boolean isEmptyChecker(String value) {
		return value == null || value.length() == 0 || value.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// System.out.println(this.title);

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		// System.out.println(other.title);
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}