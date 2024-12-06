package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleUpdateRequestDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
            @RequestParam(required = false) String updatedDate, // 수정일 필터
            @RequestParam(required = false) String author // 작성자 필터
    ) {
        List<ScheduleResponseDto> schedules = scheduleService.findAllSchedules(updatedDate, author);
        return ResponseEntity.ok(schedules);
    }

    // 단건 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleResponseDto schedule = scheduleService.findScheduleById(id);
        return ResponseEntity.ok(schedule);
    }
    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok("일정이 성공적으로 수정되었습니다.");
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id,
            @RequestParam String password
    ) {
        scheduleService.deleteSchedule(id, password);
        return ResponseEntity.ok("일정이 성공적으로 삭제되었습니다.");
    }
}
