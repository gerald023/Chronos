package com.Chronos.service;

import com.Chronos.models.UserTimeZone;

import java.util.List;

public interface TimeZoneServices {
    public String localTime();
    List<UserTimeZone> seededData();
    public String updateTime(Long id);
    public String getOneTime(Long id);
    public Iterable<UserTimeZone> getAllTimezones();
}
