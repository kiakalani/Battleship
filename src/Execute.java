import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Kia Kalani
 * <code>Execute</code> class is responsible for running the code.
 * @version 1.00
 * @since 1.00
 */
public class Execute extends Application {
    /**
     * A static variable for having flexibility with the stage functionality.
     */
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        changeScene(new GameStage().getScene());
        stage.show();
    }

    /**
     * A method for changing the display.
     *
     * @param scene is the scene of the new display.
     */
    public static void changeScene(Scene scene) {
        stage.setScene(scene);
    }

    /**
     * The main method
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
