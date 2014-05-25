package catchball;

import java.awt.Point;

public abstract class Agente {
	protected Point posicao;

	public Agente(int x, int y) {
		this.posicao = new Point(x, y);
	}

	public boolean colide(Agente agente) {
		Retangulo r1 = this.getBoundingBox();
		Retangulo r2 = agente.getBoundingBox();
		return r1.colide(r2);
	}

	public Point getPosicao() {
		return posicao;
	}

	public void moverHorizontalmente(int x) {
		posicao.x = x;
	}

	public void deslocarVerticalmente(int deslocamento) {
		posicao.y += deslocamento;
	}
	
	public abstract Retangulo getBoundingBox();
}