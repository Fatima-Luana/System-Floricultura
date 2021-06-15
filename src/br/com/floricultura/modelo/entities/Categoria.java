package br.com.floricultura.modelo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que corresponde ao objeto Categoria, na qual possui métodos e
 * atributos responsáveis por manipular as instâncias relacionada ao tipo desta
 * classe.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	/** atributo utilizado para definir o id da Categoria. */
	private Integer idCategoria;

	/** atributo utilizado para definir o nome da categoria. */
	private String nomeCategoria;

	/** atributo utilizado para guardar uma coleção de produtos. */
	private Set<Produto> produtos = new HashSet<>();

	public Categoria() {
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	@Override
	public String toString() {
		return this.nomeCategoria;
	}
}
