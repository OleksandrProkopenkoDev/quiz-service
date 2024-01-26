package ua.com.quizservice.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import ua.com.quizservice.exception.content.TextImageWrapperSerializeException;
import ua.com.quizservice.model.content.TextImageWrapper;

/**
 * Serializer allow us to exclude field "text" from {@link TextImageWrapper} during serialization
 *
 * @author Zakhar Kuropiatnyk
 */
public class TextImageWrapperSerializer extends JsonSerializer<TextImageWrapper> {

  @Override
  public void serialize(
      final TextImageWrapper value,
      final JsonGenerator jsonGenerator,
      final SerializerProvider serializers) {

    try {
      jsonGenerator.writeStartObject();
      if (value.isNeedText()) {
        jsonGenerator.writeStringField("text", value.getText());
      } else {
        jsonGenerator.writeStringField("text", null);
      }
      jsonGenerator.writeStringField("image", value.getImage());

      jsonGenerator.writeEndObject();
    } catch (final IOException exc) {
      throw new TextImageWrapperSerializeException(exc);
    }
  }
}
