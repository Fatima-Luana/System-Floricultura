package br.com.floricultura.modelo.servicos;

import java.util.List;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.VendaDao;
import br.com.floricultura.modelo.entidades.Venda;

/**
 * Classe respons�vel por fazer o processamento da entidade Venda, no caso,
 * disponibilizar� servi�os de intera��o com o banco de dados, chamando os
 * m�todos que est�o no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoVenda {

	/** atributo respons�vel por disponiblizar as intera��es com o banco de dados. */
	private VendaDao vendaDao = DaoFactory.criarVendaDao();

	public List<Venda> buscarTodos() {
		return vendaDao.buscarTudo();
	}

	public Venda buscarUltimaVenda() {
		return vendaDao.buscarUltimaVenda();
	}

	public void SalvarOuAtualizar(Venda obj) {
		if (obj.getIdVenda() == null) {
			vendaDao.inserir(obj);
		} else {
			vendaDao.atualizar(obj);
		}
	}
	
	public void remover(Venda obj) {
		vendaDao.deletarPorId(obj.getIdVenda());
	}
}
