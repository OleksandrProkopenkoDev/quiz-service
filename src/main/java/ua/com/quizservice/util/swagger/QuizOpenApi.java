package ua.com.quizservice.util.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.exception.model.ErrorResponse;

/** OpenAPI specification for Quiz operations. */
@Tag(name = "Quizzes", description = "API for operations with quizzes")
public interface QuizOpenApi {

  @Operation(summary = "Create quiz", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Quiz was created successfully",
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
  @PostMapping
  ResponseEntity<QuizDto> createQuiz(@RequestBody QuizDto quizDto);

  @Operation(summary = "Find quiz by id", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Quiz was found successfully",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = QuizDto.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(
            responseCode = "404",
            description = "Quiz not found",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  @GetMapping("/{quizId}")
  ResponseEntity<QuizDto> findById(@PathVariable UUID quizId);

  @Operation(
      summary = "Finds all quizzes. Sorting by quiz fields available. Pagination available",
      security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Quizzes were found successfully",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = QuizDto.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
      })
  @Parameter(
      name = "pageable",
      description = "Pageable information(page, size, sort)",
      example =
          """
      {
        "page": 0,
        "size": 10,
        "sort": [
          "title"
        ]
      }""")
  @GetMapping
  ResponseEntity<Page<QuizDto>> findAll(@PageableDefault Pageable pageable);

  @Operation(summary = "Update quiz by id", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Quiz was updated successfully",
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
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Quiz not found",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  @PutMapping("/{quizId}")
  ResponseEntity<QuizDto> updateQuiz(@PathVariable UUID quizId, @RequestBody QuizDto quizDto);

  @Operation(summary = "Delete quiz by id", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Quiz was deleted successfully"),
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
  @DeleteMapping("/{quizId}")
  ResponseEntity<Void> deleteById(@PathVariable UUID quizId);

  @Operation(
      summary = "Set Quiz.published to 'true'",
      security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Quiz was published successfully",
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
  @PostMapping("/{quizId}/publish")
  ResponseEntity<QuizDto> publishQuiz(@PathVariable UUID quizId);
}
