package br.com.floricultura.conexao;

public class ErroIntegridade extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ErroIntegridade(String msg) {
		super(msg);
	}
}
