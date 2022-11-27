package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class implements the MarbleSolitaireController interface.
 * It takes in player input and displays the state of the game as well as presenting messages that
 * help the player play through the game.
 * It calls upon both the model and view to execute the game for the player.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable input;
  private boolean hasQuite = false;

  /**
   * The constructor that takes in a model, view, and an appendable.If any of those three fields
   * are null, an IllegalArgumentException will be thrown.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("One or all three of the inputs are null.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner s = new Scanner(this.input);
    try {
      view.renderMessage("Start by Making a Move" + "\n");
      // Step 1
      view.renderBoard();
      // Step 2
      view.renderMessage("Score: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    // main while loop that runs the method until the game is over
    while (!model.isGameOver() && !hasQuite) {
      try {

        makingMove(s);

      } catch (NoSuchElementException e) {
        throw new IllegalStateException(e);
      }
    }

    // setting the board once the game is over (aka when the player comes to a natural end)
    if (model.isGameOver()) {
      try {
        view.renderMessage("Game over!" + "\n");
        view.renderBoard();
        view.renderMessage("Score: " + model.getScore() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
  }

  /**
   * Print the desired message in a try catch. Helps with decluttering code.
   * @param message is the message that should be displayed to the player
   */
  private void printStatement(String message) {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * Takes in user inputs and splits them up into groups of four inorder to preform the desired move
   * for the player. Makes sure the inputs are valid and displays messages to help the player.
   * @param s the scanner that reads the inputs of the player
   */
  private void makingMove(Scanner s) {

    int count = 0;
    int[] integerArray = new int[4];
    String nextValue;

    while (count < 4 && !this.hasQuite) {
      try {
        nextValue = s.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException(e);
      }

      try {
        // checking to see if the player wants to quit the game
        if (nextValue.equals("q") || nextValue.equals("Q")) {
          view.renderMessage("\nGame quit!" + "\n");
          view.renderMessage("State of game when quit:" + "\n");
          view.renderBoard();
          view.renderMessage("Score: " + model.getScore() + "\n");
          this.hasQuite = true;
          count = 5;

        } else {
          try {
            integerArray[count] = Integer.parseInt(nextValue);
            count++;
          } catch (NumberFormatException e) {
            printStatement(
                    "Invalid Move! Play again. Make sure your inputs are natural numbers!\n");
          }
        }
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }

    // making the move and rendering the board only when four valid inputs are in
    if (integerArray[0] > 0 && integerArray[1] > 0 && integerArray[2] > 0 && integerArray[3] > 0) {
      try {
        model.move(integerArray[0] - 1, integerArray[1] - 1,
                integerArray[2] - 1, integerArray[3] - 1);
        view.renderBoard();
        view.renderMessage("Score: " + model.getScore() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
      catch (IllegalArgumentException e) {
        printStatement("Sorry, not a valid move!"); }
    }
  }
}