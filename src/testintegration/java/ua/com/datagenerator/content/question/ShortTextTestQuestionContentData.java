package ua.com.datagenerator.content.question;

import ua.com.quizservice.model.content.TextImageWrapper;
import ua.com.quizservice.model.content.questioncontent.ShortTextTestQuestionContent;

/**
 * Utility class providing JSON data and instances for testing {@link ShortTextTestQuestionContent}.
 *
 * @implNote The JSON data includes a correct JSON representation and a modified version with an
 *     incorrect content contentType. The {@link #getJsonMatchInstance()} method creates an instance
 *     of {@link ShortTextTestQuestionContent} for testing purposes.
 * @author Zakhar Kuropiatnyk
 */
public class ShortTextTestQuestionContentData {

  private static final String JSON =
      """
                  {\
                  "questionDescription":{\
                  "text":"What is the capital of France?",\
                  "image":"paris_image_url.jpg"\
                  },\
                  "correctAnswer":"Paris",\
                  "providedAnswer":"Paris",\
                  "contentType":"TEXT_TEST_CONTENT"\
                  }""";
  private static final String JSON_TEXT_NULL =
      """
                  {\
                  "questionDescription":{\
                  "text":null,\
                  "image":"paris_image_url.jpg"\
                  },\
                  "correctAnswer":"Paris",\
                  "providedAnswer":"Paris",\
                  "contentType":"TEXT_TEST_CONTENT"\
                  }""";

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
  public static ShortTextTestQuestionContent getJsonMatchInstance() {
    return ShortTextTestQuestionContent.builder()
        .correctAnswer("Paris")
        .providedAnswer("Paris")
        .questionDescription(
            TextImageWrapper.builder()
                .text("What is the capital of France?")
                .image("paris_image_url.jpg")
                .build())
        .build();
  }
}