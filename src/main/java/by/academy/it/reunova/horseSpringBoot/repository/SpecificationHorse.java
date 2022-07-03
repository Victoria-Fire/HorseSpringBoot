package by.academy.it.reunova.horseSpringBoot.repository;

import by.academy.it.reunova.horseSpringBoot.entity.Horse_;
import by.academy.it.reunova.horseSpringBoot.repository.entity.Horse;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SpecificationHorse {

    public static Specification<Horse> getHorseByNameSpec(String name) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (name != null) {
                predicatesMain.add(criteriaBuilder.equal(root.get(Horse_.NAME_HORSE), name));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }

    public static Specification<Horse> getHorseByTypeSpec(String type) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (type != null) {
                predicatesMain.add(criteriaBuilder.equal(root.get(Horse_.TYPE_HORSE), type));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }

    public static Specification<Horse> getHorseByAgeSpec(Integer age) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (age != null) {
                predicatesMain.add(criteriaBuilder.equal(root.get(Horse_.AGE_HORSE), age));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }

    public static Specification<Horse> getHorseByPriceSpec(Integer price) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (price != null) {
                predicatesMain.add(criteriaBuilder.equal(root.get(Horse_.PRICE_HORSE), price));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }
}
