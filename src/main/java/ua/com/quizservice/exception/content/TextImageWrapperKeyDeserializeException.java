package ua.com.quizservice.exception.content;

import ua.com.quizservice.model.content.TextImageWrapper;

/**
 * Exception thrown when an error occurs during the deserialization of a key into a {@link
 * TextImageWrapper} instance.
 *
 * <p>This exception extends {@link RuntimeException} and is designed to be thrown when an
 * unexpected error occurs during the deserialization of a key, typically used in a map, into a
 * {@link TextImageWrapper} object.
 *
 * @implNote This exception includes a generic error message and wraps the original exception that
 *     caused the issue.
 * @see TextImageWrapper
 * @see RuntimeException
 * @author [Your Name]
 */
public class TextImageWrapperKeyDeserializeException extends RuntimeException {

  private static final String ERROR_MESSAGE =
      "Something went wrong during deserialization TextImageWrapper like key of Map: ";

  public TextImageWrapperKeyDeserializeException(Exception e) {
    super(ERROR_MESSAGE, e);
  }
}
