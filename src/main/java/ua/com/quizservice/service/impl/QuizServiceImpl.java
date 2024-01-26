package ua.com.quizservice.service.impl;

import static ua.com.quizservice.util.validations.QuizValidation.throwIfQuizIsAlreadyPublished;
import static ua.com.quizservice.util.validations.QuizValidation.validateQuestions;
import static ua.com.quizservice.util.validations.QuizValidation.validateQuizQuestions;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.entity.quiz.Quiz;
import ua.com.quizservice.exception.QuizNotFoundException;
import ua.com.quizservice.repository.QuestionRepository;
import ua.com.quizservice.repository.QuizQuestionRepository;
import ua.com.quizservice.repository.QuizRepository;
import ua.com.quizservice.service.QuizService;
import ua.com.quizservice.util.mapper.QuizMapper;

/**
 * Implementation of the {@link ua.com.quizservice.service.QuizService} interface providing
 * operations for managing quizzes and associated questions.
 *
 * @author Oleksandr Prokopenko
 * @version 1.2
 * @since 2024-01-04
 */
@Slf4j
@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepository;
  private final QuizQuestionRepository quizQuestionRepository;
  private final QuestionRepository questionRepository;
  private final QuizMapper quizMapper;

  @Override
  public QuizDto createQuiz(QuizDto quizDto) {
    validateQuestions(quizDto, questionRepository);

    final Quiz quiz = quizMapper.toEntity(quizDto);
    // now there is no calculations for totalDuration and totalQuestions
    return quizMapper.toDto(quizRepository.save(quiz));
  }

  @Override
  public QuizDto findById(UUID id) {
    return quizMapper.toDto(findQuizById(id));
  }

  @Override
  public Page<QuizDto> findAll(Pageable pageable) {
    return quizRepository.findAll(pageable).map(quizMapper::toDto);
  }

  @Override
  @Transactional
  public QuizDto updateQuiz(UUID quizId, QuizDto quizDto) {

    final Quiz quiz = findQuizById(quizId);

    validateQuizQuestions(quizDto, quiz, quizQuestionRepository);

    quizMapper.updateEntityFromDto(quiz, quizDto);

    return quizMapper.toDto(quizRepository.save(quiz));
  }

  @Override
  public void deleteQuizById(UUID id) {
    quizRepository.deleteById(id);
  }

  @Override
  public QuizDto publish(UUID quizId) {
    final Quiz quizById = findQuizById(quizId);
    throwIfQuizIsAlreadyPublished(quizById);
    quizById.setPublished(true);

    return quizMapper.toDto(quizRepository.save(quizById));
  }

  private Quiz findQuizById(UUID quizId) {
    return quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(quizId));
  }
}
