package puzzle;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public enum Direction {
	UP(PI), RIGHT(2 * PI), DOWN(-2 * PI), LEFT(-PI), NONE(0);

	private int direction;
	private double angle;

	private Direction(double angle) {
		this.angle = angle;
		direction = (int) cos(angle);
	}

	public int getDirection() {
		return direction;
	}

	public int getXDirection() {
		if (angle == PI || angle == -2 * PI)
			return 0;
		else
			return direction;

	}

	public int getYDirection() {
		if (angle == 2 * PI || angle == -PI)
			return 0;
		else
			return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String toString() {
		switch (this) {
			case UP:
				return "Up";
			case RIGHT:
				return "Right";
			case DOWN:
				return "Down";
			case LEFT:
				return "Left";
			default:
				return "None";
		}
	}

};
