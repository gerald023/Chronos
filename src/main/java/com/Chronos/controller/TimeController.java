package com.Chronos.controller;
import com.Chronos.models.ForeignTimeZones;
import com.Chronos.repository.TimeZoneRepository;
import com.Chronos.service.implementation.TimeZoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@RestController
@RequestMapping("/api")
public class TimeController {
@Autowired
private TimeZoneServiceImpl timeZoneService;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE - MMMM - yyyy");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private  TimeZoneRepository repository;
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


    @PutMapping("/updateAllForeignTime")
    public List<ForeignTimeZones> updateAllForeignTime(){
        List<ForeignTimeZones> ids = timeZoneService.getAllForeignTime();
        return timeZoneService.updateForeignTimes(ids);
    }
    @GetMapping("/allForeign")
    public List<ForeignTimeZones> getAllForeignTime(){

        return timeZoneService.getAllForeignTime();
    }



    @DeleteMapping("/delete/{id}")
    public void deleteForeignTime(@PathVariable Long id){
        timeZoneService.deleteForeignTime(id);
    }
    @PutMapping("/localTime")
    public String localTime(){
        return timeZoneService.localTime();
    }

    @GetMapping("/newTime/{id}")
    public String getNewTime(@PathVariable Long id){
        return timeZoneService.getOneTime(id);
    }



}
