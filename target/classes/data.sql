INSERT INTO style (style_id, style_name) VALUES (1, 'Action');
INSERT INTO style (style_id, style_name) VALUES (2, 'Drame');
INSERT INTO style (style_id, style_name) VALUES (3, 'Comédie');

INSERT INTO productor (productor_id, name) VALUES (1, 'Universal Pictures');
INSERT INTO productor (productor_id, name) VALUES (2, 'Warner Bros');
INSERT INTO productor (productor_id, name) VALUES (3, 'Paramount Pictures');

INSERT INTO movie (id_movie, title, production_year, reference, description, style_id, productor_id)
VALUES (1, 'Fast Action', 2023, 'FA-123456', 'Un film action palpitant', 1, 1);
INSERT INTO movie (id_movie, title, production_year, reference, description, style_id, productor_id)
VALUES (2, 'Dramatic Tales', 2022, 'DT-654321', 'Un film dramatique touchant', 2, 2);
INSERT INTO movie (id_movie, title, production_year, reference, description, style_id, productor_id)
VALUES (3, 'Funny Moments', 2023, 'FM-112233', 'Une comédie hilarante', 3, 3);

INSERT INTO actor (actor_id, last_name, first_name, birth_date) VALUES (1, 'Smith', 'John', '1990-01-01');
INSERT INTO actor (actor_id, last_name, first_name, birth_date) VALUES (2, 'Doe', 'Jane', '1995-02-02');
INSERT INTO actor (actor_id, last_name, first_name, birth_date) VALUES (3, 'Davis', 'Emily', '2000-03-03');

INSERT INTO role (role_id, actor_id, movie_id, role) VALUES (1, 1, 1, 'Protagoniste');

INSERT INTO customer (customer_id, name) VALUES (1, 'Alice');

INSERT INTO borrow (borrow_id, date, customer_id, movie_id, status)
VALUES (1, '2023-12-20', 1, 1, 'BORROWED');