package ua.com.quizservice.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.dto.quiz.QuizQuestionDto;
import ua.com.quizservice.service.QuizQuestionService;

/**
 * Controller class for handling quiz questions related operations.
 *
 * <p>This class defines RESTful endpoints for managing quiz questions within quizzes. It includes
 * methods for creating and deleting quiz questions associated with a specific quiz.
 *
 * @version 1.0.
 * @since 2024-01-21
 */
@RestController
@RequestMapping("/api/v1/quizzes")
@AllArgsConstructor
public class QuizQuestionController implements ua.com.quizservice.util.swagger.QuizQuestionOpenApi {

  private final QuizQuestionService quizQuestionService;

  @Override
  @PostMapping("/{quizId}/quiz-question")
  public ResponseEntity<QuizDto> createQuestionByQuizId(
      @PathVariable UUID quizId, @Valid @RequestBody QuizQuestionDto quizQuestionDto) {
    final QuizDto result = quizQuestionService.addQuizQuestionToQuiz(quizId, quizQuestionDto);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @Override
  @PostMapping("/{quizId}/quiz-question-new")
  public ResponseEntity<QuizDto> createNewQuestionByQuizId(
      @PathVariable UUID quizId, @Valid @RequestBody QuizQuestionDto quizQuestionDto) {
    final QuizDto result = quizQuestionService.createQuizQuestionToQuiz(quizId, quizQuestionDto);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping("/{quizId}/quiz-question/{quizQuestionId}")
  public ResponseEntity<QuizDto> deleteQuizQuestionFromQuiz(
      @PathVariable UUID quizId, @PathVariable UUID quizQuestionId) {
    return ResponseEntity.ok(
        quizQuestionService.deleteQuizQuestionFromQuiz(quizId, quizQuestionId));
  }
}
