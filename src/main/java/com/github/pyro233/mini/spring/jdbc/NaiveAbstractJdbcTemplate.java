package com.github.pyro233.mini.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author: tao.zhou
 * @Date: 2023/9/15 9:40
 */
@Deprecated
abstract class NaiveAbstractJdbcTemplate {
    protected abstract Object doInStatement(ResultSet rs);

    public Object query(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
        }
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root＆password=root");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return doInStatement(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
