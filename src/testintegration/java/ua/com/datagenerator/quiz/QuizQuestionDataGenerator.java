package ua.com.datagenerator.quiz;

import static ua.com.datagenerator.QuestionDataGenerator.generateQuestion1Dto;
import static ua.com.datagenerator.QuestionDataGenerator.generateQuestion2Dto;
import static ua.com.datagenerator.QuestionDataGenerator.generateQuestion3Dto;
import static ua.com.datagenerator.QuestionDataGenerator.generateQuestion4Dto;
import static ua.com.datagenerator.QuestionDataGenerator.generateQuestion5Dto;
import static ua.com.datagenerator.QuestionDataGenerator.generateQuestionDtoWithNotExistingId;
import static ua.com.datagenerator.QuestionDataGenerator.generateQuestionDtoWithoutId;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import ua.com.quizservice.dto.question.QuestionDto;
import ua.com.quizservice.dto.quiz.QuizQuestionDto;

/**
 * The QuizQuestionDataGenerator class provides utility methods for generating QuizQuestionDto
 * objects and lists for quiz questions, including different scenarios and sequences.
 *
 * <p>This class includes methods to generate QuizQuestionDto objects with or without IDs,
 * sequences, and associated QuestionDto objects. It is designed as a utility class and cannot be
 * instantiated.
 *
 * @author Oleksandr Prokopenko
 * @version 1.0
 * @since 2024-01-12
 */
@Component
public final class QuizQuestionDataGenerator {

  private static final UUID QUIZ1_QUESTION1_ID =
      UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
  private static final UUID QUIZ1_QUESTION2_ID =
      UUID.fromString("550e8400-e29b-41d4-a716-446655440002");
  private static final UUID QUIZ1_QUESTION3_ID =
      UUID.fromString("550e8400-e29b-41d4-a716-446655440003");
  private static final UUID QUIZ2_QUESTION1_ID =
      UUID.fromString("550e8400-e29b-41d4-a716-446655440011");
  private static final UUID QUIZ2_QUESTION2_ID =
      UUID.fromString("550e8400-e29b-41d4-a716-446655440012");
  private static final UUID QUIZ2_QUESTION3_ID =
      UUID.fromString("550e8400-e29b-41d4-a716-446655440013");

  private static final UUID NOT_EXISTING_QUIZ_QUESTION_ID =
      UUID.fromString("450e8400-1111-41d4-a716-446655440000");

  private static final Integer DEFAULT_WEIGHT = 40;
  private static final Integer SEQUENCE_FIRST = 1;
  private static final Integer SEQUENCE_SECOND = 2;
  private static final Integer SEQUENCE_THIRD = 3;
  private static final Integer SEQUENCE_FOUR = 4;

  private QuizQuestionDataGenerator() {
    throw new UnsupportedOperationException(
        "Utility QuizQuestionDataGenerator class cannot be instantiated");
  }

  public static List<QuizQuestionDto>
      generateQuizQuestionDtoListWithoutIdAndNotExistingQuestionId() {
    return List.of(
        generateQuizQuestionDto(null, SEQUENCE_FIRST, generateQuestionDtoWithNotExistingId()),
        generateQuizQuestionDto(null, SEQUENCE_SECOND, generateQuestionDtoWithNotExistingId()),
        generateQuizQuestionDto(null, SEQUENCE_THIRD, generateQuestionDtoWithNotExistingId()));
  }

  public static List<QuizQuestionDto> generateQuizQuestionDtoListWithoutIdAndQuestionId() {
    return List.of(
        generateQuizQuestionDto(null, SEQUENCE_FIRST, generateQuestionDtoWithoutId()),
        generateQuizQuestionDto(null, SEQUENCE_SECOND, generateQuestionDtoWithoutId()),
        generateQuizQuestionDto(null, SEQUENCE_THIRD, generateQuestionDtoWithoutId()));
  }

  public static List<QuizQuestionDto> generateQuizQuestionDtoListWithoutId() {
    return List.of(
        generateQuizQuestionDto(null, SEQUENCE_FIRST, generateQuestion1Dto()),
        generateQuizQuestionDto(null, SEQUENCE_SECOND, generateQuestion2Dto()),
        generateQuizQuestionDto(null, SEQUENCE_THIRD, generateQuestion3Dto()));
  }

  public static List<QuizQuestionDto> generateQuiz1QuestionDtoList() {
    return List.of(
        generateQuizQuestionDto(QUIZ1_QUESTION1_ID, SEQUENCE_FIRST, generateQuestion1Dto()),
        generateQuizQuestionDto(QUIZ1_QUESTION2_ID, SEQUENCE_SECOND, generateQuestion2Dto()),
        generateQuizQuestionDto(QUIZ1_QUESTION3_ID, SEQUENCE_THIRD, generateQuestion3Dto()));
  }

  public static List<QuizQuestionDto> generateQuiz2QuestionDtoList() {
    return List.of(
        generateQuizQuestionDto(QUIZ2_QUESTION1_ID, SEQUENCE_FIRST, generateQuestion3Dto()),
        generateQuizQuestionDto(QUIZ2_QUESTION2_ID, SEQUENCE_SECOND, generateQuestion4Dto()),
        generateQuizQuestionDto(QUIZ2_QUESTION3_ID, SEQUENCE_THIRD, generateQuestion5Dto()));
  }

  private static QuizQuestionDto generateQuizQuestionDto(
      UUID id, Integer sequence, QuestionDto questionDto) {
    return QuizQuestionDto.builder()
        .id(id)
        .weight(DEFAULT_WEIGHT)
        .sequence(sequence)
        .questionDto(questionDto)
        .build();
  }

  public static QuizQuestionDto generateQuizQuestion1Dto() {
    return QuizQuestionDto.builder()
        .id(null)
        .weight(DEFAULT_WEIGHT)
        .sequence(SEQUENCE_FOUR)
        .questionDto(generateQuestion1Dto())
        .build();
  }

  public static QuizQuestionDto generateQuizQuestion1DtoWithSequence1() {
    return QuizQuestionDto.builder()
        .id(null)
        .weight(DEFAULT_WEIGHT)
        .sequence(SEQUENCE_FIRST)
        .questionDto(generateQuestion1Dto())
        .build();
  }

  public static QuizQuestionDto generateQuizQuestion4Dto() {
    return QuizQuestionDto.builder()
        .id(null)
        .weight(DEFAULT_WEIGHT)
        .sequence(SEQUENCE_FOUR)
        .questionDto(generateQuestion4Dto())
        .build();
  }

  public static UUID generateNotExistingQuizQuestionId() {
    return NOT_EXISTING_QUIZ_QUESTION_ID;
  }

  public static UUID generateExistingQuiz1Question1Id() {
    return QUIZ1_QUESTION1_ID;
  }
}
