package com.blevins.BudgetApp.daos;

import com.blevins.BudgetApp.models.Income;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class IncomeDao {
    private final JdbcTemplate jdbcTemplate;

    public IncomeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Income get(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM income WHERE id = ?",
                this::mapToIncome,
                id
        );
    }

    public List<Income> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM income",
                this::mapToIncome
        );
    }

    public Income create(Income income) {
        Integer id = 0;
        try {
            id = jdbcTemplate.queryForObject(
                    "INSERT INTO income (amount, is_monthly, times_per_month, weeks_per_period) values (?,?,?,?) RETURNING id;",
                    Integer.class,
                    income.getAmount(),
                    income.isMonthly(),
                    income.getTimesPerMonth(),
                    income.getWeeksPerPeriod()
            );
        } catch (Exception e) {
            System.out.println("Exception");
        }

        return get(id);
    }

    public Income update(Income income) {
        int rowsAffected = jdbcTemplate.update(
                "UPDATE income SET amount = ?, is_monthly = ?, times_per_month = ?, weeks_per_period = ? WHERE id = ?",
                income.getAmount(),
                income.isMonthly(),
                income.getTimesPerMonth(),
                income.getWeeksPerPeriod(),
                income.getId()
        );

        return income;
    }

    public boolean delete(int id) {
        return jdbcTemplate.update(
                "DELETE FROM income WHERE id = ?",
                id
        ) > 0;
    }

    public Income mapToIncome(ResultSet rs, int rowNum) throws SQLException {
        Income income = new Income();
        income.setId(rs.getInt("id"));
        income.setAmount(rs.getBigDecimal("amount"));
        income.setMonthly(rs.getBoolean("is_monthly"));
        income.setTimesPerMonth(rs.getInt("times_per_month"));
        income.setWeeksPerPeriod(rs.getInt("weeks_per_period"));
        return income;
    }
}
