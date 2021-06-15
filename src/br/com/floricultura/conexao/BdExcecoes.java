package br.com.floricultura.conexao;

public class BdExcecoes extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BdExcecoes(String msg) {
		super(msg);
	}
}
