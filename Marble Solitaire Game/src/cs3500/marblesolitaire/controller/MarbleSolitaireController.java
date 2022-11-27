package cs3500.marblesolitaire.controller;

/**
 * This interface represents operations that should be offered by
 * the controller for the Marble solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * Takes in inputs from the player and calls upon the viewer and model to complete the operations
   * of the Marble solitaire game. It returns the board and any relevant messages to the player to
   * aid in their enjoyment of the game.
   * @throws IllegalStateException if any of the fields are null
   */
  public void playGame() throws IllegalStateException;
}
