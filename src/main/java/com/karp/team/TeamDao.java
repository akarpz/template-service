package com.karp.team;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

@Repository
public class TeamDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean getThing() {
        return true;
    }
    
    public String getTeam(String team) {
        String sql = "select name from test_results.team where short_name = :team;";

        return namedParameterJdbcTemplate.query(sql, ImmutableMap.of("team", team), resultSetExtractor);
    }

    public boolean setThing(String stuff) {

        String sql = "insert into test_results.team (name, short_name) values (:name, :short_name);";

        namedParameterJdbcTemplate.update(sql, ImmutableMap.of("stuff", stuff));
        return true;
    }

    private final ResultSetExtractor<String> resultSetExtractor = rs -> {
        List<String> result = new ArrayList<>();
        while (rs.next()) {
            result.add(rs.getString("name"));
        }

        return result.get(0);
    };

}
