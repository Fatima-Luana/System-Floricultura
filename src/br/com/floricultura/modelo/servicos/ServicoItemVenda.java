package br.com.floricultura.modelo.servicos;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.ItemVenda;

/**
 * Classe respons�vel por fazer o processamento da entidade ItemVenda, no caso,
 * disponibilizar� servi�os de intera��o com o banco de dados, chamando os
 * m�todos que est�o no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoItemVenda {
	
	/** atributo respons�vel por disponiblizar as intera��es com o banco de dados. */
	private DaoGenerico<ItemVenda> itemVendaDao = DaoFactory.criarItemVendaDao();

	public void atualizar(ItemVenda obj) {
		itemVendaDao.atualizar(obj);
	}
	
	public void inserir(ItemVenda obj) {
		itemVendaDao.inserir(obj);
	}
	
	public void remover(ItemVenda obj) {
		itemVendaDao.deletarPorId(obj.getIdItemDeVenda());
	}
}
