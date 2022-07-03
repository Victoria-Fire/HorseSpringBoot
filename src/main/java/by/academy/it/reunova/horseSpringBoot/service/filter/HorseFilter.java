package by.academy.it.reunova.horseSpringBoot.service.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorseFilter {
    private String nameFilter;
    private String typeFilter;
    private Integer ageFilter;
    private Integer priceFilter;
}
