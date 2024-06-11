package com.Chronos.seeder;

import com.Chronos.models.UserTimeZone;
import com.Chronos.repository.TimeZoneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;

@Configuration
public class SeededTimeZones {
    @Bean
    CommandLineRunner seedTimezone(TimeZoneRepository repository){
        LocalTime recordedAt = LocalTime.now();
        return args -> {
              repository.save(new UserTimeZone(null, "New_York", "America", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Los_Angeles", "America", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Chicago", "America", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Phoenix", "America", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Honolulu", "Pacific", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Paris", "Europe", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Berlin", "Europe", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Rome", "Europe", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Shanghai", "Asia", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Tokyo", "Asia", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Jerusalem", "Asia", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Hong_Kong", "Asia", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Johannesburg", "Africa", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Lagos", "Africa", null, null, recordedAt));
            repository.save(new UserTimeZone(null, "Kolkata", "Asia", null, null, recordedAt));
        };
    }
}
