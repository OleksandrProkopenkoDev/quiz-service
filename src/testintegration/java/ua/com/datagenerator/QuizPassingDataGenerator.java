package ua.com.datagenerator;

import java.util.UUID;

/** Replace this stub by correct Javadoc. */
public class QuizPassingDataGenerator {

  private static final UUID USER_ID_WITH_FINISHED_QUIZ_PASSING =
      UUID.fromString("981e1e88-1bd2-47e6-b975-c644f2c33ae1");
  private static final UUID USER_ID_WITH_IN_PROGRES_QUIZ_PASSING =
      UUID.fromString("981e1e88-1bd2-47e6-b975-c644f2c33ae2");

  public static UUID generateUserIdWithFinishedQuizPassing() {
    return USER_ID_WITH_FINISHED_QUIZ_PASSING;
  }

  public static UUID generateUserIdWithInProgresQuizPassing() {
    return USER_ID_WITH_IN_PROGRES_QUIZ_PASSING;
  }
}
