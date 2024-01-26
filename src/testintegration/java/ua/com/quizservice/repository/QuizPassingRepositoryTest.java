package ua.com.quizservice.repository;

import static org.junit.jupiter.api.Assertions.*;
import static ua.com.constant.TestDataConstant.QUESTIONS_DATA_SQL;
import static ua.com.constant.TestDataConstant.QUIZ_DATA_SQL;
import static ua.com.constant.TestDataConstant.QUIZ_PASSING_REPOSITORY_SQL;
import static ua.com.constant.TestDataConstant.QUIZ_QUESTIONS_DATA_SQL;
import static ua.com.datagenerator.QuizPassingDataGenerator.generateUserIdWithFinishedQuizPassing;
import static ua.com.datagenerator.QuizPassingDataGenerator.generateUserIdWithInProgresQuizPassing;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ua.com.quizservice.IntegrationTestBase;

/** Replace this stub by correct Javadoc. */
@Sql(
    scripts = {
      QUESTIONS_DATA_SQL,
      QUIZ_DATA_SQL,
      QUIZ_QUESTIONS_DATA_SQL,
      QUIZ_PASSING_REPOSITORY_SQL
    })
class QuizPassingRepositoryTest extends IntegrationTestBase {

  @Autowired private QuizPassingRepository quizPassingRepository;

  @Test
  void isUserPassQuizNow_shouldReturnFalse_whenUserDontHaveInProgresQuizPassings() {
    final UUID userId = generateUserIdWithFinishedQuizPassing();

    final boolean actual = quizPassingRepository.isUserPassQuizNow(userId);

    assertFalse(actual, "User should not have in-progress quiz passings");
  }

  @Test
  void isUserPassQuizNow_shouldReturnTrue_whenUserHaveInProgresQuizPassings() {
    final UUID userId = generateUserIdWithInProgresQuizPassing();

    final boolean actual = quizPassingRepository.isUserPassQuizNow(userId);

    assertTrue(actual, "User should have in-progress quiz passings");
  }
}
