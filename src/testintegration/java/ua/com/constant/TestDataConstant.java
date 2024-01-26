package ua.com.constant;

import ua.com.quizservice.entity.quiz.Quiz;
import ua.com.quizservice.entity.vacancy.Vacancy;

/**
 * Class for keeping Paths to sql scripts.
 *
 * @author Artyom Kondratenko
 * @implNote To add new Constant, follow the pattern: {SQL_PATH + "scriptName" + POSTFIX}
 * @since 12/16/23
 */
public final class TestDataConstant {

  private static final String SQL_PATH = "classpath:/db/";
  private static final String POSTFIX = ".sql";

  /** Sql script for testing TestTour. Included data: {@link Quiz}, {@link Vacancy}. */
  public static final String TEST_TOUR_SQL = SQL_PATH + "insertTestTourData" + POSTFIX;

  public static final String QUESTIONS_DATA_SQL = SQL_PATH + "question-test-data" + POSTFIX;
  public static final String QUIZ_DATA_SQL = SQL_PATH + "quiz-test-data" + POSTFIX;
  public static final String QUIZ_QUESTIONS_DATA_SQL =
      SQL_PATH + "quiz-question-test-data" + POSTFIX;
  public static final String QUIZ_PASSING_REPOSITORY_SQL =
      SQL_PATH + "quiz-passing-repository-test-data" + POSTFIX;
}
