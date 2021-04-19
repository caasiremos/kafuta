package com.kafuta.Kafuta.web.fuelstation;

import com.kafuta.Kafuta.web.area.Area;
import com.kafuta.Kafuta.web.district.District;
import com.kafuta.Kafuta.web.district.DistrictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class FuelStationController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FuelStationService service;
    @Autowired
    private DistrictService districtService;

    @GetMapping("/stations")
    public String index(Model model){
        model.addAttribute("stations", service.getFuelStations());
        return "stations/index";
    }

    @GetMapping("/stations/create")
    public String create(FuelStation fuelStation, Model model){
        model.addAttribute("districts", districtService.getDistricts());
        model.addAttribute("station", fuelStation);
        return "stations/create";
    }

    @PostMapping("/stations/store")
    public String store(@Valid FuelStation station, BindingResult result, @RequestParam int districtId, Model model,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            model.addAttribute("districts", districtService.getDistricts());
            return "stations/create";
        }
        station.setDistrict(new District(districtId, ""));
        service.createFuelStation(station);
        model.addAttribute("stations", service.getFuelStations());
        redirectAttributes.addFlashAttribute("message", station.getName() + " saved successfully");
        return "redirect:/stations";
    }

    @GetMapping("/stations/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        FuelStation fuelStation = service.getFuelStation(id).orElseThrow(
                ()->new IllegalArgumentException("Invalid id supplied" + id));
        model.addAttribute("fuelStation", fuelStation);
        model.addAttribute("districts", districtService.getDistricts());
        return "stations/edit";
    }

    @PostMapping("/stations/{id}")
    public String update(@Valid FuelStation fuelStation, @PathVariable int id, Model model,
                         BindingResult result, @RequestParam int districtId, RedirectAttributes attributes) {
        if (result.hasErrors()){
            fuelStation.setId(id);
            model.addAttribute("districts", districtService.getDistricts());
            return "stations/edit";
        }
        fuelStation.setDistrict(new District(districtId, ""));
        service.createFuelStation(fuelStation);
        attributes.addFlashAttribute("message", "Station updated successfully");
        model.addAttribute("stations", service.getFuelStations());
        return "redirect:/stations";
    }

    @GetMapping("stations/{id}/delete")
    public String destroy(@PathVariable int id, Model model, RedirectAttributes attributes) {
        service.deleteFuelStation(id);
        model.addAttribute("areas", service.getFuelStations());
        attributes.addFlashAttribute("message", "Station deleted successfully");
        return "redirect:/stations";
    }
}
