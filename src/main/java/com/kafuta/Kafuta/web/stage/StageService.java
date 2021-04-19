package com.kafuta.Kafuta.web.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StageService {
    @Autowired
    private StageRepository repository;

    public List<Stage> getStages(){
        ArrayList<Stage> stages = new ArrayList<>();
        repository.findAll().forEach(stages::add);
        return stages;
    }

    public Optional<Stage> getStage(int id){
        return repository.findById(id);
    }

    public void createStage(Stage stage){
         repository.save(stage);
    }

    public void updateStage(Stage stage){
         repository.save(stage);
    }

    public void deleteStage(int id){
        repository.deleteById(id);
    }
}
