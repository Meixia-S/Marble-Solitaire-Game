package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import org.junit.Test;

/**
 * This class is used to test the methods in the EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {

  // Example Model and View constructors
  EnglishSolitaireModel m1;
  EnglishSolitaireModel m2;
  EnglishSolitaireModel m2V2;
  EnglishSolitaireModel m3;
  EnglishSolitaireModel m4;
  MarbleSolitaireTextView v1;

  // Example boards represented as Strings
  String sStart;
  String sMiddle;
  String sEnd;

  @Before
  public void init() {
    // Example Model constructors
    this.m1 = new EnglishSolitaireModel();
    this.m2 = new EnglishSolitaireModel(2, 4);
    this.m3 = new EnglishSolitaireModel(5);
    this.m4 = new EnglishSolitaireModel(5, 8, 7);

    // Example View constructor
    this.v1 = new MarbleSolitaireTextView(this.m1);

    // Example boards represented as Strings
    this.sStart = new String("    O O O" +
            "\n    O O O" +
            "\nO O O O O O O" +
            "\nO O O _ O O O" +
            "\nO O O O O O O" +
            "\n    O O O" +
            "\n    O O O");

    this.sMiddle = new String("    O O O" +
            "\n    O O O" +
            "\n_ _ O O O O O" +
            "\n_ _ O _ O O O" +
            "\n_ _ _ _ _ O O" +
            "\n    _ _ _" +
            "\n    _ _ _");

    this.sEnd = new String("    _ _ _" +
            "\n    _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n_ _ _ O _ _ _" +
            "\n_ _ _ _ _ _ _" +
            "\n    _ _ _" +
            "\n    _ _ _");
  }

  // Tests getBoardSize
  @Test
  public void testGetBoardSize() {
    // testing m1 that has a armThickness of 3
    assertEquals(7, this.m1.getBoardSize());
    // testing m2 that has a armThickness of 3
    assertEquals(7, this.m2.getBoardSize());
    // testing m3 that has a armThickness of 5
    assertEquals(13, this.m3.getBoardSize());
    // testing m4 that has a armThickness of 5
    assertEquals(13, this.m4.getBoardSize());
  }

  // testing getSlotState for all 4 constructors
  @Test
  public void testGetSlotState() {
    // testing m1 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m1.getSlotAt(1, 5));
    // testing m2 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m2.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m2.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m2.getSlotAt(5, 1));
    // testing m3 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m3.getSlotAt(5, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m3.getSlotAt(3, 3));
    // testing m4 three states
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m4.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m4.getSlotAt(8, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m4.getSlotAt(10, 10));
  }

  // testing getScore for all 4 constructors
  // indirectly testing move and isValidMove here as well
  @Test
  public void testGetScore() {
    // testing m1 score
    assertEquals(32, this.m1.getScore());
    this.m1.move(5, 3, 3, 3);
    assertEquals(31, this.m1.getScore());
    this.m1.move(4, 1, 4, 3);
    assertEquals(30, this.m1.getScore());
    // testing m2 score
    assertEquals(32, this.m2.getScore());
    this.m2.move(4, 4, 2, 4);
    assertEquals(31, this.m2.getScore());
    this.m2.move(3, 6, 3, 4);
    assertEquals(30, this.m2.getScore());
    // testing m3 score (beginning score = ((4 * 5) * 2) + (5 * 13) = 105 - (empty slot) = 103)
    assertEquals(104, this.m3.getScore());
    this.m3.move(4, 6, 6, 6);
    assertEquals(103, this.m3.getScore());
    this.m3.move(5, 4, 5, 6);
    assertEquals(102, this.m3.getScore());
    // testing m4 score (beginning score = ((4 * 5) * 2) + (5 * 13) = 105 - (empty slot) = 103)
    assertEquals(104, this.m4.getScore());
    this.m4.move(8, 9, 8, 7);
    assertEquals(103, this.m4.getScore());
    this.m4.move(6, 8, 8, 8);
    assertEquals(102, this.m4.getScore());
  }

  // testing isGameOver and toString with the first constructor
  // indirectly testing move, isValidMove, isInvalid, and buildBoard as well
  @Test
  public void testIsGameOver() {
    assertEquals(this.sStart, this.v1.toString());
    this.m1.move(5, 3, 3, 3);
    this.m1.move(4, 1, 4, 3);
    this.m1.move(3, 3, 5, 3);
    this.m1.move(6, 3, 4, 3);
    this.m1.move(6, 2, 4, 2);
    this.m1.move(4, 3, 4, 1);
    this.m1.move(4, 5, 4, 3);
    this.m1.move(6, 4, 4, 4);
    this.m1.move(4, 3, 4, 5);
    assertEquals(false, this.m1.isGameOver());
    this.m1.move(4, 0, 4, 2);
    this.m1.move(3, 2, 5, 2);
    this.m1.move(2, 1, 4, 1);
    this.m1.move(2, 0, 4, 0);
    this.m1.move(4, 0, 4, 2);
    this.m1.move(5, 2, 3, 2);
    assertEquals(false, this.m1.isGameOver());
    assertEquals(this.sMiddle, this.v1.toString());
    this.m1.move(4, 6, 4, 4);
    this.m1.move(3, 4, 5, 4);
    this.m1.move(2, 6, 4, 6);
    this.m1.move(2, 5, 4, 5);
    this.m1.move(4, 6, 4, 4);
    this.m1.move(5, 4, 3, 4);
    assertEquals(false, this.m1.isGameOver());
    this.m1.move(2, 3, 2, 1);
    this.m1.move(0, 2, 2, 2);
    this.m1.move(3, 2, 1, 2);
    this.m1.move(0, 4, 0, 2);
    this.m1.move(0, 2, 2, 2);
    this.m1.move(2, 1, 2, 3);
    assertEquals(false, this.m1.isGameOver());
    this.m1.move(1, 3, 3, 3);
    this.m1.move(3, 3, 3, 5);
    this.m1.move(1, 4, 3, 4);
    this.m1.move(3, 5, 3, 3);
    assertEquals(true, this.m1.isGameOver());
    assertEquals(this.sEnd, this.v1.toString());

    // Video Used: https://www.youtube.com/watch?v=HSaGviwFSyE
  }

  // testing all exceptions / IllegalArgumentExceptions

  // test an exception (m2 sRow and sCol is not a valid place)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new EnglishSolitaireModel(1, 1);
  }

  // test an exception (m2 sRow and sCol is not a valid place)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2Two() {
    new EnglishSolitaireModel(5, 6);
  }

  // test an exception (m2 sRow is negative)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2Three() {
    new EnglishSolitaireModel(-1, 1);
  }

  // test an exception (m2 sCow is beyond the board)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2Four() {
    new EnglishSolitaireModel(3, 8);
  }

  // test an exception (m3 armThickness is < 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new EnglishSolitaireModel(-1);
  }

  // test an exception (m3 armThickness is an even number)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3Two() {
    new EnglishSolitaireModel(2);
  }

  // test an exception (m4 armThickness is < 0)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    new EnglishSolitaireModel(0, 8, 7);
  }

  // test an exception (m4 armThickness is an even number)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Two() {
    new EnglishSolitaireModel(2, 8, 7);
  }

  // test an exception (m4 point is Invalid)
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Three() {
    new EnglishSolitaireModel(5, 0, 0);
  }

  // test an exception (m4 point is out of bounds) NEEDED?
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Four() {
    new EnglishSolitaireModel(5, -1, 5);
  }

  // tests an exception (from point is Empty)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionFrom() {
    this.m1.move(3, 3, 5, 2);
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
    this.m1.move(0, 0, 0, 3);
  }

  // tests an exception (points are diagonal)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionDiagonal() {
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
    this.m1.move(6, 2, 3, 3);
  }

  // tests an exception (Slot State is not in bound of board)
  // Indirectly testing inValidMove
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionOutOfBound() {
    this.m2V2 = new EnglishSolitaireModel(3, 3);
    this.m2V2.move(-1, 2, 3, 3);
  }

  // tests an exception (Slot State is not in bound of board 2)
  // Indirectly testing inValidMove
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionOutOfBound2() {
    this.m2V2 = new EnglishSolitaireModel(3, 3);
    this.m2V2.move(3, 2, 0, 8);
  }

  // tests an exception (Slot State is not in bound of board 3)
  // Indirectly testing inValidMove
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionOutOfBound3() {
    this.m2V2 = new EnglishSolitaireModel(3, 3);
    this.m2V2.move(0, -2, 7, 5);
  }
}