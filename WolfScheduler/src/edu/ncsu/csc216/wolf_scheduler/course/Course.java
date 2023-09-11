package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Keeps track of course name (e.g., CSC 216), title (e.g., Software Development
 * Fundamentals), section (e.g., 001), credit hours (e.g., 4), instructor’s
 * unity id (e.g., sesmith5), meeting days (e.g., MW), start time (e.g., 1330),
 * and end time (e.g., 1445).
 * 
 * @author Kevin John
 */
public class Course {

	/**
	 * minimum name length described as a constant integer
	 */
	private static final int MIN_NAME_LENGTH = 5;

	/**
	 * max name length described as a constant integer
	 */
	private static final int MAX_NAME_LENGTH = 8;

	/**
	 * minimum letter count described as a constant integer
	 */
	private static final int MIN_LETTER_COUNT = 1;

	/**
	 * max letter count described as a constant integer
	 */
	private static final int MAX_LETTER_COUNT = 4;

	/**
	 * digit count described as a constant integer
	 */
	private static final int DIGIT_COUNT = 3;

	/**
	 * section length described as a constant integer
	 */
	private static final int SECTION_LENGTH = 3;

	/**
	 * max credits described as a constant integer
	 */
	private static final int MAX_CREDITS = 5;

	/**
	 * minimum credits count described as a constant integer
	 */
	private static final int MIN_CREDITS = 1;

	/**
	 * upper hour described as a constant integer
	 */
	private static final int UPPER_HOUR = 24;

	/**
	 * upper minute described as a constant integer
	 */
	private static final int UPPER_MINUTE = 60;

	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set's the Course's name.
	 * 
	 * @param name the name to set
	 */
	private void setName(String name) {
		// check if name is empty or null
		if (isEmptyChecker(name)) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		// check if the course name is less than or greater than the min/max length
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		// creates an array that splits the prefix and numeric representation
		// into 2 objects
		String[] parts = name.split(" ");

		// checks if there is two objects that represent prefix and number
		if (parts.length != 2) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		// assigns the two parts into individual prefix and number strings
		String prefix = parts[0];
		String number = parts[1];
		// checks if the number part of the course name is equal to the required length
		if (number.length() != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		// checks if the prefix part satisfies the min/max length requirement
		if (number.length() < MIN_LETTER_COUNT || number.length() > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		// checks for illegal characters in the prefix
		for (int i = 0; i < prefix.length(); i++) {
			if (!Character.isLetterOrDigit(prefix.charAt(i))) {
				throw new IllegalArgumentException("Invalid course name.");
			}
		}
		// checks for illegal characters in the number part
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isLetterOrDigit(number.charAt(i))) {
				throw new IllegalArgumentException("Invalid course name.");
			}
		}
		// checks if the course name contains the required space
		if (!name.contains(" ")) {
			throw new IllegalArgumentException("Invalid course name.");
		}

//		boolean illegalChar = false;
//		for (int i = 0; i < name.length(); i++) {
//			if (Character.isLetterOrDigit(name.charAt(i)) ) {
//				illegalChar = true;
//			}
//			if (illegalChar) {
//				throw new IllegalArgumentException("Invalid course name.");
//			}
//			
//		}

		// if all tests pass, set the variable
		this.name = name;
	}

	/**
	 * Gets the Course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		// checks for equality of title
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		// checks empty or null object
		if (isEmptyChecker(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Gets the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section
	 * 
	 * @param section the section to set
	 */
	public void setSection(String section) {
		// checks for empty or null section string
		if (isEmptyChecker(section)) {
			throw new IllegalArgumentException("Invalid section.");
		}
		// checks if section string is as defined in SECTION_LENGTH
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		// checks if any of section's three characters are not digits
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		// if flow control not transfered, section gets set
		this.section = section;
	}

	/**
	 * Gets the Course's credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * 
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		// checks if the credits variable is empty or null
		if (isEmptyChecker("" + credits)) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		// if flow control is not transfered, credits is set
		this.credits = credits;
	}

	/**
	 * Gets the Course's instructor ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor ID
	 * 
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		// checks if instructorID is empty or null
		if (isEmptyChecker(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		// if flow control is not transfered, instructorId is set
		this.instructorId = instructorId;
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
	 * Sets the Course's meeting days. Initializes meetingDays startTime and endTime
	 * based on common requirements
	 * 
	 * @param meetingDays the meetingDays to set
	 * @param startTime   int storing the start time
	 * @param endTime     int storing the end time
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
			int letterCountM = 0, letterCountT = 0, letterCountW = 0, letterCountH = 0, letterCountF = 0;

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
				if (!('M' == meetingDays.charAt(i)) && !('T' == meetingDays.charAt(i))
						&& !('W' == meetingDays.charAt(i)) && !('H' == meetingDays.charAt(i))
						&& !('F' == meetingDays.charAt(i))) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			if (letterCountM > 1 || letterCountT > 1 || letterCountW > 1 || letterCountH > 1 || letterCountF > 1) {
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
	private boolean isEmptyChecker(String value) {
		return value == null || value.length() == 0 || value.isEmpty();
	}

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		setName(name);
		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Generates a hashCode for Course using all fields
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
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

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(meetingDays)) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
				+ startTime + "," + endTime;
	}

}
