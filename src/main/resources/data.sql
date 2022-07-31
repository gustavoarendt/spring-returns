INSERT INTO USERS(name, email, password) VALUES('Gus', 'gus@mail.com', '$2a$10$jqzNNWN91cg5V4Ldflsf9.hhgtRa5.Jv6Ex.4uBoJh8.ahYuA0Vum');

INSERT INTO COURSE(name, category) VALUES('Spring', 'Java');
INSERT INTO COURSE(name, category) VALUES('.NET', 'Csharp');

INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id)
VALUES('Question', 'Error trying to install webtools', '2022-07-29 18:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id)
VALUES('Question Again', 'Error trying to compile project', '2022-07-29 17:00:00', 'NOT_ANSWERED', 1, 2);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id)
VALUES('Question', '.NET CLI ERROR', '2022-07-29 20:00:00', 'NOT_ANSWERED', 1, 2);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id)
VALUES('Kafka vs RabbitMQ', 'Should I go for kafka or rabbitmq later?', '2022-07-29 20:30:00', 'NOT_ANSWERED', 1, 1);