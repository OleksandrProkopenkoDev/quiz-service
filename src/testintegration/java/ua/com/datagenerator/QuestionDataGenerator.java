package ua.com.datagenerator;

import static ua.com.quizservice.enums.QuestionType.MATCHING_TEST;
import static ua.com.quizservice.enums.QuestionType.MULTIPLE_TEST;
import static ua.com.quizservice.enums.QuestionType.SINGLE_TEST;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import ua.com.datagenerator.content.question.MatchingTestQuestionContentData;
import ua.com.datagenerator.content.question.MultipleChoiceTestQuestionContentData;
import ua.com.datagenerator.content.question.SingleChoiceTestQuestionContentData;
import ua.com.quizservice.dto.question.QuestionDto;
import ua.com.quizservice.dto.question.QuestionFilterCriteria;
import ua.com.quizservice.enums.QuestionType;
import ua.com.quizservice.model.content.Content;

/** Please, replace this stub with valid comment. */
public class QuestionDataGenerator {
  private static final UUID EXISTING_QUESTION_ID =
      UUID.fromString("baa8bb7d-9533-4483-bdb4-204efba55e7a");
  private static final UUID QUESTION2_ID = UUID.fromString("2bc5530e-0eb3-4ed7-8f74-cbc4cbf7b5bb");
  private static final UUID QUESTION3_ID = UUID.fromString("570f93b5-485c-42ea-82f9-8c866ac0e4c4");
  private static final UUID QUESTION4_ID = UUID.fromString("024890e3-c883-4049-947b-8af2f9a6bfdf");
  private static final UUID QUESTION5_ID = UUID.fromString("e4f282d8-4308-44e8-a47c-687e556ead24");
  private static final UUID NOT_EXISTING_QUESTION_ID = UUID.randomUUID();
  private static final QuestionType DEFAULT_QUESTION_TYPE = SINGLE_TEST;
  private static final Integer DEFAULT_DURATION = 50;
  private static final String DEFAULT_DESCRIPTION = "description";

  private static final Content SINGLE_CHOICE_TEST_QUESTION_CONTENT =
      SingleChoiceTestQuestionContentData.getJsonMatchInstance();
  private static final Content MULTIPLE_CHOICE_TEST_QUESTION_CONTENT =
      MultipleChoiceTestQuestionContentData.getJsonMatchInstance();
  private static final Content MATCHING_TEST_QUESTION_CONTENT =
      MatchingTestQuestionContentData.getJsonMatchInstance();
  private static final List<QuestionType> DEFAULT_LIST_WITH_SEVERAL_QUESTION_TYPES =
      List.of(SINGLE_TEST, MULTIPLE_TEST);
  private static final Integer DEFAULT_MAX_DURATION = 60;
  private static final Integer DEFAULT_MIN_DURATION = 40;
  private static final Pageable DEFAULT_UNPAGED_PAGEABLE = Pageable.unpaged();
  private static final Integer EXPECTED_SIZE_SELECTED_TYPE = 2;
  private static final Integer EXPECTED_SIZE_SEVERAL_SELECTED_TYPE = 3;
  private static final Integer EXPECTED_SIZE_MAX_DURATION = 4;
  private static final Integer EXPECTED_SIZE_MIN_DURATION = 4;
  private static final Integer EXPECTED_SIZE_WITH_CRITERIA = 3;
  private static final Integer EXPECTED_SIZE_WITHOUT_CRITERIA = 5;

  public static UUID generateExistsQuestionId() {
    return EXISTING_QUESTION_ID;
  }

  public static UUID generateNotExistsQuestionId() {
    return NOT_EXISTING_QUESTION_ID;
  }

  public static QuestionDto generateQuestionDto() {
    return QuestionDto.builder()
        .id(EXISTING_QUESTION_ID)
        .type(DEFAULT_QUESTION_TYPE)
        .duration(DEFAULT_DURATION)
        .description(DEFAULT_DESCRIPTION)
        .content(SINGLE_CHOICE_TEST_QUESTION_CONTENT)
        .build();
  }

  public static QuestionDto generateQuestionDtoWithType(QuestionType type) {
    return QuestionDto.builder()
        .id(EXISTING_QUESTION_ID)
        .type(type)
        .duration(DEFAULT_DURATION)
        .description(DEFAULT_DESCRIPTION)
        .content(SINGLE_CHOICE_TEST_QUESTION_CONTENT)
        .build();
  }

  public static QuestionDto generateQuestionDtoWithoutId() {
    return QuestionDto.builder()
        .type(DEFAULT_QUESTION_TYPE)
        .duration(DEFAULT_DURATION)
        .description(DEFAULT_DESCRIPTION)
        .content(SINGLE_CHOICE_TEST_QUESTION_CONTENT)
        .build();
  }

  public static QuestionDto generateQuestion1Dto() {
    return generateQuestionDto();
  }

  public static QuestionDto generateQuestion2Dto() {
    return QuestionDto.builder()
        .id(QUESTION2_ID)
        .type(MULTIPLE_TEST)
        .duration(60)
        .description(DEFAULT_DESCRIPTION)
        .content(MULTIPLE_CHOICE_TEST_QUESTION_CONTENT)
        .build();
  }

  public static QuestionDto generateQuestion3Dto() {
    return QuestionDto.builder()
        .id(QUESTION3_ID)
        .type(SINGLE_TEST)
        .duration(40)
        .description(DEFAULT_DESCRIPTION)
        .content(SINGLE_CHOICE_TEST_QUESTION_CONTENT)
        .build();
  }

  public static QuestionDto generateQuestion4Dto() {
    return QuestionDto.builder()
        .id(QUESTION4_ID)
        .type(MATCHING_TEST)
        .duration(70)
        .description(DEFAULT_DESCRIPTION)
        .content(MATCHING_TEST_QUESTION_CONTENT)
        .build();
  }

  public static QuestionDto generateQuestion5Dto() {
    return QuestionDto.builder()
        .id(QUESTION5_ID)
        .type(MATCHING_TEST)
        .duration(20)
        .description(DEFAULT_DESCRIPTION)
        .content(MATCHING_TEST_QUESTION_CONTENT)
        .build();
  }

  public static QuestionDto generateQuestionDtoWithNotExistingId() {
    return QuestionDto.builder()
        .id(NOT_EXISTING_QUESTION_ID)
        .type(DEFAULT_QUESTION_TYPE)
        .duration(DEFAULT_DURATION)
        .description(DEFAULT_DESCRIPTION)
        .content(SINGLE_CHOICE_TEST_QUESTION_CONTENT)
        .build();
  }

  public static QuestionFilterCriteria generateQuestionFilterCriteriaWithType() {
    return QuestionFilterCriteria.builder().type(List.of(DEFAULT_QUESTION_TYPE)).build();
  }

  public static QuestionFilterCriteria generateQuestionFilterCriteriaWithSeveralType() {
    return QuestionFilterCriteria.builder().type(DEFAULT_LIST_WITH_SEVERAL_QUESTION_TYPES).build();
  }

  public static QuestionFilterCriteria generateQuestionFilterCriteriaWithMaxDuration() {
    return QuestionFilterCriteria.builder().maxDuration(DEFAULT_MAX_DURATION).build();
  }

  public static QuestionFilterCriteria generateQuestionFilterCriteriaWithMinDuration() {
    return QuestionFilterCriteria.builder().minDuration(DEFAULT_MIN_DURATION).build();
  }

  public static QuestionFilterCriteria generateQuestionFilterCriteriaWithAllCriterias() {
    return QuestionFilterCriteria.builder()
        .type(DEFAULT_LIST_WITH_SEVERAL_QUESTION_TYPES)
        .maxDuration(DEFAULT_MAX_DURATION)
        .minDuration(DEFAULT_MIN_DURATION)
        .build();
  }

  public static Pageable generateUnpagedPageable() {
    return DEFAULT_UNPAGED_PAGEABLE;
  }

  public static Integer generateExpectedSizeForFindAllWithSelectedType() {
    return EXPECTED_SIZE_SELECTED_TYPE;
  }

  public static Integer generateExpectedSizeForFindAllWithSeveralSelectedType() {
    return EXPECTED_SIZE_SEVERAL_SELECTED_TYPE;
  }

  public static Integer generateExpectedSizeForFindAllWithSelectedMaxDuration() {
    return EXPECTED_SIZE_MAX_DURATION;
  }

  public static Integer generateExpectedSizeForFindAllWithSelectedMinDuration() {
    return EXPECTED_SIZE_MIN_DURATION;
  }

  public static Integer generateExpectedSizeForFindAllWithAllFiltrationCriteria() {
    return EXPECTED_SIZE_WITH_CRITERIA;
  }

  public static Integer generateExpectedSizeForFindAllWithoutFiltrationCriteria() {
    return EXPECTED_SIZE_WITHOUT_CRITERIA;
  }
}
