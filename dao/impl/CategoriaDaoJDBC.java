package br.com.floricultura.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import br.com.floricultura.conexao.BD;
import br.com.floricultura.conexao.BdExcecoes;
import br.com.floricultura.modelo.dao.DaoGenerico;
import br.com.floricultura.modelo.entidades.Categoria;

/**
 * Classe responsável por implementar a interface - DaoGenerico, no qual será
 * passado como paramêto um Categoria.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class CategoriaDaoJDBC implements DaoGenerico<Categoria> {

	private Connection conn;

	public CategoriaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método responsável por fazer a inserção de dados do objeto Categoria no banco
	 * de dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Categoria.
	 */
	@Override
	public void inserir(Categoria obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("INSERT INTO categoria " + "(NOME) " + "VALUES " + "(?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getNomeCategoria());

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdCategoria(id);
				}
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
	 * Método que recebe um obj do tipo Categoria como paramêto, responsável por
	 * atualizar esse objeto no banco de dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Categoria.
	 */
	@Override
	public void atualizar(Categoria obj) {
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("UPDATE categoria " + "SET NOME = ? " + "WHERE IDCATEGORIA = ?");

			ps.setString(1, obj.getNomeCategoria());
			ps.setInt(2, obj.getIdCategoria());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());
		} finally {
			BD.fecharStatement(ps);
		}
	}

	/**
	 * Método que recebe como paramêtro um inteiro de um tipo Wrapper Interger. Esse
	 * método é responsável por deletar um objeto Categoria pelo seu Id no banco de
	 * dados. Retorna vazio.
	 * 
	 * @param id - id da Categoria que deseja excluir.
	 */
	@Override
	public void deletarPorId(Integer id) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM categoria WHERE IDCATEGORIA = ?");

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
	 * método é responsável por buscar um objeto Categoria pelo seu Id no banco de
	 * dados.
	 * 
	 * @param id - id da Categoria que deseja buscar.
	 * @return Categoria - retorna o Categoria referente ao id passado.
	 */
	@Override
	public Categoria buscarPorId(Integer id) {

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM categoria WHERE IDCATEGORIA= ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("IDCATEGORIA"));
				categoria.setNomeCategoria(rs.getString("NOME"));

				return categoria;
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
	 * Método responsável por buscar todos os objetos de Categoria cadastrados no
	 * banco de dados.
	 * 
	 * @return Lis<Categoria> - Lista com todas as Categoria.
	 */
	@Override
	public List<Categoria> buscarTudo() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM categoria ORDER BY IDCATEGORIA");

			rs = ps.executeQuery();

			List<Categoria> listaCategorias = new ArrayList<>();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("IDCATEGORIA"));
				categoria.setNomeCategoria(rs.getString("NOME"));
				listaCategorias.add(categoria);
			}

			return listaCategorias;

		} catch (SQLException e) {

			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
}
