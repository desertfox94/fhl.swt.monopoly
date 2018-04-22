package monopoly;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Monopoly");
			initRootLayout(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout(Stage primaryStage) {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/AppView.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setMinHeight(rootLayout.getMinHeight());
			primaryStage.setMinWidth(rootLayout.getMinWidth());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	// This is where the fun begins.
	public static void main(String[] args) {
		launch(args);
	}

}
