package com.Chronos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "timeZones")
public class UserTimeZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String region;
    private String country;
    private String timeZoneID;
    private String currentRegionTime;
    private LocalTime recordedAt;
    public UserTimeZone(Long id, String region, String country, String timeZoneID, String currentRegionTime, LocalTime recordedAt) {
        this.id = id;
        this.region = region;
        this.country = country;
        this.timeZoneID = timeZoneID;
        this.currentRegionTime = currentRegionTime;
        this.recordedAt = recordedAt;
    }
}
