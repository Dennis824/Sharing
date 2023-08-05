package com.example.Sharing.controllers;

import com.example.Sharing.model.Notification;
import com.example.Sharing.model.User;
import com.example.Sharing.services.NotificationService;
import com.example.Sharing.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class NotificationController {

    final NotificationService service;

    final UserService userService;

    public NotificationController(NotificationService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("profile/notifications")
    @PreAuthorize("hasAuthority('USER')")
    public String list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("list", service.findByUser(user.getId()));
        return "notifications/list";
    }

    @GetMapping("/profile/notifications/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("object", service.findById(id));
        return "notifications/edit";
    }

    @PostMapping("/profile/notifications/edit")
    public String edit(@Valid @ModelAttribute("object") Notification object, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "notifications/edit";
        }
        try {
            service.save(object);
        }catch (Exception e){
            bindingResult.rejectValue("name", "error.name", e.getMessage());
            return "notifications/edit";
        }
        return "redirect:/profile/notifications";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", service.findById(id));
        return "notifications/delete";
    }

    @DeleteMapping("/delete")
    public String delete(Notification object) {
        service.delete(object);
        return "redirect:/profile/notification";
    }

}