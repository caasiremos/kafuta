package com.kafuta.Kafuta.web.fuelstation;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuelStationRepository extends CrudRepository<FuelStation, Integer> {
    List<FuelStation> findByDistrictId(int districtId);
}
