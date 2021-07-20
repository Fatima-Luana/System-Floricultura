package br.com.floricultura.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classe responsável por disponibilizar métodos para gerar alertas caso seja
 * nescessário utilizar.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Alertas {

	/**
	 * método responsável por mostrar alertas.
	 * 
	 * @param titulo
	 * @param cabecalho
	 * @param conteudo
	 * @param tipoAlerta
	 */
	public static void mostrarAlerta(String titulo, String cabecalho, String conteudo, AlertType tipoAlerta) {
		Alert alert = new Alert(tipoAlerta);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.show();
	}

	/**
	 * método responsável por gerar um alerta de confirmação. Esse alerta será
	 * gerado quando o usuário tentar excluir algum objeto no sistema.
	 * 
	 * @param titulo
	 * @param conteudo
	 * @return Optional<ButtonType> - botão de confirmação
	 */
	public static Optional<ButtonType> Confirmação(String titulo, String conteudo) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(conteudo);
		return alert.showAndWait();
	}
}
