package by.academy.it.reunova.horseSpringBoot.service;

import by.academy.it.reunova.horseSpringBoot.models.Horse;

import java.util.List;

public interface HorseService {

    void createHorse(String type, Integer age, Integer price);

    List<Horse> findAllHorses();

    void updateHorse(Integer id, String type, Integer age, Integer price);

    void deleteHorse(Integer id);

    Horse findHorseById(Integer id);
}
