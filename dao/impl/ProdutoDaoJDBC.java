package br.com.floricultura.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.floricultura.conexao.BD;
import br.com.floricultura.conexao.BdExcecoes;
import br.com.floricultura.modelo.dao.DaoFactory;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Categoria;
import br.com.floricultura.modelo.entidades.Produto;

/**
 * Classe responsável por implementar a interface - DaoGenerico, no qual será
 * passado como paramêto um Produto.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class ProdutoDaoJDBC implements DaoGenerico<Produto> {

	private Connection conn;

	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Método responsável por fazer a inserção de dados do objeto Produto no banco
	 * de dados. Retorna nada.
	 * 
	 * @param obj - Instância de Produto.
	 */
	@Override
	public void inserir(Produto obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(
					"INSERT INTO produto" + "(PRODUTO, ESTOQUE, PRECO, ID_CATEGORIA) " + "VALUES" + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getNomeProduto());
			ps.setInt(2, obj.getEstoque());
			ps.setDouble(3, obj.getPreco());
			ps.setInt(4, obj.getCategoria().getIdCategoria());

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setCdProduto(id);
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
	 * Método que recebe um obj do tipo Produto como paramêto, responsável por
	 * atualizar esse objeto no banco de dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Produto.
	 */
	@Override
	public void atualizar(Produto obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("UPDATE produto SET PRODUTO=?, ESTOQUE=?, PRECO=?, ID_CATEGORIA=? WHERE IDPRODUTO=?");

			ps.setString(1, obj.getNomeProduto());
			ps.setInt(2, obj.getEstoque());
			ps.setDouble(3, obj.getPreco());
			ps.setInt(4, obj.getCategoria().getIdCategoria());
			ps.setInt(5, obj.getCdProduto());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}
	}

	/**
	 * Método que recebe como paramêtro um inteiro de um tipo Wrapper Interger. Esse
	 * método é responsável por deletar um objeto Produto pelo seu Id no banco de
	 * dados. Retorna vazio.
	 * 
	 * @param id - id do Produto que deseja excluir.
	 */
	@Override
	public void deletarPorId(Integer id) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM produto WHERE IDPRODUTO = ?");

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
	 * método é responsável por buscar um objeto Produto pelo seu Id no banco de
	 * dados.
	 * 
	 * @param id - id do Produto que deseja buscar.
	 * @return Produto - retorna o Produto referente ao seu Id.
	 */
	@Override
	public Produto buscarPorId(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT produto.*,categoria.NOME as NOME_CATEGORIA " + "FROM produto INNER JOIN categoria "
							+ "ON produto.ID_CATEGORIA = categoria.IDCATEGORIA " + "WHERE produto.IDPRODUTO = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				
				Categoria c = new Categoria();
				c.setIdCategoria(rs.getInt("ID_CATEGORIA"));
				c.setNomeCategoria(rs.getString("NOME_CATEGORIA"));
				Produto produto = instanciarProduto(rs, c);
				
				return produto;
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
	 * Método responsável por buscar todos os objetos de Produto cadastrados no
	 * banco de dados.
	 * 
	 * @return Lis<Produto> - Lista com todos os Produto.
	 */
	@Override
	public List<Produto> buscarTudo() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM produto");

			rs = ps.executeQuery();

			List<Produto> listaProduto = new ArrayList<>();

			while (rs.next()) {

				Categoria categoria = instanciarCategoria(rs);
				Produto produto = instanciarProduto(rs, categoria);

				listaProduto.add(produto);
			}

			return listaProduto;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
	
	/**
	 * Método privado responsável por instanciar os Produtos.
	 * 
	 * @return Produto - Produto já instanciado. 
	 */
	private Produto instanciarProduto(ResultSet rs, Categoria cat) throws SQLException {

		Produto produto = new Produto();

		produto.setCdProduto(rs.getInt("IDPRODUTO"));
		produto.setNomeProduto(rs.getString("PRODUTO"));
		produto.setEstoque(rs.getInt("ESTOQUE"));
		produto.setPreco(rs.getDouble("PRECO"));
		produto.setCategoria(cat);

		return produto;
	}
	
	/**
	 * Método privado responsável por instanciar as categorias.
	 * 
	 * @return Categoria - Categoria já instanciado. 
	 */
	private Categoria instanciarCategoria(ResultSet rs) throws SQLException {

		Categoria categoria = new Categoria();
		categoria.setIdCategoria(rs.getInt("ID_CATEGORIA"));

		DaoGenerico<Categoria> categoriadao = DaoFactory.criarCategoriaDao();
		categoria = categoriadao.buscarPorId(categoria.getIdCategoria());

		return categoria;
	}
	
	/**
	 * Método responsável por buscar todos os objetos de Produto cadastrados no
	 * banco de dados por categoria. Caso não tenha produtos, retorna vazio.
	 * 
	 * @param categoria - Categoria que será buscada no banco de dados.
	 * @return List<Produto> - Lista com todos os Produto referente a categoria passada.
	 */
	public List<Produto> listarPorCategoria(Categoria categoria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT produto.*,categoria.NOME as NOME_CATEGORIA "
					+ "FROM produto INNER JOIN categoria " + "ON produto.ID_CATEGORIA = categoria.IDCATEGORIA "
					+ "WHERE IDCATEGORIA = ? " + "ORDER BY NOME");

			ps.setInt(1, categoria.getIdCategoria());

			rs = ps.executeQuery();

			List<Produto> listaProdutos = new ArrayList<>();
			Map<Integer, Categoria> map = new HashMap<>();

			while (rs.next()) {

				Categoria cat = map.get(rs.getInt("ID_CATEGORIA"));

				if (cat == null) {
					Categoria c = new Categoria();
					c.setIdCategoria(rs.getInt("ID_CATEGORIA"));
					c.setNomeCategoria(rs.getString("NOME_CATEGORIA"));
					map.put(rs.getInt("DepartmentId"), c);
				}

				Produto obj = instanciarProduto(rs, cat);
				listaProdutos.add(obj);
			}
			return listaProdutos;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
}
