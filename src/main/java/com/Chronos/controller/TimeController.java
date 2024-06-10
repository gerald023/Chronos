package com.Chronos.controller;

import com.Chronos.models.TimeUpdateEmitter;
import com.Chronos.repository.TimeZoneRepository;
import com.Chronos.service.implementation.TimeZoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api")
public class TimeController {
@Autowired
private TimeZoneServiceImpl timeZoneService;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE - MMMM - yyyy");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private  TimeZoneRepository repository;
//    @GetMapping("/times")
//    public String getCurrentTime() {
//        LocalTime currentTime = LocalTime.now();
//
//        if (currentTime.getHour() > 11){
//            String clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "pm";
//            return clock;
//        }
//        if (currentTime.getHour() == 0){
//            String clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "u be winch";
//            return clock;
//        }
//        String clock = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "am";
//        return clock;
//    }
    @PutMapping
    public String updateNewTime(){
        return timeZoneService.updateTime(40L);
    }
    @GetMapping("/date")
    public String getToday(){
        LocalDate today = LocalDate.now();
        return today.format(dateFormat);
    }
        @PutMapping("/foreignTime/{id}")
    public String getOneTime(@PathVariable Long id){
        return timeZoneService.getOneTime(id);
    }

//    @GetMapping("/clock")
//    public TimeUpdateEmitter streamTimeUpdates() {
//        TimeUpdateEmitter emitter = new TimeUpdateEmitter();
//        timeZoneService.startSendingTimeUpdates(emitter, repository);
//        return emitter;
//    }

    @PutMapping("/localTime")
    public String localTime(){
        return timeZoneService.localTime();
    }

    @GetMapping("/newTime/{id}")
    public String getNewTime(@PathVariable Long id){
        return timeZoneService.getOneTime(id);
    }

    @GetMapping("/clock")
    public AtomicReference<String> updateTime(){
        TimeUpdateEmitter emitter = new TimeUpdateEmitter();

        return timeZoneService.startSendingTimeUpdates(emitter, repository);
    }


}
