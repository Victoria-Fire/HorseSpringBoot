package by.academy.it.reunova.horseSpringBoot.feign;

import by.academy.it.reunova.horseSpringBoot.service.dto.HorseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "simple-feign", url = "http://192.168.57.15:8080")
public interface SimpleClient {

//    кол-во и тип полей должно быть одинаковым
    @GetMapping(value = "/horse-rest", consumes = {MediaType.APPLICATION_JSON_VALUE})
    List<HorseDto> getHorses();
}
