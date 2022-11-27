package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class extends the AbstractMarbleSolitaireModelImpl class and indirectly implements the
 * MarbleSolitaireModel interface.
 * It is where the inner working of the game that communicates with the controller and view.
 * This class implements a different type of board (Triangular).
 **/
public class TriangleSolitaireTextView extends AbstractMarbleSolitaireTextView {

  /**
   * First constructor that only takes in a model.
   *
   * @param model represents the internal workings of the game and offers the operations within it.
   * @throws IllegalArgumentException if the model is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model, System.out);
  }

  /**
   * Second constructor that takes in a model and an appendable.
   *
   * @param model      represents the internal workings of the game and offers the operations
   *                   within it.
   * @param appendable an object in which char sequences and values are appended
   * @throws IllegalArgumentException if either model or appendable is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable)
          throws IllegalArgumentException {
    super(model, appendable);
  }

  /**
   * This method is used to first determine whether-or-not a new line is needed to make the rows
   * and then to append the symbols to it to represent the board game.
   *
   * @param row    the row number of the slot we are currently traversing over
   * @param col    the column number of the slot we are currently traversing over
   * @param line   is the String we are currently building on
   *               (just like buildBoard in the toString() method)
   * @param symbol is the String we use to symbolize the marble, empty, and invalid SlotState
   * @return a boolean that determines if a new line is needed
   */
  private String printNewLine(int row, int col, String line, String symbol) {
    // here we are addressing the first slot in each row
    if (col == 0) {
      int spaceCounter = model.getBoardSize() - (row + 1);
      for (int spacer = 1; spacer <= spaceCounter; spacer++) {
        line = line + " ";
      }
      line = line + symbol;
      // this is for any other slot that is not in col 0 or Invalid
    } else {
      line = line + " " + symbol; }
    return line;
  }

  /**
   * I am not abstracting the toString method since the Triangle only needs to know if the given
   * point is in the last row while the European and English need to know the row and col when
   * omitting a new line after the symbol.
   */
  @Override
  public String toString() {
    String buildBoard = "";
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        if (model.getSlotAt(row, col).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          buildBoard = printNewLine(row, col, buildBoard, "O");
        } else if (model.getSlotAt(row, col).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          buildBoard = printNewLine(row, col, buildBoard, "_");
        } else if (model.getSlotAt(row, col).equals(MarbleSolitaireModelState.SlotState.Invalid)) {
         // nothing is done here since we do not want to print a space or symbol
        }
      }
      if (row != model.getBoardSize() - 1) {
        // adds a new line if we are not on the last row
        buildBoard = buildBoard + "\n";
      }
    }
    return buildBoard;
  }
}