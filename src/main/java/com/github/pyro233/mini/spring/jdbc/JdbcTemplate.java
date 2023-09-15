package com.github.pyro233.mini.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.function.Function;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/29 12:57
 */
public class JdbcTemplate {

    public static <T> T query(String sql, Function<ResultSet, T> mapper) {
        loadDriver();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root＆password=root");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return mapper.apply(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object query(String sql, Object[] args, PreparedStatementCallback statementCallback) {
        loadDriver();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root＆password=root");
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                final Object arg = args[i];
                if (arg instanceof String) {
                    pstmt.setString(i + 1, (String) arg);
                } else if (arg instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) arg);
                }
            }
            return statementCallback.doInPreparedStatement(pstmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
        }
    }
    // endregion callback
}

