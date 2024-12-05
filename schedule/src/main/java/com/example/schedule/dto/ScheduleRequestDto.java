package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title; // 할 일
    private String author; // 작성자명
    private String password; // 비밀번호
    private String content; // 내용
}
