INSERT INTO quizzes (id, title, description, status, passing_score, created_at,
                     is_deleted, is_published)
VALUES (1, 'Default quiz title', 'Default quiz Description', 'UNDONE', 20,
        '2023-12-10 01:17:41.144000 +00:00', default, true
  ),
       (2, 'Default quiz title', 'Default quiz Description', 'UNDONE', 20,
        '2023-12-10 01:17:41.144000 +00:00',
         default, true),
       (3, 'Default quiz title', 'Default quiz Description', 'UNDONE', 20,
        '2023-12-10 01:17:41.144000 +00:00', default, true);

insert into vacancies(id, status, title, department, description, work_arrangement, currency,
                      time_period, lower_bound, upper_bound)
values ('2f2ca980-9743-11ee-b9d1-0242ac120012', 'ACTIVE', 'test title', 'TEST', 'TEST', 'FULL_TIME',
        '$', 'day', 6789, 9985);

insert into test_tour (id, title, description, vacancy_id)
VALUES (1, 'Default title', 'Default description', '2f2ca980-9743-11ee-b9d1-0242ac120012');

insert into test_tour_task (id, test_tour_id, type, title, description, display_order)
VALUES ('2cb51c08-9821-11ee-b9d1-0242ac120002', 1, 'QUIZ_TASK', 'quiz task title',
        'quiz task description', 1),
       ('2f2cab9c-9743-11ee-b9d1-0242ac120003', 1, 'QUIZ_TASK', 'quiz task title',
        'quiz task description', 2),
       ('2f2cacb4-9743-11ee-b9d1-0242ac120004', 1, 'QUIZ_TASK', 'quiz task title',
        'quiz task description', 3);

insert into quiz_test_tour_task(id, quiz_id)
values ('2cb51c08-9821-11ee-b9d1-0242ac120002', 1),
       ('2f2cab9c-9743-11ee-b9d1-0242ac120003', 2),
       ('2f2cacb4-9743-11ee-b9d1-0242ac120004', 3);
