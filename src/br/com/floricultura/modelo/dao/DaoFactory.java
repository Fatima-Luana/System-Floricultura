package br.com.floricultura.modelo.dao;

import br.com.floricultura.conexao.BD;
import br.com.floricultura.modelo.dao.impl.CategoriaDaoJDBC;
import br.com.floricultura.modelo.dao.impl.ClienteDaoJDBC;
import br.com.floricultura.modelo.dao.impl.FuncionarioDaoJDB;
import br.com.floricultura.modelo.dao.impl.ItemVendaJDBC;
import br.com.floricultura.modelo.dao.impl.ProdutoDaoJDBC;
import br.com.floricultura.modelo.dao.impl.UsuarioDaoJDBC;
import br.com.floricultura.modelo.dao.impl.VendaDaoJDBC;
import br.com.floricultura.modelo.entidades.Categoria;
import br.com.floricultura.modelo.entidades.Cliente;
import br.com.floricultura.modelo.entidades.Funcionario;
import br.com.floricultura.modelo.entidades.ItemVenda;
import br.com.floricultura.modelo.entidades.Produto;
import br.com.floricultura.modelo.entidades.Usuario;

/**
 * Aplicação do padrão de projeto Factory que será responsável por fazer as
 * instanciações dos DAOS.
 * 
 * @author luana - UFERSA
 * @version 1.4
 *
 */
public class DaoFactory {

	public static DaoGenerico<Cliente> criarClienteDao() {
		return new ClienteDaoJDBC(BD.getConectando());
	}

	public static DaoGenerico<Categoria> criarCategoriaDao() {
		return new CategoriaDaoJDBC(BD.getConectando());
	}

	public static DaoGenerico<Funcionario> criarFuncinarioDao() {
		return new FuncionarioDaoJDB(BD.getConectando());
	}

	public static VendaDao criarVendaDao() {
		return new VendaDaoJDBC(BD.getConectando());
	}

	public static DaoGenerico<ItemVenda> criarItemVendaDao() {
		return new ItemVendaJDBC(BD.getConectando());
	}

	public static DaoGenerico<Produto> criarProdutoDao() {
		return new ProdutoDaoJDBC(BD.getConectando());
	}

	public static DaoGenerico<Usuario> criarUsuarioDao() {
		return new UsuarioDaoJDBC(BD.getConectando());
	}
}
