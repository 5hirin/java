package puzzle;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import processing.core.PImage;

public class Board extends Element {
	private ArrayList<Piece> pieces;
	private User player;
	private static byte amountOfPieces = NUMBER_OF_PIECES;
	private boolean pieceIsMoving;

	public Board(PImage image, User player) {
		super(image);
		pieces = initializePieces();
		dimension = calculateBoardDimension();
		pieceIsMoving = false;
		this.player = player;

		do {
			shufflePieces();
		}
		while (isGameOver());
		updateMovablePieces();

		Piece.setBoard(this);
		puzzle.size(dimension.width, dimension.height);
		draw();
	}

	public User getPlayer() {
		return player;
	}

	public void setPieceIsMoving(boolean pieceIsMoving) {
		this.pieceIsMoving = pieceIsMoving;
	}

	public boolean isPieceMoving() {
		return pieceIsMoving;
	}

	public int getIndexOf(Piece piece) {
		return pieces.indexOf(piece);
	}

	public void swap(int i, int j) {
		Collections.swap(pieces, i, j);
	}

	public void changeDestinyOf(int index) {
		pieces.get(index).position = destiny;
	}

	private ArrayList<Piece> initializePieces() {
		ArrayList<Piece> pieces = new ArrayList<Piece>();

		int sizeX = pieceDimensions().width;
		int sizeY = pieceDimensions().height;

		int xp, dx = position.x;
		int yp, dy = position.y;

		for (int i = 0, y = 0; i < amountOfPieces; i++, y += sizeY)
			for (int j = 0, x = 0; j < amountOfPieces; j++, x += sizeX) {
				xp = x + SPACE * (j + 1 + dx);
				yp = y + SPACE * (i + 1 + dy);

				Point point = new Point(xp, yp);
				Dimension dimension = new Dimension(sizeX, sizeY);
				PImage croppedImage = image.get(x, y, sizeX, sizeY);

				Piece newPiece = new Piece(point, dimension, croppedImage);

				pieces.add(newPiece);
			}

		pieces.get(pieces.size() - 1).setHidden(true);

		return pieces;
	}

	private Dimension calculateBoardDimension() {
		int x = 2 * position.x + SPACE + amountOfPieces * (SPACE + pieceDimensions().width);
		int y = 2 * position.y + SPACE + amountOfPieces * (SPACE + pieceDimensions().height);

		return new Dimension(x, y);
	}

	private Dimension pieceDimensions() {
		int x = (int) image.width / amountOfPieces;
		int y = (int) image.height / amountOfPieces;

		return new Dimension(x, y);
	}

	private void shufflePieces() {
		Random random = new Random();
		for (int i = 0, r; i < pieces.size(); i++) {
			r = random.nextInt(pieces.size());
			if (pieces.get(i).isNotHidden() && pieces.get(r).isNotHidden()) {
				Point temp = new Point(pieces.get(i).position);
				pieces.get(i).position = pieces.get(r).position;
				pieces.get(r).position = temp;

				Collections.swap(pieces, i, r);
			}
		}
	}

	public int getHiddenIndex() {
		for (Piece piece : pieces)
			if (piece.isHidden())
				return pieces.indexOf(piece);
		return -1;
	}

	public void updateMovablePieces() {
		for (Piece piece : pieces)
			piece.direction = Direction.NONE;

		for (int i = 0, line = 0; i < pieces.size(); i++) {
			if (i % amountOfPieces == 0)
				line++;
			if (pieces.get(i).isHidden()) {
				Point destinyPosition = pieces.get(i).position;
				if (i + 1 < line * amountOfPieces) {
					pieces.get(i + 1).direction = Direction.LEFT;
					pieces.get(i + 1).destiny = destinyPosition;
				}
				if (i - 1 >= (line - 1) * amountOfPieces) {
					pieces.get(i - 1).direction = Direction.RIGHT;
					pieces.get(i - 1).destiny = destinyPosition;
				}
				if (i - amountOfPieces >= 0) {
					pieces.get(i - amountOfPieces).direction = Direction.DOWN;
					pieces.get(i - amountOfPieces).destiny = destinyPosition;
				}
				if (i + amountOfPieces < amountOfPieces * amountOfPieces) {
					pieces.get(i + amountOfPieces).direction = Direction.UP;
					pieces.get(i + amountOfPieces).destiny = destinyPosition;
				}

			}
		}
	}

	private boolean isGameOver() {
		for (Piece piece : pieces)
			if (pieces.indexOf(piece) != piece.getId())
				return false;

		return true;
	}

	private boolean isThereAnEvent() {
		if (!pieceIsMoving) {
			if (puzzle.keyPressed) {
				if (puzzle.key == CODED) {
					switch (puzzle.keyCode) {
						case UP:
							direction = Direction.UP;
							break;

						case RIGHT:
							direction = Direction.RIGHT;
							break;

						case DOWN:
							direction = Direction.DOWN;
							break;

						case LEFT:
							direction = Direction.LEFT;
							break;

						default:
							direction = Direction.NONE;
							break;
					}

				}
				else {
					switch (puzzle.key) {
						case 'w':
						case 'W':
						case '8':
							direction = Direction.UP;
							break;
						case 'd':
						case 'D':
						case '6':
							direction = Direction.RIGHT;
							break;
						case 's':
						case 'S':
						case '2':
							direction = Direction.DOWN;
							break;
						case 'a':
						case 'A':
						case '4':
							direction = Direction.LEFT;
							break;
						default:
							direction = Direction.NONE;
							break;
					}
				}
			}
			else if (puzzle.mousePressed) {
				for (Piece piece : pieces)
					if (piece.isNotHidden() && piece.isCursorOver()) {
						direction = piece.direction;
						break;
					}
			}
			else
				direction = Direction.NONE;
		}

		return direction != Direction.NONE;

	}

	public void move() throws EndOfGame {
		if (isThereAnEvent()) {
			for (Piece piece : pieces) {
				if (direction != Direction.NONE && piece.direction == direction) {
					piece.startMovement();
					try {
						piece.move();
					}
					catch (EndOfMovement end) {
						piece.finishMovement();
						if (isGameOver())
							throw new EndOfGame(this);
					}
					break;
				}
			}
		}
	}

	public void draw() {
		puzzle.background(0);
		for (Piece piece : pieces)
			piece.draw();
	}

	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append("Number of pieces created: ");
		output.append(Piece.getNumberOfPieces());
		output.append("\n\n");

		for (Piece piece : pieces) {
			output.append("Position in the list: ");
			output.append(pieces.indexOf(piece));
			output.append("\n");
			output.append(piece.toString());
		}
		return output.toString();
	}

}
