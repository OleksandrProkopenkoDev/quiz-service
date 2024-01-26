package ua.com.quizservice.enums;

/** Replace this stub by correct Javadoc. */
public enum QuizStatus {
  /** quizzes can be complited, but execution has not started yet */
  UNDONE,
  /** applicant finished execution, checking manual answers by reviewer */
  CHECKING,
  /** If passed: contacts of HR and Calendly-like panel, for next interview */
  PASSED,
  /**
   * If failed: markdown with recommendations, what skills need to improve. Or if uniq case, become
   * AMNESTY_ONCE.
   */
  FAILED,
  /** If quiz was failed, it can be completed once again. Must finally becmome FAILED or PASSED */
  AMNESTY_ONCE
}
