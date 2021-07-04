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
import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.ItemVenda;
import br.com.floricultura.modelo.entidades.Produto;
import br.com.floricultura.modelo.entidades.Venda;

/**
 * Classe respons�vel por implementar a interface - DaoGenerico, no qual ser�
 * passado como param�to um ItemVenda.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class ItemVendaJDBC implements DaoGenerico<ItemVenda> {

	private Connection conn;

	public ItemVendaJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(ItemVenda obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(
					"INSERT INTO itensvenda" + "(QUANTIDADE, PRECO, CODIVENDA, IDPRODUTO) " + "VALUES" + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, obj.getQuantidade());
			ps.setDouble(2, obj.getValor());
			ps.setInt(3, obj.getVenda().getIdVenda());
			ps.setInt(4, obj.getProduto().getCdProduto());

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdItemDeVenda(id);
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
	 * M�todo que recebe um obj do tipo ItemVenda como param�to, respons�vel por
	 * atualizar esse objeto no banco de dados. Retorna vazio.
	 * 
	 * @param obj - Inst�ncia de ItemVenda.
	 */
	@Override
	public void atualizar(ItemVenda obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("UPDATA itensvenda"
					+ "SET QUANTIDADE = ?, PRECO = ?, CODIVENDA = ?, IDPRODUTO = ? " + "WHERE IDITEMVENDA = ?");

			ps.setInt(1, obj.getQuantidade());
			ps.setDouble(2, obj.getValor());
			ps.setInt(3, obj.getVenda().getIdVenda());
			ps.setInt(4, obj.getProduto().getCdProduto());
			ps.setInt(5, obj.getIdItemDeVenda());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}
	}

	/**
	 * M�todo que recebe como param�tro um inteiro de um tipo Wrapper Interger. Esse
	 * m�todo � respons�vel por deletar um objeto ItemVenda pelo seu Id no banco de
	 * dados. Retorna vazio.
	 * 
	 * @param id - id do ItemVenda que deseja excluir.
	 */
	@Override
	public void deletarPorId(Integer id) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM itensvenda WHERE IDITEMVENDA = ?");

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}
	}

	/**
	 * M�todo que recebe como param�tro um inteiro de um tipo Wrapper Interger. Esse
	 * m�todo � respons�vel por buscar um objeto ItemVenda pelo seu Id no banco de
	 * dados.
	 * 
	 * @param id - id do ItemVenda que deseja buscar.
	 * @return ItemVenda - retorna o ItemVenda referente ao id passado.
	 */
	@Override
	public ItemVenda buscarPorId(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM itensvenda WHERE IDITEMVENDA = ? ");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Produto produto = instanciarProduto(rs);
				Venda venda = instanciarVenda(rs);
				ItemVenda item = instanciarItemVenda(rs, venda, produto);

				return item;
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
	 * M�todo respons�vel por buscar todos os objetos de ItemVenda cadastrados no
	 * banco de dados.
	 * 
	 * @return Lis<ItemVenda> - Lista com todas os itens de venda.
	 */
	@Override
	public List<ItemVenda> buscarTudo() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM itensvenda");

			rs = ps.executeQuery();

			List<ItemVenda> listaItens = new ArrayList<>();

			while (rs.next()) {

				Produto produto = instanciarProduto(rs);
				Venda venda = instanciarVenda(rs);
				ItemVenda item = instanciarItemVenda(rs, venda, produto);

				listaItens.add(item);

			}

			return listaItens;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}

	/**
	 * M�todo privado respons�vel por instanciar os Itens de venda.
	 * 
	 * @return ItemVenda - ItemVenda j� instanciado.
	 */
	private ItemVenda instanciarItemVenda(ResultSet rs, Venda vend, Produto prod) throws SQLException {

		ItemVenda item = new ItemVenda();
		item.setIdItemDeVenda(rs.getInt("IDITEMVENDA"));
		item.setQuantidade(rs.getInt("QUANTIDADE"));
		item.setValor(rs.getDouble("PRECO"));
		item.setVenda(vend);
		item.setProduto(prod);

		return item;
	}

	/**
	 * M�todo privado respons�vel por instanciar os Produtos.
	 * 
	 * @return Produto - Produto j� instanciado.
	 */
	private Produto instanciarProduto(ResultSet rs) throws SQLException {
		Produto produto = new Produto();

		produto.setCdProduto(rs.getInt("IDPRODUTO"));

		DaoGenerico<Produto> produtodao = DaoFactory.criarProdutoDao();
		produto = produtodao.buscarPorId(produto.getCdProduto());

		return produto;
	}

	/**
	 * M�todo privado respons�vel por instanciar as Vendas.
	 * 
	 * @return Venda - Venda j� instanciado.
	 */
	private Venda instanciarVenda(ResultSet rs) throws SQLException {
		Venda venda = new Venda();
		venda.setIdVenda(rs.getInt("CODIVENDA"));

		DaoGenerico<Venda> vendadao = DaoFactory.criarVendaDao();
		venda = vendadao.buscarPorId(venda.getIdVenda());

		return venda;
	}

	/**
	 * M�todo respons�vel por lista retornar uma lista de Itens de Venda por venda.
	 * 
	 * @return List<ItemVenda> - Lista de ItemVenda relacionado a venda.
	 */
	public List<ItemVenda> listarPorVenda(Venda obj) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM itensvenda WHERE CODIVENDA = ?");

			List<ItemVenda> listaItens = new ArrayList<>();

			ps.setInt(1, obj.getIdVenda());
			rs = ps.executeQuery();

			while (rs.next()) {
				Produto produto = instanciarProduto(rs);
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt("CODIVENDA"));
				ItemVenda item = instanciarItemVenda(rs, venda, produto);

				listaItens.add(item);
			}

			return listaItens;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
}
