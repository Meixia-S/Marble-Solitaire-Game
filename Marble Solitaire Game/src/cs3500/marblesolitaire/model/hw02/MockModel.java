package cs3500.marblesolitaire.model.hw02;

import java.io.IOException;
import java.util.Objects;

/**
 * A mock of the model class that is used to test the relationship between the controller and model.
 */
public class MockModel implements MarbleSolitaireModel {
  private final Appendable appendable;

  public MockModel(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    try {
      this.appendable.append(fromRow + " " + fromCol + " " + toRow + " " + toCol);
    }
    catch (IOException e) {
      throw new IllegalArgumentException("Appendable Failed.");
    }
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}