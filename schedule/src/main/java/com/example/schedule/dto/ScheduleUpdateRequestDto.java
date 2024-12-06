package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private String title; // 수정할 할일
    private String author; // 수정할 작성자명
    private String password; // 일정 비밀번호 (검증용)
}
