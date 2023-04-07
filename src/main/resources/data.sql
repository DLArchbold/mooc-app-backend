/*https://www.browserling.com/tools/bcrypt Use Rounds 10*/

--drop table enrolled;
delete from todo;

delete from application_user;
delete from comment;

delete from feedback;
delete from lesson;
delete from course;
delete from enrolled;
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




insert into application_user(id,email,password, user_type, lesson_id, interests, name)
values(1, 'student1@gmail.com', 'student1', 'student', 1, 'Machine Learning', 'ChenxiSun');

insert into application_user(id, email,password, user_type, lesson_id, interests, name)
values(2,'instructor1@gmail.com', 'instructor1', 'instructor', 2,'Natual Language Processing', 'XintianCao');

insert into application_user(id,email,password, user_type, lesson_id, interests, name)
values(3, 'student3@gmail.com', 'student3', 'student', 1, 'Web development, Software', 'Jack');

insert into application_user(id,email,password, user_type, lesson_id, interests, name)
values(4, 'student4@gmail.com', 'student4', 'student', 2, 'Hardware', 'Jane');

insert into application_user(id,email,password, user_type, lesson_id, interests, name)
values(5, 's5@gatech.edu', 'student5', 'student', 2, 'Software', 'Michael');

insert into application_user(id,email,password, user_type, lesson_id, interests, name)
values(6, 's6@gatech.edu', 'student6', 'student', 1, 'Computer Vision', 'Leon');

insert into application_user(id,email,password, user_type, lesson_id, interests, name)
values(7, 's7@gatech.edu', 'student7', 'student', 2, 'Deep Learning', 'Penny');

insert into application_user(id,email,password, user_type, lesson_id, interests, name)
values(8, 's8@gatech.edu', 'student8', 'student', 1, 'System Control', 'Bess');


insert into lesson(id, video_link, lesson_number, course_id, description)
values(1, 'https://www.youtube.com/embed/L3LMbpZIKhQ?list=PLB7540DEDD482705B', 1, 1, 'First lesson of course 1');

insert into lesson(id, video_link, lesson_number, course_id, description)
values(2, 'https://www.youtube.com/embed/aircAruvnKk', 2, 1, 'Second lesson of course 1');






--insert into course(id, test, description)
--values(1, 2, 'First courses');
--
--insert into course(id, test, description)
--values(2, 2, 'Second course');

insert into course(id,  title, description, instructor_application_user_id)
values(1,  'Course 1','Firstcourse', 1);

insert into course(id,  title, description, instructor_application_user_id)
values(2,  'Course 2', 'Second course', 2);






insert into enrolled(id, username, course_id)
values(1,'student1@gmail.com',1);

insert into enrolled(id, username, course_id)
values(2,'student1@gmail.com',2);

--insert into user(id, email,password, user_type)
--values(3, '', 3, 2);





	