package ua.com.quizservice.util.validations;

import java.util.List;
import java.util.UUID;
import ua.com.quizservice.dto.quiz.QuizDto;
import ua.com.quizservice.dto.quiz.QuizQuestionDto;
import ua.com.quizservice.entity.quiz.Quiz;
import ua.com.quizservice.entity.quiz.QuizQuestion;
import ua.com.quizservice.exception.QuestionNotFoundException;
import ua.com.quizservice.exception.QuestionWithoutIdException;
import ua.com.quizservice.exception.QuizAlreadyContainQuestionException;
import ua.com.quizservice.exception.QuizAlreadyPublishedException;
import ua.com.quizservice.exception.QuizQuestionIdCanNotBeNullException;
import ua.com.quizservice.exception.QuizQuestionNotFoundException;
import ua.com.quizservice.exception.QuizQuestionSequenceDuplicateException;
import ua.com.quizservice.exception.QuizQuestionsListSizeException;
import ua.com.quizservice.repository.QuestionRepository;
import ua.com.quizservice.repository.QuizQuestionRepository;

/** Utility class for validating Quiz and QuizQuestion entities. */
public final class QuizValidation {

  private QuizValidation() {
    throw new UnsupportedOperationException("Utility QuizValidation class cannot be instantiated");
  }

  public static void validateQuestions(QuizDto quizDto, QuestionRepository questionRepository) {
    final List<QuizQuestionDto> quizQuestionDtos = quizDto.getQuizQuestionDtos();

    throwIfQuestionIdIsNull(quizQuestionDtos);

    throwIfQuestionNotFound(quizQuestionDtos, questionRepository);

    throwIfSequenceIsInvalid(quizQuestionDtos);
  }

  public static void validateQuizQuestions(
      QuizDto quizDto, Quiz quiz, QuizQuestionRepository quizQuestionRepository) {
    final List<QuizQuestionDto> quizQuestionDtos = quizDto.getQuizQuestionDtos();

    throwIfQuizQuestionIdIsNull(quizQuestionDtos);

    throwIfQuizQuestionNotFound(quizQuestionDtos, quizQuestionRepository);

    throwIfQuizQuestionListSizeNotValid(quiz, quizQuestionDtos);

    throwIfSequenceIsInvalid(quizQuestionDtos);
  }

  public static void throwIfQuestionIsNotUnique(Quiz quiz, QuizQuestionDto quizQuestionDto) {
    final UUID id = quizQuestionDto.getQuestionDto().getId();
    final boolean questionAlreadyExists =
        quiz.getQuizQuestions().stream()
            .anyMatch(quizQuestion -> quizQuestion.getQuestion().getId().equals(id));
    if (questionAlreadyExists) {
      throw new QuizAlreadyContainQuestionException(id);
    }
  }

  public static void throwIfSequenceIsInvalid(List<QuizQuestionDto> quizQuestionDtos) {
    final List<Integer> sequenceList =
        quizQuestionDtos.stream().map(QuizQuestionDto::getSequence).toList();
    if (sequenceList.size() != sequenceList.stream().distinct().count()) {
      throw new QuizQuestionSequenceDuplicateException(sequenceList);
    }
  }

  public static void throwIfQuestionNotFound(
      List<QuizQuestionDto> quizQuestionDtos, QuestionRepository questionRepository) {
    quizQuestionDtos.forEach(
        quizQuestionDto -> {
          final UUID id = quizQuestionDto.getQuestionDto().getId();
          if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException(id);
          }
        });
  }

  public static void throwIfQuestionIdIsNull(List<QuizQuestionDto> quizQuestionDtos) {
    final boolean questionIdNotPresent =
        quizQuestionDtos.stream()
            .anyMatch(quizQuestionDto -> quizQuestionDto.getQuestionDto().getId() == null);
    if (questionIdNotPresent) {
      throw new QuestionWithoutIdException();
    }
  }

  public static void throwIfQuizQuestionListSizeNotValid(
      Quiz quiz, List<QuizQuestionDto> quizQuestionDtos) {
    // check if number of QuizQuestions in DB equals number of QuizQuestions in passed dto
    final List<QuizQuestion> quizQuestions = quiz.getQuizQuestions();
    final int entityListSize = quizQuestions.size();
    final int dtoListSize = quizQuestionDtos.size();
    if (entityListSize != dtoListSize) {
      throw new QuizQuestionsListSizeException(entityListSize, dtoListSize);
    }
  }

  public static void throwIfQuizQuestionNotFound(
      List<QuizQuestionDto> quizQuestionDtos, QuizQuestionRepository quizQuestionRepository) {
    quizQuestionDtos.forEach(
        quizQuestionDto -> {
          final UUID id = quizQuestionDto.getId();
          if (!quizQuestionRepository.existsById(id)) {
            throw new QuizQuestionNotFoundException(id);
          }
        });
  }

  public static void throwIfQuizQuestionIdIsNull(List<QuizQuestionDto> quizQuestionDtos) {
    final boolean idIsNull =
        quizQuestionDtos.stream().anyMatch(quizQuestionDto -> quizQuestionDto.getId() == null);
    if (idIsNull) {
      throw new QuizQuestionIdCanNotBeNullException();
    }
  }

  public static void throwIfQuizIsAlreadyPublished(Quiz quizById) {
    if (quizById.getPublished()) {
      throw new QuizAlreadyPublishedException(quizById.getId());
    }
  }
}
