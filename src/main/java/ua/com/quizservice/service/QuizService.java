package ua.com.quizservice.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.quizservice.dto.quiz.QuizDto;

/**
 * Service interface for managing quizzes and associated questions. Defines methods for creating,
 * retrieving, updating, and deleting quizzes, as well as operations related to quiz questions.
 *
 * @author Oleksandr Prokopenko
 * @version 1.2
 * @since 2024-01-04
 */
public interface QuizService {

  QuizDto createQuiz(QuizDto quizDto);

  QuizDto findById(UUID id);

  Page<QuizDto> findAll(Pageable pageable);

  QuizDto updateQuiz(UUID quizId, QuizDto quizDto);

  void deleteQuizById(UUID id);

  QuizDto publish(UUID quizId);
}
