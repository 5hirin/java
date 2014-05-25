package catchball;

import java.awt.Point;

public class Retangulo {
	int largura;
	int altura;
	Point cantoSuperiorEsquerdo;

	public Retangulo(int largura, int altura, Point cantoSuperiorEsquerdo) {
		super();
		this.largura = largura;
		this.altura = altura;
		this.cantoSuperiorEsquerdo = cantoSuperiorEsquerdo;
	}

	public boolean contains(Point p) {
		boolean horizontal = false, vertical = false;

		if (p.x >= this.cantoSuperiorEsquerdo.x
				&& p.x <= this.cantoSuperiorEsquerdo.x + this.largura)
			horizontal = true;

		if (p.y >= this.cantoSuperiorEsquerdo.y
				&& p.y <= this.cantoSuperiorEsquerdo.y + this.altura)
			vertical = true;

		return horizontal && vertical;
	}

	public boolean colide(Retangulo outro) {
		boolean haColisao = false;

		haColisao = haColisao || this.contemAlgumCanto(outro);
		haColisao = haColisao || outro.contemAlgumCanto(this);

		return haColisao;
	}

	private boolean contemAlgumCanto(Retangulo outro) {
		boolean haColisao = false;

		Point cantoSuperiorDireito = new Point(outro.cantoSuperiorEsquerdo);
		cantoSuperiorDireito.x += outro.largura;

		Point cantoInferiorEsquerdo = new Point(outro.cantoSuperiorEsquerdo);
		cantoInferiorEsquerdo.y += outro.altura;

		Point cantoInferiorDireito = new Point(outro.cantoSuperiorEsquerdo);
		cantoInferiorDireito.x += outro.largura;
		cantoInferiorDireito.y += outro.altura;

		haColisao = haColisao || this.contains(outro.cantoSuperiorEsquerdo);
		haColisao = haColisao || this.contains(cantoSuperiorDireito);
		haColisao = haColisao || this.contains(cantoInferiorEsquerdo);
		haColisao = haColisao || this.contains(cantoInferiorDireito);

		return haColisao;
	}
}