package com.blevins.BudgetApp.daos;

import com.blevins.BudgetApp.models.Transaction;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    public TransactionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Transaction get(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM transaction WHERE id = ?",
                this::mapToTransaction,
                id
        );
    }

    public List<Transaction> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM transaction",
                this::mapToTransaction
        );
    }

    public Transaction create(Transaction transaction) {
        Integer id = 0;
        try {
            id = jdbcTemplate.queryForObject(
                    "INSERT INTO transaction (amount, description, date) values (?, ?, ?) RETURNING id;",
                    Integer.class,
                    transaction.getAmount(),
                    transaction.getDescription(),
                    transaction.getDate()
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("EmptyResultDataAccessException");
        } catch (Exception e) {
            System.out.println("Exception");
        }

        return get(id);
    }

    public Transaction update(Transaction transaction) {
        int rowsAffected = jdbcTemplate.update(
                "UPDATE transaction (amount, description, date) WHERE id = ?",
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate()
        );
        return get(transaction.getId());
    }

    public boolean delete(int id) {
        return jdbcTemplate.update(
                "DELETE FROM transaction WHERE id = ?",
                id
        ) == 1;
    }

    // Mappring method
    private Transaction mapToTransaction(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setAmount(rs.getBigDecimal("amount"));
        transaction.setDescription(rs.getString("description"));
        transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
        return transaction;
    }
}
