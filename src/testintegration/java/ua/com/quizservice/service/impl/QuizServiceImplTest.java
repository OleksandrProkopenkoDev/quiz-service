package ua.com.quizservice.service.impl;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ua.com.constant.TestDataConstant.QUESTIONS_DATA_SQL;
import static ua.com.constant.TestDataConstant.QUIZ_DATA_SQL;
import static ua.com.constant.TestDataConstant.QUIZ_QUESTIONS_DATA_SQL;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateDefaultPageable;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateExistingQuiz1Id;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateNotExistingQuizId;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateQuiz1Dto;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateQuiz2Dto;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateQuizDtoList;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateQuizDtoWithNotExistingQuizQuestionId;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateQuizDtoWithoutId;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateQuizDtoWithoutIdAndNotExistingQuestionId;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateQuizDtoWithoutQuestionId;
import static ua.com.datagenerator.quiz.QuizDataGenerator.generateQuizDtoWithoutQuizQuestionId;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import ua.com.quizservice.IntegrationTestBase;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.exception.QuestionNotFoundException;
import ua.com.quizservice.exception.QuestionWithoutIdException;
import ua.com.quizservice.exception.QuizNotFoundException;
import ua.com.quizservice.exception.QuizQuestionIdCanNotBeNullException;
import ua.com.quizservice.exception.QuizQuestionNotFoundException;
import ua.com.quizservice.service.QuizService;

/**
 * JUnit test class for {@link QuizServiceImpl}.
 *
 * <p>This class contains test cases for various methods in the {@link QuizServiceImpl}
 * implementation of the {@link QuizService} interface. It covers scenarios such as finding quizzes
 * by ID, filtering quizzes, creating new quizzes, updating existing quizzes, deleting quizzes by
 * ID, and publishing quizzes.
 *
 * <p>Integration tests are performed, and the test data is initialized using SQL scripts. Mocked
 * data generators and predefined constants are used for consistent and repeatable test scenarios.
 *
 * <p>Note: The actual implementation of the QuizServiceImpl and its dependencies is expected to be
 * properly configured in the Spring context for successful execution of these tests.
 *
 * @author Oleksandr Prokopenko
 * @version 1.0
 * @since 2024-01-12
 */
@Sql(scripts = {QUESTIONS_DATA_SQL, QUIZ_DATA_SQL, QUIZ_QUESTIONS_DATA_SQL})
class QuizServiceImplTest extends IntegrationTestBase {

  @Autowired private QuizService quizService;

  @Test
  void findById_shouldReturnQuiz_whenIdIsValid() {
    final UUID quizId = generateExistingQuiz1Id();
    final QuizDto expected = generateQuiz1Dto();

    final QuizDto actual = quizService.findById(quizId);

    assertEquals(expected, actual, "Returned quiz doesn`t match expected");
  }

  @Test
  void findById_shouldThrow_whenIdNotExists() {
    final UUID notExistingQuizId = generateNotExistingQuizId();
    final QuizNotFoundException exception =
        assertThrows(QuizNotFoundException.class, () -> quizService.findById(notExistingQuizId));
    assertEquals(
        format("Quiz entity with id: %s not found.", notExistingQuizId),
        exception.getMessage(),
        "Quiz exists and exception is not thrown");
  }

  @Test
  void findAll_shouldReturnPageWithList() {
    final Page<QuizDto> actualPage = quizService.findAll(generateDefaultPageable());
    final List<QuizDto> actual = actualPage.stream().toList();
    final List<QuizDto> expected = generateQuizDtoList();

    assertEquals(expected, actual, "Returned page dont match expected");
  }

  @Test
  void createQuiz_shouldSaveNewQuiz() {
    final QuizDto quizDto = generateQuizDtoWithoutId();

    final QuizDto actual = quizService.createQuiz(quizDto);

    assertNotNull(actual.getId(), "QuizId is null after saving to DB");
  }

  @Test
  void createQuiz_shouldThrow_whenQuestionIdNotPresentInRequest() {
    final QuizDto quizDto = generateQuizDtoWithoutQuestionId();

    assertThrows(
        QuestionWithoutIdException.class,
        () -> quizService.createQuiz(quizDto),
        "QuestionId is present, exception not thrown");
  }

  @Test
  void createQuiz_shouldThrow_whenQuestionIdNotPresentInDatabase() {
    final QuizDto quizDto = generateQuizDtoWithoutIdAndNotExistingQuestionId();

    assertThrows(
        QuestionNotFoundException.class,
        () -> quizService.createQuiz(quizDto),
        "QuestionId is present, exception not thrown");
  }

  @Test
  void updateQuiz_shouldReturnUpdatedDto_whenQuizIsFound() {
    final UUID quizId = generateExistingQuiz1Id();
    final QuizDto newQuizDto = generateQuiz2Dto();

    final QuizDto updatedQuiz = quizService.updateQuiz(quizId, newQuizDto);

    assertNotNull(updatedQuiz.getId(), "QuizId is null after saving to DB");
    assertThat(updatedQuiz).usingRecursiveComparison().ignoringFields("id").isEqualTo(newQuizDto);
  }

  @Test
  void updateQuiz_shouldThrow_whenQuizQuestionIdIsNull() {
    final UUID quizId = generateExistingQuiz1Id();
    final QuizDto newQuizDto = generateQuizDtoWithoutQuizQuestionId();

    assertThrows(
        QuizQuestionIdCanNotBeNullException.class,
        () -> quizService.updateQuiz(quizId, newQuizDto),
        "QuizQuestion id is not null");
  }

  @Test
  void updateQuiz_shouldThrow_whenQuizQuestionIdNotExists() {
    final UUID quizId = generateExistingQuiz1Id();
    final QuizDto newQuizDto = generateQuizDtoWithNotExistingQuizQuestionId();

    assertThrows(
        QuizQuestionNotFoundException.class,
        () -> quizService.updateQuiz(quizId, newQuizDto),
        "QuizQuestion exists in db");
  }

  @Test
  void deleteQuizById_shouldDelete() {
    final UUID quizId = generateExistingQuiz1Id();

    quizService.deleteQuizById(quizId);

    assertThrows(
        QuizNotFoundException.class,
        () -> quizService.findById(quizId),
        "Quiz exists after deleting");
  }

  @Test
  void publish_shouldPublishQuiz() {
    final UUID quiz1Id = generateExistingQuiz1Id();
    final QuizDto publishedQuiz = quizService.publish(quiz1Id);

    assertTrue(publishedQuiz.getPublished(), "Quiz is not published");
  }
}
