package br.com.floricultura.modelo.servicos;

import java.util.List;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Categoria;

/**
 * Classe responsável por fazer o processamento da entidade Categoria, no caso,
 * disponibilizará serviços de interação com o banco de dados, chamando os
 * métodos que estão no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoCaretogia {

	/** atributo responsável por disponiblizar as interações com o banco de dados. */
	private DaoGenerico<Categoria> categoriaDao = DaoFactory.criarCategoriaDao();

	public List<Categoria> buscarTodos() {
		return categoriaDao.buscarTudo();
	}

	public void SalvarOuAtualizar(Categoria obj) {

		if (obj.getIdCategoria() == null) {
			categoriaDao.inserir(obj);

		} else {
			categoriaDao.atualizar(obj);
		}
	}

	public void remover(Categoria obj) {
		categoriaDao.deletarPorId(obj.getIdCategoria());
	}
}
