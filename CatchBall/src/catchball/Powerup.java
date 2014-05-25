package catchball;

import java.awt.Point;

public class Powerup extends Agente {
	/*
	 * tipos de powerups: todos quadrados 1.dobra o tamanho da base 2.aumenta o
	 * numero de bolas na tela 3.diminui 10 pontos 4.dobra os pontos(raro)
	 * 5.aumenta o tempo
	 */
	public Powerup(int x, int y) {
		super(x, y);

	}

	public Retangulo getBoundingBox() {
		return new Retangulo(5, 5, new Point());
	}

}
