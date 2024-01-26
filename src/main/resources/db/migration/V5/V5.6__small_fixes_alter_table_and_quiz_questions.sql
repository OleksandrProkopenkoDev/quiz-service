alter table if exists questions
    add column image_id varchar(255);
alter table if exists answers
    add column question_type varchar(255) check (question_type in
                                                 ('SINGLE_CHOICE', 'MULTIPLE_CHOICE', 'MATCHING',
                                                  'AUTOCHECKABLE_TEXT', 'HUMANCHECKABLE_TEXT',
                                                  'MEDIA_TO_MENTOR'));

DROP TABLE question_answers;

ALTER TABLE quizzes
    DROP COLUMN is_repeatable;
ALTER TABLE quizzes
    DROP COLUMN score;
ALTER TABLE quizzes
    DROP COLUMN status;

ALTER TABLE questions
    ADD COLUMN correct_answer varchar;

create table quiz_question
(
    quiz_id     bigint not null,
    question_id bigint not null,
    foreign key (question_id) references questions,
    foreign key (quiz_id) references quizzes
);