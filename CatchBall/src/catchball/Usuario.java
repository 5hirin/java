package catchball;

import java.io.Serializable;

public class Usuario implements Comparable<Usuario>, Serializable {
	private static final long serialVersionUID = 1L;
	private String nome;
	private int pontuacao;
	private double aproveitamento;
	private boolean bot;

	public Usuario() {
		this("", 0);
	}

	public Usuario(String nome) {
		this(nome, 0);
	}

	public Usuario(String nome, int pontuacao) {
		this.nome = nome;
		this.pontuacao = pontuacao;
	}

	public double getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(double aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public void setPoints(int p) {
		this.pontuacao = p;
	}

	public int getPoints() {
		return this.pontuacao;
	}

	public void tipoDeJogador(boolean b) {
		bot = b;
	}

	public boolean eBot() {
		return bot;
	}

	public void setNome(String nome) {
		if (nome == null)
			this.nome = "Sem nome";
		else
			this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public int modoBot(Bolinha b) {
		return b.getPosicao().x - 2 * b.getRaio();
	}

	public int compareTo(Usuario o) {
		if (this.pontuacao - o.pontuacao == 0)
			return (int) Math.floor(this.aproveitamento - o.aproveitamento);
		else
			return this.pontuacao - o.pontuacao;
	}

	public String toString() {
		return String.format("%s: %d pontos => %.2f%s", getNome(), getPoints(),
				getAproveitamento(), "%");
	}

}