package br.com.floricultura.controller.categoria;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import br.com.floricultura.conexao.BdExcecoes;
import br.com.floricultura.modelo.entidades.Categoria;
import br.com.floricultura.modelo.excecoes.ValidacaoExcecoes;
import br.com.floricultura.modelo.servicos.ServicoCaretogia;
import br.com.floricultura.ouvintes.EscutandoEvendo;
import br.com.floricultura.utils.Alertas;
import br.com.floricultura.utils.Restricoes;
import br.com.floricultura.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormCategoriaController implements Initializable {

	private Categoria categoria;

	private List<EscutandoEvendo> dadosEscutandoEvento = new ArrayList<>();

	private ServicoCaretogia servicoCategoria;

	@FXML
	private TextField textFildId;

	@FXML
	private TextField textFildNomeCategoria;

	@FXML
	private Label labelErroNome;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setServicoCategoria(ServicoCaretogia servicoCategoria) {
		this.servicoCategoria = servicoCategoria;
	}

	public void escrevendoDadosEscutados(EscutandoEvendo ouvintes) {
		dadosEscutandoEvento.add(ouvintes);
	}

	@FXML
	public void acaoBotaoSalvar(ActionEvent event) {

		if (categoria == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (servicoCategoria == null) {
			throw new IllegalStateException("Service was null");
		}

		try {

			categoria = getDadosForm();
			servicoCategoria.SalvarOuAtualizar(categoria);
			notificarDadosAlteradosOuvintes();
			Alertas.mostrarAlerta("Cadastro de categoria", "Categoria cadastrada com sucesso!!!",
					"Aperte em OK para continuar", AlertType.CONFIRMATION);

			Utils.currentStage(event).close();
			
		} catch (ValidacaoExcecoes ve) {
			setMensagensErros(ve.getErros());
		} catch (BdExcecoes e) {
			Alertas.mostrarAlerta("Erro ao salvar o objeto Categoria", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notificarDadosAlteradosOuvintes() {

		for (EscutandoEvendo ouvintes : dadosEscutandoEvento) {
			ouvintes.dadosAlterados();
		}
	}

	// pegar os dados do formularios
	private Categoria getDadosForm() {
		Categoria obj = new Categoria();

		ValidacaoExcecoes validacao = new ValidacaoExcecoes("Erro Validação");

		obj.setIdCategoria(Utils.converterParaInteiro(textFildId.getText()));

		if (textFildNomeCategoria.getText() == null || textFildNomeCategoria.getText().trim().equals("")) {
			validacao.addErro("Categoria", "O campo não pode ser vazio");
		}
		obj.setNomeCategoria(textFildNomeCategoria.getText());

		if (validacao.getErros().size() > 0) {
			throw validacao;
		}

		return obj;
	}

	private void setMensagensErros(Map<String, String> erro) {
		Set<String> campos = erro.keySet();

		if (campos.contains("Categoria")) {
			labelErroNome.setText(erro.get("Categoria"));
		}
	}

	@FXML
	public void acaoBotaoCancelar(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		Restricoes.setCampoTextoInteiro(textFildId);
		Restricoes.setTamanhoMaximoCampoTexto(textFildNomeCategoria, 50);
	}

	public void atualizarFormularioCategoria() {

		if (categoria == null) {
			throw new IllegalStateException("Categoria não foi instanciada");
		}

		textFildId.setText(String.valueOf(categoria.getIdCategoria()));
		textFildNomeCategoria.setText(categoria.getNomeCategoria());
	}

}
