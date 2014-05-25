package puzzle;

public class User implements Comparable<User> {

	private String name;
	private Time time;
	private int moves;

	public User() {
		this("", new Time(0));
	}

	public User(String name) {
		this(name, new Time(0));
	}

	public User(String name, Time time) {
		setName(name);
		this.time = time;
		this.moves = 0;
	}

	public int getMoves() {
		return moves;
	}

	public void incrementMoves() {
		this.moves++;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Time getTime() {
		return this.time;
	}

	public void setName(String name) {
		this.name = (name == null || name == "") ? "Unknown" : name;
	}

	public String getName() {
		return this.name;
	}

	public int compareTo(User user) {
		if (this.getTime().compareTo(user.getTime()) == 0)
			if (this.getMoves() - user.getMoves() == 0)
				return this.getName().compareTo(user.getName());
			else
				return this.getMoves() - user.getMoves();
		else
			return this.getTime().compareTo(user.getTime());
	}

	public String toString() {
		return String.format("%s  (%d)  ( %s )", name, moves, time);
	}

}