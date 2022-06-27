package by.academy.it.reunova.horseSpringBoot.service;

import by.academy.it.reunova.horseSpringBoot.models.Horse;
import by.academy.it.reunova.horseSpringBoot.repository.HorseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService {

    private final HorseRepository horseRepository;

    @Override
    public void createHorse(String type, Integer age, Integer price) {
        Horse horse = Horse.builder()
                .type(type)
                .age(age)
                .price(price)
                .build();
        horseRepository.save(horse);
    }

    @Override
    public List<Horse> findAllHorses() {
        return (List<Horse>) horseRepository.findAll();
    }

    @Override
    public void updateHorse(Integer id, String type, Integer age, Integer price) {
        Horse horse = horseRepository.findById(id).get();
        horse.setType(type);
        horse.setAge(Integer.valueOf(age));
        horse.setPrice(Integer.valueOf(price));
        horseRepository.save(horse);
    }

    @Override
    public void deleteHorse(Integer id) {
        Horse horse = horseRepository.findById(id).orElseThrow();
        horseRepository.delete(horse);
    }

    @Override
    public Horse findHorseById(Integer id) {
        return horseRepository.findById(id).orElseThrow();
    }
}
