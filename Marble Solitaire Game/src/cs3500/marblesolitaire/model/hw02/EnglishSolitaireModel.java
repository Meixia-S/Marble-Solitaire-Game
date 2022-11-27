package cs3500.marblesolitaire.model.hw02;


import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModelImpl;

/**
 * This class extends the AbstractMarbleSolitaireModelImpl class and indirectly implements the
 * MarbleSolitaireModel interface.
 * It is where the inner working of the game that communicates with the controller and view.
 * This class implements a different type of board (English).
 */
public class EnglishSolitaireModel extends AbstractMarbleSolitaireModelImpl {

  /**
   * First constructor that takes in 0 parameters.
   * (Assumes armThickness is 3, sRow is 3, and sCol is 3)
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Second constructor that takes in 2 parameters where the Empty slot is given (sRow and sCol).
   * (Assumes armThickness is 3)
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(3, sRow, sCol);
  }

  /**
   * Third constructor that takes in 1 parameters where armThickness is given.
   * The armThickness must be larger than 0 and an odd number.
   * sRow and sCol are then calculated based of the given armThickness.
   */
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness, (((armThickness * 3) - 2) / 2), (((armThickness * 3) - 2) / 2));
  }

  /**
   * Fourth constructor that takes in 3 parameters where armThickness, sRow, and sCol are given.
   * The armThickness must be greater than 0 and an odd number.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  @Override
  protected boolean isInvalid(int row, int col) {
    return ((row < (super.armThickness - 1) && col < (super.armThickness - 1)) //UL
            || (row > ((super.armThickness * 2) - 2) && (col < (super.armThickness - 1))) //BL
            || (row < (super.armThickness - 1) && col > ((super.armThickness * 2) - 2)) //UR
            || (row > (super.armThickness * 2) - 2) && col > ((super.armThickness * 2) - 2));
  }
}