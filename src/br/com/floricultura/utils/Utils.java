package br.com.floricultura.utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	/**
	 * Método estático responsável por retornar o stage atual referente a ação que foi
	 * acionada. Caso o usuário clique em um botão, vai pegar o palco refetente aquele botão. 
	 * 
	 * @param event - Objeto de evento.
	 * @return Stage - Stage atual.
	 */
	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * método responsável por converter o campo de texto pra inteiro; 
	 * @param str - nome do campo
	 * @return Integer - número Inteiro. 
	 */
	public static Integer converterParaInteiro(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	/**
	 * método responsável por converter o campo de texto pra Double; 
	 * @param str - nome do campo
	 * @return Double - número Double. 
	 */
	public static Double converterParaDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
