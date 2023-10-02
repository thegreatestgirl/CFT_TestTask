package com.example.cft_testtask.repositories;

import com.example.cft_testtask.models.Chatroom;
import com.example.cft_testtask.models.Message;
import com.example.cft_testtask.Reader;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//    @Override
//    public Optional<Message> findById(Long id) {
//        String mQuery = "SELECT * FROM readers WHERE id = " + id;
//
//        try (Connection con = dataSource.getConnection();
//             Statement st = con.createStatement()) {
//            ResultSet rs = st.executeQuery(mQuery);
//
//            if (!rs.next()) {
//                return Optional.empty();
//            }
//            Long userId = rs.getLong(2);
//            Long roomId = rs.getLong(3);
//            Reader user = findUser(userId);
//            Chatroom room = findChat(roomId);
//            return Optional.of(new Message(rs.getLong(1), user, room,
//                    rs.getString(4), rs.getTimestamp(5).toLocalDateTime()));
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return Optional.empty();
//    }

//    private Reader findUser(Long id) throws SQLException {
//        String uQuery = "SELECT * FROM readers WHERE id = " + id;
//
//        try (Connection con = dataSource.getConnection();
//             Statement st = con.createStatement()) {
//            ResultSet rs = st.executeQuery(uQuery);
//            if (!rs.next()) {
//                return null;
//            }
//            //return new Reader(id, rs.getString(2), rs.getString(3));
//        }
//    }

//    private Chatroom findChat(Long id) throws SQLException {
////        String cQuery = "SELECT * FROM readers WHERE id = " + id;
////
////        try (Connection con = dataSource.getConnection();
////             Statement st = con.createStatement()) {
////            ResultSet rs = st.executeQuery(cQuery);
////            if (!rs.next()) {
////                return null;
////            }
////            return new Chatroom(id, rs.getString(2));
////        }
//    }
}