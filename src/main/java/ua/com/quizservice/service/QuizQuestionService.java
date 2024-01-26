package ua.com.quizservice.service;

import java.util.UUID;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.dto.quiz.QuizQuestionDto;

/** Service interface for managing QuizQuestions within a Quiz. */
public interface QuizQuestionService {

  QuizDto addQuizQuestionToQuiz(UUID quizId, QuizQuestionDto quizQuestionDto);

  QuizDto deleteQuizQuestionFromQuiz(UUID quizId, UUID quizQuestionId);

  QuizDto createQuizQuestionToQuiz(UUID quizId, QuizQuestionDto quizQuestionDto);
}
