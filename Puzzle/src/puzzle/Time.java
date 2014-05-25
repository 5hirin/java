package puzzle;

public class Time implements Comparable<Time> {

	private int millis;
	private int hours;
	private int minutes;
	private int seconds;

	public Time() {
		this(0);
	}

	public Time(int millis) {
		setTime(millis);
	}

	public void setTime(int millis) {
		this.millis = millis;
		this.hours = (int) millis / (1000 * 60 * 60);
		this.minutes = (int) (millis % (1000 * 60 * 60)) / (1000 * 60);
		this.seconds = (int) ((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000;
	}

	public int getMillis() {
		return millis;
	}

	public int compareTo(Time time) {
		return this.getMillis() - time.getMillis();
	}

	public String toString() {
		String output = "";
		output += hours > 0 ? hours + "h " : "";
		output += minutes > 0 ? minutes + "m " : "";
		output += seconds > 0 ? seconds + "s " : "";
		return output;
	}

}