package ua.com.quizservice.exception;

import java.util.UUID;

/**
 * Exception which should be thrown when Question entity not found in Database.
 *
 * @author gexter
 */
public class QuestionNotFoundException extends RuntimeException {

  private static final String ERROR_MESSAGE = "Question entity with id: %s not found.";

  public QuestionNotFoundException(final UUID questionId) {
    super(ERROR_MESSAGE.formatted(questionId));
  }
}
