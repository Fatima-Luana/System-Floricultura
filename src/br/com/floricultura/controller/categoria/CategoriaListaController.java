package br.com.floricultura.controller.categoria;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.floricultura.conexao.ErroIntegridade;
import br.com.floricultura.modelo.entidades.Categoria;
import br.com.floricultura.modelo.servicos.ServicoCaretogia;
import br.com.floricultura.ouvintes.EscutandoEvendo;
import br.com.floricultura.utils.Alertas;
import br.com.floricultura.utils.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CategoriaListaController implements Initializable, EscutandoEvendo {

	private ServicoCaretogia servicoCategoria;

	@FXML
	private TableView<Categoria> tableViewCategoria;

	@FXML
	private TableColumn<Categoria, Integer> tcId;

	@FXML
	private TableColumn<Categoria, String> tcNomeCategoria;

	@FXML
	private TableColumn<Categoria, Categoria> tableColumneditar;

	@FXML
	private TableColumn<Categoria, Categoria> tableColumneRemover;

	@FXML
	private TextField texFildPesquisar;

	@FXML
	private Button btAdicionar;

	@FXML
	private Button btBuscar;

	// para carregar as categorias
	private ObservableList<Categoria> observableList;

	// injetar dependência
	public void setServicoCategoria(ServicoCaretogia servicoCategoria) {
		this.servicoCategoria = servicoCategoria;
	}

	@FXML
	void açãoBotaoPesquisar(ActionEvent event) {
		tableViewCategoria.setItems(pesquisar());
	}

	@FXML
	void acaoBtAdicionar(ActionEvent event) {
		Stage paiStage = Utils.currentStage(event);
		Categoria categoria = new Categoria();
		criarTelaDiagoloCategoria(categoria, "/br/com/floricultura/view/categoria/FormCategoria.fxml", paiStage);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		iniciarComponentesTabela();

		texFildPesquisar.setOnKeyReleased((KeyEvent) -> {
			tableViewCategoria.setItems(pesquisar());

		});
	}

	/* iniciar o comportamento das colunas. */
	public void iniciarComponentesTabela() {

		tcId.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
		tcNomeCategoria.setCellValueFactory(new PropertyValueFactory<>("nomeCategoria"));
	}

	/*
	 * Metodo responsável por acessar o serviço, carregar as categorias e jogando
	 * dentro do observableList.
	 */
	public void atualizarTabelaCategoria() {

		if (servicoCategoria == null) {
			throw new IllegalStateException("Serviço está nulo, faça a injeção da dependencia!");
		}

		List<Categoria> listaCategoria = servicoCategoria.buscarTodos();
		observableList = FXCollections.observableArrayList(listaCategoria);

		/* carregar os itens na tabela e mostrar na tela. */
		tableViewCategoria.setItems(observableList);
		botoesEditar();
		BotoesRemover();
	}

	/* Método responsável por carregar a janela de formulario de categoria. */
	private void criarTelaDiagoloCategoria(Categoria categoria, String caminhoTela, Stage stagePai) {

		try {

			FXMLLoader carregando = new FXMLLoader(getClass().getResource(caminhoTela));
			AnchorPane anchorPane = carregando.load();

			FormCategoriaController controller = carregando.getController();
			controller.setCategoria(categoria);
			controller.setServicoCategoria(new ServicoCaretogia());
			controller.escrevendoDadosEscutados(this);
			controller.atualizarFormularioCategoria();

			Stage telaDialogo = new Stage();
			telaDialogo.setTitle("Entre com os dados da categoria");
			telaDialogo.setScene(new Scene(anchorPane));
			telaDialogo.setResizable(false);
			telaDialogo.initOwner(stagePai); // stage pai dessa janela
			telaDialogo.initModality(Modality.WINDOW_MODAL);

			telaDialogo.showAndWait();

		} catch (IOException e) {
			Alertas.mostrarAlerta("IOException", "Erro ao carregar tabela de diagolo", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void dadosAlterados() {
		atualizarTabelaCategoria();
	}

	private void botoesEditar() {
		tableColumneditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumneditar.setCellFactory(param -> new TableCell<Categoria, Categoria>() {
			private final Button button = new Button("editar");

			@Override
			protected void updateItem(Categoria obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> criarTelaDiagoloCategoria(obj,
						"/br/com/floricultura/view/categoria/FormCategoria.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void BotoesRemover() {
		tableColumneRemover.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumneRemover.setCellFactory(param -> new TableCell<Categoria, Categoria>() {
			private final Button button = new Button("excluir");

			@Override
			protected void updateItem(Categoria obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> RemovendoCategoria(obj));
			}
		});
	}

	private void RemovendoCategoria(Categoria obj) {
		Optional<ButtonType> result = Alertas.Confirmação("Confirmação", "Tem certeza que deseja deletar a categoria?");

		if (result.get() == ButtonType.OK) {
			if (servicoCategoria == null) {
				throw new IllegalStateException("Servico nulo");
			}
			try {
				servicoCategoria.remover(obj);
				atualizarTabelaCategoria();
			} catch (ErroIntegridade e) {
				Alertas.mostrarAlerta("Erro ao remover a categoria", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}

	private ObservableList<Categoria> pesquisar() {
		ObservableList<Categoria> categoria = FXCollections.observableArrayList();

		for (int x = 0; x < observableList.size(); x++) {
			if (observableList.get(x).getNomeCategoria().toLowerCase().contains(texFildPesquisar.getText())) {
				categoria.add(observableList.get(x));
			}
		}
		return categoria;
	}
}
