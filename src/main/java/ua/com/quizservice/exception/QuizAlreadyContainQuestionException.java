package ua.com.quizservice.exception;

import java.util.UUID;

/**
 * Exception thrown when attempting to add a quiz question to a quiz, but the quiz already contains
 * a question with the given ID.
 */
public class QuizAlreadyContainQuestionException extends RuntimeException {
  public QuizAlreadyContainQuestionException(UUID id) {
    super("Quiz already contain question with id = " + id);
  }
}
