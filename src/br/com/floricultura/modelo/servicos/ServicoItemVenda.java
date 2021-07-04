package br.com.floricultura.modelo.servicos;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.ItemVenda;

/**
 * Classe responsável por fazer o processamento da entidade ItemVenda, no caso,
 * disponibilizará serviços de interação com o banco de dados, chamando os
 * métodos que estão no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoItemVenda {
	
	/** atributo responsável por disponiblizar as interações com o banco de dados. */
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
