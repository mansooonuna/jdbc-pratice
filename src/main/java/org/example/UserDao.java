package org.example;

import java.sql.*;

public class UserDao {


    public void create(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;

        try {
            con = ConnectionManager.getConnection();
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            pstat = con.prepareStatement(sql);
            pstat.setString(1, user.getUserId());
            pstat.setString(2, user.getPassword());
            pstat.setString(3, user.getName());
            pstat.setString(4, user.getEmail());

            pstat.executeUpdate();

        } finally {

            if (pstat != null) {
                pstat.close();
            }

            if (con != null) {
                pstat.close();
            }
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
            pstat = con.prepareStatement(sql);
            pstat.setString(1, userId);


            rs = pstat.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("email"));
            }
            return user;
        } finally {
            if( rs != null) {
                rs.close();
            }

            if(pstat != null) {
                pstat.close();
            }

            if(con != null) {
                con.close();
            }
        }


    }
}


