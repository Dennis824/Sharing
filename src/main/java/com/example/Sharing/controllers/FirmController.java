package com.example.Sharing.controllers;

import com.example.Sharing.model.Firm;
import com.example.Sharing.services.FirmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/firms")
@PreAuthorize("hasAuthority('ADMIN')")
public class FirmController {
    private final FirmService firmService;

    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", firmService.findAll());
        return "firms/list";
    }

    @GetMapping(value = "/new")
    public String addType(Model model) {
        model.addAttribute("object", new Firm());
        return "firms/new";
    }


    @PostMapping("add")
    public String addType(@Valid @ModelAttribute("object") Firm object, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "firms/new";
        }
        try {
            firmService.save(object);
        } catch (Exception e) {
            bindingResult.rejectValue("name", "error.name", e.getMessage());
            return "firms/new";
        }
        return "redirect:/admin/firms";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", firmService.findById(id));
        return "firms/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("object") Firm object, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "firms/edit";
        }
        try {
            firmService.save(object);
        } catch (Exception e) {
            bindingResult.rejectValue("name", "error.name", e.getMessage());
            return "firms/edit";
        }
        return "redirect:/admin/firms";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", firmService.findById(id));
        return "firms/delete";
    }

    @DeleteMapping("/delete")
    public String delete(Firm object) {
        firmService.delete(object);
        return "redirect:/admin/firms";
    }

}
