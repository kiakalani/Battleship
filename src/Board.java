import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * @author Kia Kalani
 * <code>Board</code> class is the container of the grid that refers to the
 * board of the game.
 * <strong>Note: </strong>
 * <p>This class is not used as a main display for the game.</p>
 * For further information on the usages of this class see:
 * {@link GameStage}
 * @version 1.00
 * @since 1.00
 */
public class Board extends GridPane {
    /**
     * The grid for the game board.
     */
    private final Cell[][] cells = new Cell[10][10];

    /**
     * This method is responsible for initiating the contents of the board.
     * @param children the children of the parent display object.
     */
    private void setupBoard(ObservableList<Node> children) {
        for(int i =0;i<10;i++) {
            addRow(i);
            addColumn(i);
        }
        for (int r=0;r<10;r++) {
            for (int c=0;c<10;c++) {
                cells[r][c] = new Cell();
                add(cells[r][c],r,c);
            }
        }
        children.add(this);
    }

    /**
     * The constructor
     * @param children the children of the parent display object.
     */
    public Board(ObservableList<Node> children) {
        setupBoard(children);
        setVgap(5);
        setHgap(5);
    }
}
