package cs3500.marblesolitaire.model.hw04;

/**
 * This class extends the AbstractMarbleSolitaireModelImpl class and indirectly implements the
 * MarbleSolitaireModel interface.
 * It is where the inner working of the game that communicates with the controller and view.
 * This class implements a different type of board (Triangle).
 */
public class TriangleSolitaireModel extends AbstractMarbleSolitaireModelImpl {

  /**
   * First constructor that takes in 0 parameters.
   * (Assumes armThickness is 5, sRow is 0, and sCol is 0)
   */
  public TriangleSolitaireModel() {
    super.armThickness = 5;
    super.sRow = 0;
    super.sCol = 0;

    this.buildBoard();
  }

  /**
   * Second constructor that takes in 1 parameters, the armThickness.
   * (Assumes sRow is 0, and sCol is 0)
   */
  public TriangleSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness <= 0)  {
      throw new IllegalArgumentException("The armThickness must be a positive number.");
    }
    super.armThickness = armThickness;
    super.sRow = 0;
    super.sCol = 0;

    this.buildBoard();
  }

  /**
   * Third constructor that takes in 2 parameters, the sRow  and sCol (aka where the empty slot
   * is at the beginning of the game, and it is assumed that the armThickness is 5).
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    if (isInvalid(sRow,sCol))  {
      throw new IllegalArgumentException(
              "Invalid Empty Slot Position (" + sRow + "," + sCol + ").");
    }
    super.armThickness = 5;
    super.sRow = sRow;
    super.sCol = sCol;

    this.buildBoard();
  }

  /**
   * Fourth constructor that takes in 3 parameters, the armThickness, sRow  and sCol.
   * Makes sure the user input is valid before running the program.
   */
  public TriangleSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness <= 0)  {
      throw new IllegalArgumentException("The armThickness must be a positive number.");
    }
    else {
      super.armThickness = armThickness;
    }

    if (isInvalid(sRow,sCol)) {
      throw new IllegalArgumentException(
              "Invalid Empty Slot Position (" + sRow + "," + sCol + ").");
    }
    else {
      super.sRow = sRow;
      super.sCol = sCol;
    }

    this.buildBoard();
  }

  @Override
  protected boolean isInvalid(int row, int col) {
    return (col > row);
  }

  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow < 0 || fromRow >= getBoardSize()
            || fromCol < 0 || fromCol >= getBoardSize()
            || toRow < 0 || toRow >= getBoardSize()
            || toCol < 0 || toCol >= getBoardSize()) {
      return false;
    } else if ((super.grid[fromRow][fromCol].equals(SlotState.Marble))
            && (super.grid[toRow][toCol].equals(SlotState.Empty))) {
      if ((Math.abs(fromRow - toRow) == 2) && (fromCol == toCol)
              || (fromRow == toRow) && (Math.abs(fromCol - toCol) == 2)
              || ((Math.abs(fromRow - toRow) == 2) && (Math.abs(fromCol - toCol) == 2))) {
        // checking the stat of the middle point
        if ((super.grid[((fromRow + toRow) / 2)][((fromCol + toCol) / 2)]
                .equals(SlotState.Marble))) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < getBoardSize(); row ++) {
      for (int col = 0; col < getBoardSize(); col ++) {
        if ((isValidMove(row, col, row, (col + 2)))
                || (isValidMove(row, col, row, (col - 2)))
                || (isValidMove(row, col, (row + 2), col))
                || (isValidMove(row, col, (row - 2), col))
                || (isValidMove(row, col, (row + 2), col + 2))
                || (isValidMove(row, col, (row - 2), col - 2))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return (super.armThickness);
  }
}