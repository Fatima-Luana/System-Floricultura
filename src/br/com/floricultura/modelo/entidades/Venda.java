package br.com.floricultura.modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe que corresponde ao objeto Venda, na qual possui métodos e atributos
 * responsáveis por manipular as instâncias relacionada ao tipo desta classe.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	/** atributo utilizado para definir o id da venda. */
	private Integer idVenda;
	
	/** atributo utilizado para definir a data da venda. */
	private LocalDate dataVenda;
	
	/** atributo utilizado para definir o valor da venda. */
	private double valor;
	
	/** atributo utilizado para definir a lista de ItemVenda. */
	private List<ItemVenda> itensDeVenda;
	
	/** atributo utilizado para definir o usuário. */
	private Cliente cliente;

	public Venda() {
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemVenda> getItensDeVenda() {
		return itensDeVenda;
	}

	public void setItensDeVenda(List<ItemVenda> itensDeVenda) {
		this.itensDeVenda = itensDeVenda;
	}

	public Integer getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}

	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", dataVenda=" + dataVenda + ", valor=" + valor + ", itensDeVenda="
				+ itensDeVenda + ", cliente=" + cliente + "]";
	}

}
