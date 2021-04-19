package com.kafuta.Kafuta.web.area;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AreaRepository extends CrudRepository<Area, Integer> {
    public List<Area> findByDistrictId(int districtId);
}
