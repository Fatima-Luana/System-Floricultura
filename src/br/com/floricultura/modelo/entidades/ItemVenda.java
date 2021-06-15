package br.com.floricultura.modelo.entidades;

import java.io.Serializable;

/**
 * Classe que corresponde ao objeto ItemVenda, na qual possui métodos e
 * atributos responsáveis por manipular as instâncias relacionada ao tipo desta
 * classe. Possui uma referência para o objeto Venda e o Produto.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class ItemVenda implements Serializable {

	private static final long serialVersionUID = 1L;

	/** atributo utilizado para definir o id do item de venda. */
	private int idItemDeVenda;

	/** atributo utilizado para definir a quantidade de item de venda. */
	private int quantidade;

	/** atributo utilizado para definir o valor do item de venda. */
	private double valor;

	/** atributo utilizada para definir uma associação com o produto. */
	private Produto produto;

	/** atributo utilizada para definir uma associação com a venda. */
	private Venda venda;

	public ItemVenda() {
	}

	public int getIdItemDeVenda() {
		return idItemDeVenda;
	}

	public void setIdItemDeVenda(int idItemDeVenda) {
		this.idItemDeVenda = idItemDeVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return "ItemVenda [idItemDeVenda=" + idItemDeVenda + ", quantidade=" + quantidade + ", valor=" + valor
				+ ", produto=" + produto + ", venda=" + venda + "]";
	}

}
