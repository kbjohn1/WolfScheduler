package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Keeps track of course name (e.g., CSC 216), title (e.g., Software Development
 * Fundamentals), section (e.g., 001), credit hours (e.g., 4), instructor’s
 * unity id (e.g., sesmith5), meeting days (e.g., MW), start time (e.g., 1330),
 * and end time (e.g., 1445).
 * 
 * @author Kevin John
 */
public class Course extends Activity {

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

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course, following specific validation rules.
	 *
	 * @param name The name of the course to be set. It should consist of two parts
	 *             separated by a space: a prefix and a numeric representation. -
	 *             The prefix must contain only letters and digits. - The numeric
	 *             representation must contain exactly DIGIT_COUNT digits. - The
	 *             combined length of the prefix and numeric representation must be
	 *             within the range of MIN_NAME_LENGTH and MAX_NAME_LENGTH
	 *             (inclusive).
	 *
	 * @throws IllegalArgumentException If the provided name does not meet the
	 *                                  validation criteria: - It is empty or null.
	 *                                  - It does not contain two parts separated by
	 *                                  a space. - The prefix or numeric
	 *                                  representation contains illegal characters.
	 *                                  - The length of the prefix or numeric
	 *                                  representation is outside the specified
	 *                                  bounds. - The name does not contain a space
	 *                                  separating the two parts.
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

		// if all tests pass, set the variable
		this.name = name;
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
	 * Sets the section of the course, following specific validation rules.
	 *
	 * @param section The section of the course to be set. It should consist of
	 *                exactly SECTION_LENGTH digits.
	 *
	 * @throws IllegalArgumentException If the provided section does not meet the
	 *                                  validation criteria: - It is empty or null.
	 *                                  - It does not contain exactly SECTION_LENGTH
	 *                                  digits. - It contains non-digit characters.
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
	 * Sets the number of credits associated with the course, following specific
	 * validation rules.
	 *
	 * @param credits The number of credits to be set. It should be within the
	 *                allowable range defined by MIN_CREDITS and MAX_CREDITS
	 *                (inclusive).
	 *
	 * @throws IllegalArgumentException If the provided credits do not meet the
	 *                                  validation criteria: - It is empty or null.
	 *                                  - It falls outside the allowable range
	 *                                  specified by MIN_CREDITS and MAX_CREDITS.
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
	 * Sets the instructor's ID associated with the course.
	 *
	 * @param instructorId The instructor's ID to be set. It should not be empty or
	 *                     null.
	 *
	 * @throws IllegalArgumentException If the provided instructorId is empty or
	 *                                  null.
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
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
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
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * returns an array of length 4 containing the Course name, section, title, and
	 * meeting string.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = { getName(), getSection(), getTitle(), getMeetingString() };
		return shortDisplayArray;
	}

	/**
	 * returns an array of length 7 containing the Course name, section, title,
	 * credits, instructorId, meeting string, empty string (for a field that Event
	 * will have that Course does not).
	 * 
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = { getName(), getSection(), getTitle(), getCredits() + "", getInstructorId(),
				getMeetingString(), "" };
		return longDisplayArray;
	}

	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		for (int i = 0; i < meetingDays.length(); i++) {
			char letter = meetingDays.charAt(i);
			if (!(letter == 'M' || letter == 'T' || letter == 'W' || letter == 'H' || letter == 'F'
					|| letter == 'A' && meetingDays.length() == 1)) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * checks for duplicate courses
	 * 
	 * @param activity - activity superclass
	 * @return true if a duplicate is found
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			if (course.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
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
		return true;
	}

}
