package com.Chronos.service;

import com.Chronos.models.ForeignTimeZones;
import com.Chronos.models.UserTimeZone;

import java.util.List;

public interface TimeZoneServices {
    public String localTime();
    public String updateTime(Long id);
    public String getOneTime(Long id);

    public List<ForeignTimeZones> getAllForeignTime();
    public void deleteForeignTime(Long id);
    public Iterable<UserTimeZone> getAllTimezones();
}
