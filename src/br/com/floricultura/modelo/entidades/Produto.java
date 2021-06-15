package br.com.floricultura.modelo.entidades;

import java.io.Serializable;

/**
 * Classe que corresponde ao objeto Produto, na qual possui métodos e atributos
 * responsáveis por manipular as instâncias relacionada ao tipo desta classe.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** atributo utilizado para definir o id do produto. */
	private Integer cdProduto;

	/** atributo utilizado para definir o nome do produto. */
	private String nomeProduto;

	/** atributo utilizado para definir o preço do produto. */
	private double preco;

	/** atributo utilizado para definir o estoque do produto. */
	private int estoque;

	/** atributo utilizado para definir a categoria do produto. */
	private Categoria categoria;

	public Produto() {
	}

	public Integer getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(Integer cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return this.getNomeProduto();
	}
}
