package br.com.floricultura.modelo.servicos;

import java.util.List;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Produto;
import br.com.floricultura.modelo.entidades.Venda;

/**
 * Classe respons�vel por fazer o processamento da entidade Produto, no caso,
 * disponibilizar� servi�os de intera��o com o banco de dados, chamando os
 * m�todos que est�o no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoProduto {

	private DaoGenerico<Produto> produtoDao = DaoFactory.criarProdutoDao();

	public List<Produto> buscarTodos() {
		return produtoDao.buscarTudo();
	}

	public void SalvarOuAtualizar(Produto obj) {

		if (obj.getCdProduto() == null) {
			produtoDao.inserir(obj);

		} else {
			produtoDao.atualizar(obj);
		}
	}

	public void remover(Venda obj) {
		produtoDao.deletarPorId(obj.getIdVenda());
	}
	
	public void removerProduto(Produto obj) {
		produtoDao.deletarPorId(obj.getCdProduto());
	}
}
