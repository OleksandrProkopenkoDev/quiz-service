package ua.com.quizservice.exception;

import java.util.List;

/**
 * Exception thrown when a list of QuizQuestions contains duplicates in the sequence. This exception
 * indicates that the sequence values within the list must be unique.
 */
public class QuizQuestionSequenceDuplicateException extends RuntimeException {
  public QuizQuestionSequenceDuplicateException(List<Integer> sequenceList) {
    super("List of QuizQuestions contains duplicates in sequence : " + sequenceList);
  }
}
