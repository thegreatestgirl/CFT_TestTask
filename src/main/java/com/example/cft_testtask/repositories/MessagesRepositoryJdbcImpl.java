package com.example.cft_testtask.repositories;

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
}
