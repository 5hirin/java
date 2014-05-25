package puzzle;

import java.awt.Point;

public abstract interface MyConstants {
	public static final String MSG[] = {
			"Supported file extensions: jpg, tga, gif, png\nPath:",
			"Type the path or link of the image:", "Name:", "Write your name:" };
	public static final String IMAGE_PATH = "halo4.jpg";
	public static final String INSTRUCTIONS = "To move the pieces, use W, A, S, D or the arrows.\n\n"
			+ "You can use the mouse as well.";
	public static final String TITLE = "Puzzle!";

	public static final Point START_POSITION = new Point(0, 0);

	public static final byte NUMBER_OF_PIECES = 4;
	public static final byte FPS = 60;
	public static final byte SPACE = 1;
	public static final float SPEED = 0.05f;
}
