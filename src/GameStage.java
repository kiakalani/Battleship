import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;

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
    private final Scene scene = new Scene(this, 1024, 768);
    /**
     * The board that would keep track of the player's baord.
     */
    private final Board board = new Board(getChildren());
    /**
     * The board for the opponent.
     */
    private final Board opponentBoard = new Board(getChildren());
    private Button left, right, up, down, done, rotate;
    private final LinkedList<Ship> playerShips = new LinkedList<>();
    private int curRow, curCol;

    private void setButtons(double x, double y) {
        left = new Button("<");
        right = new Button(">");
        up = new Button("^");
        down = new Button("v");
        up.relocate(x + 25, y);
        down.relocate(x + 25, y + 25);
        left.relocate(x, y + 25);
        right.relocate(x + 50, y + 25);
        done = new Button("Done");
        done.relocate(x + 15, y + 50);
        rotate = new Button("Rotate");
        rotate.relocate(x + 15, y - 25);
        getChildren().addAll(left, right, up, down, done, rotate);
    }

    /**
     * The constructor.
     */
    public GameStage() {
        board.relocate(90, 90);
        opponentBoard.relocate(500, 90);
        setButtons(600, 650);
        curRow = 0;
        curCol = 0;
        playerShips.add(new Ship(2, getChildren(), board.getLayoutX() + 35 * curRow, board.getLayoutY() + 35 * curCol));
        handleButtons();
    }

    private void moveShip() {
        Ship cur = playerShips.get(playerShips.size() - 1);
        if (curRow > 9) {
            curRow = 0;
        }
        if (curRow < 0) {
            curRow = 9;
        }
        if (curCol > 9) {
            curCol = 0;
        }
        if (curCol < 0) {
            curCol = 9;
        }
        if (cur.getFace() == Ship.Face.HORIZONTAL && curRow + playerShips.get(playerShips.size() - 1).getLength() > 10) {
            curRow = 0;
        }
        if (cur.getFace() == Ship.Face.VERTICAL && curCol + cur.getLength() > 10) {
            curCol = 0;
        }
        if (cur.getFace()== Ship.Face.HORIZONTAL&&curRow<0) {
            curRow = 10-cur.getLength();
        }
        if (cur.getFace() == Ship.Face.VERTICAL&&curCol<0) {
            curCol = 10-cur.getLength();
        }
        playerShips.get(playerShips.size() - 1).relocateShip(board.getLayoutX() + 35 * curRow, board.getLayoutY() + 35 * curCol);
    }
    private void handleButtons() {
        left.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curRow-=1;
                moveShip();
            }
        });
        right.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curRow+=1;
                moveShip();
            }
        });
        up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curCol-=1;
                moveShip();
            }
        });
        down.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curCol+=1;
                moveShip();
            }
        });
        rotate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playerShips.get(playerShips.size()-1).rotate();
                moveShip();
            }
        });
        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isValidPos()) {
                    if (playerShips.size() == 5) {
                        getChildren().removeAll(left, right, up, down, done, rotate);
                    }else {
                        if (playerShips.getLast().getFace() == Ship.Face.VERTICAL) {
                            for (int r = curRow;r<curRow+playerShips.getLast().getLength();r++) {
                                board.getCell(r,curCol).setShipLocated(true);
                            }
                        } else {
                            for (int c=curCol;c<curCol+playerShips.getLast().getLength();c++) {
                                board.getCell(curRow,c).setShipLocated(true);
                            }
                        }
                        curRow = 0;
                        curCol = 0;
                        if (playerShips.size() < 3) {
                            playerShips.add(new Ship(3, getChildren(), 0, 0));
                        } else if (playerShips.size() == 3) {
                            playerShips.add(new Ship(4, getChildren(), 0, 0));
                        } else {
                            playerShips.add(new Ship(5, getChildren(), 0, 0));
                        }
                        moveShip();
                    }

                } else showInvalid();
            }
        });
    }
    private boolean isValidPos() {
        Ship cur = playerShips.get(playerShips.size()-1);
        for (int i=0;i<playerShips.size()-1;i++) {
            if (cur.collides(playerShips.get(i).getBounds())) {
                return false;
            }
        }
        return true;
    }
    private void showInvalid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid position");
        alert.setHeaderText("Error");
        alert.setContentText("Sorry; this position is not valid.");
        alert.showAndWait();
    }

}
