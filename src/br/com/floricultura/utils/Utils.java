package br.com.floricultura.utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	/**
	 * M�todo est�tico respons�vel por retornar o stage atual referente a a��o que foi
	 * acionada. Caso o usu�rio clique em um bot�o, vai pegar o palco refetente aquele bot�o. 
	 * 
	 * @param event - Objeto de evento.
	 * @return Stage - Stage atual.
	 */
	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * m�todo respons�vel por converter o campo de texto pra inteiro; 
	 * @param str - nome do campo
	 * @return Integer - n�mero Inteiro. 
	 */
	public static Integer converterParaInteiro(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	/**
	 * m�todo respons�vel por converter o campo de texto pra Double; 
	 * @param str - nome do campo
	 * @return Double - n�mero Double. 
	 */
	public static Double converterParaDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
