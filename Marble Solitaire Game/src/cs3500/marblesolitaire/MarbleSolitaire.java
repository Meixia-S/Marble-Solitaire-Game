package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import java.io.InputStreamReader;

/**
 * The class that is used to run the game and check to see if the general functions of the game
 * work properly.
 */
public class MarbleSolitaire {

  /**
   * This constructor allows me to play the game and act as the player to test out the operations
   * and format.
   */
  public static void main(String[] args) {

    Readable read = new InputStreamReader(System.in);
    Appendable append = System.out;

    if ("English".equalsIgnoreCase(args[0])) {
      if (args.length > 7) {
        throw new IllegalArgumentException("Not a valid start.");
      } else if (args.length == 7) {
        EnglishSolitaireModel model1 = new EnglishSolitaireModel(Integer.parseInt(args[2]),
                Integer.parseInt(args[4]), Integer.parseInt(args[6]));
        MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(model1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(model1, view1, read);
        controller1.playGame();
      } else if (args.length == 5) {
        EnglishSolitaireModel model1 = new EnglishSolitaireModel(Integer.parseInt(args[2]),
                Integer.parseInt(args[4]));
        MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(model1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(model1, view1, read);
        controller1.playGame();
      } else if (args.length == 3) {
        EnglishSolitaireModel model1 = new EnglishSolitaireModel(Integer.parseInt(args[2]));
        MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(model1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(model1, view1, read);
        controller1.playGame();
      } else if (args.length == 1) {
        EnglishSolitaireModel model1 = new EnglishSolitaireModel();
        MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(model1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(model1, view1, read);
        controller1.playGame();
      }
    }

    if ("European".equalsIgnoreCase(args[0])) {
      if (args.length > 7) {
        throw new IllegalArgumentException("Not a valid start.");
      } else if (args.length == 7) {
        EuropeanSolitaireModel modelE1 = new EuropeanSolitaireModel(Integer.parseInt(args[2]),
                Integer.parseInt(args[4]), Integer.parseInt(args[6]));
        MarbleSolitaireTextView viewE1 = new MarbleSolitaireTextView(modelE1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(modelE1, viewE1, read);
        controller1.playGame();
      } else if (args.length == 5) {
        EuropeanSolitaireModel modelE1 = new EuropeanSolitaireModel(Integer.parseInt(args[2]),
                Integer.parseInt(args[4]));
        MarbleSolitaireTextView viewE1 = new MarbleSolitaireTextView(modelE1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(modelE1, viewE1, read);
        controller1.playGame();
      } else if (args.length == 3) {
        EuropeanSolitaireModel modelE1 = new EuropeanSolitaireModel(Integer.parseInt(args[2]));
        MarbleSolitaireTextView viewE1 = new MarbleSolitaireTextView(modelE1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(modelE1, viewE1, read);
        controller1.playGame();
      } else if (args.length == 1) {
        EuropeanSolitaireModel modelE1 = new EuropeanSolitaireModel();
        MarbleSolitaireTextView viewE1 = new MarbleSolitaireTextView(modelE1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(modelE1, viewE1, read);
        controller1.playGame();
      }
    }

    if ("Triangle".equalsIgnoreCase(args[0])) {
      if (args.length > 7) {
        throw new IllegalArgumentException("Not a valid start.");
      } else if (args.length == 7) {
        TriangleSolitaireModel modelT1 = new TriangleSolitaireModel(Integer.parseInt(args[2]),
                Integer.parseInt(args[4]), Integer.parseInt(args[6]));
        TriangleSolitaireTextView viewT1 = new TriangleSolitaireTextView(modelT1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(modelT1, viewT1, read);
        controller1.playGame();
      } else if (args.length == 5) {
        TriangleSolitaireModel modelT1 = new TriangleSolitaireModel(Integer.parseInt(args[2]),
                Integer.parseInt(args[4]));
        TriangleSolitaireTextView viewT1 = new TriangleSolitaireTextView(modelT1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(modelT1, viewT1, read);
        controller1.playGame();
      } else if (args.length == 3) {
        TriangleSolitaireModel modelT1 = new TriangleSolitaireModel(Integer.parseInt(args[2]));
        TriangleSolitaireTextView viewT1 = new TriangleSolitaireTextView(modelT1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(modelT1, viewT1, read);
        controller1.playGame();
      } else if (args.length == 1) {
        TriangleSolitaireModel modelT1 = new TriangleSolitaireModel();
        TriangleSolitaireTextView viewT1 = new TriangleSolitaireTextView(modelT1, append);
        MarbleSolitaireControllerImpl controller1 =
                new MarbleSolitaireControllerImpl(modelT1, viewT1, read);
        controller1.playGame();
      }
    }
  }
}