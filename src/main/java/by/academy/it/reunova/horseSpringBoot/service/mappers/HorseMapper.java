package by.academy.it.reunova.horseSpringBoot.service.mappers;

import by.academy.it.reunova.horseSpringBoot.repository.entity.Horse;
import by.academy.it.reunova.horseSpringBoot.service.dto.HorseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HorseMapper {
    HorseDto toHorseDto(Horse horse);
    Horse toHorse(HorseDto horseDto);
}
