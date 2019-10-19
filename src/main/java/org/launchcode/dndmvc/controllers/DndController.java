package org.launchcode.dndmvc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("dnd")
public class DndController {
    static ArrayList<String> classes = new ArrayList<>();
    @RequestMapping(value = "")


    public String index(Model model){




        model.addAttribute("classes", classes);
        model.addAttribute("title", "Dungeons and Dragons");
        return "dnd/index";
    }

    @RequestMapping(value= "add", method = RequestMethod.GET)
    public String displayCharacters(Model model){
        model.addAttribute("title", "AddClass");
        return "dnd/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processCharacters(@RequestParam String className){
        classes.add(className);
        return "redirect:";
    }
}
