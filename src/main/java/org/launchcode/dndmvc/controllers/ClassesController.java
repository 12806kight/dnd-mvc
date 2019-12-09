package org.launchcode.dndmvc.controllers;

import org.launchcode.dndmvc.models.ClassFeatures;
import org.launchcode.dndmvc.models.Classes;
import org.launchcode.dndmvc.models.Subclasses;
import org.launchcode.dndmvc.models.data.ClassFeaturesDao;
import org.launchcode.dndmvc.models.data.ClassesDao;
import org.launchcode.dndmvc.models.data.SubclassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("classes")
public class ClassesController {
    @Autowired
    private ClassesDao classesDao;

    @Autowired
    private SubclassDao subclassDao;

    @Autowired
    private ClassFeaturesDao classFeaturesDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Classes");
        model.addAttribute("classes", classesDao.findAll());
        return "classes/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Classes());
        model.addAttribute("title", "Add Class");
        return "classes/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Classes classes, Errors errors) {
        if (errors.hasErrors()) {
            return "classes/add";
        } else {
            classesDao.save(classes);
            return "redirect:/classes";
        }
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Classes classes = classesDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + id));
        model.addAttribute("classes", classes);
        return "classes/update";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Classes classes, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            classes.setId(id);
            return "classes/update";
        }


        classesDao.save(classes);
        model.addAttribute("classes", classesDao.findAll());
        return "classes/index";
    }


    @GetMapping("description/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model, Subclasses subclasses) {
        Classes classes = classesDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid classes Id:" + id));
        model.addAttribute("classes", classes);
        model.addAttribute("subclasses", subclassDao.findByClasses(classes));
        model.addAttribute("classFeatures", classFeaturesDao.findByClasses(classes));
        return "classes/description";
    }



    @RequestMapping(value = "subclassAdd", method = RequestMethod.GET)
    public String subclassAdd(Model model) {
        model.addAttribute(new Subclasses());
        model.addAttribute("title", "Add Subclass");
        model.addAttribute("classes", classesDao.findAll());
        return "classes/subclassAdd";
    }

    @RequestMapping(value = "subclassAdd", method = RequestMethod.POST)
    public String addStudent(@Valid Subclasses subclasses, BindingResult result, @RequestParam long classesId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title","Add Character");
            return "classes/subclassAdd";
        }

        Classes classes = classesDao.findById(classesId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + classesId));
        subclasses.setClasses(classes);
        subclassDao.save(subclasses);
        return "redirect:/classes";
    }


    @RequestMapping(value = "classFeatureAdd", method = RequestMethod.GET)
    public String classFeatureAdd(Model model) {
        model.addAttribute(new ClassFeatures());
        model.addAttribute("title", "Add Class Features");
        model.addAttribute("classes", classesDao.findAll());
        return "classes/classFeatureAdd";
    }

    @RequestMapping(value = "classFeatureAdd", method = RequestMethod.POST)
    public String classFeatureAdded(@Valid ClassFeatures classFeatures, BindingResult result, @RequestParam long classesId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title","Add Class Feature");
            return "classes/classFeatureAdd";
        }

        Classes classes = classesDao.findById(classesId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + classesId));
        classFeatures.setClasses(classes);
        classFeaturesDao.save(classFeatures);
        return "redirect:/classes";
    }


    @GetMapping("subclass/{id}")
    public String subclassPage(@PathVariable("id") long id, Model model) {
        Subclasses subclasses = subclassDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subclasses Id:" + id));
        model.addAttribute("subclasses", subclasses);
        return "classes/subclass";
    }


}