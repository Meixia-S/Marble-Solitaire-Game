package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This is an abstract class that helps reduce redundant code between the three different models.
 * It implements the MarbleSolitaireModel interface which allows the other models to indirectly
 * implement the methods needed to run the game. Any methods that can not be abstract are
 * Overrided in that particular model class.
 */
public abstract class AbstractMarbleSolitaireModelImpl implements MarbleSolitaireModel {
  protected int armThickness;
  protected int sRow;
  protected int sCol;
  protected SlotState[][] grid;

  /**
   * First constructor that takes in 0 parameters.
   * (Assumes armThickness is 3, sRow is 3, and sCol is 3)
   */
  public AbstractMarbleSolitaireModelImpl() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;

    this.buildBoard();
  }

  /**
   * Second constructor that takes in 1 parameters where armThickness is given.
   * The armThickness must be larger than 0 and an odd number.
   * sRow and sCol are then calculated based of the given armThickness.
   */
  public AbstractMarbleSolitaireModelImpl(int armThickness) {
    if ((armThickness % 2 == 0) || (armThickness <= 0)) {
      throw new IllegalArgumentException(
              "Invalid Thickness As It Must Be An Odd and Positive Number.");
    } else {
      this.armThickness = armThickness;
      this.sRow = ((this.armThickness - 3) / 2);
      this.sCol = ((this.armThickness - 3) / 2);

      this.buildBoard();
    }
  }

  /**
   * Second constructor that takes in 2 parameters where the Empty slot is given (sRow and sCol).
   * (Assumes armThickness is 3)
   */
  public AbstractMarbleSolitaireModelImpl(int sRow, int sCol) {
    this.armThickness = 3;
    if (isInvalid(sRow, sCol)) {
      throw new IllegalArgumentException(
            "Invalid Empty Slot Position (" + sRow + "," + sCol + ").");
    } else if (sRow < 0 || sCol >= ((this.armThickness * 3) - 2)) {
      throw new IllegalArgumentException("This Is A Slot Beyond The Board.");
    } else {
      this.sRow = sRow;
      this.sCol = sCol;
    }

    this.buildBoard();
  }

  /**
   * Fourth constructor that takes in 3 parameters where armThickness, sRow, and sCol are given.
   * The armThickness must be greater than 0 and an odd number.
   */
  public AbstractMarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if ((armThickness > 0) && (armThickness % 2 != 1)) {
      throw new IllegalArgumentException(
              "Invalid Thickness As It Must Be An Odd and Positive Number.");
    } else {
      this.armThickness = armThickness;
    }

    if (isInvalid(sRow, sCol) || (sRow < 0 || sCol >= ((this.armThickness * 3) - 2))) {
      throw new IllegalArgumentException(
              "Invalid Empty Slot Position (" + sRow + "," + sCol + ").");
    } else {
      this.sRow = sRow;
      this.sCol = sCol;
    }

    this.buildBoard();
  }

  /**
   * Determines whether-or-not a given point's Slot State is Invalid.
   *
   * @param row the row the current point being viewed
   * @param col the column the current point being viewed
   * @return a boolean: true when the Slot State is Invalid and false when Empty or Marble
   */
  protected boolean isInvalid(int row, int col) {
    return false;
  }

  /**
   * Assembles the board for the solitaire game (assigns each coordinate with a SlotState).
   */
  protected void buildBoard() {
    this.grid = new SlotState[getBoardSize()][getBoardSize()];
    for (int row = 0; row < getBoardSize(); row ++) {
      for (int col = 0; col < getBoardSize(); col ++) {
        if (row == this.sRow && col == sCol) {
          this.grid[row][col] = SlotState.Empty;
        }
        else if (isInvalid(row, col)) {
          this.grid[row][col] = SlotState.Invalid;
        }
        else {
          this.grid[row][col] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * Determines if the given move is possible/valid.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @return a boolean: true if the move given is possible/valid and false if it is not
   *     possible/invalid
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow < 0 || fromRow >= getBoardSize()
            || fromCol < 0 || fromCol >= getBoardSize()
            || toRow < 0 || toRow >= getBoardSize()
            || toCol < 0 || toCol >= getBoardSize()) {
      return false;
    } else if ((this.grid[fromRow][fromCol].equals(SlotState.Marble))
            && (this.grid[toRow][toCol].equals(SlotState.Empty))) {
      if ((Math.abs(fromRow - toRow) == 2) && (fromCol == toCol) ||
              (fromRow == toRow) && (Math.abs(fromCol - toCol) == 2)) {
        // checking the stat of the middle point
        if ((this.grid[((fromRow + toRow) / 2)][((fromCol + toCol) / 2)]
                .equals(SlotState.Marble))) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (isValidMove(fromRow, fromCol, toRow, toCol)) {
      this.grid[fromRow][fromCol] = (SlotState.Empty);
      this.grid[toRow][toCol] = (SlotState.Marble);
      this.grid[((fromRow + toRow) / 2)][((fromCol + toCol) / 2)] = (SlotState.Empty);
    } else {
      throw new IllegalArgumentException("This Move Is Not Possible");
    }
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < ((this.armThickness * 3) - 2); row ++) {
      for (int col = 0; col < ((this.armThickness * 3) - 2); col ++) {
        if ((isValidMove(row, col, row, (col + 2)))
                || (isValidMove(row, col, row, (col - 2)))
                || (isValidMove(row, col, (row + 2), col))
                || (isValidMove(row, col, (row - 2), col))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return ((this.armThickness * 3) - 2);
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > getBoardSize()
            || col < 0 || col > getBoardSize()) {
      throw new IllegalArgumentException("This Is A Slot Beyond The Board.");
    } else {
      return grid[row][col];
    }
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < getBoardSize(); row ++) {
      for (int col = 0; col < getBoardSize(); col ++) {
        if (this.grid[row][col].equals(SlotState.Marble)) {
          score += 1;
        }
      }
    }
    return score;
  }
}