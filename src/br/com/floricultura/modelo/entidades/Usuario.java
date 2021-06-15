package br.com.floricultura.modelo.entidades;

import java.io.Serializable;

/**
 * Classe que corresponde ao objeto Usuario, na qual possui m�todos e atributos
 * respons�veis por manipular as inst�ncias relacionada ao tipo desta classe.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	/** atributo utilizado para definir o id do usu�rio. */
	private Integer idUsuario;
	
	/** atributo utilizado para definir a senha do usu�rio. */
	private String senha;
	
	/** atributo utilizado para definir o email do usu�rio. */
	private String email;
	private String nivel_acesso;

	public Usuario() {
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNivel_acesso() {
		return nivel_acesso;
	}

	public void setNivel_acesso(String nivel_acesso) {
		this.nivel_acesso = nivel_acesso;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", senha=" + senha + ", email=" + email + ", nivel_acesso="
				+ nivel_acesso + "]";
	}
}
