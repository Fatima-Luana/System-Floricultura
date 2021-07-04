package br.com.floricultura.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.floricultura.Login;
import br.com.floricultura.TelaPrincipal;
import br.com.floricultura.modelo.entidades.Usuario;
import br.com.floricultura.modelo.servicos.ServicoUsuario;
import br.com.floricultura.utils.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	private ServicoUsuario servicoUsuario;

	@FXML
	private Button btEntrar;

	@FXML
	private PasswordField txSenha;

	@FXML
	private TextField txEmail;

	public void setServicoUsuario(ServicoUsuario servicoUsuario) {
		this.servicoUsuario = servicoUsuario;
	}

	@FXML
	void acaoBtEntrar(ActionEvent event) {

		setServicoUsuario(new ServicoUsuario());
		List<Usuario> usuarios = servicoUsuario.buscarTodos();

		for (int total = 0; total < usuarios.size(); total++) {

			if (txEmail.getText().equals(usuarios.get(total).getEmail())
					&& txSenha.getText().equals(usuarios.get(total).getSenha())) {

				TelaPrincipal p = new TelaPrincipal();
				p.start(new Stage());
				total = usuarios.size();
				Login.getStage().close();

			} else {

				/*
				 * depois de percorrer toda lista e não tiver nenhum usuario cadastrado, mostrar
				 * erro!
				 */
				if (total == usuarios.size() - 1) {

					Alertas.mostrarAlerta("Erro!", "Erro ao acessar o sistema",
							"Usuário ou senha inválida, verifique e tente novamente.", AlertType.ERROR);
				}
			}
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}
}
