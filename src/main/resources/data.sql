/*https://www.browserling.com/tools/bcrypt Use Rounds 10*/

--drop table comment;
delete from todo;

delete from application_user;
delete from comment;

delete from feedback;
--/*in28minutes/dummy*/
--INSERT INTO USER (ID, USERNAME, PASSWORD, ROLE) 
--VALUES (1, 'in28minutes', '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e','ROLE_USER');
--
--/*in28minutes2/mypassword*/
--INSERT INTO USER (ID, USERNAME, PASSWORD, ROLE) 
--VALUES (2, 'in28minutes2', '$2a$10$i9AckmxMkb4yKtLCdxeQheCm2pXWB3qZ2G189/Ph/DUci1DvLO.Rq','ROLE_USER');
--



insert into todo(id, username,description,target_date,is_done)
values(10001, 'in28minutes', 'Learn JPA', sysdate(), false);

insert into todo(id, username,description,target_date,is_done)
values(10002, 'in28minutes', 'Learn Data JPA', sysdate(), false);

insert into todo(id, username,description,target_date,is_done)
values(10003, 'in28minutes', 'Learn Microservices', sysdate(), false);


insert into comment(id, description, urgency_level, in_response_to, target_date, username, votes, lesson_id, comment_type, answered)
values(1, 'description 1', '3', 0, LOCALTIMESTAMP, 'student1@gmail.com', 1, 1, 'question', TRUE);

insert into comment(id, description,urgency_level,in_response_to, target_date,username, votes,lesson_id, comment_type, answered)
values(2, 'description 2', '2', 1, LOCALTIMESTAMP, 'student1@gmail.com', 1,1, 'statement', NULL);

insert into comment(id, description,urgency_level,in_response_to, target_date,username, votes,lesson_id, comment_type, answered)
values(3, 'description 3', '1', 2, LOCALTIMESTAMP, 'instructor1@gmail.com', 1, 1, 'statement', NULL);

insert into comment(id, description,urgency_level,in_response_to, target_date,username, votes, lesson_id, comment_type, answered)
values(4, 'description 4', '3', 0, LOCALTIMESTAMP, 'student1@gmail.com', 1,2, 'question', FALSE);

insert into comment(id, description,urgency_level,in_response_to, target_date,username, votes,lesson_id, comment_type, answered)
values(5, 'description 5', '2', 4, LOCALTIMESTAMP, 'student1@gmail.com', 1,2, 'statement', NULL);

insert into comment(id, description,urgency_level,in_response_to, target_date,username, votes,lesson_id, comment_type, answered)
values(6, 'description 6', '1', 5, LOCALTIMESTAMP, 'instructor1@gmail.com', 1, 2, 'statement', NULL);





insert into feedback(id, feedback_comment,feedback_rating, lesson_id)
values(1, 'feedback comment 1', 1, 1);

insert into feedback(id, feedback_comment,feedback_rating, lesson_id)
values(3, 'feedback comment 2', 2, 1);

insert into feedback(id, feedback_comment,feedback_rating, lesson_id)
values(2, 'feedback comment 3', 3, 1);

insert into feedback(id, feedback_comment,feedback_rating, lesson_id)
values(4, 'feedback comment 4', 3, 2);

insert into feedback(id, feedback_comment,feedback_rating, lesson_id)
values(5, 'feedback comment 5', 3, 2);



insert into application_user(id, email,password, user_type)
values(1, 'student1@gmail.com', 'student1', 'student');

insert into application_user(id, email,password, user_type)
values(2, 'instructor1@gmail.com', 'instructor1', 'instructor');

--insert into user(id, email,password, user_type)
--values(3, '', 3, 2);





	