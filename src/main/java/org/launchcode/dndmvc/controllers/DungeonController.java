package org.launchcode.dndmvc.controllers;

import org.launchcode.dndmvc.models.Dungeon;
import org.launchcode.dndmvc.models.data.DungeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/dungeons/")


public class DungeonController {

    private final DungeonRepository dungeonRepository;

    @Autowired
    public DungeonController(DungeonRepository dungeonRepository) {
        this.dungeonRepository = dungeonRepository;
    }

    @GetMapping("create")
    public String showCharacterForm(Dungeon dungeon) {
        return "add";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("dungeons", dungeonRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addCharacter(@Valid Dungeon dungeon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add";
        }

        dungeonRepository.save(dungeon);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Dungeon dungeon = dungeonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid character Id:" + id));
        model.addAttribute("dungeon", dungeon);
        return "update-student";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Dungeon dungeon, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            dungeon.setId(id);
            return "update-student";
        }

        dungeonRepository.save(dungeon);
        model.addAttribute("dungeons", dungeonRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Dungeon dungeon = dungeonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        dungeonRepository.delete(dungeon);
        model.addAttribute("dungeons", dungeonRepository.findAll());
        return "index";
    }
}
