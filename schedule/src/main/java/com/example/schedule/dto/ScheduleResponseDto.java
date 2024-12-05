package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private String createdAt; // 작성일 (YYYY-MM-DD 형식)
    private String updatedAt; // 수정일 (YYYY-MM-DD 형식)

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.author = schedule.getAuthor();
        this.content = schedule.getContent();
        this.createdAt = formatDate(schedule.getCreatedAt());
        this.updatedAt = formatDate(schedule.getUpdatedAt());
    }

    private String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
    }
}
