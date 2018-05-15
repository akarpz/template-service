package org.template.transfer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import org.template.transfer.domain.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

@Repository
public class TransferDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    List<Transfer> getTransfersForUserId(int userId) {
        String sql = "select user_id, amount from system.transfer where user_id = :userId;";

        return namedParameterJdbcTemplate.query(sql, ImmutableMap.of("userId", userId), resultSetTransferExtractor);
    }

    Integer getTransferSumForUserId(int userId) {
        String sql = "select sum(amount) from system.transfer where user_id = :userId;";

        return namedParameterJdbcTemplate.query(sql, ImmutableMap.of("userId", userId), resultSetTransferSumExtractor);
    }

    boolean createTransfer(int userId, int amount) {

        String sql = "insert into system.transfer (user_id, amount) "
                     + "values (:userId, :amount);";

        namedParameterJdbcTemplate.update(sql, ImmutableMap.of("userId", userId,
                                                               "amount", amount));
        return true;
    }

    private final ResultSetExtractor<List<Transfer>> resultSetTransferExtractor = rs -> {
        List<Transfer> result = new ArrayList<>();
        while (rs.next()) {
            Transfer transfer = new Transfer();
            transfer.setUserId(rs.getInt("user_id"));
            final int amount = rs.getInt("amount");
            transfer.setAmount(amount);
            if(amount < 0) {
                transfer.setAction("REMOVE");
            }else {
                transfer.setAction("ADD");
            }
            result.add(transfer);
        }

        return result;
    };

    private final ResultSetExtractor<Integer> resultSetTransferSumExtractor = rs -> {
        List<Integer> result = new ArrayList<>();
        while (rs.next()) {
            result.add(rs.getInt("sum"));
        }

        return result.get(0);
    };

}
