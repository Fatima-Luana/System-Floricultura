package br.com.floricultura.modelo.dao;

import br.com.floricultura.modelo.entidades.Venda;

/**
 * Interface responsável por definir as operações de acesso a dados banco de
 * dados, bem como: inserir, atualizar deletar e buscar, que são métodos
 * herdados de DaoGenerico. Além disso, define a operação de buscar ultima
 * venda.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public interface VendaDao extends DaoGenerico<Venda> {

	Venda buscarUltimaVenda();
}
