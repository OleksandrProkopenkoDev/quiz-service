package ua.com.quizservice.contenttests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.Import;
import ua.com.datagenerator.content.question.ContentListData;
import ua.com.datagenerator.content.question.LongTextQuestionContentData;
import ua.com.datagenerator.content.question.MatchingTestQuestionContentData;
import ua.com.datagenerator.content.question.MultipleChoiceTestQuestionContentData;
import ua.com.datagenerator.content.question.ShortTextTestQuestionContentData;
import ua.com.datagenerator.content.question.SingleChoiceTestQuestionContentData;
import ua.com.datagenerator.content.question.VoiceMediaQuestionContentData;
import ua.com.quizservice.config.JacksonConfiguration;
import ua.com.quizservice.model.content.Content;
import ua.com.quizservice.model.content.TextImageWrapper;
import ua.com.quizservice.model.content.questioncontent.LongTextQuestionContent;
import ua.com.quizservice.model.content.questioncontent.MatchingTestQuestionContent;
import ua.com.quizservice.model.content.questioncontent.MultipleChoiceTestQuestionContent;
import ua.com.quizservice.model.content.questioncontent.ShortTextTestQuestionContent;
import ua.com.quizservice.model.content.questioncontent.SingleChoiceTestQuestionContent;
import ua.com.quizservice.model.content.questioncontent.VoiceMediaQuestionContent;

/**
 * Test cases to check correct serialization {@link TextImageWrapper} when method {@code
 * excludeTextFromTextImageWrapper()} of {@link Content} class was used.
 *
 * @author Zakhar Kuropiatnyk
 */
@JsonTest
@Import({JacksonConfiguration.class, ObjectMapper.class})
class TextImageWraperNullTextTest {

  public static final String EQUALS_TO_EXPECTED_STRING =
      "Returned json must be equals to: expected string";

  @Autowired private ObjectMapper objectMapper;

  @Test
  void readValue_shouldReturnShortTestQuestionContentInstance_providedJsonStringWithTextNull()
      throws IOException {
    final String json = ShortTextTestQuestionContentData.getJsonTextNull();

    final Content content = objectMapper.readValue(json, Content.class);

    assertEquals(
        ShortTextTestQuestionContent.class,
        content.getClass(),
        "Returned instance must be instance of ShortTextTestQuestionContent");
  }

  @Test
  void writeValueAsString_shouldReturnCorrectJsonString_providedShortTextTestContentInstance()
      throws JsonProcessingException {
    final String expectedJson = ShortTextTestQuestionContentData.getJsonTextNull();

    final Content content = ShortTextTestQuestionContentData.getJsonMatchInstance();

    content.excludeTextFromTextImageWrapper();

    final String actualJson = objectMapper.writeValueAsString(content);
    assertEquals(expectedJson, actualJson, EQUALS_TO_EXPECTED_STRING);
  }

  @Test
  void readValue_shouldReturnLongTestQuestionContentInstance_providedJsonStringWithTextNull()
      throws IOException {

    final String json = LongTextQuestionContentData.getJsonTextNull();

    final Content content = objectMapper.readValue(json, Content.class);

    assertEquals(
        LongTextQuestionContent.class,
        content.getClass(),
        "Returned instance must be instance of LongTextTestQuestionContent");
  }

  @Test
  void writeValueAsString_shouldReturnCorrectJsonString_providedLongTextTestContentInstance()
      throws JsonProcessingException {
    final String expectedJson = LongTextQuestionContentData.getJsonTextNull();

    final Content content = LongTextQuestionContentData.getJsonMatchInstance();
    content.excludeTextFromTextImageWrapper();

    final String actualJson = objectMapper.writeValueAsString(content);
    assertEquals(expectedJson, actualJson, EQUALS_TO_EXPECTED_STRING);
  }

  @Test
  void readValue_shouldReturnMatchingTestQuestionContentInstance_providedJsonStringWithTextNull()
      throws IOException {

    final String json = MatchingTestQuestionContentData.getJsonTextNull();

    final Content content = objectMapper.readValue(json, Content.class);

    assertEquals(
        MatchingTestQuestionContent.class,
        content.getClass(),
        "Returned instance must be instance of MatchingTestQuestionContent");
  }

  @Test
  void writeValueAsString_shouldReturnCorrectJsonString_providedJsonStringWithTextNull()
      throws JsonProcessingException {
    final String expectedJson = MatchingTestQuestionContentData.getJsonTextNull();

    final Content content = MatchingTestQuestionContentData.getJsonMatchInstance();
    content.excludeTextFromTextImageWrapper();
    final String actualJson = objectMapper.writeValueAsString(content);
    assertEquals(expectedJson, actualJson, EQUALS_TO_EXPECTED_STRING);
  }

  @Test
  void
      readValue_shouldReturnMultipleChoiceTestQuestionContentInstance_providedJsonStringWithTextNull()
          throws IOException {

    final String json = MultipleChoiceTestQuestionContentData.getJsonTextNull();

    final Content content = objectMapper.readValue(json, Content.class);

    assertEquals(
        MultipleChoiceTestQuestionContent.class,
        content.getClass(),
        "Returned instance must be instance of MultipleChoiceTestQuestionContent");
  }

  @Test
  void writeValueAsString_shouldReturnCorrectJsonString_providedMultipleChoiceTestQuestionInstance()
      throws JsonProcessingException {
    final String expectedJson = MultipleChoiceTestQuestionContentData.getJsonTextNull();

    final Content content = MultipleChoiceTestQuestionContentData.getJsonMatchInstance();
    content.excludeTextFromTextImageWrapper();

    final String actualJson = objectMapper.writeValueAsString(content);
    assertEquals(expectedJson, actualJson, EQUALS_TO_EXPECTED_STRING);
  }

  @Test
  void
      readValue_shouldReturnSingleChoiceTestQuestionContentInstance_providedJsonStringWithTextNull()
          throws IOException {

    final String json = SingleChoiceTestQuestionContentData.getJsonTextNull();

    final Content content = objectMapper.readValue(json, Content.class);

    assertEquals(
        SingleChoiceTestQuestionContent.class,
        content.getClass(),
        "Returned instance must be instance of SingleChoiceTestQuestionContent");
  }

  @Test
  void writeValueAsString_shouldReturnCorrectJsonString_providedSingleChoiceTestInstance()
      throws JsonProcessingException {
    final String expectedJson = SingleChoiceTestQuestionContentData.getJsonTextNull();

    final Content content = SingleChoiceTestQuestionContentData.getJsonMatchInstance();
    content.excludeTextFromTextImageWrapper();

    final String actualJson = objectMapper.writeValueAsString(content);
    assertEquals(expectedJson, actualJson, EQUALS_TO_EXPECTED_STRING);
  }

  @Test
  void readValue_shouldReturnVoiceMediaQuestionContentInstance_providedJsonStringWithTextNull()
      throws IOException {

    final String json = VoiceMediaQuestionContentData.getJsonTextNull();

    final Content content = objectMapper.readValue(json, Content.class);

    assertEquals(
        VoiceMediaQuestionContent.class,
        content.getClass(),
        "Returned instance must be instance of VoiceMediaQuestionContent");
  }

  @Test
  void writeValueAsString_shouldReturnCorrectJsonString_providedVoiceMediaQuestionContentInstance()
      throws JsonProcessingException {
    final String expectedJson = VoiceMediaQuestionContentData.getJsonTextNull();

    final Content content = VoiceMediaQuestionContentData.getJsonMatchInstance();
    content.excludeTextFromTextImageWrapper();
    final String actualJson = objectMapper.writeValueAsString(content);
    assertEquals(expectedJson, actualJson, EQUALS_TO_EXPECTED_STRING);
  }

  @Test
  void writeValueAsString_shouldReturnCorrectJsonString_providedListOfContent()
      throws JsonProcessingException {
    final String expectedJson = ContentListData.getJsonForListOfContentsWithTextNull();

    final List<Content> contents = ContentListData.getListOfContentIntsnces();

    contents.forEach(Content::excludeTextFromTextImageWrapper);

    final String actualJson = objectMapper.writeValueAsString(contents);
    assertEquals(expectedJson, actualJson, EQUALS_TO_EXPECTED_STRING);
  }
}
