package ua.com.quizservice.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.service.QuizQuestionService;
import ua.com.quizservice.service.QuizService;
import ua.com.quizservice.util.swagger.QuizOpenApi;

/**
 * RESTful controller for managing quiz-related operations through API endpoints. Handles requests
 * related to quizzes and their associated questions.
 *
 * @author Oleksandr Prokopenko
 * @version 1.2
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/api/v1/quizzes")
@AllArgsConstructor
public class QuizController implements QuizOpenApi {

  private final QuizService quizService;
  private final QuizQuestionService quizQuestionService;

  /**
   * Creates a new quiz based on the provided {@link ua.com.quizservice.dto.quiz.QuizDto}.
   *
   * @param quizDto DTO containing information to create the quiz.
   * @return ResponseEntity containing the DTO representation of the created quiz.
   */
  @Override
  @PostMapping
  public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody QuizDto quizDto) {
    final QuizDto result = quizService.createQuiz(quizDto);

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  /**
   * Retrieves a specific quiz by its unique identifier.
   *
   * @param quizId The unique identifier of the quiz.
   * @return ResponseEntity containing the DTO representation of the quiz.
   */
  @Override
  @GetMapping("/{quizId}")
  public ResponseEntity<QuizDto> findById(@PathVariable UUID quizId) {
    final QuizDto result = quizService.findById(quizId);

    return ResponseEntity.ok(result);
  }

  /**
   * Retrieves a paginated list of quizzes.
   *
   * @param pageable Pagination information.
   * @return ResponseEntity containing a paginated list of quiz DTOs.
   */
  @Override
  @GetMapping
  public ResponseEntity<Page<QuizDto>> findAll(@PageableDefault Pageable pageable) {
    final Page<QuizDto> result = quizService.findAll(pageable);

    return ResponseEntity.ok(result);
  }

  /**
   * Updates an existing quiz based on the provided {@link ua.com.quizservice.dto.quiz.QuizDto}.
   *
   * @param quizId The unique identifier of the quiz to be updated.
   * @param quizDto DTO containing updated information for the quiz.
   * @return ResponseEntity containing the DTO representation of the updated quiz.
   */
  @Override
  @PutMapping("/{quizId}")
  public ResponseEntity<QuizDto> updateQuiz(
      @PathVariable UUID quizId, @Valid @RequestBody QuizDto quizDto) {
    final QuizDto result = quizService.updateQuiz(quizId, quizDto);

    return ResponseEntity.ok(result);
  }

  @Override
  @DeleteMapping("/{quizId}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID quizId) {
    quizService.deleteQuizById(quizId);
    return ResponseEntity.noContent().build();
  }

  @Override
  @PostMapping("/{quizId}/publish")
  public ResponseEntity<QuizDto> publishQuiz(@PathVariable UUID quizId) {

    return ResponseEntity.ok(quizService.publish(quizId));
  }
}
