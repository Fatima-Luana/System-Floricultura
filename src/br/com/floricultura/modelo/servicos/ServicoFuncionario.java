package br.com.floricultura.modelo.servicos;

import java.util.List;

import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Funcionario;

/**
 * Classe responsável por fazer o processamento da entidade Funcionario, no caso,
 * disponibilizará serviços de interação com o banco de dados, chamando os
 * métodos que estão no DaoGenerico .
 * 
 * @author luana
 * @version 1.4
 */
public class ServicoFuncionario {

	/** atributo responsável por disponiblizar as interações com o banco de dados. */
	private DaoGenerico<Funcionario> funcionarioDao = DaoFactory.criarFuncinarioDao();

	public List<Funcionario> buscarTodos() {
		return funcionarioDao.buscarTudo();
	}

	public void SalvarOuAtualizar(Funcionario obj) {

		if (obj.getIdPessoa() == null) {
			funcionarioDao.inserir(obj);

		} else {
			funcionarioDao.atualizar(obj);
		}
	}

	public void remover(Funcionario obj) {
		funcionarioDao.deletarPorId(obj.getIdPessoa());
	}

	public void buscar(Integer id) {
		funcionarioDao.buscarPorId(id);
	}
}
