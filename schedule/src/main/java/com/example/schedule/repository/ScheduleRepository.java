package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // 일정 저장
    public void save(Schedule schedule) {
        String sql = "INSERT INTO schedule (TITLE, AUTHOR, PASSWORD, CONTENT, CREATED_AT, UPDATED_AT) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, schedule.getTitle(), schedule.getAuthor(), schedule.getPassword(),
                schedule.getContent(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    //전체 일정조회
    public List<Schedule> findAll(String updatedDate, String author) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1=1");

        if (updatedDate != null) {
            sql.append(" AND DATE(UPDATED_AT) = '").append(updatedDate).append("'");
        }

        if (author != null) {
            sql.append(" AND AUTHOR = '").append(author).append("'");
        }

        sql.append(" ORDER BY UPDATED_AT DESC");

        return jdbcTemplate.query(sql.toString(), new ScheduleRowMapper());
    }

    // 단건 일정 조회
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE ID = ?";
        List<Schedule> schedules = jdbcTemplate.query(sql, new ScheduleRowMapper(), id);
        return schedules.stream().findFirst();
    }


    // 일정 수정
    public void update(Long id, String title, String author, LocalDateTime updatedAt) {
        String sql = "UPDATE schedule SET TITLE = ?, AUTHOR = ?, UPDATED_AT = ? WHERE ID = ?";
        jdbcTemplate.update(sql, title, author, updatedAt, id);
    }

    // 일정 삭제
    public void deleteById(Long id) {
        String sql = "DELETE FROM schedule WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }


    private static class ScheduleRowMapper implements RowMapper<Schedule> {
        @Override
        public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
            Schedule schedule = new Schedule(
                    rs.getString("TITLE"),
                    rs.getString("AUTHOR"),
                    rs.getString("PASSWORD"),
                    rs.getString("CONTENT"),
                    rs.getTimestamp("CREATED_AT").toLocalDateTime()
            );
            schedule.setId(rs.getLong("ID"));
            schedule.setUpdatedAt(rs.getTimestamp("UPDATED_AT").toLocalDateTime());
            return schedule;
        }
    }

}
