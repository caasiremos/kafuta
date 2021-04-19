package com.kafuta.Kafuta.web.district;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository repository;

    public List<District> getDistricts(){
        ArrayList<District> districts = new ArrayList<>();
        repository.findAll().forEach(districts::add);
        return districts;
    }

    public Optional<District> getDistrict(int id){
        return repository.findById(id);
    }

    public void createDistrict(District district){
        repository.save(district);
    }

    public void updateDistrict(int id, District district) {

        repository.save(district);
    }

    public void deleteDistrict (int id) {
        repository.deleteById(id);
    }
}
