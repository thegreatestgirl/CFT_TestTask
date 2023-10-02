package com.example.cft_testtask.repositories;

import com.example.cft_testtask.BookingAddController;
import com.example.cft_testtask.models.Book;
import com.example.cft_testtask.models.Booking;
import com.example.cft_testtask.models.Reader;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Reader> getAllReaders() {
        String mQuery = "SELECT * FROM readers";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            List<Reader> readers = new ArrayList<>(); //perhaps linked list better?
            ResultSet rs = st.executeQuery(mQuery);

            while (rs.next()) {
                Reader reader = new Reader(rs.getInt("id"), rs.getString("surname"), rs.getString("name"), rs.getString("patronymic"), rs.getDate("dateOfBirth"));
                readers.add(reader);
            }
            return readers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Book> getAllBooks() {
        String mQuery = "SELECT * FROM books";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            List<Book> books = new ArrayList<>(); //perhaps linked list better?
            ResultSet rs = st.executeQuery(mQuery);

            while (rs.next()) {
                Book book = new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getInt("year"));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Booking> getAllBookings() {
        String mQuery = "SELECT booked_books.id AS id, readers.surname AS readerSurname, readers.name AS readerName, " +
                "readers.patronymic AS readerPatronymic, books.name AS bookName, givendate, returndate FROM booked_books " +
                "JOIN readers ON booked_books.readerid = readers.id " +
                "JOIN books ON booked_books.bookid = books.id"; //order by current bookings?

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            List<Booking> bookings = new ArrayList<>(); //perhaps linked list better?
            ResultSet rs = st.executeQuery(mQuery);

            while (rs.next()) {
                Booking booking = new Booking(rs.getInt("id"), rs.getString("readerSurname"), rs.getString("readerName"),
                        rs.getString("readerPatronymic"), rs.getString("bookName"), rs.getDate("givendate"), rs.getDate("returndate"));
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public Integer getLastIdAtTable() {
        String mQuery = "SELECT max(id) AS id FROM readers"; //order by current bookings?

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(mQuery);
            Integer maxId = null;
            while (rs.next()) {
                maxId = rs.getInt("id");
            }
            return maxId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public void addNewReader(Reader newReader) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {

            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDateStr = dmyFormat.format(newReader.getDateOfBirth());

            ResultSet rs = st.executeQuery("INSERT INTO readers(surname, name, patronymic, dateOfBirth)" +
                    "VALUES (\'" + newReader.getSurname() + "\', \'" + newReader.getName() + "\', \'" + newReader.getPatronymic() + "\', \'" + formattedDateStr + "\') RETURNING id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addNewBook(Book newBook) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("INSERT INTO books(name, author, year)" +
                    "VALUES (\'" + newBook.getName() + "\', \'" + newBook.getAuthor() + "\', \'" + newBook.getYear() + "\') RETURNING id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addNewBooking(Booking newBooking) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {

            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedGivenDate = dmyFormat.format(newBooking.getGivenDate());
            String formattedReturnDate = dmyFormat.format(newBooking.getReturnDate());

            ResultSet rs = st.executeQuery("INSERT INTO booked_books(readerid, bookid, givendate, returndate) " +
                                        "VALUES ((SELECT id FROM readers WHERE readers.surname = \'" + newBooking.getReaderSurname() + "\' " +
                                        "AND readers.name = \'" + newBooking.getReaderName() + "\' " +
                                        "AND readers.patronymic = \'" + newBooking.getReaderPatronymic() + "\'), " +
                                        "(SELECT id FROM books WHERE books.name = \'" + newBooking.getBookName() + "\'), " +
                                        "\'" + newBooking.getGivenDate() + "\', \'" + newBooking.getReturnDate() + "\') RETURNING id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateReader(Reader updatedReader) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            String mQuery = "select id, surname, name, patronymic, dateofbirth from readers where id = ";
            ResultSet rs = st.executeQuery(mQuery + updatedReader.getId());
            while (rs.next()) {

                Reader oldReader = new Reader(rs.getInt("id"), rs.getString("surname"), rs.getString("name"), rs.getString("patronymic"), rs.getDate("dateOfBirth"));

                if (!oldReader.getSurname().equals(updatedReader.getSurname()) ||
                    !oldReader.getName().equals(updatedReader.getName()) ||
                    !oldReader.getPatronymic().equals(updatedReader.getPatronymic()) ||
                    !oldReader.getDateOfBirth().equals(updatedReader.getDateOfBirth())) {
                    rs = st.executeQuery("UPDATE readers " +
                            "SET surname = \'" + updatedReader.getSurname() + "\', " +
                            "name = \'" + updatedReader.getName() + "\', " +
                            "patronymic = \'" + updatedReader.getPatronymic() + "\', " +
                            "dateofbirth = \'" + updatedReader.getDateOfBirth() + "\' " +
                            "WHERE id = " + updatedReader.getId());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateBook(Book updatedBook) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            String mQuery = "select id, name, author, year from books where id = ";
            ResultSet rs = st.executeQuery(mQuery + updatedBook.getId());
            while (rs.next()) {

                Book oldBook = new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getInt("year"));

                if (!oldBook.getName().equals(updatedBook.getName()) ||
                        !oldBook.getAuthor().equals(updatedBook.getAuthor()) ||
                        !oldBook.getYear().equals(updatedBook.getYear())) {
                    rs = st.executeQuery("UPDATE books " +
                            "SET name = \'" + updatedBook.getName() + "\', " +
                            "author = \'" + updatedBook.getAuthor() + "\', " +
                            "year = " + updatedBook.getYear() + " " +
                            "WHERE id = " + updatedBook.getId());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteItemById(Integer valId, String table) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("DELETE FROM " + table + " WHERE id = " + valId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
