package catchball;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GerenteUsuario {
	private ArrayList<Usuario> users;
	private Logger log;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Usuario u;
	private final String arquivo = "ranking.ser";
	private final int NUM_JOGADORES = 5;

	public GerenteUsuario() {
		users = new ArrayList<Usuario>();
		log = Logger.getLogger("Catch the ball");
		u = new Usuario();
	}

	public void start(Usuario jogador) {
		if (arquivoExiste())
			lerUsuarios();
		adicionarUsuario(jogador);
		gravarUsuarios();
		lerUsuarios();
		mostrarRanking();
		fecharArquivo();
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		users = usuarios;
	}

	public ArrayList<Usuario> getUsuarios() {
		return users;
	}

	private void adicionarUsuario(Usuario u) {
		users.add(u);
	}

	private boolean arquivoExiste() {
		try {
			new ObjectInputStream(new FileInputStream(arquivo));
		}
		catch (FileNotFoundException e) {
			log.info("Exceção capturada, o arquivo não existe, retornando falso.");
			return false;
		}
		catch (IOException e) {
		}

		return true;
	}

	private void lerUsuarios()// ler do arquivo ranking.ser
	{
		users = new ArrayList<Usuario>();
		try {
			input = new ObjectInputStream(new FileInputStream(arquivo));
		}
		catch (IOException e) {
			log.warning("O arquivo " + arquivo + " não existe.");
		}

		try {
			while (true) {
				u = (Usuario) input.readObject();
				this.users.add(u);
			}
		}
		catch (EOFException e) {
			log.info("Fim do arquivo, todos os objetos lidos.");
			return;
		}
		catch (ClassNotFoundException e) {
			log
					.warning("A classe do objeto especificado não foi encontrada no arquivo.");
		}
		catch (IOException e) {
			log.warning("Erro ao tentar ler os objetos dos arquivos.");
		}
	}

	private void gravarUsuarios()// gravar no arquivo
	{
		try {
			output = new ObjectOutputStream(new FileOutputStream(arquivo));
		}
		catch (IOException e) {
			log.warning("Erro ao tentar gravar no arquivo.");
		}

		try {
			for (Usuario user : users)
				output.writeObject(user);
		}
		catch (IOException e) {
			log.warning("Não foi possível escrever objetos no arquivo.");
		}
	}

	private void fecharArquivo() {
		try {
			if (output != null)
				output.close();
		}
		catch (IOException e) {
			log.warning("O fluxo de saída de dados não pôde ser fechado.");
		}

		try {
			if (input != null)
				input.close();
		}
		catch (IOException e) {
			log.warning("O fluxo de entrada de dados não pôde ser fechado.");
		}

	}

	private void mostrarRanking() {
		Collections.sort(users);
		Collections.reverse(users);
		String saida = "";

		for (int i = 0; i < NUM_JOGADORES; i++) {
			if (i == users.size())
				break;
			saida += String.format("%d. %s\n\n", i + 1, users.get(i));
		}

		JOptionPane.showMessageDialog(null, saida, "Ranking dos 5 melhores",
				JOptionPane.INFORMATION_MESSAGE);
	}
}