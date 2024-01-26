package ua.com.quizservice.exception;

/** Replace this stub by correct Javadoc. */
public class QuestionAnswerNotFoundException extends RuntimeException {

  private static final String ERROR_MESSAGE =
      "QuestionAnswer with id: %s in quiz part with id: %s in quiz with id: %s not found.";

  public QuestionAnswerNotFoundException(Long questionAnswerId, Long quizPartId, Long quizId) {
    super(ERROR_MESSAGE.formatted(questionAnswerId, quizPartId, quizId));
  }
}
