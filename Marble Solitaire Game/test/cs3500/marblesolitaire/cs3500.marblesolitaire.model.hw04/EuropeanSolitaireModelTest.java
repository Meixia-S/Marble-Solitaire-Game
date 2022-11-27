package cs3500.marblesolitaire.cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import org.junit.Test;

/**
 * This class is used to test the methods in the EnglishSolitaireModel class.
 */
public class EuropeanSolitaireModelTest {

  // Example Model and View constructors
  EuropeanSolitaireModel m1;
  EuropeanSolitaireModel m2;
  EuropeanSolitaireModel m2V2;
  EuropeanSolitaireModel m3;
  EuropeanSolitaireModel m4;
  MarbleSolitaireTextView v1;

  // Example boards represented as Strings
  String sStart;
  String sMiddle;
  String sEnd;

  @Before
  public void init() {
    // Example Model constructors
    this.m1 = new EuropeanSolitaireModel();
    this.m2 = new EuropeanSolitaireModel(5, 6, 6);
    this.m3 = new EuropeanSolitaireModel(6, 2);
    this.m4 = new EuropeanSolitaireModel(5, 4, 7);

    // Example View constructor
    this.v1 = new MarbleSolitaireTextView(this.m3);

    // Example boards represented as Strings
    this.sStart = new String("    O O O" +
            "\n  O O O O O" +
            "\nO O O O O O O" +
            "\nO O O O O O O" +
            "\nO O O O O O O" +
            "\n  O O O O O" +
            "\n    _ O O");

    this.sMiddle = new String("    O O O" +
            "\n  O O O O O" +
            "\nO O O O O O O" +
            "\n_ O O O O O O" +
            "\n_ _ O _ O O O" +
            "\n  _ _ _ _ O" +
            "\n    _ _ _");

    this.sEnd = new String("    _ _ O" +
            "\n  _ _ _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n  _ _ _ _ _" +
            "\n    _ _ _");
  }

  // Tests getBoardSize
  @Test
  public void testGetBoardSize() {
    // testing m1 that has a armThickness of 3
    assertEquals(7, this.m1.getBoardSize());
    // testing m2 that has a armThickness of 3
    assertEquals(13, this.m2.getBoardSize());
    // testing m3 that has a armThickness of 5
    assertEquals(7, this.m3.getBoardSize());
    // testing m4 that has a armThickness of 5
    assertEquals(13, this.m4.getBoardSize());
  }

  // testing getSlotState for all 4 constructors
  @Test
  public void testGetSlotState() {
    // testing m1 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m1.getSlotAt(1, 6));
    // testing m2 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m2.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m2.getSlotAt(10, 11));
    // testing m3 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m3.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m3.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m3.getSlotAt(5, 0));
    // testing m4 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m4.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m4.getSlotAt(4, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m4.getSlotAt(2, 1));
  }

  // testing getScore for all 4 constructors
  // indirectly testing move and isValidMove here as well
  @Test
  public void testGetScore() {
    // testing m1 score
    assertEquals(36, this.m1.getScore());
    this.m1.move(5, 3, 3, 3);
    assertEquals(35, this.m1.getScore());
    this.m1.move(4, 1, 4, 3);
    assertEquals(34, this.m1.getScore());
    // testing m2 score
    assertEquals(128, this.m2.getScore());
    this.m2.move(4, 6, 6, 6);
    assertEquals(127, this.m2.getScore());
    this.m2.move(5, 4, 5, 6);
    assertEquals(126, this.m2.getScore());
    // testing m3 score
    assertEquals(36, this.m3.getScore());
    this.m3.move(4, 2, 6, 2);
    assertEquals(35, this.m3.getScore());
    this.m3.move(5, 4, 5, 2);
    assertEquals(34, this.m3.getScore());
    // testing m4 score
    assertEquals(128, this.m4.getScore());
    this.m4.move(2, 7, 4, 7);
    assertEquals(127, this.m4.getScore());
    this.m4.move(2, 5, 2, 7);
    assertEquals(126, this.m4.getScore());
  }

  // testing isGameOver and toString with the first constructor
  // indirectly testing move, isValidMove, isInvalid, and buildBoard as well
  @Test
  public void testIsGameOver() {
    assertEquals(this.sStart, this.v1.toString());
    this.m3.move(4, 2, 6, 2);
    this.m3.move(4, 4, 4, 2);
    this.m3.move(6, 4, 4, 4);
    this.m3.move(6, 3, 4, 3);
    this.m3.move(3, 2, 5, 2);

    assertEquals(false, this.m3.isGameOver());
    this.m3.move(6, 2, 4, 2);
    this.m3.move(3, 0, 3, 2);
    this.m3.move(5, 1, 3, 1);
    this.m3.move(4, 3, 4, 1);
    this.m3.move(4, 0, 4, 2);

    assertEquals(false, this.m3.isGameOver());
    assertEquals(this.sMiddle, this.v1.toString());
    this.m3.move(2, 1, 4, 1);
    this.m3.move(4, 1, 4, 3);
    this.m3.move(2, 3, 2, 1);
    this.m3.move(2, 0, 2, 2);
    this.m3.move(0, 3, 2, 3);

    assertEquals(false, this.m3.isGameOver());
    this.m3.move(1, 1, 1, 3);
    this.m3.move(3, 2, 1, 2);
    this.m3.move(0, 2, 2, 2);
    this.m3.move(1, 4, 1, 2);
    this.m3.move(3, 4, 1, 4);

    assertEquals(false, this.m3.isGameOver());
    this.m3.move(2, 6, 2, 4);
    this.m3.move(3, 6, 3, 4);
    this.m3.move(5, 5, 3, 5);
    this.m3.move(4, 3, 4, 5);
    this.m3.move(4, 6, 4, 4);

    assertEquals(false, this.m3.isGameOver());
    this.m3.move(2, 3, 2, 5);
    this.m3.move(2, 5, 4, 5);
    this.m3.move(4, 5, 4, 3);
    this.m3.move(4, 3, 2, 3);
    this.m3.move(2, 2, 2, 4);

    assertEquals(false, this.m3.isGameOver());
    this.m3.move(1, 5, 1, 3);
    this.m3.move(3, 4, 1, 4);
    this.m3.move(0, 4, 2, 4);
    this.m3.move(1, 2, 1, 4);
    this.m3.move(2, 4, 0, 4);
    assertEquals(true, this.m3.isGameOver());
    assertEquals(this.sEnd, this.v1.toString());

    // Video Used: https://www.youtube.com/watch?v=fUVukxJtGi8
  }

  // testing all exceptions / IllegalArgumentExceptions

  // test an exception (m2 armThickness is < 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new EuropeanSolitaireModel(-1);
  }

  // test an exception (m2 armThickness is 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2Two() {
    new EuropeanSolitaireModel(0);
  }

  // test an exception (m2 armThickness is an even number)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2Three() {
    new EuropeanSolitaireModel(2);
  }

  // test an exception (m3 sRow and sCol is not a valid place)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new EuropeanSolitaireModel(1, 0);
  }

  // test an exception (m3 sRow and sCol is not a valid place)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3Two() {
    new EuropeanSolitaireModel(5, 6);
  }

  // test an exception (m3 sRow is negative)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3Three() {
    new EuropeanSolitaireModel(-1, 1);
  }

  // test an exception (m4 armThickness is < 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    new EuropeanSolitaireModel(0, 8, 7);
  }

  // test an exception (m4 armThickness is an even number)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Two() {
    new EuropeanSolitaireModel(2, 8, 7);
  }

  // test an exception (m4 point is Invalid)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Three() {
    new EuropeanSolitaireModel(5, 0, 0);
  }

  // test an exception (m4 point is out of bounds) NEEDED?
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Four() {
    new EuropeanSolitaireModel(5, -1, 5);
  }

  // tests an exception (from point is Empty)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionFrom() {
    this.m1.move(3, 3, 5, 3);
  }

  // tests an exception (to point is Marble)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTo() {
    this.m1.move(3, 2, 5, 2);
  }

  // tests an exception (middle point is Empty)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionMiddle() {
    this.m1.move(2, 3, 4, 3);
  }

  // tests an exception (one point is Invalid)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalid() {
    this.m1.move(0, 0, 0, 2);
  }

  // tests an exception (points are diagonal upper middle to )
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionDiagonal1() {
    this.m1.move(3, 5, 3, 3);
    this.m1.move(1, 3, 3, 5);
  }

  // tests an exception (points are too close)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooClose() {
    this.m1.move(3, 2, 3, 3);
  }

  // tests an exception (points are too far apart)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooFar() {
    this.m1.move(6, 3, 3, 3);
  }

  // tests an exception (Slot State is not in bound of board)
  // Indirectly testing inValidMove
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionOutOfBound() {
    this.m2V2 = new EuropeanSolitaireModel(3, 3);
    this.m2V2.move(-1, 2, 3, 3);
  }

  // tests an exception (Slot State is not in bound of board 2)
  // Indirectly testing inValidMove
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionOutOfBound2() {
    this.m2V2 = new EuropeanSolitaireModel(3, 3);
    this.m2V2.move(3, 2, 0, 8);
  }

  // tests an exception (Slot State is not in bound of board 3)
  // Indirectly testing inValidMove
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionOutOfBound3() {
    this.m2V2 = new EuropeanSolitaireModel(3, 3);
    this.m2V2.move(0, -2, 7, 5);
  }
}