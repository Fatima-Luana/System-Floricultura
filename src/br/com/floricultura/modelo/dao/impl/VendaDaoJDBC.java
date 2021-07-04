package br.com.floricultura.modelo.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.floricultura.conexao.BD;
import br.com.floricultura.conexao.BdExcecoes;
import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.dao.VendaDao;
import br.com.floricultura.modelo.entidades.Cliente;
import br.com.floricultura.modelo.entidades.ItemVenda;
import br.com.floricultura.modelo.entidades.Venda;

/**
 * Classe responsável por implementar a interface - VendaDao.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class VendaDaoJDBC implements VendaDao {

	private Connection conn;

	public VendaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método responsável por fazer a inserção de dados do objeto Venda no banco de
	 * dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Venda.
	 */
	@Override
	public void inserir(Venda obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(
					"INSERT INTO venda" + "(VALOR, DATA_VENDA, ID_CLIENTE) " + "VALUES" + "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setDouble(1, obj.getValor());
			ps.setDate(2, Date.valueOf(obj.getDataVenda()));
			ps.setInt(3, obj.getCliente().getIdPessoa());

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdVenda(id);
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
	 * Método que recebe um obj do tipo Venda como paramêto, responsável por
	 * atualizar esse objeto no banco de dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Venda.
	 */
	@Override
	public void atualizar(Venda obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(
					"UPDATA venda" + "SET VALOR = ?, DATA_VENDA = ?, ID_CLIENTE = ? " + "WHERE CODIVENDA = ?");

			ps.setDouble(1, obj.getValor());
			ps.setDate(2, Date.valueOf(obj.getDataVenda()));
			ps.setInt(3, obj.getCliente().getIdPessoa());
			ps.setInt(4, obj.getIdVenda());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}
	}

	/**
	 * Método que recebe como paramêtro um inteiro de um tipo Wrapper Interger. Esse
	 * método é responsável por deletar um objeto Venda pelo seu Id no banco de
	 * dados. Retorna vazio.
	 * 
	 * @param id - id da Venda que deseja excluir.
	 */
	@Override
	public void deletarPorId(Integer id) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM venda WHERE CODIVENDA = ?");

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
	 * método é responsável por buscar um objeto Venda pelo seu Id no banco de
	 * dados.
	 * 
	 * @param id - id da Venda que deseja buscar.
	 * @return Venda - retorna a Venda referente ao id passado.
	 */
	@Override
	public Venda buscarPorId(Integer id) {
		return null;
	}

	private Cliente instanciarCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setIdPessoa(rs.getInt("ID_CLIENTE"));

		// Obtendo os dados completos do Cliente associado à Venda
		DaoGenerico<Cliente> clientedao = DaoFactory.criarClienteDao();
		cliente = clientedao.buscarPorId(cliente.getIdPessoa());

		return cliente;
	}

	/**
	 * Método responsável por buscar todos os objetos de Venda cadastrados no banco
	 * de dados.
	 * 
	 * @return Lis<Venda> - Lista com todas as Venda.
	 */
	@Override
	public List<Venda> buscarTudo() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM venda");

			rs = ps.executeQuery();

			List<Venda> listaVendas = new ArrayList<>();

			while (rs.next()) {

				Cliente cliente = instanciarCliente(rs);
				Venda obj = new Venda();
				obj.setIdVenda(rs.getInt("CODIVENDA"));
				obj.setValor(rs.getDouble("VALOR"));
				obj.setDataVenda(rs.getDate("DATA_VENDA").toLocalDate());

				List<ItemVenda> itensDeVenda = new ArrayList<>();

				// Obtendo os dados completos dos Itens de Venda associados à Venda
				ItemVendaJDBC itemdao = new ItemVendaJDBC(BD.getConectando());
				itensDeVenda = itemdao.listarPorVenda(obj);

				obj.setCliente(cliente);
				obj.setItensDeVenda(itensDeVenda);
				listaVendas.add(obj);
			}

			return listaVendas;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}

	/**
	 * Método responsável por busca a última venda cadastrada no banco de dados.Esse
	 * método é importante, pois com ele podemos vincular os itens de venda na venda
	 * cadastrada.
	 * 
	 * @return Venda - última venda cadastrada.
	 */
	@Override
	public Venda buscarUltimaVenda() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT max(CODIVENDA) FROM venda");
			rs = ps.executeQuery();

			Venda venda = new Venda();

			if (rs.next()) {
				venda.setIdVenda(rs.getInt("max(CODIVENDA)"));
				return venda;
			}

			return venda;

		} catch (SQLException e) {

			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
}
