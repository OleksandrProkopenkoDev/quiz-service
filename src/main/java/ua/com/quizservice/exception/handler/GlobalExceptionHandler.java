package ua.com.quizservice.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.quizservice.exception.AnswerInQuizPassingNotFoundException;
import ua.com.quizservice.exception.AnswerNotFoundException;
import ua.com.quizservice.exception.QuestionNotFoundException;
import ua.com.quizservice.exception.QuestionWithoutIdException;
import ua.com.quizservice.exception.QuizAlreadyContainQuestionException;
import ua.com.quizservice.exception.QuizAlreadyPublishedException;
import ua.com.quizservice.exception.QuizNotFoundException;
import ua.com.quizservice.exception.QuizNotPublishedException;
import ua.com.quizservice.exception.QuizPassingNotFoundException;
import ua.com.quizservice.exception.QuizQuestionIdCanNotBeNullException;
import ua.com.quizservice.exception.QuizQuestionNotFoundException;
import ua.com.quizservice.exception.QuizQuestionSequenceDuplicateException;
import ua.com.quizservice.exception.QuizQuestionsListSizeException;
import ua.com.quizservice.exception.UnableToGetDataFromImageException;
import ua.com.quizservice.exception.UnableToSendVerificationException;
import ua.com.quizservice.exception.UserAlreadyPassingQuizException;
import ua.com.quizservice.exception.UserNotFoundException;
import ua.com.quizservice.exception.VacancyNotFoundException;
import ua.com.quizservice.exception.model.ErrorResponse;

/**
 * Global exception handler for handling specific exceptions and providing consistent error
 * responses.
 */
@ControllerAdvice
@SuppressWarnings({"PMD.ExcessiveImports", "PMD.CouplingBetweenObjects"})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(VacancyNotFoundException.class)
  public ResponseEntity<String> handleVacancyNotFoundException(VacancyNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(UnableToSendVerificationException.class)
  public ResponseEntity<String> handleUnableToSendVerificationException(
      UnableToSendVerificationException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

  @ExceptionHandler(UnableToGetDataFromImageException.class)
  public ResponseEntity<String> handleUnableToGetDataFromImageException(
      UnableToGetDataFromImageException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

  @ExceptionHandler({
    QuestionNotFoundException.class,
    AnswerNotFoundException.class,
    QuizNotFoundException.class,
    QuizPassingNotFoundException.class,
    QuizQuestionNotFoundException.class,
    AnswerInQuizPassingNotFoundException.class
  })
  public ResponseEntity<Object> handleNotFoundException(
      final RuntimeException ex, final WebRequest request) {

    final HttpStatus status = HttpStatus.NOT_FOUND;

    final ErrorResponse errorResponse = buildErrorResponse(ex, (ServletWebRequest) request, status);

    return ResponseEntity.status(status).body(errorResponse);
  }

  @ExceptionHandler({
    QuizAlreadyPublishedException.class,
    QuizQuestionSequenceDuplicateException.class,
    QuizAlreadyContainQuestionException.class,
    QuizQuestionsListSizeException.class,
    QuestionWithoutIdException.class,
    QuizQuestionIdCanNotBeNullException.class,
    UserAlreadyPassingQuizException.class,
    QuizNotPublishedException.class
  })
  public ResponseEntity<Object> handleBadRequestException(
      final RuntimeException ex, final WebRequest request) {

    final HttpStatus status = HttpStatus.BAD_REQUEST;

    final ErrorResponse errorResponse = buildErrorResponse(ex, (ServletWebRequest) request, status);

    return ResponseEntity.status(status).body(errorResponse);
  }

  private ErrorResponse buildErrorResponse(
      final Exception ex, final ServletWebRequest request, final HttpStatus status) {
    return new ErrorResponse(status.value(), ex.getMessage(), request.getRequest().getRequestURI());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    final Map<String, Object> body = new ConcurrentHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.BAD_REQUEST);
    final List<String> errors =
        ex.getBindingResult().getAllErrors().stream().map(this::getErrorMessage).toList();
    body.put("errors", errors);
    return new ResponseEntity<>(body, headers, status);
  }

  private String getErrorMessage(ObjectError e) {
    if (e instanceof FieldError) {
      final String field = ((FieldError) e).getField();
      final String message = e.getDefaultMessage();
      return field + " " + message;
    }
    return e.getDefaultMessage();
  }
}
