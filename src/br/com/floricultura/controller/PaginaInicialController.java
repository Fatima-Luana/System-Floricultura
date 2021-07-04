package br.com.floricultura.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import br.com.floricultura.controller.categoria.CategoriaListaController;
import br.com.floricultura.controller.clientes.ListaClienteController;
import br.com.floricultura.controller.funcionario.ListaFuncionarioController;
import br.com.floricultura.controller.produtos.ListaProdutosController;
import br.com.floricultura.controller.usuario.ListaUsuariosController;
import br.com.floricultura.modelo.servicos.ServicoCaretogia;
import br.com.floricultura.modelo.servicos.ServicoCliente;
import br.com.floricultura.modelo.servicos.ServicoFuncionario;
import br.com.floricultura.modelo.servicos.ServicoProduto;
import br.com.floricultura.modelo.servicos.ServicoUsuario;
import br.com.floricultura.utils.Alertas;
import br.com.floricultura.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PaginaInicialController implements Initializable {

	@FXML
	private Button bem_vindo;

	@FXML
	private Button btCliente;

	@FXML
	private Button btFuncionario;

	@FXML
	private Button btCategorias;

	@FXML
	private Button btProdutos;

	@FXML
	private Button btVendas;

	@FXML
	public Button btRelatorios;

	@FXML
	private Button btGraficos;

	@FXML
	private AnchorPane telaCorrente;

	@FXML
	private Button btUsuarios;

	@FXML
	private Button btSalvar;

	@FXML
	void acaoBotaoSalvar(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@FXML
	void exibirPaginaCategorias(ActionEvent event) {
		carregandoTela("/br/com/floricultura/view/categoria/CRUDCategoria.fxml",
				(CategoriaListaController controle) -> {
					controle.setServicoCategoria(new ServicoCaretogia());
					controle.atualizarTabelaCategoria();
				});
	}

	@FXML
	void exibirPaginaClientes(ActionEvent event) {
		carregandoTela("/br/com/floricultura/view/clientes/CRUDCliente.fxml", (ListaClienteController controle) -> {
			controle.sertServicoCliente(new ServicoCliente());
			controle.atualizarTabelaCliente();
		});
	}

	@FXML
	void exibirPaginaFuncionarios(ActionEvent event) {
		carregandoTela("/br/com/floricultura/view/funcionarios/ListaFuncionario.fxml",
				(ListaFuncionarioController controle) -> {
					controle.setServicoFuncionario(new ServicoFuncionario());
					controle.atualizarTabelaFuncionario();
				});
	}

	@FXML
	void exibirPaginaProdutos(ActionEvent event) {
		carregandoTela("/br/com/floricultura/view/produtos/CRUDProduto.fxml", (ListaProdutosController controle) -> {
			controle.setServicoProduto(new ServicoProduto());
			controle.atualizarTabelaProduto();
		});
	}

	@FXML
	public void exibirPaginaRelatorios(ActionEvent event) {
		System.out.println("Funcionalidade em andamento!");
	}

	@FXML
	void exibirPaginaUsuarios(ActionEvent event) {
		carregandoTela("/br/com/floricultura/view/usuarios/CRUDUsuarios.fxml", (ListaUsuariosController controle) -> {
			controle.setServicoUsuario(new ServicoUsuario());
			controle.atualizarTabelaUsuario();
		});
	}

	@FXML
	void exibirPaginaVendas(ActionEvent event) {
		System.out.println("Funcionalidade em endamento");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	// Função paremetrizada com o tipo qualquer
	private synchronized  <T> void carregandoTela(String caminhaTela, Consumer<T> inicializandoAção) {
		try {
			FXMLLoader carregando = new FXMLLoader(getClass().getResource(caminhaTela));
			AnchorPane anchorPane = carregando.load();

			telaCorrente.getChildren().clear();
			telaCorrente.getChildren().add(anchorPane);

			T controle = carregando.getController();
			inicializandoAção.accept(controle);

		} catch (IOException e) {
			Alertas.mostrarAlerta("IO Exception", "Erro ao carregar a cena", e.getMessage(), AlertType.ERROR);
		}
	}
}
