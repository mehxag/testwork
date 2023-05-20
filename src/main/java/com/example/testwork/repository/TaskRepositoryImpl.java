package com.example.testwork.repository;


import com.example.testwork.dto.TaskDTO;
import com.example.testwork.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String INSERT_NEW_TASK = """
            INSERT INTO Tasks (id, title, description, time, status, performer) VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String GET_SHORT_TASK = """
            SELECT id, title, status FROM Tasks
            """;

    private static final String GET_TASK_BY_ID = """
            SELECT * FROM Tasks WHERE id = ?
            """;

    private static final String UPDATE_TASK_BY_ID = """
            UPDATE tasks SET title = ?, description = ?, time = ?, status = ? WHERE id = ?;
            """;

    private static final String ASSIGN_PERFORMER_TO_TASK = """
            UPDATE Tasks SET performer = ? WHERE id = ?;
            """;

    @Override
    @Transactional
    public void saveTask(TaskEntity taskEntity) {
        jdbcTemplate.update(INSERT_NEW_TASK, taskEntity.getId(), taskEntity.getTitle(), taskEntity.getDescription(),
                taskEntity.getTime(), taskEntity.getStatus(), taskEntity.getPerformer());
    }

    @Override
    public Optional<List<TaskDTO>> getShortTask() {
        return Optional.of(jdbcTemplate.query(GET_SHORT_TASK, new TaskDTO()));
    }

    @Override
    public Optional<TaskEntity> getTaskById(int id) {
        return Optional.of(jdbcTemplate.queryForObject(GET_TASK_BY_ID, new Object[]{id}, taskRowMapper));
    }

    @Override
    public void updateTaskById(int id, TaskEntity taskEntity) {
        jdbcTemplate.update(UPDATE_TASK_BY_ID, taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getTime(), taskEntity.getStatus(), id);
    }

    @Override
    public void assignPerformerToTask(int taskId, int performerId) {
        jdbcTemplate.update(ASSIGN_PERFORMER_TO_TASK, performerId, taskId);
    }

    private RowMapper<TaskEntity> taskRowMapper = ((rs, rowNum) -> {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(rs.getInt("id"));
        taskEntity.setTitle(rs.getString("title"));
        taskEntity.setDescription(rs.getString("description"));
        taskEntity.setTime(rs.getTimestamp("time"));
        taskEntity.setStatus(rs.getString("status"));
        taskEntity.setPerformer(rs.getInt("performer"));
        return taskEntity;
    });
}
