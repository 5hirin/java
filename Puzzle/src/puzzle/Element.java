package puzzle;

import java.awt.Dimension;
import java.awt.Point;

import processing.core.PConstants;
import processing.core.PImage;
import javax.swing.JOptionPane;
public abstract class Element implements PConstants, MyConstants {
	protected static Puzzle puzzle;
	protected PImage image;
	protected Dimension dimension;
	protected Point position;
	protected Point destiny;
	protected Direction direction;

	protected Element(PImage image) {
		this(START_POSITION, null, image);
	}

	protected Element(Point position, Dimension dimension, PImage image) {
		this.position = position;
		this.dimension = dimension;
		this.image = image;
		this.direction = Direction.NONE;
		destiny = new Point();
	}

	public static void setPuzzle(Puzzle puzzle) {
		Element.puzzle = puzzle;
	}

	protected final boolean isCursorOver() {
		return (puzzle.mouseX >= position.x && puzzle.mouseX <= position.x + dimension.width
				&& puzzle.mouseY >= position.y && puzzle.mouseY <= position.y + dimension.height);
	}

	public abstract void move() throws Exception;

	public abstract void draw();
	
	public static class EndOfMovement extends Exception {
		private static final long serialVersionUID = 1L;
	}
	

	public static class EndOfGame extends Exception {
		private static final long serialVersionUID = 5416623080891674646L;

		private Board board;

		public EndOfGame(Board board) {
			this.board = board;
		}

		public void showStatus(int endTimeMillis) {
			Time end = new Time(endTimeMillis - board.getPlayer().getTime().getMillis());
			board.getPlayer().setTime(end);
			JOptionPane.showMessageDialog(null, this);
		}

		public String toString() {
			return String.format("Game over %s !\nYou finished in %s, with %d moves",
					board.getPlayer().getName(), board.getPlayer().getTime(), board
							.getPlayer().getMoves());
		}
	}
	
}
