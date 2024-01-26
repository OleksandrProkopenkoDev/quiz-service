INSERT INTO questions (id, type, description, duration, content)
VALUES ('baa8bb7d-9533-4483-bdb4-204efba55e7a', 'SINGLE_TEST', 'description', 50, '{
  "questionDescription": {
    "text": "What is the capital of France?",
    "image": "paris_image_url.jpg"
  },
  "answerOptions": [
    {
      "text": "Answer1",
      "image": "answer_image1_url.jpg"
    },
    {
      "text": "Answer2",
      "image": "answer_image1_url.jpg"
    }
  ],
  "correctAnswer": "A",
  "providedAnswer": "A",
  "contentType": "SINGLE_TEST_CONTENT"
}');

INSERT INTO questions (id, type, description, duration, content)
VALUES ('2bc5530e-0eb3-4ed7-8f74-cbc4cbf7b5bb', 'MULTIPLE_TEST', 'description', 60, '{
  "questionDescription": {
    "text": "What is the capital of France?",
    "image": "paris_image_url.jpg"
  },
  "answerOptions": [
    {
      "text": "Answer1",
      "image": "answer_image1_url.jpg"
    },
    {
      "text": "Answer2",
      "image": "answer_image1_url.jpg"
    }
  ],
  "correctAnswer": [
    "A",
    "B"
  ],
  "providedAnswer": [
    "A",
    "B"
  ],
  "contentType": "MULTIPLE_TEST_CONTENT"
}');

INSERT INTO questions (id, type, description, duration, content)
VALUES ('570f93b5-485c-42ea-82f9-8c866ac0e4c4', 'SINGLE_TEST', 'description', 40, '{
  "questionDescription": {
    "text": "What is the capital of France?",
    "image": "paris_image_url.jpg"
  },
  "answerOptions": [
    {
      "text": "Answer1",
      "image": "answer_image1_url.jpg"
    },
    {
      "text": "Answer2",
      "image": "answer_image1_url.jpg"
    }
  ],
  "correctAnswer": "A",
  "providedAnswer": "A",
  "contentType": "SINGLE_TEST_CONTENT"
}');

INSERT INTO questions (id, type, description, duration, content)
VALUES ('024890e3-c883-4049-947b-8af2f9a6bfdf', 'MATCHING_TEST', 'description', 70, '{
  "questionDescription": {
    "text": "What is the capital of France?",
    "image": "paris_image_url.jpg"
  },
  "answerOptions": {
    "TextImageWrapper(text=Answer1, image=answer_image1_url.jpg)": {
      "text": "Maybe capital of France is Paris?",
      "image": "paris_image_url.jpg"
    },
    "TextImageWrapper(text=Answer2, image=answer_image1_url.jpg)": {
      "text": "Maybe capital of France is?",
      "image": "image_url.jpg"
    }
  },
  "correctAnswer": {
    "A": "Paris",
    "B": "Pari"
  },
  "providedAnswer": {
    "A": "Paris",
    "B": "Pari"
  },
  "contentType": "MATCHING_TEST_CONTENT"
}');

INSERT INTO questions (id, type, description, duration, content)
VALUES ('e4f282d8-4308-44e8-a47c-687e556ead24', 'MATCHING_TEST', 'description', 20, '{
  "questionDescription": {
    "text": "What is the capital of France?",
    "image": "paris_image_url.jpg"
  },
  "answerOptions": {
    "TextImageWrapper(text=Answer1, image=answer_image1_url.jpg)": {
      "text": "Maybe capital of France is Paris?",
      "image": "paris_image_url.jpg"
    },
    "TextImageWrapper(text=Answer2, image=answer_image1_url.jpg)": {
      "text": "Maybe capital of France is?",
      "image": "image_url.jpg"
    }
  },
  "correctAnswer": {
    "A": "Paris",
    "B": "Pari"
  },
  "providedAnswer": {
    "A": "Paris",
    "B": "Pari"
  },
  "contentType": "MATCHING_TEST_CONTENT"
}');

-- Quiz 1
INSERT INTO quizzes (id, title, description, passing_score, created_at, updated_at, total_question,
                     total_duration, published)
VALUES ('550e8400-1111-41d4-a716-446655440001', 'Quiz1 Title', 'Test your skills', 0.50,
        '2024-01-12 00:17:19.699000 +00:00', '2024-01-12 00:17:19.699892 +00:00', 3, 150, false);

-- Quiz 2
INSERT INTO quizzes (id, title, description, passing_score, created_at, updated_at, total_question,
                     total_duration, published)
VALUES ('550e8400-1111-41d4-a716-446655440002', 'Quiz2 Title', 'Test your geographical skills',
        0.60, '2024-01-12 00:24:06.309000 +00:00', '2024-01-12 00:24:06.309765 +00:00', 3, 130,
        false);


-- Quiz1 Question 1
INSERT INTO quizzes_questions (id, sequence, weight, question_id, quiz_id)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 1, 40, 'baa8bb7d-9533-4483-bdb4-204efba55e7a',
        '550e8400-1111-41d4-a716-446655440001');

-- Quiz1 Question 2
INSERT INTO quizzes_questions (id, sequence, weight, question_id, quiz_id)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 2, 40, '2bc5530e-0eb3-4ed7-8f74-cbc4cbf7b5bb',
        '550e8400-1111-41d4-a716-446655440001');

-- Quiz1 Question 3
INSERT INTO quizzes_questions (id, sequence, weight, question_id, quiz_id)
VALUES ('550e8400-e29b-41d4-a716-446655440003', 3, 40, '570f93b5-485c-42ea-82f9-8c866ac0e4c4',
        '550e8400-1111-41d4-a716-446655440001');

-- Quiz2 Question 1
INSERT INTO quizzes_questions (id, sequence, weight, question_id, quiz_id)
VALUES ('550e8400-e29b-41d4-a716-446655440011', 1, 40, '570f93b5-485c-42ea-82f9-8c866ac0e4c4',
        '550e8400-1111-41d4-a716-446655440002');

-- Quiz2 Question 2
INSERT INTO quizzes_questions (id, sequence, weight, question_id, quiz_id)
VALUES ('550e8400-e29b-41d4-a716-446655440012', 2, 40, '024890e3-c883-4049-947b-8af2f9a6bfdf',
        '550e8400-1111-41d4-a716-446655440002');

-- Quiz2 Question 3
INSERT INTO quizzes_questions (id, sequence, weight, question_id, quiz_id)
VALUES ('550e8400-e29b-41d4-a716-446655440013', 3, 40, 'e4f282d8-4308-44e8-a47c-687e556ead24',
        '550e8400-1111-41d4-a716-446655440002');


