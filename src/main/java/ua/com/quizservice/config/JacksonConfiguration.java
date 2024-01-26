package ua.com.quizservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Objects;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.com.quizservice.model.content.ContentType;

/**
 * Configuration class for customizing Jackson to enable the usage of custom class annotations, such
 * as {@link ContentType}. This class registers subtypes based on the annotations found in the
 * specified package.
 *
 * @apiNote This configuration allows Jackson to recognize and handle custom content types specified
 *     by the {@link ContentType} annotation. The subtypes are determined by scanning classes within
 *     the package defined by {@link JacksonConfiguration#UA_COM_QUIZSERVICE_MODEL_CONTENT}.
 * @author Zakhar Kuropiatnyk
 * @since 06/01/2024
 */
@Configuration
public class JacksonConfiguration {

  /** The base package for scanning classes annotated with {@link ContentType}. */
  private static final String UA_COM_QUIZSERVICE_MODEL_CONTENT = "ua.com.quizservice.model.content";

  /**
   * Provides a customized ObjectMapper bean with registered subtypes based on annotations.
   *
   * @return The customized ObjectMapper bean.
   */
  @Bean
  public ObjectMapper objectMapper() {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    registerContentSubtypesByAnnotations(objectMapper);
    return objectMapper;
  }

  /**
   * Registers Jackson subtypes based on classes annotated with {@link ContentType}.
   *
   * @param objectMapper The ObjectMapper to configure with subtypes.
   */
  private void registerContentSubtypesByAnnotations(ObjectMapper objectMapper) {
    final Reflections reflections = new Reflections(UA_COM_QUIZSERVICE_MODEL_CONTENT);
    final Set<Class<?>> subtypes = reflections.getTypesAnnotatedWith(ContentType.class);

    subtypes.stream()
        .map(
            subType -> {
              final ContentType annotation = subType.getAnnotation(ContentType.class);
              if (annotation != null) {
                final String typeName = annotation.value();
                return new NamedType(subType, typeName);
              }
              return null; // Filter out classes without @ContentType annotation
            })
        .filter(Objects::nonNull)
        .forEach(objectMapper::registerSubtypes);
  }
}
