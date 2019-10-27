package org.launchcode.dndmvc.controllers;

import org.launchcode.dndmvc.models.Dnd;

import org.launchcode.dndmvc.models.DndData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("dnd")
public class DndController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", DndData.getAll());
        model.addAttribute("title", "Classes");

        return "dnd/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Class");
        model.addAttribute(new Dnd());
        return "dnd/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Dnd newDnd,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Class");
            return "dnd/add";
        }

        DndData.add(newDnd);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", DndData.getAll());
        model.addAttribute("title", "Remove Class");
        return "dnd/remove";
    }



}
