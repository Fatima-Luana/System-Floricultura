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
import br.com.floricultura.modelo.entidades.Usuario;

/**
 * Classe responsável por implementar a interface - DaoGenerico, no qual será
 * passado como paramêto um Usuario.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class UsuarioDaoJDBC implements DaoGenerico<Usuario> {

	private Connection conn;

	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método responsável por fazer a inserção de dados do objeto Usuario no banco
	 * de dados. Retorna nada.
	 * 
	 * @param obj - Instância de Usuario.
	 */
	@Override
	public void inserir(Usuario obj) {
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("INSERT INTO usuario" + "(EMAIL, SENHA, NIVEL_ACESSO) " + "VALUES" + "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getEmail());
			ps.setString(2, obj.getSenha());
			ps.setString(3, obj.getNivel_acesso());

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdUsuario(id);
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
	 * Método responsável por fazer a atualização de dados do objeto Usuario no
	 * banco de dados. Retorna nada.
	 * 
	 * @param obj - Instância de Usuario.
	 */
	@Override
	public void atualizar(Usuario obj) {
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(
					"UPDATE usuario " + "SET EMAIL = ?, SENHA = ?, NIVEL_ACESSO = ? " + "WHERE IDUSUARIO = ?");

			ps.setString(1, obj.getEmail());
			ps.setString(2, obj.getSenha());
			ps.setString(3, obj.getNivel_acesso());
			ps.setInt(4, obj.getIdUsuario());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}
	}

	/**
	 * Método que recebe como paramêtro um inteiro de um tipo Wrapper Interger. Esse
	 * método é responsável por deletar um objeto Usuario pelo seu Id no banco de
	 * dados. Retorna vazio.
	 * 
	 * @param id - id do Usuario que deseja excluir.
	 */
	@Override
	public void deletarPorId(Integer id) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM usuario WHERE IDUSUARIO = ?");

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
	 * método é responsável por buscar um objeto Usuario pelo seu Id no banco de
	 * dados.
	 * 
	 * @param id - id do Usuario que deseja buscar.
	 * @return Usuario - retorna o Usuario referente ao seu Id.
	 */
	@Override
	public Usuario buscarPorId(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM usuario WHERE IDUSUARIO = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSenha(rs.getString("SENHA"));
				usuario.setNivel_acesso(rs.getString("NIVEL_ACESSO"));
				;

				return usuario;
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
	 * Método responsável por buscar todos os objetos de Usuario cadastrados no
	 * banco de dados.
	 * 
	 * @return Lis<Usuario> - Lista com todos os Usuarios.
	 */
	@Override
	public List<Usuario> buscarTudo() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM usuario");

			rs = ps.executeQuery();

			List<Usuario> listaUsuarios = new ArrayList<>();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSenha(rs.getString("SENHA"));
				usuario.setNivel_acesso(rs.getString("NIVEL_ACESSO"));

				listaUsuarios.add(usuario);
			}

			return listaUsuarios;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
}
