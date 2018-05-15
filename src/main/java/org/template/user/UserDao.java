package org.template.user;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

@Repository
public class UserDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public boolean doesUserExist(int userId) {
        String sql = "select * from system.user where id = :userId";

        return namedParameterJdbcTemplate.query(sql, ImmutableMap.of("userId", userId), resultSetChecker);
    }

    boolean createUser(User user) {

        String sql = "insert into system.user (first_name, last_name, email_address) "
                     + "values (:firstName, :lastName, :emailAddress);";

        namedParameterJdbcTemplate.update(sql, ImmutableMap.of("firstName", user.getFirstName(),
                                                               "lastName", user.getLastName(),
                                                               "emailAddress",  user.getEmailAddress()));
        return true;
    }

    private final ResultSetExtractor<Boolean> resultSetChecker = ResultSet::isBeforeFirst;

}
