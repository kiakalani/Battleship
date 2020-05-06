import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * @author Kia Kalani
 * <code>Ship</code> class contains all of the functionalities needed for
 * ships according to the logic of the game.
 * @version 1.00
 * @since 1.00
 */
public class Ship {
    /**
     * The image view of the ship.
     */
    private final ImageView ship = new ImageView("img/ship.jpg");
    /**
     * The length of the ship.
     */
    private int length;

    /**
     * An enum showing which way the ship is facing.
     */
    protected enum Face {
        HORIZONTAL, VERTICAL
    }

    /**
     * The <code>Face</code> object that would show which way the ship would face.
     */
    private Face face;

    public int getLength() {
        return length;
    }

    /**
     * The constructor.
     *
     * @param length   is the length of the ship.
     * @param children is the children of the parent class.
     */
    public Ship(int length, ObservableList<Node> children, double x, double y) {
        this(length);
        ship.setFitWidth(34.5 * length);
        System.out.println(x);
        relocateShip(x, y);
        children.add(ship);
        face = Face.HORIZONTAL;
    }

    /**
     * Getter of the facing orientation
     *
     * @return the indicated object.
     */
    public Face getFace() {
        return face;
    }

    /**
     * Setter of the face in case it needs to be changed.
     *
     * @param face the facing orientation.
     */
    public void setFace(Face face) {
        this.face = face;
    }

    /**
     * The constructor.
     * <strong>Important:</strong>
     * This constructor is mainly responsible for the opponent's board which should not be shown.
     *
     * @param length the length of the ship.
     */
    public Ship(int length) {
        this.length = length;
    }


    /**
     * This method relocates the ship to the exact given point.
     *
     * @param x is the x position.
     * @param y is the y position.
     */
    public void relocateShip(double x, double y) {
        ship.relocate(x, y);
        if (face == Face.VERTICAL) {
            if (length == 2) {
                ship.relocate(x - 20, y + 20);
            } else if (length == 3) {
                ship.relocate(x-35,y+40);
            } else if (length == 4) {
                ship.relocate(x-57,y+57);
            } else if (length == 5) {
                ship.relocate(x-75,y+73);
            }
        }
    }


    /**
     * This method rotates the ship.
     */
    public void rotate() {
        if (face == Face.HORIZONTAL) {
            face = Face.VERTICAL;
            ship.setRotate(90);
        } else {
            face = Face.HORIZONTAL;
            ship.setRotate(0);
        }
    }

    public boolean collides(Bounds objectBounds) {
        return ship.getBoundsInParent().intersects(objectBounds);
    }

    public Bounds getBounds() {
        return ship.getBoundsInParent();
    }
}
