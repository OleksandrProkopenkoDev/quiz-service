package ua.com.quizservice.entity.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Entity class representing the association between quizzes and questions in the Quiz service. */
@Entity
@Table(name = "quizzes_questions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuizQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "question_id", referencedColumnName = "id")
  private Question question;

  private Integer weight;

  private Integer sequence;
}
