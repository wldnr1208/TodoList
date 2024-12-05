package com.example.schedule.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {
    private Long id; // 고유 식별자
    private String title; // 할 일
    private String author; // 작성자명
    private String password; // 비밀번호
    private String content; // 내용
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일

    public Schedule(String title, String author, String password, String content, LocalDateTime createdAt) {
        this.title = title;
        this.author = author;
        this.password = password;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = createdAt; // 최초 작성 시 수정일 = 작성일
    }
}
