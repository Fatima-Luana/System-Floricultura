package br.com.floricultura.modelo.servicos;

import java.util.List;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Usuario;

/**
 * Classe responsável por fazer o processamento da entidade Usuario, no caso,
 * disponibilizará serviços de interação com o banco de dados, chamando os
 * métodos que estão no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoUsuario {

	/** atributo responsável por disponiblizar as interações com o banco de dados. */
	private DaoGenerico<Usuario> usuarioDao = DaoFactory.criarUsuarioDao();

	public List<Usuario> buscarTodos() {
		return usuarioDao.buscarTudo();
	}

	public void SalvarOuAtualizar(Usuario obj) {

		if (obj.getIdUsuario() == null) {
			usuarioDao.inserir(obj);

		} else {
			usuarioDao.atualizar(obj);
		}
	}

	public void remover(Usuario obj) {
		usuarioDao.deletarPorId(obj.getIdUsuario());
	}

}
