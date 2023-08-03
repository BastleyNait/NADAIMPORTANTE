import Controller.MenuPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Cargar el archivo FXML y vincularlo con el controlador
            Parent root = FXMLLoader.load(getClass().getResource("FXML/menuPrincipal.fxml"));
            // Crear una escena y asignarla a la ventana principal
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("DOTA SAC - ALMACENES");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
