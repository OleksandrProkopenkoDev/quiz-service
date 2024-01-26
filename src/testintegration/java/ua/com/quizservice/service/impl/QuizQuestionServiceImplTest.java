package ua.com.quizservice.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ua.com.constant.TestDataConstant.QUESTIONS_DATA_SQL;
import static ua.com.constant.TestDataConstant.QUIZ_DATA_SQL;
import static ua.com.constant.TestDataConstant.QUIZ_QUESTIONS_DATA_SQL;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateExistingQuiz1Id;
import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateExistingQuiz1Question1Id;
import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateQuizQuestion1Dto;
import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateQuizQuestion1DtoWithSequence1;
import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateQuizQuestion4Dto;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ua.com.quizservice.IntegrationTestBase;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.dto.quiz.QuizQuestionDto;
import ua.com.quizservice.exception.QuizAlreadyContainQuestionException;
import ua.com.quizservice.service.QuizQuestionService;

/**
 * JUnit test class for {@link QuizQuestionServiceImpl}.
 *
 * <p>This class contains test cases for various methods in the {@link QuizQuestionServiceImpl}
 * implementation of the {@link QuizQuestionService} interface. It covers scenarios such as adding
 * quiz questions to quizzes, handling exceptions when questions are already added or have duplicate
 * sequences, and deleting quiz questions from quizzes.
 *
 * <p>Integration tests are performed, and the test data is initialized using SQL scripts. Mocked
 * data generators and predefined constants are used for consistent and repeatable test scenarios.
 *
 * <p>Note: The actual implementation of the QuizQuestionServiceImpl and its dependencies is
 * expected to be properly configured in the Spring context for successful execution of these tests.
 *
 * @author Oleksandr Prokopenko
 * @version 1.0
 * @since 2024-01-12
 */
@Slf4j
@Sql(scripts = {QUESTIONS_DATA_SQL, QUIZ_DATA_SQL, QUIZ_QUESTIONS_DATA_SQL})
class QuizQuestionServiceImplTest extends IntegrationTestBase {

  @Autowired private QuizQuestionService quizQuestionService;

  @Test
  void addQuizQuestionToQuiz_shouldAdd_whenQuizQuestionIsValid() {
    final UUID quiz1Id = generateExistingQuiz1Id();

    final QuizQuestionDto quizQuestionDto = generateQuizQuestion4Dto();

    final QuizDto quizDto = quizQuestionService.addQuizQuestionToQuiz(quiz1Id, quizQuestionDto);

    assertThat(quizDto.getQuizQuestionDtos()).size().isEqualTo(4);
  }

  @Test
  void addQuizQuestionToQuiz_shouldThrow_whenQuestionAlreadyAdded() {
    final UUID quiz1Id = generateExistingQuiz1Id();

    final QuizQuestionDto quizQuestionDto = generateQuizQuestion1Dto();

    assertThrows(
        QuizAlreadyContainQuestionException.class,
        () -> quizQuestionService.addQuizQuestionToQuiz(quiz1Id, quizQuestionDto));
  }

  @Test
  void addQuizQuestionToQuiz_shouldThrow_whenQuestionSequenceNotUnique() {
    final UUID quiz1Id = generateExistingQuiz1Id();

    final QuizQuestionDto quizQuestionDto = generateQuizQuestion1DtoWithSequence1();

    assertThrows(
        QuizAlreadyContainQuestionException.class,
        () -> quizQuestionService.addQuizQuestionToQuiz(quiz1Id, quizQuestionDto));
  }

  @Test
  void deleteQuizQuestionFromQuiz_shouldDelete_whenIdIsValid() {
    final UUID quiz1Id = generateExistingQuiz1Id();
    final UUID quiz1Question1Id = generateExistingQuiz1Question1Id();

    final QuizDto quizDto =
        quizQuestionService.deleteQuizQuestionFromQuiz(quiz1Id, quiz1Question1Id);

    final int expectedQuizQuestionDtosSize = 2;
    assertThat(quizDto.getQuizQuestionDtos()).size().isEqualTo(expectedQuizQuestionDtosSize);
  }
}
