package ua.com.datagenerator.quiz;

import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateQuiz1QuestionDtoList;
import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateQuiz2QuestionDtoList;
import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateQuizQuestionDtoListWithoutId;
import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateQuizQuestionDtoListWithoutIdAndNotExistingQuestionId;
import static ua.com.datagenerator.quiz.QuizQuestionDataGenerator.generateQuizQuestionDtoListWithoutIdAndQuestionId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import ua.com.quizservice.dto.quiz.QuizDto;

/**
 * The QuizDataGenerator class provides static methods for getting quizDto.
 *
 * @author Artyom Kondratenko
 * @since 12/11/23
 */
public final class QuizDataGenerator {

  private static final UUID QUIZ1_ID = UUID.fromString("550e8400-1111-41d4-a716-446655440001");
  private static final UUID QUIZ2_ID = UUID.fromString("550e8400-1111-41d4-a716-446655440002");

  private static final int DEFAULT_PAGE_SIZE = 5;

  private static final String QUIZ1_TITLE = "Quiz1 Title";
  private static final String QUIZ2_TITLE = "Quiz2 Title";
  private static final String QUIZ1_DESCRIPTION = "Test your skills";
  private static final String QUIZ2_DESCRIPTION = "Test your geographical skills";
  private static final Float QUIZ1_PASSING_SCORE = 0.50F;
  private static final Float QUIZ2_PASSING_SCORE = 0.60F;
  private static final String QUIZ1_CREATED_AT = "2024-01-12T00:17:19.699000Z";
  private static final String QUIZ1_UPDATED_AT = "2024-01-12T00:17:19.699892Z";
  private static final String QUIZ2_CREATED_AT = "2024-01-12T00:24:06.309000Z";
  private static final String QUIZ2_UPDATED_AT = "2024-01-12T00:24:06.309765Z";
  private static final int QUIZ1_TOTAL_QUESTION = 3;
  private static final int QUIZ1_TOTAL_DURATION = 150;
  private static final Integer QUIZ2_TOTAL_QUESTION = 3;
  private static final int QUIZ2_TOTAL_DURATION = 130;

  private QuizDataGenerator() {
    throw new UnsupportedOperationException(
        "Utility QuizDataGenerator class cannot be instantiated");
  }

  public static QuizDto generateQuiz1Dto(UUID id) {
    final QuizDto quizDto = generateQuiz1Dto();
    quizDto.setId(id);
    return quizDto;
  }

  public static QuizDto generateQuizDtoWithoutIdAndNotExistingQuestionId() {
    return QuizDto.builder()
        .id(null)
        .title(QUIZ1_TITLE)
        .description(QUIZ1_DESCRIPTION)
        .passingScore(QUIZ1_PASSING_SCORE)
        .quizQuestionDtos(generateQuizQuestionDtoListWithoutIdAndNotExistingQuestionId())
        .published(false)
        .build();
  }

  public static QuizDto generateQuizDtoWithoutQuestionId() {
    return QuizDto.builder()
        .id(null)
        .title(QUIZ1_TITLE)
        .description(QUIZ1_DESCRIPTION)
        .passingScore(QUIZ1_PASSING_SCORE)
        .quizQuestionDtos(generateQuizQuestionDtoListWithoutIdAndQuestionId())
        .published(false)
        .build();
  }

  public static QuizDto generateQuizDtoWithoutQuizQuestionId() {
    final QuizDto quizDto = generateQuizDtoWithoutQuestionId();
    quizDto.getQuizQuestionDtos().get(0).setId(null);
    return quizDto;
  }

  public static QuizDto generateQuizDtoWithNotExistingQuizQuestionId() {
    final QuizDto quizDto = generateQuiz1Dto();
    quizDto.getQuizQuestionDtos().get(0).setId(UUID.randomUUID());
    return quizDto;
  }

  public static QuizDto generateQuizDtoWithoutId() {
    return QuizDto.builder()
        .id(null)
        .title(QUIZ1_TITLE)
        .description(QUIZ1_DESCRIPTION)
        .passingScore(QUIZ1_PASSING_SCORE)
        .quizQuestionDtos(generateQuizQuestionDtoListWithoutId())
        .published(false)
        .build();
  }

  public static QuizDto generateQuiz1Dto() {
    return QuizDto.builder()
        .id(QUIZ1_ID)
        .title(QUIZ1_TITLE)
        .description(QUIZ1_DESCRIPTION)
        .passingScore(QUIZ1_PASSING_SCORE)
        .quizQuestionDtos(generateQuiz1QuestionDtoList())
        .createdAt(ZonedDateTime.parse(QUIZ1_CREATED_AT))
        .updatedAt(ZonedDateTime.parse(QUIZ1_UPDATED_AT))
        .totalQuestion(QUIZ1_TOTAL_QUESTION)
        .totalDuration(QUIZ1_TOTAL_DURATION)
        .published(false)
        .build();
  }

  public static QuizDto generateQuiz2Dto() {
    return QuizDto.builder()
        .id(QUIZ2_ID)
        .title(QUIZ2_TITLE)
        .description(QUIZ2_DESCRIPTION)
        .passingScore(QUIZ2_PASSING_SCORE)
        .quizQuestionDtos(generateQuiz2QuestionDtoList())
        .createdAt(ZonedDateTime.parse(QUIZ2_CREATED_AT))
        .updatedAt(ZonedDateTime.parse(QUIZ2_UPDATED_AT))
        .totalQuestion(QUIZ2_TOTAL_QUESTION)
        .totalDuration(QUIZ2_TOTAL_DURATION)
        .published(false)
        .build();
  }

  public static List<QuizDto> generateQuizDtoList() {
    return List.of(generateQuiz1Dto(), generateQuiz2Dto());
  }

  public static UUID generateExistingQuiz1Id() {
    return QUIZ1_ID;
  }

  public static UUID generateNotExistingQuizId() {
    return UUID.randomUUID();
  }

  public static Pageable generateDefaultPageable() {
    return Pageable.ofSize(DEFAULT_PAGE_SIZE);
  }
}
