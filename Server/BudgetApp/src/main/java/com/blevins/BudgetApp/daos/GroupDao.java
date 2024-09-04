package com.blevins.BudgetApp.daos;

import com.blevins.BudgetApp.models.Group;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class GroupDao {
    private final JdbcTemplate jdbcTemplate;

    public GroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Group get(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM \"group\" WHERE id = ?",
                this::mapToGroup,
                id
        );
    }

    public List<Group> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM \"group\"",
                this::mapToGroup
        );
    }

    public Group create(Group group) {
        Integer id = 0;
        try {
            id = jdbcTemplate.queryForObject(
                    "INSERT INTO \"group\" (name) values (?) RETURNING id;",
                    Integer.class,
                    group.getName()
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("EmptyResultDataAccessException");
        } catch (Exception e) {
            System.out.println("Exception");
        }

        return get(id);
    }

    public Group update(Group group) {
        int rowsAffected = jdbcTemplate.update(
                "UPDATE \"group\" SET name = ? WHERE id = ?",
                group.getName(),
                group.getId()
        );

        return get(group.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM \"group\" WHERE id = ?",
                id
        );
    }

    private Group mapToGroup(ResultSet rs, int rowNum) throws SQLException {
        return new Group(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
