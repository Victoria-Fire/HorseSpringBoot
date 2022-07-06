package by.academy.it.reunova.horseSpringBoot.repository;

import by.academy.it.reunova.horseSpringBoot.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
