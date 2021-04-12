package Analisis;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;

import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane mainLayout;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage =primaryStage;
		this.primaryStage.setTitle("Nutri-check");
		this.primaryStage.getIcons().add(new Image("main/java/Imagenes/Logo Nutri-check.png"));
		this.primaryStage.setResizable(false);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout = loader.load();
		mainLayout.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(mainLayout);

		// Seteo la scene y la muestro
		primaryStage.setScene(scene);
		primaryStage.show();

	}



	public static void main(String[] args) {
		launch(args);
		System.out.println(System.getProperty("java.class.path"));
	}
}
