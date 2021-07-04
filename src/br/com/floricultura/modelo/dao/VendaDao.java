package br.com.floricultura.modelo.dao;

import br.com.floricultura.modelo.entidades.Venda;

/**
 * Interface respons�vel por definir as opera��es de acesso a dados banco de
 * dados, bem como: inserir, atualizar deletar e buscar, que s�o m�todos
 * herdados de DaoGenerico. Al�m disso, define a opera��o de buscar ultima
 * venda.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public interface VendaDao extends DaoGenerico<Venda> {

	Venda buscarUltimaVenda();
}
