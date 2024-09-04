package com.blevins.BudgetApp.daos;

import com.blevins.BudgetApp.models.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Category get(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM category WHERE id = ?",
                this::mapToCategory,
                id
        );
    }

    public List<Category> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM category",
                this::mapToCategory
        );
    }

    public Category create(Category category) {
        Integer id = 0;
        try {
            id = jdbcTemplate.queryForObject(
                    "INSERT INTO category (name, amount_assigned, group_id) values (?,?,?) RETURNING id;",
                    Integer.class,
                    category.getName(),
                    category.getAmountAssigned(),
                    category.getGroupId()
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("EmptyResultDataAccessException");
        } catch (Exception e) {
            System.out.println("Exception");
        }

        return get(id);
    }

    public Category update(Category category) {
        int rowsAffected = jdbcTemplate.update(
                "UPDATE category SET name = ?, amount_assigned = ?, group_id = ? WHERE id = ?",
                category.getName(),
                category.getAmountAssigned(),
                category.getGroupId(),
                category.getId()
        );
        if (rowsAffected == 0) {
            return null;
        }
        return get(category.getId());
    }

    public boolean delete(int id) {
        return jdbcTemplate.update(
                "DELETE FROM category WHERE id = ?",
                id
        ) == 1;
    }

    // Mapping method
    public Category mapToCategory(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setAmountAssigned(rs.getBigDecimal("amount_assigned"));
        category.setGroupId(rs.getInt("group_id"));
        return category;
    }
}
