package com.Chronos.repository;

import com.Chronos.models.ForeignTimeZones;
import org.springframework.data.repository.CrudRepository;

public interface ForeignTimeZoneRepo extends CrudRepository<ForeignTimeZones, Long> {
    public boolean findByTimeZoneID(String id);
}
