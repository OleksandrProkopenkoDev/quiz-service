package ua.com.quizservice.exception;

import java.util.UUID;

/**
 * Exception which should be thrown when QuizQuestion entity not found in Database.
 *
 * @author Oleksandr Prokopenko
 */
public class QuizQuestionNotFoundException extends RuntimeException {

  private static final String ERROR_MESSAGE = "QuizQuestion entity with id: %s not found.";

  public QuizQuestionNotFoundException(UUID questionId) {
    super(ERROR_MESSAGE.formatted(questionId));
  }
}
