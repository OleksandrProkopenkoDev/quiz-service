package ua.com.quizservice.dto.quiz;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.quizservice.enums.QuizPassingStatus;

/** Replace this stub by correct Javadoc. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizPassingDto {

  private UUID id;

  @Schema(description = "Enum quiz passing status", example = "IN_PROGRES")
  private QuizPassingStatus status;

  @Schema(description = "Assessment of quiz passing", example = "0.7")
  private Float score;

  @Schema(description = "From this quiz is made quiz passing")
  private QuizDto quiz;

  @Schema(description = "List of answers that users should give")
  private List<AnswerDto> answers;
}
