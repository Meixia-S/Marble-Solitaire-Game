package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class implements the MarbleSolitaireView interface.
 * It gives the player an updated visual representation of the board and the game in play.
 * It calls on the model to build the board and is called upon by the controller.
 */
public class MarbleSolitaireTextView extends AbstractMarbleSolitaireTextView {
  private MarbleSolitaireModelState model;
  private Appendable appendable;

  /**
   * First constructor that only takes in a model.
   * @param model represents the internal workings of the game and offers the operations within it.
   * @throws IllegalArgumentException if the model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    super(model, System.out);
  }

  /**
   * Second constructor that takes in a model and an appendable.
   * @param model represents the internal workings of the game and offers the operations within it.
   * @param appendable an object in which char sequences and values are appended
   * @throws IllegalArgumentException if either model or appendable is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable) {
    super(model, appendable);
  }
}