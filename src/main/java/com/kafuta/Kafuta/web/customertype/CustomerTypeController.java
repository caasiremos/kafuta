package com.kafuta.Kafuta.web.customertype;

import com.kafuta.Kafuta.web.customer.Customer;
import com.kafuta.Kafuta.web.district.District;
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
public class CustomerTypeController {

    @Autowired
    private CustomerTypeService service;

    @RequestMapping("/customer-types")
    public String index(Model model) {
        model.addAttribute("customerTypes", service.getCustomerTypes());
        return "customer_types/index";
    }

    @GetMapping("/customer-types/create")
    public String create(Model model, CustomerType customerType){
        model.addAttribute("customerType", customerType);
        return "customer_types/create";
    }

    @PostMapping("/customer-types/store")
    public String store(@Valid CustomerType customerType, BindingResult result, Model model,
                        RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            model.addAttribute("customerType", customerType);
            return "customer_types/create";
        }
        service.createCustomerType(customerType);
        redirectAttributes.addFlashAttribute("message", customerType.getName() + " saved successfully");
        return "redirect:/customer-types";
    }

    @GetMapping("/customer-types/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        CustomerType customerType = service.getCustomerType(id).orElseThrow(()->
                new IllegalArgumentException("invalid id" + id));
        model.addAttribute("customerType", customerType);
        return "customer_types/edit";
    }

    @PostMapping("/customer-types/{id}")
    public String update(@PathVariable("id") int id, @Valid CustomerType customerType,
                         BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()){
            customerType.setId(id);
            return "customer_types/edit";
        }
        service.updateCustomerTpye(customerType);
        model.addAttribute("districts", service.getCustomerTypes());
        attributes.addFlashAttribute("message", customerType.getName() + " updated successfully");
        return "redirect:/customer-types";
    }

    @GetMapping("/customer-types/{id}/delete")
    public String destroy(@PathVariable("id") int id, Model model){
        service.deleteCustomerType(id);
        model.addAttribute("customerTypes", service.getCustomerTypes());
        return "redirect:/customer-types";
    }
}
