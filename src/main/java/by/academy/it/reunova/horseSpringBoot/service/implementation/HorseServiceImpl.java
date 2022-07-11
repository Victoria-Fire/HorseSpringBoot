package by.academy.it.reunova.horseSpringBoot.service.implementation;

import by.academy.it.reunova.horseSpringBoot.repository.SpecificationHorse;
import by.academy.it.reunova.horseSpringBoot.repository.entity.Horse;
import by.academy.it.reunova.horseSpringBoot.repository.HorseRepository;
import by.academy.it.reunova.horseSpringBoot.service.dto.HorseDto;
import by.academy.it.reunova.horseSpringBoot.service.filter.HorseFilter;
import by.academy.it.reunova.horseSpringBoot.service.interfaces.HorseService;
import by.academy.it.reunova.horseSpringBoot.service.mappers.HorseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService {

    private final HorseRepository horseRepository;
    private final HorseMapper horseMapper;

    @Override
    public void createHorse(String name, String type, Integer age, Integer price) {
        Horse horse = Horse.builder()
                .nameHorse(name)
                .typeHorse(type)
                .ageHorse(age)
                .priceHorse(price)
                .build();
        horseRepository.save(horse);
    }

    @Override
    public void saveHorse(HorseDto horseDto) {
        horseRepository.save(horseMapper.toHorse(horseDto));
    }

    @Override
    public List<HorseDto> findAllHorses() {
        return StreamSupport
                .stream(horseRepository.findAll().spliterator(), false)
                .map(horseMapper::toHorseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateHorse(Integer id, String name, String type, Integer age, Integer price) {
        Horse horse = horseRepository.findById(id).get();
        horse.setNameHorse(name);
        horse.setTypeHorse(type);
        horse.setAgeHorse(Integer.valueOf(age));
        horse.setPriceHorse(Integer.valueOf(price));
        horseRepository.save(horse);
    }

    @Override
    public void deleteHorse(Integer id) {
        Horse horse = horseRepository.findById(id).orElseThrow();
        horseRepository.delete(horse);
    }

    @Override
    public HorseDto findHorseById(Integer id) {
        Horse horse = horseRepository.findById(id).orElseThrow();
        return horseMapper.toHorseDto(horse);
    }

    @Override
    public Page<Horse> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return horseRepository.findAll(pageable);
    }

    @Override
    public HorseFilter horseFilterBuild(String name, String type, String age, String price) {

        List<Integer> valuesFilter = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            valuesFilter.add(i);
        }

        HorseFilter horseFilter = new HorseFilter();

        for (Integer check : valuesFilter) {
            switch (check) {
                case 1:
                    if (name != "") {
                        horseFilter.setNameFilter(name);
                    }
                    break;
                case 2:
                    if (type != "") {
                        horseFilter.setTypeFilter(type);
                    }
                    break;
                case 3:
                    if (age != "") {
                        horseFilter.setAgeFilter(Integer.valueOf(age));
                    }
                    break;
                case 4:
                    if (price != "") {
                        horseFilter.setPriceFilter(Integer.valueOf(price));
                    }
                    break;
            }
        }

        return horseFilter;
    }

    @Override
    public List<HorseDto> findAll(HorseFilter horseFilter) {
        Specification<Horse> horseSpecification =
                Specification
                        .where(Optional.ofNullable(horseFilter.getNameFilter())
                                .map(SpecificationHorse::getHorseByNameSpec)
                                .orElse(null))
                        .and(Optional.ofNullable(horseFilter.getTypeFilter())
                                .map(SpecificationHorse::getHorseByTypeSpec)
                                .orElse(null))
                        .and(Optional.ofNullable(horseFilter.getAgeFilter())
                                .map(SpecificationHorse::getHorseByAgeSpec)
                                .orElse(null))
                        .and(Optional.ofNullable(horseFilter.getPriceFilter())
                                .map(SpecificationHorse::getHorseByPriceSpec)
                                .orElse(null));

        return horseRepository.findAll(horseSpecification).stream()
                .map(horseMapper::toHorseDto)
                .collect(Collectors.toList());
    }
}
