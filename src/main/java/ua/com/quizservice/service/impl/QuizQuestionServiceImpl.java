package ua.com.quizservice.service.impl;

import static ua.com.quizservice.util.validations.QuizValidation.throwIfQuestionIsNotUnique;
import static ua.com.quizservice.util.validations.QuizValidation.throwIfQuizIsAlreadyPublished;
import static ua.com.quizservice.util.validations.QuizValidation.throwIfSequenceIsInvalid;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.quizservice.dto.question.QuestionDto;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.dto.quiz.QuizQuestionDto;
import ua.com.quizservice.entity.quiz.Question;
import ua.com.quizservice.entity.quiz.Quiz;
import ua.com.quizservice.entity.quiz.QuizQuestion;
import ua.com.quizservice.exception.QuizNotFoundException;
import ua.com.quizservice.exception.QuizQuestionNotFoundException;
import ua.com.quizservice.repository.QuestionRepository;
import ua.com.quizservice.repository.QuizQuestionRepository;
import ua.com.quizservice.repository.QuizRepository;
import ua.com.quizservice.service.QuestionService;
import ua.com.quizservice.service.QuizQuestionService;
import ua.com.quizservice.util.mapper.QuizMapper;
import ua.com.quizservice.util.mapper.QuizQuestionMapper;

/** Service implementation for managing QuizQuestions within a Quiz. */
@Slf4j
@Service
@AllArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {

  private final QuizRepository quizRepository;
  private final QuestionRepository questionRepository;
  private final QuizQuestionRepository quizQuestionRepository;
  private final QuizMapper quizMapper;
  private final QuizQuestionMapper quizQuestionMapper;
  // use it for creating new questions!!!! have auto add images in save method
  private final QuestionService questionService;

  @Override
  @Transactional
  public QuizDto addQuizQuestionToQuiz(UUID quizId, QuizQuestionDto quizQuestionDto) {
    final Quiz quiz = findQuizById(quizId);

    throwIfQuizIsAlreadyPublished(quiz);

    // check if there are no questions saved in db with new Question id (questions for Quiz should
    // be unique)
    throwIfQuestionIsNotUnique(quiz, quizQuestionDto);

    final QuizQuestion quizQuestion = quizQuestionMapper.toEntity(quizQuestionDto);

    quiz.getQuizQuestions().add(quizQuestion);

    // this is needed to recalculate totalDuration and totalQuestions
    fetchQuestionsFromDbTo(quiz);
    quiz.calculateDerivedFields();

    final List<QuizQuestionDto> quizQuestionDtos =
        quiz.getQuizQuestions().stream().map(quizQuestionMapper::toDto).toList();

    throwIfSequenceIsInvalid(quizQuestionDtos);

    return quizMapper.toDto(quizRepository.save(quiz));
  }

  @Override
  @Transactional
  public QuizDto deleteQuizQuestionFromQuiz(UUID quizId, UUID quizQuestionId) {
    final Quiz quiz = findQuizById(quizId);

    throwIfQuizIsAlreadyPublished(quiz);

    final QuizQuestion quizQuestion = findQuizQuestionById(quizQuestionId);

    quiz.getQuizQuestions().remove(quizQuestion);

    quizQuestionRepository.delete(quizQuestion);

    // this is needed to recalculate totalDuration and totalQuestions
    fetchQuestionsFromDbTo(quiz);
    quiz.calculateDerivedFields();

    return quizMapper.toDto(quizRepository.save(quiz));
  }

  private Quiz findQuizById(UUID quizId) {
    return quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(quizId));
  }

  private QuizQuestion findQuizQuestionById(UUID quizQuestionId) {
    return quizQuestionRepository
        .findById(quizQuestionId)
        .orElseThrow(() -> new QuizQuestionNotFoundException(quizQuestionId));
  }

  private void fetchQuestionsFromDbTo(Quiz quiz) {
    // adding question`s data to ability calculate totalDuration
    final List<UUID> questionsIdList =
        quiz.getQuizQuestions().stream()
            .map(quizQuestion -> quizQuestion.getQuestion().getId())
            .toList();
    final List<Question> questions = questionRepository.findAllById(questionsIdList);
    for (int i = 0; i < questions.size(); i++) {
      quiz.getQuizQuestions().get(i).setQuestion(questions.get(i));
    }
  }

  @Override
  public QuizDto createQuizQuestionToQuiz(
      final UUID quizId, final QuizQuestionDto quizQuestionDto) {

    final QuestionDto newQuestion = questionService.save(quizQuestionDto.getQuestionDto());

    quizQuestionDto.setQuestionDto(newQuestion);
    return addQuizQuestionToQuiz(quizId, quizQuestionDto);
  }
}
