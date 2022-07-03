package by.academy.it.reunova.horseSpringBoot.controllers;

import by.academy.it.reunova.horseSpringBoot.repository.entity.Horse;
import by.academy.it.reunova.horseSpringBoot.service.filter.HorseFilter;
import by.academy.it.reunova.horseSpringBoot.service.interfaces.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static by.academy.it.reunova.horseSpringBoot.controllers.constants.Constants.*;

@Controller
@RequiredArgsConstructor
public class HorseController {

    private final HorseService horseService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return HOME;
    }

    @GetMapping("/horse")
    public String horseMain(Model model) {
        return findPaginated(1, "id", "asc", "4", model);
    }

    @GetMapping("/horse/add")
    public String horseAdd(Model model) {
        return HORSE_ADD;
    }

    @PostMapping("/horse/add")
    public String horsePostAdd(@RequestParam String name, @RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {
        horseService.createHorse(name, type, Integer.valueOf(age), Integer.valueOf(price));
        return REDIRECT_HORSE;
    }

    @GetMapping("/horse/{id}/update")
    public String horseUpdate(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("horse", horseService.findHorseById(id));
        return HORSE_UPDATE;
    }

    @PostMapping("/horse/{id}/update")
    public String horsePostUpdate(@PathVariable(value = "id") int id, @RequestParam String name, @RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {
        horseService.updateHorse(id, name, type, Integer.valueOf(age), Integer.valueOf(price));
        return REDIRECT_HORSE;
    }

    @GetMapping("/horse/{id}/delete")
    public String horsePostDelete(@PathVariable(value = "id") int id, Model model) {
        horseService.deleteHorse(id);
        return REDIRECT_HORSE;
    }

    @GetMapping("/horse/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int currentPage,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam String pageSize,
                                Model model) {

        Page<Horse> page = horseService.findPaginated(currentPage, Integer.valueOf(pageSize), sortField, sortDir);
        List<Horse> listHorses = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("pageSize", pageSize);

        model.addAttribute("listHorses", listHorses);
        return HORSE_MAIN;
    }

    @GetMapping("/horse/search")
    public String horseSearch(Model model) {
        return HORSE_SEARCH;
    }

    @GetMapping("/horse/searchFilter")
    public String horseFilter(@RequestParam String name, @RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {

        HorseFilter horseFilter = horseService.horseFilterBuild(name, type, age, price);

        model.addAttribute("nameSearch", name);
        model.addAttribute("typeSearch", type);
        model.addAttribute("ageSearch", age);
        model.addAttribute("priceSearch", price);

        model.addAttribute("horsesFilter", horseService.findAll(horseFilter));
        return HORSE_SEARCH_FILTER;
    }
}
