package org.launchcode.dndmvc.controllers;

import org.launchcode.dndmvc.models.Classes;
import org.launchcode.dndmvc.models.Dungeon;
import org.launchcode.dndmvc.models.Races;
import org.launchcode.dndmvc.models.data.ClassesDao;
import org.launchcode.dndmvc.models.data.DungeonRepository;
import org.launchcode.dndmvc.models.data.RacesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/dungeons/")
public class DungeonController {

    private final DungeonRepository dungeonRepository;

    @Autowired
    private ClassesDao classesDao;

    @Autowired
    private RacesDao racesDao;


    @Autowired
    public DungeonController(DungeonRepository dungeonRepository) {
        this.dungeonRepository = dungeonRepository;
    }

    @GetMapping("create")
    public String showSignUpForm(Model model) {
        model.addAttribute("title","Add Character");
        model.addAttribute(new Dungeon());
        model.addAttribute("classes", classesDao.findAll());
        model.addAttribute("races", racesDao.findAll());
        return "dungeons/add";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("dungeons", dungeonRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addStudent(@Valid Dungeon dungeon, BindingResult result, @RequestParam long classesId,@RequestParam long racesId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title","Add Character");
            return "dungeons/add";
        }

        Classes classes = classesDao.findById(classesId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + classesId));
        dungeon.setClasses(classes);
        Races races = racesDao.findById(racesId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + racesId));
        dungeon.setRaces(races);
        dungeonRepository.save(dungeon);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Dungeon dungeon = dungeonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + id));
        model.addAttribute("dungeon", dungeon);
        model.addAttribute("classes", classesDao.findAll());
        model.addAttribute("races", racesDao.findAll());
        return "dungeons/update";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Dungeon dungeon, BindingResult result,  @RequestParam long classesId,@RequestParam long racesId,
                                Model model) {
        if (result.hasErrors()) {
            dungeon.setId(id);
            return "dungeons/update";
        }

        Classes classes = classesDao.findById(classesId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + classesId));
        dungeon.setClasses(classes);
        Races races = racesDao.findById(racesId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + racesId));
        dungeon.setRaces(races);
        dungeonRepository.save(dungeon);
        model.addAttribute("dungeons", dungeonRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Dungeon dungeon = dungeonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dungeon Id:" + id));
        dungeonRepository.delete(dungeon);
        model.addAttribute("dungeons", dungeonRepository.findAll());
        return "index";
    }
}