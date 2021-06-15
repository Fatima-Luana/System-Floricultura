package br.com.floricultura.modelo.entidades;

import java.io.Serializable;

/**
 * Classe para objetos do tipo Pessoa, na qual possui métodos e atributos
 * responsáveis por manipular as instâncias de classes concretas que são classes
 * derivadas dessa classe abstrata.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	/** atributo utilizado para definir o id da pessoa. */
	private Integer idPessoa;

	/** atributo utilizado para definir o nome da pessoa. */
	private String nome;

	/** atributo utilizado para definir o telefone da pessoa. */
	private String telefone;

	/** atributo utilizado para email da pessoa. */
	private String email;

	/** atributo utilizado para definir o id do item de venda. */
	private String enderco;

	/** atributo utilizado para definir o id do item de venda. */
	private String cpf;

	public Pessoa() {
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnderco() {
		return enderco;
	}

	public void setEnderco(String enderco) {
		this.enderco = enderco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "idPessoa = " + idPessoa + ", nome = " + nome + ", telefone = " + telefone + ", email = " + email
				+ ", enderco = " + enderco;
	}
}
