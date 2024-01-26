package ua.com.quizservice.entity.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.quizservice.converter.ContentConverter;
import ua.com.quizservice.enums.QuestionType;
import ua.com.quizservice.model.content.Content;

/**
 * Entity class representing a question in the quiz system.
 *
 * <p>The class includes details such as ID, description, type, duration, and content.
 */
@Entity
@Table(name = "questions")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String description;

  @Enumerated(EnumType.STRING)
  private QuestionType type;

  @Column(name = "duration")
  private Integer duration;

  @Convert(converter = ContentConverter.class)
  @Column(name = "content")
  private Content content;
}
