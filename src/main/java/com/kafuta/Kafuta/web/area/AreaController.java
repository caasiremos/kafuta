package com.kafuta.Kafuta.web.area;

import com.kafuta.Kafuta.web.district.District;
import com.kafuta.Kafuta.web.district.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AreaController {
    @Autowired
    private AreaService service;
    @Autowired
    private DistrictService districtService;

    @GetMapping("/areas")
    public String index(Model model) {
        model.addAttribute("areas", service.getOperationAreas());
        return "operation_areas/index";
    }

    @GetMapping("/areas/create")
    public String create(Area area, Model model){
        model.addAttribute("districts", districtService.getDistricts());
        return "operation_areas/create";
    }

    @PostMapping("/areas/store")
    public String store(@Valid Area area, BindingResult result, @RequestParam int districtId, Model model,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            model.addAttribute("districts", districtService.getDistricts());
            return "operation_areas/create";
        }
        area.setDistrict(new District(districtId, ""));
        service.createOperationArea(area);
        model.addAttribute("areas", service.getOperationAreas());
        redirectAttributes.addFlashAttribute("message", area.getName() + " saved successfully");
        return "redirect:/areas";
    }

    @GetMapping("/areas/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Area area = service.getOperationArea(id).orElseThrow(
                ()->new IllegalArgumentException("Invalid id supplied" + id));
        model.addAttribute("area", area);
        model.addAttribute("districts", districtService.getDistricts());
        return "operation_areas/edit";
    }

    @PostMapping("/areas/{id}")
    public String update(@Valid Area area, @PathVariable int id, Model model,
                         BindingResult result, @RequestParam int districtId, RedirectAttributes attributes) {
        if (result.hasErrors()){
            area.setId(id);
            model.addAttribute("districts", districtService.getDistricts());
            return "operation_areas/edit";
        }
        area.setDistrict(new District(districtId, ""));
        service.createOperationArea(area);
        attributes.addFlashAttribute("message", "area updated successfully");
        model.addAttribute("areas", service.getOperationAreas());
        return "redirect:/areas";
    }

    @GetMapping("areas/{id}/delete")
    public String destroy(@PathVariable int id, Model model, RedirectAttributes attributes) {
        service.deleteOperationArea(id);
        model.addAttribute("areas", service.getOperationAreas());
        attributes.addFlashAttribute("message", "Area deleted successfully");
        return "redirect:/areas";
    }
}
