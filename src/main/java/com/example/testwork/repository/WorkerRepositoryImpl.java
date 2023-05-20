package com.example.testwork.repository;

import com.example.testwork.entity.WorkerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WorkerRepositoryImpl implements WorkerRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE_WORKER = """
            INSERT INTO Workers (id, name, position, avatar) VALUES (?, ?, ?, ?)
            """;

    private static final String GET_ALL_WORKER = """
            SELECT w.*, t.title AS task_title, t.status AS task_status FROM Workers w LEFT JOIN Tasks t ON t.performer = w.id
            """;

    private static final String GET_WORKER_BY_ID = """
            SELECT w.*, t.title AS task_title, t.status AS task_status FROM Workers w LEFT JOIN Tasks t ON t.performer = w.id WHERE w.id = ?
            """;

    private static final String UPDATE_WORKER_BY_ID = """
            UPDATE Workers SET name = ?, position = ?, avatar = ? WHERE id = ?
            """;

    private static final String DELETE_WORKER_BY_ID = """
             DELETE FROM Workers WHERE id = ?
            """;

    @Override
    public void createWorker(WorkerEntity workerEntity) {
        jdbcTemplate.update(connection -> createPreparedStatement(connection, workerEntity));
    }

    @Override
    public List<WorkerEntity> getAllWorker() {
        return jdbcTemplate.query(GET_ALL_WORKER, (rs, rowNum) -> {
            WorkerEntity worker = workerRowMapper.mapRow(rs, rowNum);
            String taskTitle = rs.getString("task_title");
            String taskStatus = rs.getString("task_status");
            return taskSummary(taskTitle, taskStatus, worker);
        });
    }

    @Override
    public List<WorkerEntity> getWorkerById(int id) {
        return jdbcTemplate.query(GET_WORKER_BY_ID, new Object[]{id}, (rs, rowNum) -> {
            WorkerEntity worker = workerRowMapper.mapRow(rs, rowNum);
            String taskTitle = rs.getString("task_title");
            String taskStatus = rs.getString("task_status");
            return taskSummary(taskTitle, taskStatus, worker);
        });
    }

    private WorkerEntity taskSummary(String taskTitle, String taskStatus, WorkerEntity worker) {
        if (taskTitle != null) {
            worker.setName(worker.getName() + " (" + taskTitle + ")");
        }
        if (taskStatus != null) {
            worker.setName(worker.getName() + " (" + taskStatus + ")");
        }
        return worker;
    }

    @Override
    public void updateWorkerById(int id, WorkerEntity workerEntity) {
        jdbcTemplate.update(UPDATE_WORKER_BY_ID, workerEntity.getName(), workerEntity.getPosition(), workerEntity.getAvatar(), id);
    }

    @Override
    public void deleteWorkerById(int id) {
        jdbcTemplate.update(DELETE_WORKER_BY_ID, id);
    }


    private PreparedStatement createPreparedStatement(Connection connection, WorkerEntity workerEntity) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_WORKER);
        ps.setInt(1, workerEntity.getId());
        ps.setString(2, workerEntity.getName());
        ps.setString(3, workerEntity.getPosition());
        ps.setString(4, workerEntity.getAvatar());
        return ps;
    }

    private RowMapper<WorkerEntity> workerRowMapper = ((rs, rowNum) -> {
        WorkerEntity workerEntity = new WorkerEntity();
        workerEntity.setId(rs.getInt("id"));
        workerEntity.setName(rs.getString("name"));
        workerEntity.setPosition(rs.getString("position"));
        workerEntity.setAvatar(rs.getString("avatar"));
        return workerEntity;
    });
}
