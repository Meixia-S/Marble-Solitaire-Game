package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This is an abstract class that helps reduce redundant code between the two different views.
 * It implements the MarbleSolitaireModel interface which allows the other models to indirectly
 * implement the methods needed to display the board to the player. Any methods that can not be
 * abstract are Overrided in that particular view class.
 */
public abstract class AbstractMarbleSolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState model;
  protected Appendable appendable;

  /**
   * First constructor that only takes in a model.
   *
   * @param model represents the internal workings of the game and offers the operations
   *              within it.
   * @throws IllegalArgumentException if the model is null.
   */
  public AbstractMarbleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("One or both inputs are null.");
    }
    this.model = model;
    this.appendable = System.out;
  }

  /**
   * Second constructor that takes in a model and an appendable.
   *
   * @param model      represents the internal workings of the game and offers the operations
   *                   within it.
   * @param appendable an object in which char sequences and values are appended
   * @throws IllegalArgumentException if either model or appendable is null
   */
  public AbstractMarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable)
          throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("One or both inputs are null.");
    }
    this.model = model;
    this.appendable = appendable;
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
    int number = (model.getBoardSize() + 2) / 3;
    // this skips the " " in the given row and column (aka Invalid slots on the right side)
    if (col > (number * 2) - 2
            && model.getSlotAt(row, col).equals(MarbleSolitaireModelState.SlotState.Invalid)) {
      // nothing is done here since we do not want to print a space or symbol
      // (aka any Invalid on the right side of the board)
    }
    // checks if the given row has Invalid and the given column is the last one before Invalid
    else if (col >= (number * 2) - 3 && col < model.getBoardSize() - 1
            && model.getSlotAt(row, col + 1).equals(MarbleSolitaireModelState.SlotState.Invalid)) {
      // checks if the given row is the last one
      if (row == model.getBoardSize() - 1) {
        line = line + symbol;
      } else {
        line = line + symbol + "\n";
      }
    }
    // to make sure the last row and column do not have a \n in it
    else if (row != model.getBoardSize() - 1 && col == model.getBoardSize() - 1) {
      line = line + symbol + "\n";
    }
    // just adds the symbol with a space after it
    else {
      line = line + symbol + " ";
    }
    return line;
  }

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
          buildBoard = printNewLine(row, col, buildBoard, " ");
        }
      }
    }
    return buildBoard;
  }

  @Override
  public void renderBoard() throws IOException {
    appendable.append(toString());
    appendable.append("\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    appendable.append(message);
  }
}