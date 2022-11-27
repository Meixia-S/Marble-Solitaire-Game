package cs3500.marblesolitaire.model.hw02;

import java.io.IOException;

/**
 * A mock appendable class that is used in testing the exception called by
 * renderBoard() and renderMessage().
 */
public class MockAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}
