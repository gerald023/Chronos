package com.Chronos.repository;

import com.Chronos.models.ForeignTimeZones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ForeignTimeZoneRepo extends CrudRepository<ForeignTimeZones, Long> {

    public boolean findByTimeZoneID(String id);
}
