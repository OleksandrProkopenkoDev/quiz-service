DELETE
FROM quizzes;
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



