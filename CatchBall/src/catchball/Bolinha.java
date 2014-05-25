package catchball;

import java.awt.Point;

public class Bolinha extends Agente
{
	private int raio;
	private COR cor;
	public enum COR {RED, GREEN, BLUE};
	
	public Bolinha(int raio, int x, int y, COR cor)
	{
		super(x, y);
		this.raio = raio;
		this.cor = cor;
	}
	
	public int getRaio()
	{
		return raio;
	}

	public void setRaio(int raio)
	{
		this.raio = raio;
	}
	
	public void setPosition(Point p)
	{
		this.posicao = p;
	}

	public Point getPosicao()
	{
		return posicao;
	}

	public COR getCor ()
	{
		return cor;
	}
	
	// caixa q circunscreve o objeto
	public Retangulo getBoundingBox()
	{
		return new Retangulo(2 * raio, 2 * raio, new Point(posicao.x - raio, posicao.y - raio));
	}
}