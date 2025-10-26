package com.neuromed.appointments.controller;

import com.neuromed.appointments.dto.ScheduleDTO;
import com.neuromed.appointments.service.ITimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-slots")
@RequiredArgsConstructor
public class TimeSlotController {

    private final ITimeSlotService timeSlotService;

    @PostMapping("/create-schedule")
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO dto) {
        return ResponseEntity.ok(timeSlotService.createSchedule(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(timeSlotService.getSchedule(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleDTO>> getSchedules() {
        return ResponseEntity.ok(timeSlotService.getSchedules());
    }

    @PutMapping("/{slotId}/availability")
    public ResponseEntity<Void> updateAvailability(@PathVariable Long slotId, @RequestParam boolean available) {
        timeSlotService.updateSlotAvailability(slotId, available);
        return ResponseEntity.noContent().build();
    }
}
