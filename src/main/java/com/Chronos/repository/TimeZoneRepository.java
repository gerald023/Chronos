package com.Chronos.repository;

import com.Chronos.models.UserTimeZone;
import org.springframework.data.repository.CrudRepository;

import java.util.TimeZone;

public interface TimeZoneRepository extends CrudRepository<UserTimeZone, Long> {
}
