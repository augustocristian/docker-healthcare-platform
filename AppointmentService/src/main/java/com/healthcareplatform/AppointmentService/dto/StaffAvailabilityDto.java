package com.healthcareplatform.AppointmentService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffAvailabilityDto {
    private Long staffId;
    private String date;
    private boolean available;
    private List<String> availableSlots;
}
