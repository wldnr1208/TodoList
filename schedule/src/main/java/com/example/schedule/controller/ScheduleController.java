package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        scheduleService.createSchedule(requestDto);
        return ResponseEntity.ok("일정이 성공적으로 생성되었습니다.");
    }
}
