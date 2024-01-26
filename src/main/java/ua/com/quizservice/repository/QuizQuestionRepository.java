package ua.com.quizservice.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.quizservice.entity.quiz.QuizQuestion;

/** Repository interface for accessing and managing QuizQuestion entities. */
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, UUID> {}
