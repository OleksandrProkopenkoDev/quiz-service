package ua.com.quizservice.exception;

import static java.lang.String.format;

/**
 * Exception thrown when the size of the list of quiz questions provided for an update operation
 * does not match the existing list size in the database.
 */
public class QuizQuestionsListSizeException extends RuntimeException {

  public static final String MESSAGE =
      "QuizQuestions List size passed to update [%s]  not equals existing in Database list size"
          + " [%s]";

  public QuizQuestionsListSizeException(int entityListSize, int dtoListSize) {
    super(format(MESSAGE, dtoListSize, entityListSize));
  }
}
