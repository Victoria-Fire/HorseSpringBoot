package by.academy.it.reunova.horseSpringBoot.repository;

import by.academy.it.reunova.horseSpringBoot.models.Horse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends CrudRepository<Horse, Integer> {
}
