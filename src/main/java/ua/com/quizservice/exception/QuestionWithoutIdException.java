package ua.com.quizservice.exception;

/**
 * Exception thrown when an illegal argument is detected in the quiz system.
 *
 * <p>This exception is typically used to indicate that questions must have an ID.
 */
public class QuestionWithoutIdException extends RuntimeException {

  public QuestionWithoutIdException() {
    super("Question must have an ID");
  }
}
