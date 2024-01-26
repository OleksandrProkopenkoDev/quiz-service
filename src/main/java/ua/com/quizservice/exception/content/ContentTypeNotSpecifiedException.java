package ua.com.quizservice.exception.content;

import ua.com.quizservice.model.content.ContentType;

/**
 * Exception thrown when the content type is not specified for a class annotated with {@link
 * ContentType}.
 *
 * <p>This exception extends {@link IllegalStateException} and is designed to be thrown when an
 * attempt is made to retrieve the content type from a class, and the class does not have the
 * required {@link ContentType} annotation.
 *
 * @implNote This exception includes the class name in the error message to indicate which class
 *     lacks the content type specification.
 * @see ContentType
 * @see IllegalStateException
 * @author Zakhar Kuropiatnyk
 */
public class ContentTypeNotSpecifiedException extends IllegalStateException {
  private static final String EXCEPTION_MESSAGE = "Content type is not specified for class: ";

  /**
   * Constructs a new {@code ContentTypeNotSpecifiedException} with the class name.
   *
   * @param clazz The class for which the content type is not specified.
   */
  public ContentTypeNotSpecifiedException(Class<?> clazz) {
    super(EXCEPTION_MESSAGE + clazz.getName());
  }
}
