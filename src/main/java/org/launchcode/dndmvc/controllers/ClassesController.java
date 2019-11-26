package org.launchcode.dndmvc.controllers;

import org.launchcode.dndmvc.models.Classes;
import org.launchcode.dndmvc.models.data.ClassesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("classes")
public class ClassesController {
    @Autowired
    private ClassesDao classesDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Classes");
        model.addAttribute("classes", classesDao.findAll());
        return "classes/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Classes());
        model.addAttribute("title", "Add Class");
        return "classes/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Classes classes, Errors errors) {
        if (errors.hasErrors()) {
            return "classes/add";
        }
        else {
            classesDao.save(classes);
            return "redirect:/classes";
        }
    }
}