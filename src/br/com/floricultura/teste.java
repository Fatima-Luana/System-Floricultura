package br.com.floricultura;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Usuario;

public class teste {

	public static void main(String[] args) {
		
		DaoGenerico<Usuario> dao = DaoFactory.criarUsuarioDao();

		dao.deletarPorId(1);
	}
}
