package cs3500.marblesolitaire.cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the methods in the EnglishSolitaireModel class.
 */
public class TriangleSolitaireModelTest {

  // Example Model and View constructors
  TriangleSolitaireModel m1;
  TriangleSolitaireModel m2;
  TriangleSolitaireModel m2v2;
  TriangleSolitaireModel m3;
  TriangleSolitaireModel m4;
  TriangleSolitaireTextView v1;

  // Example boards represented as Strings
  String sStart;
  String sMiddle;
  String sEnd;

  @Before
  public void init() {
    // Example Model constructors
    this.m1 = new TriangleSolitaireModel();
    this.m2 = new TriangleSolitaireModel(4);
    this.m3 = new TriangleSolitaireModel(2, 1);
    this.m4 = new TriangleSolitaireModel(8, 5, 5);

    // Example View constructor
    this.v1 = new TriangleSolitaireTextView(this.m1);

    // Example boards represented as Strings
    this.sStart = new String("    _" +
            "\n   O O" +
            "\n  O O O" +
            "\n O O O O" +
            "\nO O O O O");

    this.sMiddle = new String("    _" +
            "\n   O O" +
            "\n  _ O O" +
            "\n _ _ _ _" +
            "\n_ _ _ _ O");

    this.sEnd = new String("    O" +
            "\n   _ _" +
            "\n  _ _ _" +
            "\n _ _ _ _" +
            "\n_ _ _ _ _");
  }

  // Tests getBoardSize
  @Test
  public void testGetBoardSize() {
    // testing m1 that has a armThickness of 3
    assertEquals(5, this.m1.getBoardSize());
    // testing m2 that has a armThickness of 3
    assertEquals(4, this.m2.getBoardSize());
    // testing m3 that has a armThickness of 5
    assertEquals(5, this.m3.getBoardSize());
    // testing m4 that has a armThickness of 5
    assertEquals(8, this.m4.getBoardSize());
  }

  // testing getSlotState for all 4 constructors
  @Test
  public void testGetSlotState() {
    // testing m1 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m1.getSlotAt(3, 4));
    // testing m2 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m2.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m2.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m2.getSlotAt(2, 3));
    // testing m3 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m3.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m3.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m3.getSlotAt(3, 4));
    // testing m4 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m4.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m4.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m4.getSlotAt(6, 7));
  }

  // testing getScore for all 4 constructors
  // indirectly testing move and isValidMove here as well
  @Test
  public void testGetScore() {
    // testing m1 score
    assertEquals(14, this.m1.getScore());
    this.m1.move(2, 2, 0, 0);
    assertEquals(13, this.m1.getScore());
    this.m1.move(2, 0, 2, 2);
    assertEquals(12, this.m1.getScore());
    // testing m2 score
    assertEquals(9, this.m2.getScore());
    this.m2.move(2, 2, 0, 0);
    assertEquals(8, this.m2.getScore());
    this.m2.move(2, 0, 2, 2);
    assertEquals(7, this.m2.getScore());
    // testing m3 score
    assertEquals(14, this.m3.getScore());
    this.m3.move(4, 3, 2, 1);
    assertEquals(13, this.m3.getScore());
    this.m3.move(4, 1, 4, 3);
    assertEquals(12, this.m3.getScore());
    // testing m4 score
    assertEquals(35, this.m4.getScore());
    this.m4.move(3, 3, 5, 5);
    assertEquals(34, this.m4.getScore());
    this.m4.move(6, 4, 4, 4);
    assertEquals(33, this.m4.getScore());
  }

  // testing isGameOver and toString with the first constructor
  // indirectly testing move, isValidMove, isInvalid, and buildBoard as well
  @Test
  public void testIsGameOver() {

    assertEquals(this.sStart, this.v1.toString());
    this.m1.move(2, 0, 0, 0);
    this.m1.move(2, 2, 2, 0);
    this.m1.move(0, 0, 2, 2);
    assertEquals(false, this.m1.isGameOver());
    this.m1.move(3, 0, 1, 0);
    this.m1.move(3, 3, 1, 1);
    this.m1.move(4, 1, 2, 1);
    assertEquals(false, this.m1.isGameOver());
    this.m1.move(4, 3, 4, 1);
    this.m1.move(4, 0, 4, 2);
    this.m1.move(4, 2, 2, 2);
    assertEquals(false, this.m1.isGameOver());
    assertEquals(this.sMiddle, this.v1.toString());
    this.m1.move(1, 1, 3, 3);
    this.m1.move(4, 4, 2, 2);
    this.m1.move(2, 2, 2, 0);
    this.m1.move(2, 0, 0, 0);
    assertEquals(true, this.m1.isGameOver());
    assertEquals(this.sEnd, this.v1.toString());

    // Video Used: https://www.youtube.com/watch?v=k3mqCJ0fTSo
  }

  // testing all exceptions / IllegalArgumentExceptions

  // test an exception (m2 armThickness is < 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new TriangleSolitaireModel(-5);
  }

  // test an exception (m2 armThickness is 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2Two() {
    new TriangleSolitaireModel(0);
  }

  // test an exception (m3 sRow and sCol is not an Invalid place)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new TriangleSolitaireModel(0, 2);
  }

  // test an exception (m3 sCol is larger than sRow )
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3Two() {
    new TriangleSolitaireModel(-1, 1);
  }

  // test an exception (m3 sCol is larger than sRow )
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3Three() {
    new TriangleSolitaireModel(3, 4);
  }

  // test an exception (m3 sCow is beyond the board)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3Four() {
    new TriangleSolitaireModel(3, 8);
  }

  // test an exception (m4 armThickness is < 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    new TriangleSolitaireModel(-5, 8, 7);
  }

  // test an exception (m4 armThickness is 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Two() {
    new TriangleSolitaireModel(0, 8, 7);
  }

  // test an exception (m4 point is Invalid (aka sCol > sRow))
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Three() {
    new TriangleSolitaireModel(8, 6, 7);
  }

  // tests an exception (from point is Empty)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionFrom() {
    this.m1.move(0, 0, 2, 0);
  }

  // tests an exception (to point is Marble)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTo() {
    this.m1.move(2, 2, 4, 2);
  }

  // tests an exception (middle point is Empty)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionMiddle() {
    this.m1.move(3, 0, 0, 0);
    this.m1.move(2, 2, 2, 0);
    this.m1.move(2, 0, 0, 0);
  }

  // tests an exception (one point is Invalid)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalid() {
    this.m1.move(0, 0, 2, 3);
  }

  // tests an exception (points are two rows apart but are top of each other)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionDiagonal() {
    this.m1.move(2, 1, 4, 2);
  }

  // tests an exception (points are too close)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooClose() {
    this.m1.move(3, 2, 3, 3);
  }

  // tests an exception (points are too far apart)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooFar() {
    this.m1.move(4, 0, 4, 3);
  }

  // tests an exception (Slot State is not in bound of board)
  // Indirectly testing inValidMove
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionOutOfBound() {
    this.m2v2 = new TriangleSolitaireModel(3, 4);
    this.m2v2.move(3, 2, 3, 4);
  }
}