package ua.com.quizservice.exception;

import java.util.UUID;

/** Replace this stub by correct Javadoc. */
public class QuizNotPublishedException extends RuntimeException {

  private static final String ERROR_MESSAGE =
      "Quiz passing cannot be created because quiz %s is not published";

  public QuizNotPublishedException(final UUID quizId) {
    super(ERROR_MESSAGE.formatted(quizId));
  }
}
