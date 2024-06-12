package com.Chronos.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class ForeignTimeZones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String region;
    private String country;
    private String timeZoneID;
    private Boolean isContained;
    private String currentRegionTime;
    public ForeignTimeZones(Long id, String region, String country, String timeZoneID, String currentRegionTime) {
        this.id = id;
        this.region = region;
        this.country = country;
        this.timeZoneID = timeZoneID;
        this.currentRegionTime = currentRegionTime;
    }
}
