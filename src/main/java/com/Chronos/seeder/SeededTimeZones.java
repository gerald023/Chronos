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

//            repository.save(new UserTimeZone("Nigeria", "Niger", null));
//            repository.save(new UserTimeZone( "Uk", "20:30:03", null));
//            repository.save(new UserTimeZone("Russia", "20:30:40", null));
//            repository.save(new UserTimeZone("USA", "02:50:40", null));
        };
    }
}
