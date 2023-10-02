INSERT INTO books(name, author, year) VALUES
                                          ('Harry potter and the Prisoner of Azkaban',
                                           'J. K. Rowling', 1999),
                                          ('Harry Potter and the Chamber of Secrets',
                                           'J. K. Rowling', 1998)
ON CONFLICT DO NOTHING;

INSERT INTO readers(surname, name, patronymic, dateOfBirth) VALUES
                                                                  ('Ivanov', 'Alex', 'Romanovich', '2000-03-24'),
                                                                  ('Petrov', 'Vladimir', 'Olegovich', '2003-05-15')
ON CONFLICT DO NOTHING;

INSERT INTO booked_books(readerId, bookId, givenDate, returnDate) VALUES
                                                                          ((SELECT id FROM readers WHERE surname = 'Ivanov' AND name = 'Alex'),
                                                                           (SELECT id FROM  books WHERE name = 'Harry potter and the Prisoner of Azkaban'),
                                                                           '2023-10-02', '2021-10-16')
ON CONFLICT DO NOTHING;