package br.com.floricultura.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classe respons�vel por disponibilizar m�todos para gerar alertas caso seja
 * nescess�rio utilizar.
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Alertas {

	/**
	 * m�todo respons�vel por mostrar alertas.
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
	 * m�todo respons�vel por gerar um alerta de confirma��o. Esse alerta ser�
	 * gerado quando o usu�rio tentar excluir algum objeto no sistema.
	 * 
	 * @param titulo
	 * @param conteudo
	 * @return Optional<ButtonType> - bot�o de confirma��o
	 */
	public static Optional<ButtonType> Confirma��o(String titulo, String conteudo) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(conteudo);
		return alert.showAndWait();
	}
}
