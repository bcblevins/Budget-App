package com.blevins.BudgetApp.daos;

import com.blevins.BudgetApp.models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Transaction getAll() {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM transaction",
                this::mapToTransaction
        );
    }

    public Transaction create(Transaction transaction) {
        return jdbcTemplate.queryForObject(
                "INSERT INTO transaction (amount, description, date) values (?, ?, ?)",
                Transaction.class,
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate()
        );
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

    public boolean delete(Transaction transaction) {
        return jdbcTemplate.update(
                "DELETE FROM transaction WHERE id = ?",
                transaction.getId()
        ) == 1;
    }

    private Transaction mapToTransaction(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setAmount(rs.getBigDecimal("amount"));
        transaction.setDescription(rs.getString("description"));
        transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
        return transaction;
    }
}
