package br.com.floricultura.modelo.servicos;

import java.util.List;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Categoria;

/**
 * Classe respons�vel por fazer o processamento da entidade Categoria, no caso,
 * disponibilizar� servi�os de intera��o com o banco de dados, chamando os
 * m�todos que est�o no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoCaretogia {

	/** atributo respons�vel por disponiblizar as intera��es com o banco de dados. */
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
