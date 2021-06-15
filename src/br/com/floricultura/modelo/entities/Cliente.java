package br.com.floricultura.modelo.entities;

/**
 * Classe que corresponde ao objeto Cliente, na qual possui métodos e atributos
 * responsáveis por manipular as instâncias relacionada ao tipo desta classe.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Cliente extends Pessoa {

	private static final long serialVersionUID = 1L;

	/** atributo utilizado para defini observações, se ouver, no cliente. */
	private String observacao;

	public Cliente() {
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
}
