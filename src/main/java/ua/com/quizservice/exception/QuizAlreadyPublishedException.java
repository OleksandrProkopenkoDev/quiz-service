package ua.com.quizservice.exception;

import java.util.UUID;

/**
 * Exception thrown when attempting to modify a quiz that has already been published.
 *
 * @author Oleksandr Prokopenko
 * @version 1.0
 * @since 2024-01-12
 */
public class QuizAlreadyPublishedException extends RuntimeException {

  private static final String ERROR_MESSAGE =
      "Quiz with id [%s] is already published. You can`t add or delete questions from published"
          + " Quiz";

  public QuizAlreadyPublishedException(UUID quizId) {
    super(ERROR_MESSAGE.formatted(quizId));
  }
}
