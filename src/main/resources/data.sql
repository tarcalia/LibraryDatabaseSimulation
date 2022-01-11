INSERT INTO author (name) VALUES ('William Shakespeare');
INSERT INTO author (name) VALUES ('Agatha Christie');
INSERT INTO author (name) VALUES ('J. K. Rowling');
INSERT INTO author (name) VALUES ('Ken Follett');

INSERT INTO book (isbnnumber, book_genre, quantity, title, author_id) VALUES (1234, 2, 3, 'Harry Potter', 3);
INSERT INTO book (isbnnumber, book_genre, quantity, title, author_id) VALUES (3456, 1, 4, 'Romeo & Juliet', 1);
INSERT INTO book (isbnnumber, book_genre, quantity, title, author_id) VALUES (4567, 3, 2, 'Poirot', 2);
INSERT INTO book (isbnnumber, book_genre, quantity, title, author_id) VALUES (1345, 0, 5, 'The Pillars of the Earth', 4);
INSERT INTO book (isbnnumber, book_genre, quantity, title, author_id) VALUES (5678, 1, 4, 'Harry Potter 2', 3);
INSERT INTO book (isbnnumber, book_genre, quantity, title, author_id) VALUES (9876, 2, 3, 'Hamlet', 1);
INSERT INTO book (isbnnumber, book_genre, quantity, title, author_id) VALUES (7654, 0, 2, 'The Mousetrap', 2);
INSERT INTO book (isbnnumber, book_genre, quantity, title, author_id) VALUES (6543, 2, 5, 'A Column of Fire', 4);

INSERT INTO customer (customer_name) VALUES ('Joe Sample');
INSERT INTO customer (customer_name) VALUES ('John Another Sample');
INSERT INTO customer (customer_name) VALUES ('Alicia Maybe Sample');

INSERT INTO book_order (order_id, isbnnumber, customer_id) VALUES (1, 1234, 2);
INSERT INTO book_order (order_id, isbnnumber, customer_id) VALUES (2, 4567, 3);
INSERT INTO book_order (order_id, isbnnumber, customer_id) VALUES (3, 7654, 1);