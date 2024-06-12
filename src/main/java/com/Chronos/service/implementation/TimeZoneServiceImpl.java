package com.Chronos.service.implementation;
import com.Chronos.models.ForeignTimeZones;
import com.Chronos.models.UserTimeZone;
import com.Chronos.repository.ForeignTimeZoneRepo;
import com.Chronos.repository.TimeZoneRepository;
import com.Chronos.service.TimeZoneServices;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        timeZone.setTimeZoneID(clock);
        timeZoneRepository.save(timeZone);
        return clock;
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




}
