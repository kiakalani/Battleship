import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * @author Kia Kalani
 * <code>Player</code> class acts as a container for the ships
 * @version 1.00
 * @since 1.00
 */
public class Player {
    /**
     * The ships of the game.
     */
    private Ship[] ships;

    /**
     * The constructor.
     * @param children the children of the parent class.
     */
    public Player(ObservableList<Node> children) {
        ships = new Ship[5];
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                ships[i] = new Ship(2,children);
            } else if (i < 3) {
                ships[i] = new Ship(3,children);
            } else ships[i] = new Ship(i+1,children);
            ships[i].relocateShip((i*(1024/5))+5,600);
        }

    }

    /**
     * This method rotates the currently selected ship.
     */
    public void rotateShips() {
        for (int i=0;i<5;i++) {
            if (ships[i].isShouldFollow()) {
                ships[i].rotate();
                break;
            }
        }
    }
}
