package by.academy.it.reunova.horseSpringBoot.repository;

import by.academy.it.reunova.horseSpringBoot.repository.entity.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Integer>, JpaSpecificationExecutor<Horse> {
}
