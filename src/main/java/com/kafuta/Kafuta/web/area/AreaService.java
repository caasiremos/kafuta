package com.kafuta.Kafuta.web.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaService {
    @Autowired
    AreaRepository repository;

    public List<Area> getOperationAreas(){
        List<Area> areas = new ArrayList<>();
        repository.findAll().forEach(areas::add);
        return areas;
    }

    public Optional<Area> getOperationArea(int id){
        return repository.findById(id);
    }

    public void createOperationArea(Area area){
        repository.save(area);
    }

    public void updateOperationArea(Area area){
        repository.save(area);
    }

    public void deleteOperationArea(int areaId){
        repository.deleteById(areaId);
    }
}
