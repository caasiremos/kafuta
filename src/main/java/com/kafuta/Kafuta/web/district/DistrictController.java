package com.kafuta.Kafuta.web.district;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class DistrictController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DistrictService service;

    @GetMapping("/districts")
    public String index(Model model) {
        List<District> districts = service.getDistricts();
        model.addAttribute("districts", districts);
        return "districts/index";
    }

    @GetMapping("/districts/create")
    public String create(District district) {
        return "districts/create";
    }

    @PostMapping("/districts/store")
    public String store(@Valid District district, BindingResult result, Model model, RedirectAttributes redirAttrs) {
        if (result.hasErrors()){
            return "districts/create";
        }
        service.createDistrict(district);
        redirAttrs.addFlashAttribute("message", district.getName() + " saved successfully");
        return "redirect:/districts";
    }

    @GetMapping("/districts/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        District district = service.getDistrict(id).orElseThrow(()->
                new IllegalArgumentException("invalid id" + id));
        model.addAttribute("district", district);
        return "districts/edit";
    }

    @PostMapping("/districts/{id}")
    public String update(@PathVariable("id") int id, @Valid District district,
                       BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()){
            district.setId(id);
            return "districts/edit";
        }
        service.updateDistrict(id, district);
        model.addAttribute("districts", service.getDistricts());
        attributes.addFlashAttribute("message", district.getName() + " updated successfully");
        return "redirect:/districts";
    }

    @GetMapping("/districts/{id}/delete")
    public String destroy(@PathVariable("id") int id, Model model){
        service.deleteDistrict(id);
        model.addAttribute("districts", service.getDistricts());
        return "redirect:/districts";
    }
}
