package ua.com.quizservice.util.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.dto.quiz.QuizQuestionDto;
import ua.com.quizservice.exception.model.ErrorResponse;

public interface QuizQuestionOpenApi {

  @Operation(
      summary = "Add new QuizQuestion to quiz",
      security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "QuizQuestion was added successfully",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = QuizDto.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  @PostMapping("/{quizId}/quiz-question")
  ResponseEntity<QuizDto> createQuestionByQuizId(
      @PathVariable UUID quizId, @RequestBody QuizQuestionDto quizQuestionDto);

  @PostMapping("/{quizId}/quiz-question-new")
  ResponseEntity<QuizDto> createNewQuestionByQuizId(
      @PathVariable UUID quizId, @Valid @RequestBody QuizQuestionDto quizQuestionDto);

  @Operation(
      summary = "Delete quizQuestion from quiz",
      security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "QuizQuestion was deleted successfully"),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Quiz not found",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  @DeleteMapping("/{quizId}/quiz-question/{quizQuestionId}")
  ResponseEntity<QuizDto> deleteQuizQuestionFromQuiz(
      @PathVariable UUID quizId, @PathVariable UUID quizQuestionId);
}
