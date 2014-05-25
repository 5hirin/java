package puzzle;

import javax.swing.JOptionPane;

import processing.core.*;

public class Puzzle extends PApplet implements MyConstants {

	private static final long serialVersionUID = 7712260699362742437L;

	private Board board;

	public static void main(String[] args) {
		PApplet.main(new String[] { "puzzle.Puzzle" });
	}

	public void setup() {

		Element.setPuzzle(this);

		PImage image = loadImage(IMAGE_PATH);
		String input = null;

		do {
			input = JOptionPane.showInputDialog(null, MSG[0], MSG[1], JOptionPane.QUESTION_MESSAGE);
			if (input != null)
				image = loadImage(input);
			else
				break;
		}
		while (image == null || image.width == -1 || image.height == -1);

		input = JOptionPane.showInputDialog(null, MSG[2], MSG[3], JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(null, INSTRUCTIONS);

		User player = new User(input, new Time(millis()));

		board = new Board(image, player);

		frame.setTitle(TITLE);
		frame.setResizable(false);

		frameRate(FPS);
	}

	public void draw() {
		try {
			board.move();
		}
		catch (Element.EndOfGame endOfGame) {
			endOfGame.showStatus(millis());
			exit();
		}
		finally {
			board.draw();
		}
	}

}
