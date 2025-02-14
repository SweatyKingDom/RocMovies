INSERT INTO style (style_id, style_name) VALUES (1, 'Action');
INSERT INTO style (style_id, style_name) VALUES (2, 'Drame');
INSERT INTO style (style_id, style_name) VALUES (3, 'Comédie');

INSERT INTO productor (productor_id, name) VALUES (1, 'Universal Pictures');
INSERT INTO productor (productor_id, name) VALUES (2, 'Warner Bros');
INSERT INTO productor (productor_id, name) VALUES (3, 'Paramount Pictures');

INSERT INTO movie (id_movie, title, production_year, reference, description, style_id, productor_id)
VALUES (1, 'Fast Action', 2023, 'FA-123456', 'Un film d\'action palpitant', 1, 1);
INSERT INTO movie (id_movie, title, production_year, reference, description, style_id, productor_id)
VALUES (2, 'Dramatic Tales', 2022, 'DT-654321', 'Un film dramatique touchant', 2, 2);
INSERT INTO movie (id_movie, title, production_year, reference, description, style_id, productor_id)
VALUES (3, 'Funny Moments', 2023, 'FM-112233', 'Une comédie hilarante', 3, 3);

INSERT INTO actor (actor_id, last_name, first_name) VALUES (1, 'Smith', 'John');
INSERT INTO actor (actor_id, last_name, first_name) VALUES (2, 'Doe', 'Jane');
INSERT INTO actor (actor_id, last_name, first_name) VALUES (3, 'Davis', 'Emily');

INSERT INTO role (role_id, actor_id, movie_id, role) VALUES (1, 1, 1, 'Protagoniste');
INSERT INTO role (role_id, actor_id, movie_id, role) VALUES (2, 2, 2, 'Antagoniste');
INSERT INTO role (role_id, actor_id, movie_id, role) VALUES (3, 3, 3, 'Secondaire comique');

INSERT INTO customer (customer_id, name) VALUES (1, 'Alice');
INSERT INTO customer (customer_id, name) VALUES (2, 'Bob');

INSERT INTO borrow (borrow_id, date, customer_id, movie_id, status)
VALUES (1, '2023-12-20', 1, 1, 'BORROWED');
INSERT INTO borrow (borrow_id, date, customer_id, movie_id, status)
VALUES (2, '2023-12-21', 2, 2, 'RETURNED');