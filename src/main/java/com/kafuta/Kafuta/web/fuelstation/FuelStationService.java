package com.kafuta.Kafuta.web.fuelstation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelStationService {
    @Autowired
    private FuelStationRepository repository;

    public List<FuelStation> getFuelStations(){
        ArrayList<FuelStation> stations = new ArrayList<>();
        repository.findAll().forEach(stations::add);
        return stations;
    }

    public Optional<FuelStation> getFuelStation(int id){
        return repository.findById(id);
    }

    public void createFuelStation(FuelStation station){
         repository.save(station);
    }

    public void updateFuelStation(FuelStation station){
         repository.save(station);
    }

    public void deleteFuelStation(int id){
        repository.deleteById(id);
    }
}
