import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
     * A boolean indicating whether the ship should follow the mouse or not.
     */
    private boolean shouldFollow;
    /**
     * <code>AnimationTimer</code> object for updating the position and orientation of the ship
     */
    private AnimationTimer updater;

    /**
     * An enum showing which way the ship is facing.
     */
    private enum Face{
        HORIZONTAL, VERTICAL
    }

    /**
     * The <code>Face</code> object that would show which way the ship would face.
     */
    private Face face;

    /**
     * The constructor.
     * @param length is the length of the ship.
     * @param children is the children of the parent class.
     */
    public Ship(int length, ObservableList<Node> children){
        this(length);
        ship.setFitWidth(35*length);
        shouldFollow = false;
        children.add(ship);
        face = Face.HORIZONTAL;
        updater = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (shouldFollow) {
                    relocateAccordingly(GameStage.getMouseX(),GameStage.getMouseY());
                }
                if (face == Face.VERTICAL) {
                    ship.setRotate(90);
                } else ship.setRotate(0);
            }
        };
        updater.start();
        handle();
    }

    /**
     * Getter of the facing orientation
     * @return the indicated object.
     */
    public Face getFace() {
        return face;
    }

    /**
     * Setter of the face in case it needs to be changed.
     * @param face the facing orientation.
     */
    public void setFace(Face face) {
        this.face = face;
    }

    /**
     * The constructor.
     * <strong>Important:</strong>
     * This constructor is mainly responsible for the opponent's board which should not be shown.
     * @param length the length of the ship.
     */
    public Ship(int length) {
        this.length = length;
    }

    /**
     * The handling method for image movements.
     */
    private void handle() {
        ship.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton()==MouseButton.PRIMARY) {
                    shouldFollow = true;
                }else shouldFollow = false;
            }
        });

    }

    /**
     * This method relocates the ship to the center of the given x and y position.
     * @param x is the x position.
     * @param y is the y position.
     */
    private void relocateAccordingly(double x, double y) {
        if (face == Face.VERTICAL) {
            ship.relocate(x-(ship.getFitWidth()/2),y);
        } else ship.relocate(x-ship.getFitWidth()/2,y-ship.getFitHeight()/2);
    }

    /**
     * This method relocates the ship to the exact given point.
     * @param x is the x position.
     * @param y is the y position.
     */
    public void relocateShip(double x, double y) {
        ship.relocate(x,y);
    }

    /**
     * The getter of should follow boolean.
     * @return shouldFollow boolean.
     */
    public boolean isShouldFollow() {
        return shouldFollow;
    }

    /**
     * This method rotates the ship.
     */
    public void rotate() {
        if (face == Face.HORIZONTAL) {
            face = Face.VERTICAL;
        }else face=Face.HORIZONTAL;
    }
}
