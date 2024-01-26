package ua.com.quizservice.exception;

/**
 * Exception thrown when a QuizQuestion lacks a required ID, it may happen during update request,
 * what have a body without required QuizQuestion.id This exception indicates that quiz questions
 * must have an ID.
 */
public class QuizQuestionIdCanNotBeNullException extends RuntimeException {
  private static final String ERROR_MESSAGE = "Quiz questions must have an ID";

  public QuizQuestionIdCanNotBeNullException() {
    super(ERROR_MESSAGE);
  }
}
