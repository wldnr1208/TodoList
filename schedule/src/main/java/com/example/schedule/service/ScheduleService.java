package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public void createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getAuthor(),
                requestDto.getPassword(),
                requestDto.getContent(),
                LocalDateTime.now()
        );

        scheduleRepository.save(schedule);
    }
    // 전체 일정 조회
    public List<ScheduleResponseDto> findAllSchedules(String updatedDate, String author) {
        List<Schedule> schedules = scheduleRepository.findAll(updatedDate, author);
        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단건 일정 조회
    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        Schedule schedule = scheduleOptional.orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다."));
        return new ScheduleResponseDto(schedule);
    }
}
