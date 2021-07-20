package br.com.floricultura.utils;

import javafx.scene.control.TextField;

/**
 * Classe responsável por disponibilizar métodos estáticos que irão restringir
 * alguns dados que serão passados nos campos de textos das views. 
 * 
 * @author luana - UFERSA
 * @version 1.4
 */
public class Restricoes {

	public static void setCampoTextoInteiro(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*")) {
				txt.setText(oldValue);
			}
		});
	}

	public static void setTamanhoMaximoCampoTexto(TextField txt, int max) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && newValue.length() > max) {
				txt.setText(oldValue);
			}
		});
	}

	public static void setCampoTextoDouble(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
				txt.setText(oldValue);
			}
		});
	}

}
