package ua.com.datagenerator.content.question;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import ua.com.quizservice.model.content.TextImageWrapper;
import ua.com.quizservice.model.content.questioncontent.MatchingTestQuestionContent;
import ua.com.quizservice.model.content.questioncontent.ShortTextTestQuestionContent;

/**
 * Utility class providing JSON data and instances for testing {@link MatchingTestQuestionContent}.
 *
 * @implNote The JSON data includes a correct JSON representation and a modified version with an
 *     incorrect content contentType. The {@link #getJsonMatchInstance()} method creates an instance
 *     of {@link MatchingTestQuestionContent} for testing purposes.
 * @author Zakhar Kuropiatnyk
 */
public class MatchingTestQuestionContentData {

  private static final String JSON =
      """
                     {\
                     "questionDescription":{"text":"What is the capital of France?",\
                     "image":"paris_image_url.jpg"},\
                     "answerOptions":\
                     {"TextImageWrapper(text=Answer1, image=answer_image1_url.jpg)":\
                     {"text":"Maybe capital of France is Paris?","image":"paris_image_url.jpg"},\
                     "TextImageWrapper(text=Answer2, image=answer_image1_url.jpg)":\
                     {"text":"Maybe capital of France is?","image":"image_url.jpg"}},\
                     "correctAnswer":{"A":"Paris","B":"Pari"},\
                     "providedAnswer":{"A":"Paris","B":"Pari"},\
                     "contentType":"MATCHING_TEST_CONTENT"}""";

  private static final String JSON_TEXT_NULL =
      """
                     {\
                     "questionDescription":{\
                     "text":null,\
                     "image":"paris_image_url.jpg"\
                     },\
                     "answerOptions":{\
                     "TextImageWrapper(text=null, image=answer_image1_url.jpg)"\
                     :{\
                     "text":null,\
                     "image":"paris_image_url.jpg"},\
                     "TextImageWrapper(text=null, image=answer_image1_url.jpg)"\
                     :{"text":null,"image":"image_url.jpg"}\
                     },\
                     "correctAnswer":\
                     {"A":"Paris","B":"Pari"},\
                     "providedAnswer":{"A":"Paris","B":"Pari"},\
                     "contentType":"MATCHING_TEST_CONTENT"}""";

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
  public static MatchingTestQuestionContent getJsonMatchInstance() {
    final Map<TextImageWrapper, TextImageWrapper> answerOptions = new ConcurrentHashMap<>();
    answerOptions.put(
        TextImageWrapper.builder().text("Answer1").image("answer_image1_url.jpg").build(),
        TextImageWrapper.builder()
            .text("Maybe capital of France is Paris?")
            .image("paris_image_url.jpg")
            .build());
    answerOptions.put(
        TextImageWrapper.builder().text("Answer2").image("answer_image1_url.jpg").build(),
        TextImageWrapper.builder()
            .text("Maybe capital of France is?")
            .image("image_url.jpg")
            .build());
    final Map<String, String> correctAnswer = new ConcurrentHashMap<>();
    correctAnswer.put("A", "Paris");
    correctAnswer.put("B", "Pari");
    final Map<String, String> providedAnswer = new ConcurrentHashMap<>(correctAnswer);

    return MatchingTestQuestionContent.builder()
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
