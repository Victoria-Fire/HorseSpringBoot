package by.academy.it.reunova.horseSpringBoot.controllers;

import by.academy.it.reunova.horseSpringBoot.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HorseController {

    private final HorseService horseService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/horse")
    public String horseMain(Model model) {
        model.addAttribute("horses", horseService.findAllHorses());
        return "horse-main";
    }

    @GetMapping("/horse/add")
    public String horseAdd(Model model) {
        return "horse-add";
    }

    @PostMapping("/horse/add")
    public String horsePostAdd(@RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {
        horseService.createHorse(type, Integer.valueOf(age), Integer.valueOf(price));
        return "redirect:/horse";
    }


    @GetMapping("/horse/{id}/update")
    public String horseUpdate(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("horse", horseService.findHorseById(id));
        return "horse-update";
    }

    @PostMapping("/horse/{id}/update")
    public String horsePostUpdate(@PathVariable(value = "id") int id, @RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {
        horseService.updateHorse(id, type, Integer.valueOf(age), Integer.valueOf(price));
        return "redirect:/horse";
    }

    @PostMapping("/horse/{id}/delete")
    public String horsePostDelete(@PathVariable(value = "id") int id, Model model) {
        horseService.deleteHorse(id);
        return "redirect:/horse";
    }
}
