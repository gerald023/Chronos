package com.Chronos.controller;

import com.Chronos.models.UserTimeZone;
import com.Chronos.service.TimeZoneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class TimeZoneController {

    private final TimeZoneServices timeZoneService;

    public TimeZoneController(TimeZoneServices timeZoneService) {
        this.timeZoneService = timeZoneService;
    }

    @GetMapping
    public String getTimeZoneView(Model model) {
        Iterable<UserTimeZone> timeZones = timeZoneService.getAllTimezones();
        model.addAttribute("timezones", timeZones);
        return "timezones";
    }

}
