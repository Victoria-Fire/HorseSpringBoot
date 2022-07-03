package by.academy.it.reunova.horseSpringBoot.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HorseDto {
    private Integer id;
    private String nameHorse;
    private String typeHorse;
    private Integer ageHorse;
    private Integer priceHorse;
}

