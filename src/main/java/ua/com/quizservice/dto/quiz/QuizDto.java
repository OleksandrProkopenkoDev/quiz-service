package ua.com.quizservice.dto.quiz;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing quiz information. Contains details such as the unique
 * identifier, title, description, passing score, and a list of associated quiz questions.
 *
 * @author Oleksandr Prokopenko
 * @version 1.2
 * @since 2024-01-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizDto {

  private UUID id;

  @Size(min = 2, message = "Title must have at least two symbols")
  @Pattern(regexp = "^[a-zA-Z].*", message = "Title must start with a letter")
  private String title;

  @Size(min = 10, message = "Description must have at least 10 symbols")
  private String description;

  @DecimalMin(value = "0.00", message = "Passing score must be greater than or equal to 0.00")
  @DecimalMax(value = "1.00", message = "Passing score must be less than or equal to 1.00")
  @Digits(integer = 1, fraction = 2, message = "Invalid format for the numeric field")
  private Float passingScore;

  private Integer totalQuestion;

  private Integer totalDuration;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
  private ZonedDateTime createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
  private ZonedDateTime updatedAt;

  private Boolean published = false;

  @JsonProperty("quizQuestions")
  private List<QuizQuestionDto> quizQuestionDtos = new ArrayList<>();
}
