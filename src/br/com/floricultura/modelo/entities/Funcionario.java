package br.com.floricultura.modelo.entities;

/**
 * Classe que corresponde ao objeto Funcionario, na qual possui m�todos e
 * atributos respons�veis por manipular as inst�ncias relacionada ao tipo desta
 * classe.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	/** atributo utilizado para definir o sal�rio do funcionario */
	private Double salario;

	/** atributo utilizado para definir a data de pagamento do funcionario */
	private String dataPagamento;

	/** atributo utilizado para definir a data de admiss�o do funcionario */
	private String admissao;

	public Funcionario() {
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getAdmissao() {
		return admissao;
	}

	public void setAdmissao(String admissao) {
		this.admissao = admissao;
	}

	@Override
	public String toString() {
		return super.toString() + " [salario=" + salario + ", dataPagamento=" + dataPagamento + ", admissao=" + admissao
				+ "]";
	}
}
