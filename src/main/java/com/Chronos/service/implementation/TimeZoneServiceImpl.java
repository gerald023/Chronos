package com.Chronos.service.implementation;

import com.Chronos.models.ForeignTimeZones;
import com.Chronos.models.TimeUpdateEmitter;
import com.Chronos.models.UserTimeZone;
import com.Chronos.repository.ForeignTimeZoneRepo;
import com.Chronos.repository.TimeZoneRepository;
import com.Chronos.service.TimeZoneServices;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
//@AllArgsConstructor
@Configuration
public class TimeZoneServiceImpl implements TimeZoneServices {
    private  final TimeZoneRepository timeZoneRepository;
    private  final ForeignTimeZoneRepo foreignTimeZoneRepo;

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public TimeZoneServiceImpl(TimeZoneRepository timeZoneRepository, ForeignTimeZoneRepo foreignTimeZoneRepo) {
        this.timeZoneRepository = timeZoneRepository;
        this.foreignTimeZoneRepo = foreignTimeZoneRepo;
    }


    @Override
    public String localTime() {
        LocalTime currentTime = LocalTime.now();
        UserTimeZone timeZone = timeZoneRepository.findById(1L).get();
        String clock;
        timeZone.setCountry("Local Time");
        timeZone.setRecordedAt(currentTime);
//        timeZone.setUserName("John Doe");

        if (currentTime.getHour() > 11){
             clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "pm";
            timeZone.setTimeZoneID(clock);
            return clock;
        }
        if (currentTime.getHour() == 0){
             clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "u be winch";
            timeZone.setTimeZoneID(clock);
            return clock;
        }
         clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "am";
        timeZone.setTimeZoneID(clock);
        timeZoneRepository.save(timeZone);
        return clock;
    }

    @Override
    public List<UserTimeZone> seededData() {
        List<UserTimeZone> timeZones = Arrays.asList(
//                new UserTimeZone(1L, )
        );
        return null;
    }

    @Override
    public String updateTime(Long id) {
        LocalTime currentTime = LocalTime.now();
        UserTimeZone timeZone = timeZoneRepository.findById(40L).get();
        String clock;
        if (currentTime.getHour() > 11){
            clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "pm";
            timeZone.setTimeZoneID(clock);
            return clock;
        }
        if (currentTime.getHour() == 0){
            clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            timeZone.setTimeZoneID(clock);
            return clock;
        }
        clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "am";
        timeZoneRepository.save(timeZone);
        return clock;
    }

    @Override
    public String getOneTime(Long id) {
        UserTimeZone timeZone = timeZoneRepository.findById(id).get();
        String zoneId = timeZone.getCountry() + "/" + timeZone.getRegion();
        timeZone.setTimeZoneID(zoneId);
        String areaTime = ZonedDateTime.now(ZoneId.of(timeZone.getTimeZoneID())).format(formatter);
        timeZone.setCurrentRegionTime(areaTime);
        timeZoneRepository.save(timeZone);

        ForeignTimeZones foreignTimeZones = new ForeignTimeZones();
        foreignTimeZones.setRegion(timeZone.getRegion());
//        if (Objects.equals(foreignTimeZones.getRegion(), timeZone.getRegion())) {
//            foreignTimeZones.setIsContained(true);
//            return "time already exists";
//        }
//        if (foreignTimeZoneRepo.findByTimeZoneID(foreignTimeZones.getTimeZoneID())) {
//            foreignTimeZones.setIsContained(true);
//            return "time already exists";
//        }
        foreignTimeZones.setCountry(timeZone.getCountry());
        foreignTimeZones.setCurrentRegionTime(timeZone.getCurrentRegionTime());
        foreignTimeZones.setTimeZoneID(timeZone.getTimeZoneID());
        foreignTimeZoneRepo.save(foreignTimeZones);

        return  timeZone.getCurrentRegionTime();
    }

    @Override
    public List<ForeignTimeZones> getAllForeignTime() {

        return (List<ForeignTimeZones>) foreignTimeZoneRepo.findAll();
    }

    public List<ForeignTimeZones> updateForeignTimes(List<ForeignTimeZones> ids){
        for (long i = 0L; i < ids.toArray().length; i++) {
            foreignTimeZoneRepo.findById(i).ifPresent(time ->{
                String foreignTime = ZonedDateTime.now(ZoneId.of(time.getTimeZoneID())).format(formatter);
                time.setCurrentRegionTime(foreignTime);
                foreignTimeZoneRepo.save(time);
            });

        }
        return ids;
    }
    @Override
    public void deleteForeignTime(Long id) {
        foreignTimeZoneRepo.deleteById(id);
    }

    @Override
    public Iterable<UserTimeZone> getAllTimezones() {
        return timeZoneRepository.findAll();
    }


    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    public void start() {
        // Initialize the scheduler or other resources if needed
    }

    @PreDestroy
    public void stop() {
        scheduler.shutdown();
    }

    public AtomicReference<String> startSendingTimeUpdates(TimeUpdateEmitter emitter, TimeZoneRepository repository) {
        AtomicReference<String> time = null;
        scheduler.scheduleAtFixedRate(() -> {
            try {
                LocalTime currentTime = LocalTime.now();
                 String  clock = (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                emitter.send(SseEmitter.event().name("time-update").data(clock));
                time.set(clock);
//                UserTimeZone timeZone = new UserTimeZone(null, "Nigeria", "John", clock, LocalTime.now());
//                repository.save(new UserTimeZone(null, "Gerald", "Nigeria", clock, LocalTime.now()));
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }, 0, 1, TimeUnit.SECONDS);

    return time;
    }



}
