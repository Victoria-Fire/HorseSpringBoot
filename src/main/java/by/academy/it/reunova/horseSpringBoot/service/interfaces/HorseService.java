package by.academy.it.reunova.horseSpringBoot.service.interfaces;

import by.academy.it.reunova.horseSpringBoot.repository.entity.Horse;
import by.academy.it.reunova.horseSpringBoot.service.dto.HorseDto;
import by.academy.it.reunova.horseSpringBoot.service.filter.HorseFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HorseService {

    void createHorse(String name, String type, Integer age, Integer price);

    void saveHorse(HorseDto horseDto);

    List<HorseDto> findAllHorses();

    void updateHorse(Integer id, String name, String type, Integer age, Integer price);

    void deleteHorse(Integer id);

    HorseDto findHorseById(Integer id);

    Page<Horse> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);

    HorseFilter horseFilterBuild(String name, String type, String age, String price);

    List<HorseDto> findAll(HorseFilter filter);
}
