import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author Kia Kalani
 * <code>GameStage</code> class is the stage that the gameplay itself takes place.
 * <strong>Important:</strong>
 * This class is responsible for putting all of the game contents together.
 * For further information on the execution of this class see:
 * {@link Execute}
 * @version 1.00
 * @since 1.00
 */
public class GameStage extends Group {
    /**
     * The scene for displaying the contents.
     */
    private final Scene scene = new Scene(this,1024,768);
    /**
     * The board that would keep track of the player's baord.
     */
    private final Board board = new Board(getChildren());
    /**
     * The board for the opponent.
     */
    private final Board opponentBoard = new Board(getChildren());
    /**
     * The player object contains all the ships.
     */
    private final Player player = new Player(getChildren());
    /**
     * The x and y position of the mouse.
     */
    private static double mouseX, mouseY;

    /**
     * Mouse position getter.
     * @return the x position of the mouse.
     */
    public static double getMouseX() {
        return mouseX;
    }

    /**
     * Mouse position getter.
     * @return the y position of the mouse.
     */
    public static double getMouseY() {
        return mouseY;
    }

    /**
     * The constructor.
     */
    public GameStage() {
        board.relocate(90,90);
        opponentBoard.relocate(500,90);
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.rotateShips();
            }
        });
    }
}
