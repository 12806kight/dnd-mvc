package org.launchcode.dndmvc.controllers;

import org.launchcode.dndmvc.models.Races;
import org.launchcode.dndmvc.models.data.RacesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("races")
public class RacesController {

    @Autowired
    private RacesDao racesDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Races");
        model.addAttribute("races", racesDao.findAll());
        return "races/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Races());
        model.addAttribute("title", "Add Class");
        return "races/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Races races, Errors errors) {
        if (errors.hasErrors()) {
            return "races/add";
        }
        else {
            racesDao.save(races);
            return "redirect:/races";
        }
    }
}

