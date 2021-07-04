package br.com.floricultura.modelo.servicos;

import java.util.List;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Cliente;

/**
 * Classe responsável por fazer o processamento da entidade Cliente, no caso,
 * disponibilizará serviços de interação com o banco de dados, chamando os
 * métodos que estão no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoCliente {

	/** atributo responsável por disponiblizar as interações com o banco de dados. */
	private DaoGenerico<Cliente> categoriaDao = DaoFactory.criarClienteDao();

	public List<Cliente> buscarTodos() {
		return categoriaDao.buscarTudo();
	}

	public void SalvarOuAtualizar(Cliente obj) {

		if (obj.getIdPessoa() == null) {
			categoriaDao.inserir(obj);

		} else {
			categoriaDao.atualizar(obj);
		}
	}

	public void remover(Cliente obj) {
		categoriaDao.deletarPorId(obj.getIdPessoa());
	}

	public void buscar(Integer id) {
		categoriaDao.buscarPorId(id);
	}

}
