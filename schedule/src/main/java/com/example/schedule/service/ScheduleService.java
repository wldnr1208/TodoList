package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
