package ua.com.datagenerator.content.question;

import java.util.HashSet;
import java.util.Set;
import ua.com.quizservice.model.content.TextImageWrapper;
import ua.com.quizservice.model.content.questioncontent.MultipleChoiceTestQuestionContent;
import ua.com.quizservice.model.content.questioncontent.ShortTextTestQuestionContent;

/**
 * Utility class providing JSON data and instances for testing {@link
 * MultipleChoiceTestQuestionContent}.
 *
 * @implNote The JSON data includes a correct JSON representation and a modified version with an
 *     incorrect content contentType. The {@link #getJsonMatchInstance()} method creates an instance
 *     of {@link MultipleChoiceTestQuestionContent} for testing purposes.
 * @author Zakhar Kuropiatnyk
 */
public class MultipleChoiceTestQuestionContentData {

  private static final String JSON =
      """
                     {\
                     "questionDescription":{\
                     "text":"What is the capital of France?",\
                     "image":"paris_image_url.jpg"\
                     }\
                     ,"answerOptions":[{\
                     "text":"Answer1",\
                     "image":"answer_image1_url.jpg"},\
                     {"text":"Answer2",\
                     "image":"answer_image1_url.jpg"}],\
                     "correctAnswer":["A","B"],\
                     "providedAnswer":["A","B"],\
                     "contentType":"MULTIPLE_TEST_CONTENT"}""";

  private static final String JSON_TEXT_NULL =
      """
                     {\
                     "questionDescription":{\
                     "text":null,\
                     "image":"paris_image_url.jpg"\
                     },\
                     "answerOptions":[{\
                     "text":null,\
                     "image":"answer_image1_url.jpg"\
                     },{\
                     "text":null,\
                     "image":"answer_image1_url.jpg"\
                     }],\
                     "correctAnswer":["A","B"],\
                     "providedAnswer":["A","B"],\
                     "contentType":"MULTIPLE_TEST_CONTENT"}""";

  /**
   * Returns the correct JSON representation for testing.
   *
   * @return The correct JSON representation.
   */
  public static String getCorrectJson() {
    return JSON;
  }

  public static String getJsonTextNull() {
    return JSON_TEXT_NULL;
  }

  /**
   * Returns modified JSON with an incorrect content contentType for testing.
   *
   * @return The modified JSON representation.
   */
  public static String getWrongContentTypeJson() {
    return JSON.replace("TEXT_TEST", "WRONG");
  }

  /**
   * Creates and returns an instance of {@link ShortTextTestQuestionContent} for testing purposes.
   *
   * @return An instance of {@link ShortTextTestQuestionContent}.
   */
  public static MultipleChoiceTestQuestionContent getJsonMatchInstance() {
    final Set<TextImageWrapper> answerOptions = new HashSet<>();
    answerOptions.add(
        TextImageWrapper.builder().text("Answer1").image("answer_image1_url.jpg").build());
    answerOptions.add(
        TextImageWrapper.builder().text("Answer2").image("answer_image1_url.jpg").build());
    final Set<String> correctAnswer = new HashSet<>();
    correctAnswer.add("A");
    correctAnswer.add("B");
    final Set<String> providedAnswer = new HashSet<>(correctAnswer);

    return MultipleChoiceTestQuestionContent.builder()
        .questionDescription(
            TextImageWrapper.builder()
                .text("What is the capital of France?")
                .image("paris_image_url.jpg")
                .build())
        .answerOptions(answerOptions)
        .correctAnswer(correctAnswer)
        .providedAnswer(providedAnswer)
        .build();
  }
}
