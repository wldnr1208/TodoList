package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Schedule schedule) {
        String sql = "INSERT INTO schedule (TITLE, AUTHOR, PASSWORD, CONTENT, CREATED_AT, UPDATED_AT) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, schedule.getTitle(), schedule.getAuthor(), schedule.getPassword(),
                schedule.getContent(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }
}
