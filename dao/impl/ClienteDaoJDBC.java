package br.com.floricultura.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.floricultura.conexao.BD;
import br.com.floricultura.conexao.BdExcecoes;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Cliente;

/**
 * Classe responsável por implementar a interface - DaoGenerico, no qual será
 * passado como paramêto um Cliente.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class ClienteDaoJDBC implements DaoGenerico<Cliente> {

	private Connection conn;

	public ClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método responsável por fazer a inserção de dados do objeto Cliente no banco
	 * de dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Cliente.
	 */
	@Override
	public void inserir(Cliente obj) {
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(
					"INSERT INTO cliente" + "(NOME, TELEFONE, CPF, EMAIL, ENDERECO, OBSERVACAO) "
							+ "VALUES" + "(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getTelefone());
			ps.setString(3, obj.getCpf());
			ps.setString(4, obj.getEmail());
			ps.setString(5, obj.getEnderco());
			ps.setString(6, obj.getObservacao());

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdPessoa(id);
				}
				BD.fecharResultSet(rs);
			} else {
				throw new BdExcecoes("Erro inesperado! Nenhuma linha afetada!");
			}
		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}

	}

	/**
	 * Método que recebe um obj do tipo Cliente como paramêto, responsável por
	 * atualizar esse objeto no banco de dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Cliente.
	 */
	@Override
	public void atualizar(Cliente obj) {
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("UPDATE cliente "
					+ "SET NOME = ?, TELEFONE = ?, CPF = ?, EMAIL = ?, ENDERECO = ?, OBSERVACAO = ? "
					+ "WHERE IDCLIENTE = ?");

			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getTelefone());
			ps.setString(3, obj.getCpf());
			ps.setString(4, obj.getEmail());
			ps.setString(5, obj.getEnderco());
			ps.setString(6, obj.getObservacao());
			ps.setInt(7, obj.getIdPessoa());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}
	}

	/**
	 * Método que recebe como paramêtro um inteiro de um tipo Wrapper Interger. Esse
	 * método é responsável por deletar um objeto Cliente pelo seu Id no banco de
	 * dados. Retorna vazio.
	 * 
	 * @param id - id da Categoria que deseja excluir.
	 */
	@Override
	public void deletarPorId(Integer id) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM cliente WHERE IDCLIENTE = ?");

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}

	}

	/**
	 * Método que recebe como paramêtro um inteiro de um tipo Wrapper Interger. Esse
	 * método é responsável por buscar um objeto Cliente pelo seu Id no banco de
	 * dados.
	 * 
	 * @param id - id da Categoria que deseja buscar.
	 * @return Cliente - retorna o Cliente referente ao id passado.
	 */
	@Override
	public Cliente buscarPorId(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM cliente WHERE IDCLIENTE = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdPessoa(rs.getInt("IDCLIENTE"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setEnderco(rs.getString("ENDERECO"));
				cliente.setObservacao(rs.getString("OBSERVACAO"));

				return cliente;
			}
			return null;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}

	/**
	 * Método responsável por buscar todos os objetos de Cliente cadastrados no
	 * banco de dados.
	 * 
	 * @return Lis<Cliente> - Lista com todas os clientes.
	 */
	@Override
	public List<Cliente> buscarTudo() {

		PreparedStatement ps = null;
		ResultSet rs;

		try {

			ps = conn.prepareStatement("SELECT * FROM cliente ORDER BY IDCLIENTE");

			rs = ps.executeQuery();

			List<Cliente> listaClientes = new ArrayList<>();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdPessoa(rs.getInt("IDCLIENTE"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setEnderco(rs.getString("ENDERECO"));
				cliente.setObservacao(rs.getString("OBSERVACAO"));

				listaClientes.add(cliente);
			}

			return listaClientes;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {

			BD.fecharStatement(ps);
		}

	}
}
