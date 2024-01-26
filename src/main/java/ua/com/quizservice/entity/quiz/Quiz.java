package ua.com.quizservice.entity.quiz;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.quizservice.entity.AuditEntity;

/**
 * Entity class representing a quiz in the Quiz service.
 *
 * <p>This entity is mapped to the "quizzes" table in the database.
 *
 * <p>The quiz entity has a one-to-many relationship with {@link QuizQuestion}s, which represent the
 * association between quizzes and questions. The relationship is defined with {@link OneToMany} and
 * {@link JoinColumn} annotations.
 */
@Entity
@Table(name = "quizzes")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String title;

  private String description;

  @OneToMany(
      cascade = {CascadeType.ALL},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz_id")
  private List<QuizQuestion> quizQuestions = new ArrayList<>();

  @Column(name = "passing_score")
  private Float passingScore;

  @Column(name = "total_question")
  private Integer totalQuestion;

  @Column(name = "total_duration")
  private Integer totalDuration;

  @Embedded private AuditEntity auditEntity;

  private Boolean published = false;

  /**
   * Recalculates the derived fields 'totalQuestion' and 'totalDuration' based on the current state
   * of the 'quizQuestions' list. This method is annotated with {@link
   * jakarta.persistence.PrePersist} and {@link jakarta.persistence.PreUpdate}, ensuring it is
   * executed before entity persistence and updates.
   *
   * <p>The 'totalQuestion' field is updated with the total number of quiz questions in the
   * 'quizQuestions' list. The 'totalDuration' field is calculated by summing up the durations of
   * individual quiz questions.
   *
   * <p>Note: Ensure that the 'quizQuestions' list is not null before invoking this method.
   */
  @PrePersist
  @PreUpdate
  public void calculateDerivedFields() {
    // Calculate totalQuestion and totalDuration based on quizQuestions list
    if (quizQuestions != null) {
      totalQuestion = quizQuestions.size();
      totalDuration = calculateTotalDuration();
    }
  }

  private Integer calculateTotalDuration() {
    return quizQuestions.stream()
        .mapToInt(quizQuestion -> quizQuestion.getQuestion().getDuration())
        .sum();
  }
}
