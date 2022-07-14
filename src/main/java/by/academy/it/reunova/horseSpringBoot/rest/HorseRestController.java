package by.academy.it.reunova.horseSpringBoot.rest;

import by.academy.it.reunova.horseSpringBoot.feign.SimpleClient;
import by.academy.it.reunova.horseSpringBoot.service.dto.HorseDto;
import by.academy.it.reunova.horseSpringBoot.service.interfaces.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horse-rest")
@RequiredArgsConstructor
public class HorseRestController {

    private final HorseService horseService;
    private final SimpleClient simpleClient;

    @GetMapping
//    @RequestMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<HorseDto> getHorses() {
        return simpleClient.getHorses();
//        return horseService.findAllHorses();
    }

    @PostMapping
    public void createHorse(@RequestBody HorseDto horse) {
        horseService.saveHorse(horse);
    }
}
