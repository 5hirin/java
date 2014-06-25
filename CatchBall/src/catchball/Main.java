package catchball;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import catchball.Bolinha.COR;
import processing.core.PApplet;
import processing.core.PFont;

public class Main extends PApplet {
	private static final long serialVersionUID = 7260261092185247001L;
	private final int LARGURA = 200;
	private final int ALTURA = 200;
	private final int LARGURA_BASE = 20;
	private final int ALTURA_BASE = 5;
	private final int RAIO = 5;
	private final boolean BOT = true;
	private double tempoLimite = 30;// em segundos
	private int pontos, bolasPegadas, totalBolas;

	Usuario jogador;
	Base base;
	Random random;
	ArrayList<Bolinha> bolinhas;
	PFont f;
	GerenteUsuario gerente;

	public static void main(String args[]) {
		PApplet.main(new String[] {});
	}

	public void setup() {
		jogador = new Usuario(JOptionPane.showInputDialog(null, "Nome:",
				"Escreva seu nome", JOptionPane.QUESTION_MESSAGE));
		jogador.tipoDeJogador(!BOT);
		background(200);
		size(LARGURA, ALTURA);
		random = new Random();
		bolinhas = new ArrayList<Bolinha>();
		f = loadFont("CourierNew36.vlw");
		textFont(f, 12);
		gerente = new GerenteUsuario();
		base = new Base(ALTURA_BASE, LARGURA_BASE, LARGURA / 2 - LARGURA_BASE / 2,
				ALTURA - (ALTURA_BASE + 2));
		pontos = 0;
		bolasPegadas = 0;
		totalBolas = 0;
		frameRate(60);// o m�todo draw ser� chamado 60 vezes por segundo
	}

	public void draw() {
		criarBolinhas();
		tempoLimite -= (double) 1 / frameRate;
		verificarFim();

		background(200);

		fill(255, 255, 255);
		rect(base.getPosicao().x, base.getPosicao().y, base.getLargura(), base
				.getAltura());
		fill(0, 0, 0);

		text("Pontos: " + pontos, 5, 10);
		text(String.format("Tempo: %.2f", tempoLimite), 5, 20);

		for (Bolinha b : bolinhas) {
			b.deslocarVerticalmente(1);
		}

		for (Bolinha b : bolinhas) {
			switch (b.getCor()) {
			case RED:
				fill(255, 0, 0);
				break;
			case GREEN:
				fill(0, 255, 0);
				break;
			case BLUE:
				fill(0, 0, 255);
				break;
			}
			ellipse(b.getPosicao().x, b.getPosicao().y, RAIO * 2, RAIO * 2);
			fill(0, 0, 0);
		}

		if (!bolinhas.isEmpty())
			if (jogador.eBot())
				base.moverHorizontalmente(jogador.modoBot(bolinhas.get(0)));
			else
				base.moverHorizontalmente(mouseX);

		for (int i = bolinhas.size() - 1; i >= 0; i--) {
			Bolinha b = bolinhas.get(i);
			if (base.colide(b)) {
				bolasPegadas++;
				switch (b.getCor()) {
				case RED:
					pontos += 1;
					break;
				case GREEN:
					pontos += 2;
					break;
				case BLUE:
					pontos += 3;
					break;
				}
				bolinhas.remove(b);
			}
			if (b.getPosicao().y > ALTURA)
				bolinhas.remove(i);
		}

	}

	private void verificarFim() {
		if (tempoLimite <= 0.00) {
			for (int i = bolinhas.size() - 1; i >= 0; i--) {
				if (bolinhas.get(i).getPosicao().y < ALTURA) {
					bolinhas.remove(i);
					totalBolas--;
				}
			}

			jogador.setPoints(pontos);
			jogador.setAproveitamento(((double) bolasPegadas / totalBolas) * 100);
			String msg = String.format("O jogo acabou, voc� fez %d pontos. (%.2f%s)",
					jogador.getPoints(), jogador.getAproveitamento(), "%");
			JOptionPane.showMessageDialog(null, msg);
			gerente.start(jogador);

			System.exit(1);
		}
	}

	private void criarBolinhas() {
		if (random.nextInt(30) == 1) {
			COR umaCor = COR.RED;

			switch (random.nextInt(3)) {
			case 0:
				umaCor = COR.RED;
				break;
			case 1:
				umaCor = COR.GREEN;
				break;
			case 2:
				umaCor = COR.BLUE;
				break;
			}
			totalBolas++;
			bolinhas.add(new Bolinha(5, random.nextInt(LARGURA), 5, umaCor));
		}
	}
}