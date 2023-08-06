package com.example.Sharing.controllers;

import com.example.Sharing.model.Type;
import com.example.Sharing.services.TypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/types")
@PreAuthorize("hasAuthority('ADMIN')")
public class TypeController {
    private final TypeService service;

    public TypeController(TypeService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.findAll());
        return "types/list";
    }

    @GetMapping(value = "/new")
    public String addType(Model model) {
        model.addAttribute("object", new Type());
        return "types/new";
    }


    @PostMapping("add")
    public String addType(@Valid @ModelAttribute("object") Type object, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "types/new";
        }
        try {
            service.save(object);
        } catch (Exception e) {
            bindingResult.rejectValue("name", "error.name", e.getMessage());
            return "types/new";
        }
        model.addAttribute("success","Тип '"+object.getName()+"' added successfully");
        return "types/new";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", service.findById(id));
        return "types/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("object") Type object, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "types/edit";
        }
        try {
            service.save(object);
        } catch (Exception e) {
            bindingResult.rejectValue("name", "error.name", e.getMessage());
            return "types/edit";
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", service.findById(id));
        return "types/delete";
    }

    @DeleteMapping("/delete")
    public String delete(Type object) {
        service.delete(object);
        return "redirect:/admin/types";
    }

}