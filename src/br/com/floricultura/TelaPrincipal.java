package br.com.floricultura;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaPrincipal extends Application {
	
	private static Stage stage; 
	private static Scene cenaPrincicpal; 
	
	@Override
	public void start(Stage stage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/floricultura/view/paginaInicial/PaginaInicial.fxml")); // carrega o FXML  
			cenaPrincicpal = new Scene(root); //colocar o fxml em uma cena
			
			stage.setResizable(false);
			stage.setTitle("Flor&Cultura");
			stage.setScene(cenaPrincicpal); //coloca cena em uma janela
			stage.show(); // abre a janela 
			setStage(stage); // colocando a janela em um stage
			
		} catch (IOException  e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	public static Scene getCenaPrincipal() {
		return cenaPrincicpal;
	}
	
	public static void setStage(Stage stage) {
		TelaPrincipal.stage= stage;
	}
}

