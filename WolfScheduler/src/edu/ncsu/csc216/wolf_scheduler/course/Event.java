package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * takes most of the aspects of the activity superclass and adds event details
 * to it
 */
public class Event extends Activity {

	/** String containing the event details of a custom event */
	private String eventDetails;

	/**
	 * Event constructor extending the activity superclass
	 * 
	 * @param title        - course title
	 * @param meetingDays  - course meeting days
	 * @param startTime    - course start time
	 * @param endTime      - course end time
	 * @param eventDetails - custom event details (optional)
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Sets the event details for the course or an event.
	 *
	 * @param eventDetails The event details to be set. It should not be empty or
	 *                     null.
	 *
	 * @throws IllegalArgumentException If the provided eventDetails is empty or
	 *                                  null.
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * returns the event details when called
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * overrides the getShortDisplayArray method from the activity superclass. only
	 * shows title and meeting day/time since a custom event would not have a
	 * meeting day or time
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = { "", "", getTitle(), getMeetingString() };
		return shortDisplayArray;
	}

	/**
	 * overrides the getLongDisplayArray method from the activity superclass. only
	 * shows title and meeting day/time since a custom event would not have a
	 * meeting day or time
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = { "", "", getTitle(), "", "", getMeetingString(), getEventDetails() };
		return longDisplayArray;
	}

	/**
	 * override toString to match the format in requirements
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return getTitle() + "," + getMeetingDays() + "," + getEventDetails();
		}
		return "" + getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + ","
				+ getEventDetails();
	}

	/**
	 * overrides this method to allow for weekends but not any meeting days with A
	 * 
	 * @throws IllegalArgumentException if meeting days and times is invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if ("A".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Sets the title for the course or an event.
	 *
	 * @param title The title to be set. It should not be empty or null.
	 *
	 * @throws IllegalArgumentException If the provided title is empty or null.
	 */

	public void setTitle(String title) {
		// check if name is empty or null
		if (isEmptyChecker(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}

		super.setTitle(title);
	}

	/**
	 * checks for duplicate events
	 * 
	 * @param activity - activity superclass
	 * @return true if a duplicate is found
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event event = (Event) activity;
			if (event.getTitle().equals(getTitle())) {
				return true;
			}
		}
		return false;
	}

}
