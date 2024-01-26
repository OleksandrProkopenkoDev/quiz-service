package ua.com.quizservice.exception;

import java.util.UUID;

/** Exception thrown when a quiz entity with a specific ID is not found. */
public class QuizNotFoundException extends RuntimeException {

  private static final String ERROR_MESSAGE = "Quiz entity with id: %s not found.";

  public QuizNotFoundException(UUID quizId) {
    super(ERROR_MESSAGE.formatted(quizId));
  }
}
