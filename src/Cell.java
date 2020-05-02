import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Kia Kalani
 * <code>Cell</code> class refers to the cell position on the board.
 * <strong>Note:</strong>
 * For further information on the usages of this class see:
 * {@link Board}
 * @version 1.00
 * @since 1.00
 */
public class Cell extends Rectangle {
    /**
     * The <code>Color</code> of the cell.
     */
    private final static Color boardColor = Color.GRAY;
    /**
     * A boolean indicating whether the ship is located in this cell.
     */
    private boolean shipLocated;
    /**
     * The default size of the cells.
     */
    public static final double CELLSIZE = 30;

    /**
     * Getter of the color of the board.
     *
     * @return the color of the board.
     */
    public static Color getBoardColor() {
        return boardColor;
    }

    /**
     * The getter of the boolean indicating if there is a ship in this position.
     *
     * @return the boolean indicator.
     */
    public boolean isShipLocated() {
        return shipLocated;
    }

    /**
     * Setter of the ship located boolean
     *
     * @param shipLocated is the new value for the given argument.
     */
    public void setShipLocated(boolean shipLocated) {
        this.shipLocated = shipLocated;
    }

    /**
     * The constructor.
     */
    public Cell() {
        setFill(boardColor);
        setWidth(30);
        setHeight(30);
    }
}
