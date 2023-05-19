package com.example.testwork.entity;

import java.sql.Timestamp;

public class TaskEntity {

    private Integer id;
    private String title;
    private String description;
    private Timestamp time;
    private String status;
    private Integer performer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPerformer() {
        return performer;
    }

    public void setPerformer(int performer) {
        this.performer = performer;
    }
}
