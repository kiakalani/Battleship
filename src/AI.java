import java.util.Random;

/**
 * @author Kia Kalani
 * <code>AI</code> refers to the implementation of a finite state machine
 * for having an opponent in the game.
 * @version 1.00
 * @since 1.00
 */
public class AI {
    /**
     * The random object used for initially locating a ship.
     */
    private final Random random = new Random();
    /**
     * The objects of the game boards.
     */
    private Board board, playerBoard;
    /**
     * Save objects containing the row, column, row adder and the column adder.
     */
    private int[] save;

    /**
     * An enum identifying what stage the AI is currently on.
     */
    private enum Stage {
        WONDER_FOR_SHIP, FIND_OTHER_SIDE, KEEP_ATTACKING
    }

    /**
     * The object for the current stage
     */
    private Stage stage;

    /**
     * The constructor
     * @param board the AI's board
     * @param playerBoard the board that the player sets up.
     */
    public AI(Board board, Board playerBoard) {
        this.board = board;
        this.playerBoard = playerBoard;
        stage = Stage.WONDER_FOR_SHIP;
        save = new int[4];
    }

    /**
     * This method gets called for playing the AI's turn.
     */
    public void act() {
        if (stage == Stage.WONDER_FOR_SHIP) {
            wonderForShip();
        } else if (stage == Stage.FIND_OTHER_SIDE) {
            findOtherSide();
        } else if (stage == Stage.KEEP_ATTACKING) {
            keepAttacking();
        }
    }

    /**
     * The wondering for ship stage by looking at random spots until
     * a ship is located. By locating it, we would try to find out what way the ship is facing.
     */
    private void wonderForShip() {
        int row = random.nextInt(10);
        int col = random.nextInt(10);
        while (playerBoard.getCell(row, col).isAttacked()) {
            row = random.nextInt(10);
            col = random.nextInt(10);
        }
        playerBoard.getCell(row, col).setAttacked(true);
        if (playerBoard.getCell(row, col).isShipLocated()) {
            save[0] = row;
            save[1] = col;
            stage = Stage.FIND_OTHER_SIDE;
        } else {
            save[0] = -1;
            save[1] = -1;
            save[2] = 0;
            save[3] = 0;
        }
    }

    /**
     * This method would try to identify another end of the ship that has been located.
     * Afterwards it would keep attacking in that direction.
     */
    private void findOtherSide() {
        int row = save[0];
        int col = save[1];
        if (row + 1 < 10 && !playerBoard.getCell(row + 1, col).isAttacked()) {
            playerBoard.getCell(row + 1, col).setAttacked(true);
            if (playerBoard.getCell(row + 1, col).isShipLocated()) {
                save[0] = row + 1;
                save[1] = col;
                save[2] = 1;
                save[3] = 0;
                stage = Stage.KEEP_ATTACKING;
            }
        } else if (row - 1 > 0 && !playerBoard.getCell(row - 1, col).isAttacked()) {
            playerBoard.getCell(row - 1, col).setAttacked(true);
            if (playerBoard.getCell(row - 1, col).isShipLocated()) {
                save[0] = row - 1;
                save[1] = col;
                save[2] = -1;
                save[3] = 0;
                stage = Stage.KEEP_ATTACKING;
            }
        } else if (col + 1 < 10 && !playerBoard.getCell(row, col + 1).isAttacked()) {
            playerBoard.getCell(row, col + 1).setAttacked(true);
            if (playerBoard.getCell(row, col + 1).isShipLocated()) {
                save[0] = row;
                save[1] = col + 1;
                save[2] = 0;
                save[3] = 1;
                stage = Stage.KEEP_ATTACKING;
            }
        } else if (col - 1 > 0 && !playerBoard.getCell(row, col - 1).isAttacked()) {
            playerBoard.getCell(row, col - 1).setAttacked(true);
            if (playerBoard.getCell(row, col - 1).isShipLocated()) {
                save[0] = row;
                save[1] = col - 1;
                save[2] = 0;
                save[3] = -1;
                stage = Stage.KEEP_ATTACKING;
            }
        }

    }

    /**
     * This method keeps attacking the direction that the bot has identified.
     */
    private void keepAttacking() {
        int row = save[0];
        int col = save[1];
        int rowAdder = save[2];
        int colAdder = save[3];
        playerBoard.getCell(row+rowAdder, col+colAdder).setAttacked(true);
        if (!playerBoard.getCell(row+rowAdder,col+colAdder).isShipLocated()) {
            stage = Stage.WONDER_FOR_SHIP;
        }
    }
}
