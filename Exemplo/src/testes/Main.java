package testes;

import java.util.ArrayList;

public class Main {
	public static void main(String... args) {
		Usuario u1 = new Usuario("João Silva", 100);;
		Usuario u2 = new Usuario("Guilherme Gobbi", 50);
		Usuario u3 = new Usuario("Mauro Andrade", 30);
		Usuario u4 = new Usuario("Maria Joaquina", 200);
		Usuario u5 = new Usuario("Mario Götze", 150);
		Usuario u6 = new Usuario("John Graham", 88);
		Usuario u7 = new Usuario("Mary Kennedy", 97);
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		usuarios.add(u4);
		usuarios.add(u5);
		usuarios.add(u6);
		usuarios.add(u7);
		
		/*
		usuarios.forEach(new Consumer<Usuario>(){
			@Override
			public void accept(Usuario u) {
				System.out.println(u);				
			}			
		});
		*/
		//Alternativa quando a classe tiver apenas um método abstrato
		//Consumer<Usuario> mostrador = (Usuario u) -> {System.out.println(u);};
		//Consumer<Usuario> mostrador = u -> System.out.println(u);
		
		usuarios.forEach(u -> System.out.println(u));
		/*usuarios.forEach(u -> {
			if (u.getNome().matches("M(.*)"))
				u.setModerador(true);			
		});*/
		usuarios.forEach(Usuario::tornaModerador);

		System.out.println();
		System.out.println();
		//System.out.println("abc".matches("A(.*)"));
		
		usuarios.sort((a,b) -> {return a.getPontos() - b.getPontos();});
		//usuarios.sort((a,b) -> a.getNome().compareTo(b.getNome()));
		usuarios.forEach(System.out::println);
	}
}

class Usuario{
	private String nome;
	private int pontos;
	private boolean moderador;
	
	public Usuario(String nome, int pontos){
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = false;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public boolean isModerador() {
		return moderador;
	}
	public void setModerador(boolean moderador) {
		this.moderador = moderador;
	}
	
	public void tornaModerador(){
		this.moderador = true;
	}
	
	public String toString(){
		if (moderador)
			return "Usuário: " + nome.toUpperCase() +". Pontos: " + pontos;
		else
			return "Usuário: " + nome +". Pontos: " + pontos;
	}
	
}
