package com.kafuta.Kafuta.web.stage;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StageRepository extends CrudRepository<Stage, Integer> {
    public List<Stage> findByDistrictId(int districtId);
}
