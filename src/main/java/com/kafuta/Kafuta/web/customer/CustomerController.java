package com.kafuta.Kafuta.web.customer;

import com.kafuta.Kafuta.web.area.Area;
import com.kafuta.Kafuta.web.district.District;
import com.kafuta.Kafuta.web.district.DistrictService;
import com.kafuta.Kafuta.web.fuelstation.FuelStation;
import com.kafuta.Kafuta.web.fuelstation.FuelStationService;
import com.kafuta.Kafuta.web.stage.Stage;
import com.kafuta.Kafuta.web.stage.StageService;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService service;
    @Autowired
    private FuelStationService stationService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StageService stageService;

    @GetMapping("/customers")
    public String index(Model model) {
        model.addAttribute("customers", service.getCustomers());
        return "customers/index";
    }

    @GetMapping("/customers/create")
    public String create(Customer customer, Model model){
        model.addAttribute("districts", districtService.getDistricts());
        model.addAttribute("stages", stageService.getStages());
        model.addAttribute("stations", stationService.getFuelStations());
        return "customers/create";
    }

    @PostMapping("/customers/store")
    public String store(@Valid Customer customer, BindingResult result, Model model, RedirectAttributes attributes,
                      @RequestParam int districtId, @RequestParam int stageId, @RequestParam int stationId) {
        log.info("Stage Id =>" + customer);
        if (result.hasErrors()){
            model.addAttribute("districts", districtService.getDistricts());
            model.addAttribute("stages", stageService.getStages());
            model.addAttribute("stations", stationService.getFuelStations());
            return "customers/create";
        }
        //generate secret code base on number plate
        int min = 10; int max = 100000;
        int secretCode = (int)(Math.random() * (max - min + 1) + min);
        customer.setSecretCode(String.valueOf(secretCode));
        customer.setDistrict(new District(districtId, ""));
        customer.setStage(new Stage(stageId, "", "", "", 0));
        customer.setFuelStation(new FuelStation(stationId, "", "", "", "", 0));
        service.createCustomer(customer);
        model.addAttribute("customers", service.getCustomers());
        attributes.addFlashAttribute("message", customer.getFirstName() + " saved successfully");
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Customer customer = service.getCustomer(id).orElseThrow(
                ()->new IllegalArgumentException("Invalid id supplied" + id));
        model.addAttribute("customer", customer);
        model.addAttribute("districts", districtService.getDistricts());
        model.addAttribute("stages", stageService.getStages());
        model.addAttribute("stations", stationService.getFuelStations());
        return "customers/edit";
    }

    @PostMapping("/customers/{id}")
    public String store(@Valid Customer customer, BindingResult result, Model model, RedirectAttributes attributes,
                        @PathVariable int id, @RequestParam String secretCode, @RequestParam int districtId, @RequestParam int stageId, @RequestParam int stationId) {
        log.info("Stage Id =>" + customer);
        if (result.hasErrors()){
            model.addAttribute("customer", customer);
            model.addAttribute("districts", districtService.getDistricts());
            model.addAttribute("stages", stageService.getStages());
            model.addAttribute("stations", stationService.getFuelStations());
            return "customers/edit";
        }
        customer.setSecretCode(secretCode);
        customer.setDistrict(new District(districtId, ""));
        customer.setStage(new Stage(stageId, "", "", "", 0));
        customer.setFuelStation(new FuelStation(stationId, "", "", "", "", 0));
        service.createCustomer(customer);
        model.addAttribute("customers", service.getCustomers());
        attributes.addFlashAttribute("message", customer.getFirstName() + " updated successfully");
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}/delete")
    public String destroy(@PathVariable int id, Model model, RedirectAttributes attributes) {
        service.deleteCustomer(id);
        model.addAttribute("customers", service.getCustomers());
        attributes.addFlashAttribute("message", "Customer deleted successfully");
        return "redirect:/customers";
    }
}
