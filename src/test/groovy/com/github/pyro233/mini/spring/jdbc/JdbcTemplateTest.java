package com.github.pyro233.mini.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/29 13:06
 */
class JdbcTemplateTest {

    public static final String PREPARED_STATEMENT_SQL = "select id, name, birthday from users where id = ?";
    public static final String STATEMENT_SQL = "select id, name, birthday from users where id = 1";


    record User(int id, String name, Date birthday) {
    }

    public void testInheritance() {
        class UserJdbcImpl extends NaiveAbstractJdbcTemplate {
            @Override
            protected Object doInStatement(ResultSet rs) {
                User user = null;
                try {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        Date birthday = new Date(rs.getDate("birthday")
                                                   .getTime());
                        user = new User(id, name, birthday);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return user;
            }

        }
        UserJdbcImpl userJdbc = new UserJdbcImpl();
        final User user = (User) userJdbc.query(STATEMENT_SQL);
        assertNotNull(user);
    }

    public void testFunctionCallback() {
        Function<ResultSet, User> mapper = rs -> {
            User user = null;
            try {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Date birthday = new Date(rs.getDate("birthday")
                                               .getTime());
                    user = new User(id, name, birthday);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        };
        final User user = JdbcTemplate.query(STATEMENT_SQL, mapper);
        assertNotNull(user);
    }

    public void testCustomCallback(int userId) {
        final Object[] args = {userId};
        final PreparedStatementCallback preparedStatementCallback = pstmt -> {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date birthday = new Date(rs.getDate("birthday")
                                           .getTime());
                return new User(id, name, birthday);
            }
            return null;
        };
        final User user = (User) JdbcTemplate.query(PREPARED_STATEMENT_SQL, args, preparedStatementCallback);
        assertNotNull(user);
    }

}