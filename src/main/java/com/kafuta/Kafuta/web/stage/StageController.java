package com.kafuta.Kafuta.web.stage;

import com.kafuta.Kafuta.web.district.District;
import com.kafuta.Kafuta.web.district.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StageController {
    @Autowired
    private StageService service;
    @Autowired
    private DistrictService districtService;

    @GetMapping("/stages")
    public String index(Model model) {
        List<Stage> stages = service.getStages();
        model.addAttribute("stages", stages);
        return "stages/index";
    }

    @GetMapping("/stages/create")
    public String create(Stage stage, Model model) {
        model.addAttribute("districts", districtService.getDistricts());
        return "/stages/create";
    }

    @GetMapping("/stages/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Stage stage = service.getStage(id).orElseThrow(
                ()->new IllegalArgumentException("Invalid id supplied" + id));
        model.addAttribute("stage", stage);
        model.addAttribute("districts", districtService.getDistricts());
        return "stages/edit";
    }

    @PostMapping("/stages/store")
        public String store(@Valid Stage stage, BindingResult result, @RequestParam int districtId, Model model,
                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("districts", districtService.getDistricts());
            return "stages/create";
        }
        stage.setDistrict(new District(districtId, ""));
        service.createStage(stage);
        model.addAttribute("stages", service.getStages());
        redirectAttributes.addFlashAttribute("message", stage.getName() + " saved successfully");
        return "redirect:/stages";
    }

    @PostMapping("/stages/{id}")
    public String update(@Valid Stage stage, @PathVariable int id, Model model,
                         BindingResult result, @RequestParam int districtId, RedirectAttributes attributes) {
        if (result.hasErrors()){
            stage.setId(id);
            model.addAttribute("districts", districtService.getDistricts());
            return "stages/edit";
        }
        stage.setDistrict(new District(districtId, ""));
        service.createStage(stage);
        attributes.addFlashAttribute("message", "stage updated successfully");
        model.addAttribute("areas", service.getStages());
        return "redirect:/stages";
    }

    @GetMapping("stages/{id}/delete")
    public String destroy(@PathVariable int id, Model model, RedirectAttributes attributes) {
        service.deleteStage(id);
        model.addAttribute("stages", service.getStages());
        attributes.addFlashAttribute("message", "Stage deleted successfully");
        return "redirect:/stages";
    }
}
