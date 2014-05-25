package catchball;

public class Base extends Agente
{
	private int altura;
	private int largura;

	public Base(int altura, int largura, int x, int y)
	{
		super(x, y);
		this.largura = largura;
		this.altura = altura;
	}

	public int getAltura()
	{
		return altura;
	}

	public int getLargura()
	{
		return largura;
	}

	public void moverEsquerda()
	{
		posicao.x -= 5;
	}

	public void moverDireita()
	{
		posicao.x += 5;
	}
	
	// caixa q circunscreve o objeto
	public Retangulo getBoundingBox()
	{
		return new Retangulo(largura, altura, posicao);
	}


}