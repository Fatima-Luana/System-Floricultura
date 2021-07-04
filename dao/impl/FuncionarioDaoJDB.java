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
import br.com.floricultura.modelo.entidades.Funcionario;

/**
 * Classe responsável por implementar a interface - DaoGenerico, no qual será
 * passado como paramêto um Funcionario.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class FuncionarioDaoJDB implements DaoGenerico<Funcionario> {

	private Connection conn;

	public FuncionarioDaoJDB(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método responsável por fazer a inserção de dados do objeto Funcionario no
	 * banco de dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Funcionario.
	 */
	@Override
	public void inserir(Funcionario obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("INSERT INTO funcionario"
					+ "(NOME, TELEFONE, CPF, ENDERECO, SALARIO, DATA_PAGAMENTO, DATA_ADMISSAO, EMAIL) "
					+ "VALUES" + "(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getTelefone());
			ps.setString(3, obj.getCpf());
			ps.setString(4, obj.getEnderco());
			ps.setDouble(5, obj.getSalario());
			ps.setString(6,obj.getDataPagamento());
			ps.setString(7, obj.getAdmissao());
			ps.setString(8, obj.getEmail());

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
	 * Método que recebe um obj do tipo Funcionario como paramêto, responsável por
	 * atualizar esse objeto no banco de dados. Retorna vazio.
	 * 
	 * @param obj - Instância de Funcionario.
	 */
	@Override
	public void atualizar(Funcionario obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("UPDATE funcionario "
					+ "SET NOME = ?, TELEFONE = ?, CPF = ?, ENDERECO = ?, SALARIO = ?, DATA_PAGAMENTO = ?, DATA_ADMISSAO = ?, EMAIL = ? "
					+ "WHERE IDFUNCIONARIO = ?");

			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getTelefone());
			ps.setString(3, obj.getCpf());
			ps.setString(4, obj.getEnderco());
			ps.setDouble(5, obj.getSalario());
			ps.setString(6, obj.getDataPagamento());
			ps.setString(7, obj.getAdmissao());
			ps.setString(8, obj.getEmail());
			ps.setInt(9, obj.getIdPessoa());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
		}
	}

	/**
	 * Método que recebe como paramêtro um inteiro de um tipo Wrapper Interger. Esse
	 * método é responsável por deletar um objeto Funcionario pelo seu Id no banco
	 * de dados. Retorna vazio.
	 * 
	 * @param id - id do Funcionario que deseja excluir.
	 */
	@Override
	public void deletarPorId(Integer id) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM funcionario WHERE IDFUNCIONARIO = ?");

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
	 * método é responsável por buscar um objeto Funcionario pelo seu Id no banco de
	 * dados.
	 * 
	 * @param id - id do Funcionario que deseja buscar.
	 * @return Funcionario - retorna o Funcionario referente ao seu Id.
	 */
	@Override
	public Funcionario buscarPorId(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM funcionario WHERE IDFUNCIONARIO = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdPessoa(rs.getInt("IDFUNCIONARIO"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setTelefone(rs.getString("TELEFONE"));
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setEnderco(rs.getString("ENDERECO"));
				funcionario.setSalario(rs.getDouble("SALARIO"));
				funcionario.setDataPagamento(rs.getString("DATA_PAGAMENTO"));
				funcionario.setAdmissao(rs.getString("DATA_ADMISSAO"));
				funcionario.setEmail(rs.getString("EMAIL"));

				return funcionario;
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
	 * Método responsável por buscar todos os objetos de Funcionario cadastrados no
	 * banco de dados.
	 * 
	 * @return Lis<Funcionario> - Lista com todos os Funcionario.
	 */
	@Override
	public List<Funcionario> buscarTudo() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM funcionario");

			rs = ps.executeQuery();

			List<Funcionario> listaFuncionario = new ArrayList<>();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdPessoa(rs.getInt("IDFUNCIONARIO"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setTelefone(rs.getString("TELEFONE"));
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setEnderco(rs.getString("ENDERECO"));
				funcionario.setSalario(rs.getDouble("SALARIO"));
				funcionario.setDataPagamento(rs.getString("DATA_PAGAMENTO"));
				funcionario.setDataPagamento(rs.getString("DATA_ADMISSAO"));
				funcionario.setEmail(rs.getString("EMAIL"));

				listaFuncionario.add(funcionario);
			}

			return listaFuncionario;

		} catch (SQLException e) {
			throw new BdExcecoes(e.getMessage());

		} finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
}
