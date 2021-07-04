package br.com.floricultura.modelo.dao;

import java.util.List;

/**
 * Interface responsável por definir as operações acesso a dados banco de dados,
 * bem como: inserir, atualizar deletar e buscar.
 * 
 * @author luana - UFERSA
 * @version 1.4
 * @param <T> - tipo qualquer que será passado como parametro.
 */
public interface DaoGenerico<T extends Object> {

	void inserir(T obj);

	void atualizar(T obj);

	void deletarPorId(Integer id);

	T buscarPorId(Integer id);

	List<T> buscarTudo();
}
