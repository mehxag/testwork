package com.example.testwork.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDTO implements RowMapper<TaskDTO> {

    private Integer id;
    private String title;
    private String status;

    public TaskDTO() {}

    public TaskDTO(Integer id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    @Override
    public TaskDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaskDTO(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("status")
        );
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
}
