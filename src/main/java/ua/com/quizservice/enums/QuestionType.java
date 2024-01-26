package ua.com.quizservice.enums;

/** Replace this stub by correct Javadoc. */
public enum QuestionType {

  /** Test question with one answer option. Checks automatically. */
  SINGLE_TEST,
  /** Test question with more than one answer option. Checks automatically. */
  MULTIPLE_TEST,
  /**
   * Question in which user should select the correct matches between the fields from the first
   * column with the fields from the second. Checks automatically.
   */
  MATCHING_TEST,
  /** Question with an answer field for answer. Checks automatically. */
  TEXT_TEST,
  /** Question with an answer field for answer. Checks manually by Mentor or Manager. */
  TEXT_TO_MENTOR,
  /** Question with voice for answer. Checks manually by Mentor or Manager */
  VOICE_TO_MENTOR
}
