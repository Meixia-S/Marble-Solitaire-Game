package cs3500.marblesolitaire.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockAppendable;
import cs3500.marblesolitaire.model.hw02.MockModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * A class that tests the methods in the MarbleSolitaireControllerImpl class.
 */
public class MarbleSolitaireControllerImplTest {

  // Example Readables
  Readable r1;
  Readable r2;

  // Example Appendable
  Appendable appendable;

  // StringBuilder examples
  StringBuilder sb;
  // Example Model constructors
  EnglishSolitaireModel m1;
  EnglishSolitaireModel m2;
  TriangleSolitaireModel mt1;

  // Example View constructors
  MarbleSolitaireTextView v1;
  MarbleSolitaireTextView v2;
  MarbleSolitaireTextView v3;
  TriangleSolitaireTextView vt1;

  // Example Controller constructors

  // Game won examples
  MarbleSolitaireControllerImpl cEnglishWon;
  MarbleSolitaireControllerImpl cTriWon;

  // Game over but not won examples
  MarbleSolitaireControllerImpl cEnglishLost;
  MarbleSolitaireControllerImpl cTriLost;


  // Controller Exception examples
  MarbleSolitaireControllerImpl ce1;
  MarbleSolitaireControllerImpl ce2;
  MarbleSolitaireControllerImpl ce3;

  // Controller Test examples
  MarbleSolitaireControllerImpl ct1;
  MarbleSolitaireControllerImpl ct2;
  MarbleSolitaireControllerImpl ct3;
  MarbleSolitaireControllerImpl ct4;
  MarbleSolitaireControllerImpl ct5;
  MarbleSolitaireControllerImpl ct6;

  // Example boards represented as Strings

  // Playing through a game
  String sGoodMoveEnglish;
  String sBadMoveEnglish;
  String sGoodMoveTri;
  String sBadaMoveTri;

  String st1;
  String st2;
  String st3;
  String st4;
  String st5;
  String st6;

  // Examples of messages
  String helloWorld;
  String gameOver;

  @Before
  public void init() {
    // Example Readables
    this.r1 = new StringReader(" ");
    this.r2 = null;

    // Example StringBuilder
    sb = new StringBuilder("4 2 4 4 ");

    // Example Appendable
    this.appendable = new StringBuilder();

    // Example Models constructors
    this.m1 = new EnglishSolitaireModel();
    this.m2 = null;
    this.mt1 = new TriangleSolitaireModel();

    // Example View constructors
    this.v1 = new MarbleSolitaireTextView(this.m1, this.appendable);
    this.v2 = null;
    this.v3 = new MarbleSolitaireTextView(this.m1);
    this.vt1 = new TriangleSolitaireTextView(this.mt1);

    // Example Controller constructors

    // English game play
    this.cEnglishWon = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("6 4 4 4 5 2 5 4 4 4 6 4 7 4 5 4 7 3 5 3 5 4 5 2 5 6 5 4 7 5 5 5 "
                    + "5 4 5 6 5 1 5 3 4 3 6 3 3 2 5 2 3 1 5 1 5 1 5 3 6 3 4 3 " +
                    "5 7 5 5 4 5 6 5 3 7 5 7 3 6 5 6 5 7 5 5 6 5 4 5 " +
                    "3 4 3 2 1 3 3 3 4 3 2 3 1 5 1 3 1 3 3 3 3 2 3 4 " +
                    "2 4 4 4 4 4 4 6 2 5 4 5 4 6 4 4 q"));

    this.cEnglishLost = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("6 4 4 4 3 4 5 4 1 4 3 4 4 2 4 4 4 5 4 3 4 7 4 5 q"));


    // Triangle game play
    this.cTriWon = new MarbleSolitaireControllerImpl(this.mt1, this.vt1,
            new StringReader( "3 1 1 1 3 3 3 1 1 1 3 3 4 1 2 1 4 4 2 2 5 2 3 2 5 4 5 2 5 1 5 3 "
                    + "5 3 3 3 2 2 4 4 5 5 3 3 3 3 3 1 3 1 1 1 q"));

    this.cTriLost = new MarbleSolitaireControllerImpl(this.mt1, this.vt1,
            new StringReader( "3 3 1 1 5 3 3 3 4 2 2 2 5 1 5 3 5 4 5 2 3 1 5 1 1 1 3 1 "
                    + "3 3 1 1 5 1 5 3 5 5 3 3 q"));

    // Controller Constructor exception
    this.ce1 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("4244"));

    this.ce2 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("-4 2 4 4 q")); // negative

    this.ce3 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("-7 8 9 6 q")); // negative and invalid move

    // Other controller tests
    this.ct1 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader(" 4 2 \n 4 4 q")); // valid with line

    this.ct2 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("4 t y % 2 4 4 q")); // valid seperated by invalid inputs

    this.ct3 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("$ y e i q")); // random symbols

    this.ct4 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("4 2 4 4 4 5 4 3 Q")); // quit game properly with Q

    this.ct5 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("4 2 4 4 4 5 4 3 q")); // quit game properly with q

    this.ct6 = new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("7 8 9 6 q")); // invalid move

    // String Examples

    this.sGoodMoveEnglish = new String("Start by Making a Move" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 31" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O O O O O" +
            "\nO _ _ O O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 30" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO _ _ _ O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 29" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO _ _ O O O O" +
            "\n    O _ O" +
            "\n    O _ O" +
            "\nScore: 28" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO _ O O O O O" +
            "\n    _ _ O" +
            "\n    _ _ O" +
            "\nScore: 27" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O _ _ O O O" +
            "\n    _ _ O" +
            "\n    _ _ O" +
            "\nScore: 26" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O _ O _ _ O" +
            "\n    _ _ O" +
            "\n    _ _ O" +
            "\nScore: 25" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O _ O O _ O" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 24" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O _ _ _ O O" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 23" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\n_ _ O _ _ O O" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 22" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O _ _ O O O" +
            "\n_ _ _ _ _ O O" +
            "\n    O _ _" +
            "\n    _ _ _" +
            "\nScore: 21" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO _ O O O O O" +
            "\nO _ _ _ O O O" +
            "\n_ O _ _ _ O O" +
            "\n    O _ _" +
            "\n    _ _ _" +
            "\nScore: 20" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O O O" +
            "\n_ _ _ _ O O O" +
            "\nO O _ _ _ O O" +
            "\n    O _ _" +
            "\n    _ _ _" +
            "\nScore: 19" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O O O" +
            "\n_ _ _ _ O O O" +
            "\n_ _ O _ _ O O" +
            "\n    O _ _" +
            "\n    _ _ _" +
            "\nScore: 18" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O O O" +
            "\n_ _ O _ O O O" +
            "\n_ _ _ _ _ O O" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 17" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O O O" +
            "\n_ _ O _ O O O" +
            "\n_ _ _ _ O _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 16" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O O O" +
            "\n_ _ O _ _ O O" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ O" +
            "\n    _ _ _" +
            "\nScore: 15" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O O _" +
            "\n_ _ O _ _ O _" +
            "\n_ _ _ _ _ _ O" +
            "\n    _ _ O" +
            "\n    _ _ _" +
            "\nScore: 14" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O _ _" +
            "\n_ _ O _ _ _ _" +
            "\n_ _ _ _ _ O O" +
            "\n    _ _ O" +
            "\n    _ _ _" +
            "\nScore: 13" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O _ _" +
            "\n_ _ O _ _ _ _" +
            "\n_ _ _ _ O _ _" +
            "\n    _ _ O" +
            "\n    _ _ _" +
            "\nScore: 12" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ _ O O O _ _" +
            "\n_ _ O _ O _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 11" +
            "\n    O O O" +
            "\n    O O O" +
            "\n_ O _ _ O _ _" +
            "\n_ _ O _ O _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 10" +
            "\n    _ O O" +
            "\n    _ O O" +
            "\n_ O O _ O _ _" +
            "\n_ _ O _ O _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 9" +
            "\n    _ O O" +
            "\n    O O O" +
            "\n_ O _ _ O _ _" +
            "\n_ _ _ _ O _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 8" +
            "\n    O _ _" +
            "\n    O O O" +
            "\n_ O _ _ O _ _" +
            "\n_ _ _ _ O _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 7" +
            "\n    _ _ _" +
            "\n    _ O O" +
            "\n_ O O _ O _ _" +
            "\n_ _ _ _ O _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 6" +
            "\n    _ _ _" +
            "\n    _ O O" +
            "\n_ _ _ O O _ _" +
            "\n_ _ _ _ O _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 5" +
            "\n    _ _ _" +
            "\n    _ _ O" +
            "\n_ _ _ _ O _ _" +
            "\n_ _ _ O O _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 4" +
            "\n    _ _ _" +
            "\n    _ _ O" +
            "\n_ _ _ _ O _ _" +
            "\n_ _ _ _ _ O _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 3" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n_ _ _ _ O O _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 2" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n_ _ _ O _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 1" +
            "\nGame over!" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n_ _ _ O _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _" +
            "\nScore: 1\n");

    this.sBadMoveEnglish = new String("Start by Making a Move" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 31" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O _ O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 30" +
            "\n    O _ O" +
            "\n    O _ O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 29" +
            "\n    O _ O" +
            "\n    O _ O" +
            "\nO O O O O O O" +
            "\nO _ _ O O O O" +
            "\nO O O O O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 28" +
            "\n    O _ O" +
            "\n    O _ O" +
            "\nO O O O O O O" +
            "\nO _ O _ _ O O" +
            "\nO O O O O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 27" +
            "\n    O _ O" +
            "\n    O _ O" +
            "\nO O O O O O O" +
            "\nO _ O _ O _ _" +
            "\nO O O O O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 26" +
            "\nGame over!" +
            "\n    O _ O" +
            "\n    O _ O" +
            "\nO O O O O O O" +
            "\nO _ O _ O _ _" +
            "\nO O O O O O O" +
            "\n    O _ O" +
            "\n    O O O" +
            "\nScore: 26\n");

    this.sGoodMoveTri = new String("Start by Making a Move" +
            "\n    _" +
            "\n   O O" +
            "\n  O O O" +
            "\n O O O O" +
            "\nO O O O O" +
            "\nScore: 14" +
            "\n    O" +
            "\n   _ O" +
            "\n  _ O O" +
            "\n O O O O" +
            "\nO O O O O" +
            "\nScore: 13" +
            "\n    O" +
            "\n   _ O" +
            "\n  O _ _" +
            "\n O O O O" +
            "\nO O O O O" +
            "\nScore: 12" +
            "\n    _" +
            "\n   _ _" +
            "\n  O _ O" +
            "\n O O O O" +
            "\nO O O O O" +
            "\nScore: 11" +
            "\n    _" +
            "\n   O _" +
            "\n  _ _ O" +
            "\n _ O O O" +
            "\nO O O O O" +
            "\nScore: 10" +
            "\n    _" +
            "\n   O O" +
            "\n  _ _ _" +
            "\n _ O O _" +
            "\nO O O O O" +
            "\nScore: 9" +
            "\n    _" +
            "\n   O O" +
            "\n  _ O _" +
            "\n _ _ O _" +
            "\nO _ O O O" +
            "\nScore: 8" +
            "\n    _" +
            "\n   O O" +
            "\n  _ O _" +
            "\n _ _ O _" +
            "\nO O _ _ O" +
            "\nScore: 7" +
            "\n    _" +
            "\n   O O" +
            "\n  _ O _" +
            "\n _ _ O _" +
            "\n_ _ O _ O" +
            "\nScore: 6" +
            "\n    _" +
            "\n   O O" +
            "\n  _ O O" +
            "\n _ _ _ _" +
            "\n_ _ _ _ O" +
            "\nScore: 5" +
            "\n    _" +
            "\n   O _" +
            "\n  _ O _" +
            "\n _ _ _ O" +
            "\n_ _ _ _ O" +
            "\nScore: 4" +
            "\n    _" +
            "\n   O _" +
            "\n  _ O O" +
            "\n _ _ _ _" +
            "\n_ _ _ _ _" +
            "\nScore: 3" +
            "\n    _" +
            "\n   O _" +
            "\n  O _ _" +
            "\n _ _ _ _" +
            "\n_ _ _ _ _" +
            "\nScore: 2" +
            "\n    O" +
            "\n   _ _" +
            "\n  _ _ _" +
            "\n _ _ _ _" +
            "\n_ _ _ _ _" +
            "\nScore: 1" +
            "\nGame over!" +
            "\n    O" +
            "\n   _ _" +
            "\n  _ _ _" +
            "\n _ _ _ _" +
            "\n_ _ _ _ _" +
            "\nScore: 1\n" );

    this.sBadaMoveTri = new String("Start by Making a Move" +
            "\n    _" +
            "\n   O O" +
            "\n  O O O" +
            "\n O O O O" +
            "\nO O O O O" +
            "\nScore: 14" +
            "\n    O" +
            "\n   O _" +
            "\n  O O _" +
            "\n O O O O" +
            "\nO O O O O" +
            "\nScore: 13" +
            "\n    O" +
            "\n   O _" +
            "\n  O O O" +
            "\n O O _ O" +
            "\nO O _ O O" +
            "\nScore: 12" +
            "\n    O" +
            "\n   O O" +
            "\n  O _ O" +
            "\n O _ _ O" +
            "\nO O _ O O" +
            "\nScore: 11" +
            "\n    O" +
            "\n   O O" +
            "\n  O _ O" +
            "\n O _ _ O" +
            "\n_ _ O O O" +
            "\nScore: 10" +
            "\n    O" +
            "\n   O O" +
            "\n  O _ O" +
            "\n O _ _ O" +
            "\n_ O _ _ O" +
            "\nScore: 9" +
            "\n    O" +
            "\n   O O" +
            "\n  _ _ O" +
            "\n _ _ _ O" +
            "\nO O _ _ O" +
            "\nScore: 8" +
            "\n    _" +
            "\n   _ O" +
            "\n  O _ O" +
            "\n _ _ _ O" +
            "\nO O _ _ O" +
            "\nScore: 7" +
            "\n    O" +
            "\n   _ _" +
            "\n  O _ _" +
            "\n _ _ _ O" +
            "\nO O _ _ O" +
            "\nScore: 6" +
            "\n    O" +
            "\n   _ _" +
            "\n  O _ _" +
            "\n _ _ _ O" +
            "\n_ _ O _ O" +
            "\nScore: 5" +
            "\n    O" +
            "\n   _ _" +
            "\n  O _ O" +
            "\n _ _ _ _" +
            "\n_ _ O _ _" +
            "\nScore: 4" +
            "\nGame over!" +
            "\n    O" +
            "\n   _ _" +
            "\n  O _ O" +
            "\n _ _ _ _" +
            "\n_ _ O _ _" +
            "\nScore: 4\n" );





    this.st1 = new String("Start by Making a Move" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ _ O O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 31\n" +
            "\nGame quit!" +
            "\nState of game when quit:" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ _ O O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 31\n");

    this.st2 = new String("Start by Making a Move" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32" +
            "\nInvalid Move! Play again. Make sure your inputs are natural numbers!" +
            "\nInvalid Move! Play again. Make sure your inputs are natural numbers!" +
            "\nInvalid Move! Play again. Make sure your inputs are natural numbers!" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ _ O O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 31\n" +
            "\nGame quit!" +
            "\nState of game when quit:" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ _ O O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 31\n" );

    this.st3 = new String("Start by Making a Move" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32" +
            "\nInvalid Move! Play again. Make sure your inputs are natural numbers!" +
            "\nInvalid Move! Play again. Make sure your inputs are natural numbers!" +
            "\nInvalid Move! Play again. Make sure your inputs are natural numbers!" +
            "\nInvalid Move! Play again. Make sure your inputs are natural numbers!\n" +
            "\nGame quit!" +
            "\nState of game when quit:" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32\n" );

    this.st4 = new String("Start by Making a Move" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ _ O O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 31" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ O _ _ O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 30\n" +
            "\nGame quit!" +
            "\nState of game when quit:" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ O _ _ O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 30\n");

    this.st5 = new String("Start by Making a Move" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ _ O O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 31" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ O _ _ O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 30\n" +
            "\nGame quit!" +
            "\nState of game when quit:" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO _ O _ _ O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 30\n");

    this.st6 = new String("Start by Making a Move" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32" +
            "\nSorry, not a valid move!" +
            "\nGame quit!" +
            "\nState of game when quit:" +
            "\n    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O" +
            "\nScore: 32\n");

    // Examples of messages
    this.helloWorld = "Hello World";
    this.gameOver = "Game Over!";
  }

  // Testing playGame by playing through one entire game


  // English
  @Test
  public void testPlayGameEnglishW() {
    cEnglishWon.playGame();
    assertEquals(this.sGoodMoveEnglish, appendable.toString());
  }

  @Test
  public void testPlayGameEnglishL() {
    cEnglishLost.playGame();
    assertEquals(this.sBadMoveEnglish, appendable.toString());
  }


  // Triangle
  @Test
  public void testPlayGameTriangleW() {
    cTriWon.playGame();
    assertEquals(this.sGoodMoveTri, appendable.toString());
  }

  @Test
  public void testPlayGameTriangleL() {
    cTriLost.playGame();
    assertEquals(this.sBadaMoveTri, appendable.toString());
  }

  // Other controller tests
  @Test
  public void testConstructorLineBreak() {
    this.ct1.playGame();
    assertEquals(this.st1, appendable.toString());
  }

  @Test
  public void testConstructorValidWithLine() {
    this.ct2.playGame();
    assertEquals(this.st2, appendable.toString());
  }

  @Test
  public void testConstructorRandomSymbols() {
    this.ct3.playGame();
    assertEquals(this.st3, appendable.toString());
  }

  @Test
  public void testConstructorQuitWithQ() {
    this.ct4.playGame();
    assertEquals(this.st4, appendable.toString());
  }

  @Test
  public void testConstructorQuitWithq() {
    this.ct5.playGame();
    assertEquals(this.st5, appendable.toString());
  }

  @Test
  public void testConstructorOutOfBoundInput() {
    this.ct6.playGame();
    assertEquals(this.st6, appendable.toString());
  }


  // Testing the constructor exceptions

  // test an exception (when the model is null)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorModel() {
    new MarbleSolitaireControllerImpl(this.m2, this.v1, this.r1);
  }

  // test an exception (when the view is null)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorView() {
    new MarbleSolitaireControllerImpl(this.m1, this.v2, this.r1);
  }

  // test an exception (when the readable is null)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorReadable() {
    new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r2);
  }

  // test an exception (when every field are null)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllNull() {
    new MarbleSolitaireControllerImpl(this.m2, this.v2, this.r2);
  }

  // test an exception (negative input)
  @Test(expected = IllegalStateException.class)
  public void testConstructorNegInput() {
    new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("-4 2 4 4 q"));
  }

  // test an exception (when every field are null)
  @Test(expected = IllegalStateException.class)
  public void testConstructorNegAndInvalidInput() {
    new MarbleSolitaireControllerImpl(this.m1, this.v1,
            new StringReader("-7 8 9 6 q"));
  }

  // Testing View methods

  // English and European View

  // test an exception (when the model is null)
  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    new MarbleSolitaireTextView(this.m2);
  }

  // test an exception (renderBoard IOException)
  @Test
  public void testRenderBoardException() {
    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(this.m1, new MockAppendable());
    assertThrows(IOException.class, view1::renderBoard);
  }

  // test an exception (renderMessage IOException)
  @Test
  public void testRenderMessageException() {
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(this.m1, new MockAppendable());
    assertThrows(IOException.class, () -> view2.renderMessage("Hello"));
  }

  // testing renderMessage

  // testing hello World
  @Test
  public void testRenderMessageHW() throws IOException {
    this.v1.renderMessage(this.helloWorld);
    assertEquals(this.helloWorld, this.appendable.toString());
  }

  // testing Game over
  @Test
  public void testRenderMessageGO() throws IOException {
    this.v1.renderMessage(this.gameOver);
    assertEquals(this.gameOver, this.appendable.toString());
  }

  // Testing the communication between the model and controller with the Mock
  @Test
  public void testValidResult() {
    Appendable inputThatTheModelReceived = new StringBuilder("");
    MarbleSolitaireModel model1 = new MockModel(inputThatTheModelReceived);

    Appendable input = new StringBuilder("");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();

    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(model1, v1, userInput);
    controller1.playGame();

    assertEquals(input.toString(), inputThatTheModelReceived.toString());
  }

  // English and European View

  // test an exception (when the model is null)
  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructorTri() {
    new TriangleSolitaireTextView(this.m2);
  }

  // test an exception (renderBoard IOException)
  @Test
  public void testRenderBoardExceptionTri() {
    TriangleSolitaireTextView view1 = new TriangleSolitaireTextView(this.m1, new MockAppendable());
    assertThrows(IOException.class, view1::renderBoard);
  }

  // testing renderMessage

  // testing hello World
  @Test
  public void testRenderMessageTriHW() throws IOException {
    this.vt1.renderMessage(this.helloWorld);
    assertEquals(this.helloWorld, this.appendable.toString());
  }

  // testing Game over
  @Test
  public void testRenderMessageTriGO() throws IOException {
    this.vt1.renderMessage(this.gameOver);
    assertEquals(this.gameOver, this.appendable.toString());
  }
}