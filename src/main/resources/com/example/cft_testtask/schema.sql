CREATE TABLE IF NOT EXISTS books (
    id serial NOT NULL PRIMARY KEY,
    name varchar(100) NOT NULL,
    author varchar(100),
    year integer
);

CREATE TABLE IF NOT EXISTS readers (
    id serial NOT NULL PRIMARY KEY,
    surname varchar(50) NOT NULL,
    name varchar(15) NOT NULL,
    patronymic varchar(60),
    dateOfBirth date NOT NULL
);

CREATE TABLE IF NOT EXISTS booked_books (
    id serial NOT NULL PRIMARY KEY,
    readerId integer NOT NULL,
    bookId integer NOT NULL,
    givenDate date NOT NULL,
    returnDate date,
    foreign key (readerId) references readers(id),
    foreign key (bookId) references books(id)
);
