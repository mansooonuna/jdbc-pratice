package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    public void executeUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;

        try {
            con = ConnectionManager.getConnection();
            pstat = con.prepareStatement(sql);
            pss.setter(pstat);

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

    public Object executeQuery(String userId, String sql, PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            pstat = con.prepareStatement(sql);
            pss.setter(pstat);

            rs = pstat.executeQuery();

            Object obj = null;
            if (rs.next()) {
                return rowMapper.map(rs);
            }
            return obj;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstat != null) {
                pstat.close();
            }

            if (con != null) {
                con.close();
            }
        }


    }
}
