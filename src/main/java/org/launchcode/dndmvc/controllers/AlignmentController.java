package org.launchcode.dndmvc.controllers;

import org.launchcode.dndmvc.models.Alignment;
import org.launchcode.dndmvc.models.data.AlignmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("alignment")
public class AlignmentController {
    @Autowired
    private AlignmentDao alignmentDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Alignment");
        model.addAttribute("alignment", alignmentDao.findAll());
        return "alignment/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Alignment());
        model.addAttribute("title", "Add Alignment");
        return "alignment/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Alignment alignment, Errors errors) {
        if (errors.hasErrors()) {
            return "alignment/add";
        }
        else {
            alignmentDao.save(alignment);
            return "redirect:/alignment";
        }
    }
}
