package puzzle;

import java.awt.Point;
import java.awt.Dimension;
import processing.core.PImage;

public class Piece extends Element {
	private static Board board;
	private static byte speedX = 0;
	private static byte speedX0 = 0;
	private static byte speedY = 0;
	private static byte speedY0 = 0;
	private static byte counter = 0;
	private byte id;
	private boolean hidden;

	public Piece(Point position, Dimension dimension, PImage image) {
		super(position, dimension, image);
		this.id = counter++;
		this.hidden = false;
	}

	private static byte getSpeedX() {
		if (speedX0 != 0) {
			byte speed = speedX0;
			speedX0 = 0;
			return speed;
		}
		else
			return Piece.speedX;
	}

	private static byte getSpeedY() {
		if (speedY0 != 0) {
			byte speed = speedY0;
			speedY0 = 0;
			return speed;
		}
		else
			return Piece.speedY;
	}

	public void setSpeed() {
		speedX = (byte) (dimension.width * SPEED);
		speedY = (byte) (dimension.height * SPEED);
		speedX0 = (byte) (dimension.width % speedX + SPACE);
		speedY0 = (byte) (dimension.height % speedY + SPACE);
	}

	public byte getId() {
		return id;
	}

	public boolean isHidden() {
		return hidden;
	}

	public boolean isNotHidden() {
		return !hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public static void setBoard(Board board) {
		Piece.board = board;
	}

	public static byte getNumberOfPieces() {
		return Piece.counter;
	}

	public void startMovement() {
		if (!board.isPieceMoving() && isNotHidden()) {
			board.destiny = new Point(position);
			setSpeed();
			board.setPieceIsMoving(true);
		}
	}

	public void finishMovement() {
		board.getPlayer().incrementMoves();
		board.direction = Direction.NONE;

		int index = board.getIndexOf(this);
		int hiddenIndex = board.getHiddenIndex();
		
		board.changeDestinyOf(hiddenIndex);

		board.swap(index, hiddenIndex);
		
		board.setPieceIsMoving(false);
		board.updateMovablePieces();
	}

	public void move() throws EndOfMovement {
		if (position.x == destiny.x && position.y == destiny.y)
			throw new EndOfMovement();

		position.x += direction.getXDirection() * getSpeedX();
		position.y += direction.getYDirection() * getSpeedY();
	}

	public void draw() {
		if (isNotHidden())
			puzzle.image(image, position.x, position.y);
	}

	public String toString() {
		String output = String.format("Piece #%d\nPosition: [%d, %d]", id,
				position.x, position.y);
		output += String.format(
				"\nIs movable to: %s [%d, %d]\nSize: %dpx X %dpx\nHidden: %b\n\n",
				direction, destiny.x, destiny.y, dimension.width, dimension.height,
				hidden);
		return output;
	}

}