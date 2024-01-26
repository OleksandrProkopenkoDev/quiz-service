package ua.com.quizservice.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.quizservice.entity.quiz.Quiz;

/**
 * JPA repository for handling operations related to {@link Quiz} entities. Extends {@link
 * JpaRepository}. The repository is designed to work with entities of type {@link Quiz} and uses
 * {@link UUID} as the primary key type.
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {}
